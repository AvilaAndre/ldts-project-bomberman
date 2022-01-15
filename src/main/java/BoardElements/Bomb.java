package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingAnimation;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bomb extends BoardElement {
    int ticksLeft = 30;
    private final int radius;
    private boolean exploded = false;
    private Bomberman owner;

    private DrawingAnimation explosionAnim = new DrawingAnimation(new DrawingImage[]{
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(0, 0), 3, 3, "#DD0000", null, ' '),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(0, 0), 3, 3, "#FF0000", null, ' '),
            })
    }, new int[]{2, 2}, false);

    private final DrawingAnimation pulsatingAnim = new DrawingAnimation(new DrawingImage[]{
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#000000", '#'),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#000000", '_'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#000000", null, ' '),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#000000", '*'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#000000", '$'),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#000000", '('),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#000000", '%'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#000000", null, ' '),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#000000", '&'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#000000", ')'),
            })
    }, new int[]{2, 2}, false);

    public Bomb(Position pos_, Board gameBoard_, int bombRadius_, Bomberman owner_) {
        super(pos_, gameBoard_, new ColliderBox[]{ new ColliderBox(new Position(0,0), 1, 1) });
        this.radius = bombRadius_;
        this.owner = owner_;
    }

    @Override
    public DrawingMethod getVisual() {
        if (exploded)
            return explosionAnim;
        else
            return pulsatingAnim;
    }

    @Override
    public boolean action() {
        ticksLeft--;
        if (!exploded) {
            if (ticksLeft < 0)
                explode();
        }
        else
            return ticksLeft < 0;
        return false;
    }

    private void explode(){
        this.exploded = true;
        ticksLeft = 6;
        int[] explosionData = getBoard().bombExplosion(this.radius, this.getPosition());
        List<DrawingBlock> explosions1 = new ArrayList<>();
        List<DrawingBlock> explosions2 = new ArrayList<>();
        List<ColliderBox> explosionsColliders = new ArrayList<>();
        explosionsColliders.add(new ColliderBox(new Position(-explosionData[0],0), 1+ explosionData[0], 1));
        if (explosionData[1] > 0)
            explosionsColliders.add(new ColliderBox(new Position(0,-explosionData[1]), 1, explosionData[1]));
        if (explosionData[2] > 0)
            explosionsColliders.add(new ColliderBox(new Position(1,0), explosionData[2], 1));
        if (explosionData[3] > 0)
            explosionsColliders.add(new ColliderBox(new Position(0,1), 1, explosionData[3]));
        ColliderBox[] explColArray = new ColliderBox[explosionsColliders.size()];
        setCollider(explosionsColliders.toArray(explColArray));
        explosions1.add(new DrawingBlock(new Position(0, 0), 3, 3, "#DD0000", null, ' '));
        explosions2.add(new DrawingBlock(new Position(0, 0), 3, 3, "#FF0000", null, ' '));
        for (int i = 0; i < explosionData[0]; i++) {
            explosions1.add(new DrawingBlock(new Position(3*(-1-i), 0), 3, 3, "#DD0000", null, ' '));
            explosions2.add(new DrawingBlock(new Position(3*(-1-i), 0), 3, 3, "#FF0000", null, ' '));
        }
        for (int i = 0; i < explosionData[1]; i++) {
            explosions1.add(new DrawingBlock(new Position(0, 3* (-1-i)), 3, 3, "#DD0000", null, ' '));
            explosions2.add(new DrawingBlock(new Position(0, 3* (-1-i)), 3, 3, "#FF0000", null, ' '));
        }
        for (int i = 0; i < explosionData[2]; i++) {
            explosions1.add(new DrawingBlock(new Position(3* (1+i), 0), 3, 3, "#DD0000", null, ' '));
            explosions2.add(new DrawingBlock(new Position(3* (1+i), 0), 3, 3, "#FF0000", null, ' '));
        }
        for (int i = 0; i < explosionData[3]; i++) {
            explosions1.add(new DrawingBlock(new Position(0, 3* (1+i)), 3, 3, "#DD0000", null, ' '));
            explosions2.add(new DrawingBlock(new Position(0, 3* (1+i)), 3, 3, "#FF0000", null, ' '));
        }
        DrawingBlock[] explosionBlocks = new DrawingBlock[explosions1.size()];
        DrawingBlock[] explosionBlocks2 = new DrawingBlock[explosions1.size()];
        DrawingImage image1 = new DrawingImage(explosions1.toArray(explosionBlocks));
        DrawingImage image2 = new DrawingImage(explosions2.toArray(explosionBlocks2));
        explosionAnim = new DrawingAnimation(new DrawingImage[] { image1, image2}, new int[]{2, 2}, false);
    }

    public boolean getExploded() {
        return this.exploded;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getTicksLeft() {
        return this.ticksLeft;
    }
}
