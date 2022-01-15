package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import Structures.Position;

public class Shield extends PowerUp {

    public Shield(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_);
    }

    @Override
    public void affect(Bomberman player_) {
        player_.setShield(true);
    }

    @Override
    public String getType() {
        return "Shield";
    }
}
