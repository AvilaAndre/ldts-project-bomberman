package BoardComponents;

import BoardElements.Block;
import BoardElements.Bomb;
import BoardElements.Bomberman;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import Game.GameModel;
import Structures.ColliderBox;
import Structures.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Board {
    GameModel model;
    TextColor backColor = TextColor.Factory.fromString("#999999");
    private final TextGraphics graphics = null;
    private ArrayList<BoardElement> blocks = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private ArrayList<BoardElement> powerups = new ArrayList<>();
    private ArrayList<BoardElement> drawQueue = new ArrayList<>();

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

    public void loop() {
        drawQueue.clear();
        bombs.removeIf(bomb -> bomb.action() && bomb.getExploded());
        for (Bomberman player : model.getPlayers())
            if (player != null)
                if (!checkExplodedBombCollision(player.getPosition(), player.getCollider()))
                    player.getHurt();
        drawQueue.addAll(blocks);
        drawQueue.addAll(bombs);
    }

    public ArrayList<BoardElement> getBoard() {
        return this.blocks;
    }

    public ArrayList<BoardElement> getDrawQueue() {
        return drawQueue;
    }

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

    public boolean checkExplodedBombCollision(Position pos, ColliderBox[] collider) {
        for (Bomb elem : bombs)
            for (ColliderBox col1 : collider)
                for (ColliderBox col2 : elem.getCollider())
                    if (col1.collides(pos, col2, elem.getPosition()) && elem.getExploded())
                        return false;
        return true;
    }

    public void removeBlock(Block block) {
        blocks.removeIf(blo -> blo == block);
    }

    public ArrayList<Bomb> getBombs() {
        return this.bombs;
    }

    public void addBomb(Position position_, int bombRadius_, Bomberman owner_) {
        bombs.add(new Bomb(position_, this, bombRadius_, owner_));
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
}