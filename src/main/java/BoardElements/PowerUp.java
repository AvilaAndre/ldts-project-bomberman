package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;

public abstract class PowerUp extends BoardElement {
    public PowerUp(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_, new ColliderBox[] { new ColliderBox(new Position(0,0), 1, 1)});
    }

    @Override
    public abstract DrawingMethod getVisual();

    public abstract void affect(Bomberman player_);

    public abstract String getType();

    @Override
    public String toString() {
        return getType();
    }
}
