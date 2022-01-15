package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.PowerUp;
import Structures.Position;

public class PowerUpFactory {

    public PowerUp getPowerUp(Position pos_, Board gameBoard_, int type_) {
        switch (type_) {
            case 0:
                return new ExtraBomb(pos_, gameBoard_);
            case 1:
                return new BlastSize(pos_, gameBoard_);
            case 2:
                return new ExtraLife(pos_, gameBoard_);
            case 3:
                return new Shield(pos_, gameBoard_);
            case 4:
                return new PushTheBomb(pos_, gameBoard_);
            case 5:
                return new Invincibility(pos_, gameBoard_);
            default:
                return null;
        }
    }
}
