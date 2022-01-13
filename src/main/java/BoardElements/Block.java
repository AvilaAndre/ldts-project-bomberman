package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;

import java.util.Arrays;

public class Block extends BoardElement {
    private final boolean destructible;
    private boolean destroyed = false;
    DrawingMethod visual = null;

    public Block(Position pos_, Board gameBoard_, DrawingImage image_, ColliderBox[] collider_, boolean destructible_) {
        super(pos_, gameBoard_, collider_);
        this.destructible = destructible_;
        this.visual = image_;
    }

    public void destroy() {
        if (!destroyed && destructible) {
            destroyed = true;
            getBoard().removeBlock(this);
        }
    }

    public boolean getDestroyed() {
        return destroyed;
    }

    public boolean getDestructible() {
        return destructible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Block p = (Block) o;
        return this.getVisual().equals(p.getVisual()) && destructible == p.getDestructible();
    }

    @Override
    public DrawingMethod getVisual() {
        return this.visual;
    }
}
