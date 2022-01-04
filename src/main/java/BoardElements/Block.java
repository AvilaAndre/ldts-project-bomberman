package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingImage;
import Structures.ColliderBox;
import Structures.Position;

import java.util.Arrays;

public class Block extends BoardElement {
    private final boolean destructible;
    private boolean destroyed = false;

    public Block(Position pos_, Board gameBoard_, DrawingImage image_, ColliderBox[] collider_, boolean destructible_) {
        super(pos_, gameBoard_, image_, collider_);
        this.destructible = destructible_;
    }

    public void destroy() {
        if (!destroyed && destructible)
            destroyed = true;
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
        return this.getImage().equals(p.getImage()) && destructible == p.getDestructible();
    }

}
