package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.PowerUp;
import Structures.ColliderBox;
import Structures.Position;

public class Shield extends PowerUp {
    public Shield(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_, new ColliderBox[] { new ColliderBox(new Position(0,0), 1, 1)});
    }
}
