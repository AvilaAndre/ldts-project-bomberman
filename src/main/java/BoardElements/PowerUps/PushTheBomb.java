package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.PowerUp;
import Structures.ColliderBox;
import Structures.Position;

public class PushTheBomb extends PowerUp {
    public PushTheBomb(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_, new ColliderBox[] { new ColliderBox(new Position(0,0), 1, 1)});
    }
}
