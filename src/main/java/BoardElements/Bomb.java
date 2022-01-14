package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;

public class Bomb extends BoardElement {
    int ticksLeft = 20;
    private int radius = 1;
    private boolean exploded = false;

    public Bomb(Position pos_, Board gameBoard_, int bombRadius_) {
        super(pos_, gameBoard_, new ColliderBox[]{ new ColliderBox(new Position(0,0), 1, 1) });
        this.radius = bombRadius_;
    }

    @Override
    public DrawingMethod getVisual() {
        return null;
    }

    @Override
    public boolean action() {
        return false;
    }

    private void explode(){

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
