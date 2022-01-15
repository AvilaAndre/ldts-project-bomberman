package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;

public class PowerUp extends BoardElement {
    public PowerUp(Position pos_, Board gameBoard_, ColliderBox[] collider_) {
        super(pos_, gameBoard_, collider_);
    }

    @Override
    public DrawingMethod getVisual() {
        return null;
    }
}
