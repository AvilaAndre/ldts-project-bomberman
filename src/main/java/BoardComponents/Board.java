package BoardComponents;

import BoardElements.Block;
import BoardElements.Bomb;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import BoardElements.PowerUps.PowerUpFactory;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import Game.GameModel;
import Audio.AudioPlayer;
import Structures.ColliderBox;
import Structures.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    AudioPlayer audio = AudioPlayer.getInstance();
    GameModel model;
    TextColor backColor = TextColor.Factory.fromString("#999999");
    Random r = new Random();
    private final TextGraphics graphics = null;
    private ArrayList<BoardElement> blocks = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private ArrayList<PowerUp> powerups = new ArrayList<>();
    private ArrayList<BoardElement> drawQueue = new ArrayList<>();
    ArrayList<Pair<Bomberman, Bomberman>> eliminationsQueue = new ArrayList<>();

    public Board(String code, GameModel model_) {
        model = model_;
        int u = 0;
        DrawingImage dImage;
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 18; j++) {
                switch (code.charAt(i * 18 + j)) {
                    case '0':
                        dImage = new DrawingImage(new DrawingBlock[]{
                                new DrawingBlock(new Position(0, 0), 1, 1, "#900000", "#000000", 's'),
                                new DrawingBlock(new Position(1, 0), 1, 1, "#900000", "#000000", 'x'),
                                new DrawingBlock(new Position(2, 0), 1, 1, "#900000", "#000000", 't'),
                                new DrawingBlock(new Position(0, 1), 1, 1, "#900000", "#000000", 'w'),
                                new DrawingBlock(new Position(1, 1), 1, 1, "#900000", "#000000", '+'),
                                new DrawingBlock(new Position(2, 1), 1, 1, "#900000", "#000000", 'y'),
                                new DrawingBlock(new Position(0, 2), 1, 1, "#900000", "#000000", 'r'),
                                new DrawingBlock(new Position(1, 2), 1, 1, "#900000", "#000000", 'z'),
                                new DrawingBlock(new Position(2, 2), 1, 1, "#900000", "#000000", 'u')
                        });
                        blocks.add(new Block(new Position(j, i), this, dImage, new ColliderBox[]{
                                new ColliderBox(new Position(0, 0), 1, 1)
                        }, false));
                        u++;
                        break;
                    case '1':
                        dImage = new DrawingImage(new DrawingBlock[]{
                                new DrawingBlock(new Position(0, 0), 1, 1, "#ffffff", "#000000", ' '),
                                new DrawingBlock(new Position(1, 0), 1, 1, "#ffffff", "#000000", ' '),
                                new DrawingBlock(new Position(2, 0), 1, 1, "#ffffff", "#000000", ' '),
                                new DrawingBlock(new Position(0, 1), 1, 1, "#ffffff", "#000000", ' '),
                                new DrawingBlock(new Position(1, 1), 1, 1, "#ffffff", "#000000", ' '),
                                new DrawingBlock(new Position(2, 1), 1, 1, "#ffffff", "#000000", ' '),
                                new DrawingBlock(new Position(0, 2), 1, 1, "#ffffff", "#000000", ' '),
                                new DrawingBlock(new Position(1, 2), 1, 1, "#ffffff", "#000000", ' '),
                                new DrawingBlock(new Position(2, 2), 1, 1, "#ffffff", "#000000", ' ')
                        });
                        blocks.add(new Block(new Position(j, i), this, dImage, new ColliderBox[]{
                                new ColliderBox(new Position(0, 0), 1, 1)
                        }, true));
                        u++;
                        break;
                }
            }
    }

    public void setRandomSeed(long seed_) {
        this.r = new Random(seed_);
    }

    public void loop() {
        drawQueue.clear();
        bombs.removeIf(bomb -> bomb.action() && bomb.getExploded());
        for (Bomberman player : model.getPlayers()) {
            if (player != null) {
                player.loop();
                Bomb bomb = this.getExplodedBombCollision(player.getPosition(), player.getCollider());
                if (bomb != null)
                    player.getHurt(bomb);
                PowerUp power = getPowerUpCollision(player.getPosition(), player.getCollider());
                if (power != null) {
                    power.affect(player);
                    powerups.removeIf(pow -> pow == power);
                }
            }
        }
        drawQueue.addAll(blocks);
        drawQueue.addAll(powerups);
        drawQueue.addAll(bombs);
    }

    public ArrayList<BoardElement> getBoard() {
        return this.blocks;
    }

    public ArrayList<BoardElement> getDrawQueue() {
        return drawQueue;
    }

    public AudioPlayer getAudioPlayer() { return this.audio; }

    public TextColor getBackColor() {
        return this.backColor;
    }

    public boolean checkCollision(Position pos, ColliderBox[] collider) {
        return checkBlockCollision(pos, collider) && checkBombCollision(pos, collider);
    }

    public boolean checkBlockCollision(Position pos, ColliderBox[] collider) {
        for (BoardElement elem : blocks) {
            for (ColliderBox col1 : collider)
                for (ColliderBox col2 : elem.getCollider()) {
                    if (col1.collides(pos, col2, elem.getPosition())) {
                        return false;
                    }
                }
        }
        return true;
    }

    public BoardElement getBlockCollision(Position pos, ColliderBox[] collider) {
        for (BoardElement elem : blocks) {
            for (ColliderBox col1 : collider)
                for (ColliderBox col2 : elem.getCollider()) {
                    if (col1.collides(pos, col2, elem.getPosition())) {
                        return elem;
                    }
                }
        }
        return null;
    }

    public boolean checkBombCollision(Position pos, ColliderBox[] collider) {
        for (Bomb elem : bombs)
            for (ColliderBox col1 : collider)
                for (ColliderBox col2 : elem.getCollider())
                    if (col1.collides(pos, col2, elem.getPosition()) && !elem.getExploded())
                        return false;
        return true;
    }

    public Bomb getBombCollision(Position pos, ColliderBox[] collider) {
        for (Bomb elem : bombs)
            for (ColliderBox col1 : collider)
                for (ColliderBox col2 : elem.getCollider())
                    if (col1.collides(pos, col2, elem.getPosition()) && !elem.getExploded())
                        return elem;
        return null;
    }

    public Bomb getExplodedBombCollision(Position pos, ColliderBox[] collider) {
        for (Bomb elem : bombs)
            for (ColliderBox col1 : collider)
                for (ColliderBox col2 : elem.getCollider())
                    if (col1.collides(pos, col2, elem.getPosition()) && elem.getExploded())
                        return elem;
        return null;
    }

    public PowerUp getPowerUpCollision(Position pos, ColliderBox[] collider) {
        for (PowerUp elem : powerups)
            for (ColliderBox col1 : collider)
                for (ColliderBox col2 : elem.getCollider())
                    if (col1.collides(pos, col2, elem.getPosition()))
                        return elem;
        return null;
    }

    public void removeBlock(Block block) {
        blocks.removeIf(blo -> blo == block);
    }

    public ArrayList<PowerUp> getPowerUps() {
        return this.powerups;
    }

    public ArrayList<Bomb> getBombs() {
        return this.bombs;
    }

    public boolean addBomb(Position position_, int bombRadius_, Bomberman owner_) {
        for (Bomb bob : bombs)
            if (bob.getPosition() == position_)
                return false;
        bombs.add(new Bomb(position_, this, bombRadius_, owner_));
        return true;
    }

    public int[] bombExplosion(int radius, Position expPosition) {
        int leftExp = 0;
        int upExp = 0;
        int rightExp = 0;
        int downExp = 0;
        for (int i = 1; i <= radius; i++) {
            Block blockThatCollides = (Block) getBlockCollision(expPosition, new ColliderBox[] { new ColliderBox(new Position(-i, 0), 1, 1)});
            if (blockThatCollides != null) {
                if (blockThatCollides.getDestructible())
                    leftExp++;
                blockThatCollides.destroy();
                break;
            }
            leftExp++;
        }
        for (int i = 1; i <= radius; i++) {
            Block blockThatCollides = (Block) getBlockCollision(expPosition, new ColliderBox[] { new ColliderBox(new Position(0, -i), 1, 1)});
            if (blockThatCollides != null) {
                if (blockThatCollides.getDestructible())
                    upExp++;
                blockThatCollides.destroy();
                break;
            }
            upExp++;
        }
        for (int i = 1; i <= radius; i++) {
            Block blockThatCollides = (Block) getBlockCollision(expPosition, new ColliderBox[] { new ColliderBox(new Position(i, 0), 1, 1)});
            if (blockThatCollides != null) {
                if (blockThatCollides.getDestructible())
                    rightExp++;
                blockThatCollides.destroy();
                break;
            }
            rightExp++;
        }
        for (int i = 1; i <= radius; i++) {
            Block blockThatCollides = (Block) getBlockCollision(expPosition, new ColliderBox[] { new ColliderBox(new Position(0, i), 1, 1)});
            if (blockThatCollides != null) {
                if (blockThatCollides.getDestructible())
                    downExp++;
                blockThatCollides.destroy();
                break;
            }
            downExp++;
        }
        return new int[] {leftExp, upExp, rightExp, downExp};
    }

    private Block getBlockAtPosition(Position position) {
        for (BoardElement block : blocks)
            if (block.getPosition() == position)
                return (Block) block;
        return null;
    }

    public void createPowerUp(Position position_) {
        PowerUpFactory factory = new PowerUpFactory();
        int choice = r.nextInt((250) + 1);
        int power;
        if (choice < 150)
            power = -1;
        else if (choice < 177)
            power = 0;
        else if (choice < 199)
            power = 1;
        else if (choice < 217)
            power = 4;
        else if (choice < 232)
            power = 3;
        else if (choice < 242)
            power = 2;
        else power = 5;
        if (power > -1)
            powerups.add(factory.getPowerUp(position_, this, power));
    }

    public void PlayerEliminated(Bomberman owner_, Bomberman bomberman_) {
        if (owner_ == bomberman_)
            owner_.setEliminations(owner_.getEliminations() - 1);
        else
            owner_.setEliminations(owner_.getEliminations() + 1);
        eliminationsQueue.add(new Pair<>(owner_, bomberman_));
    }

    public ArrayList<Pair<Bomberman, Bomberman>> getEliminationsQueue() {
        ArrayList<Pair<Bomberman, Bomberman>> ret = (ArrayList<Pair<Bomberman, Bomberman>>) eliminationsQueue.clone();
        eliminationsQueue.clear();
        return ret;
    }
}