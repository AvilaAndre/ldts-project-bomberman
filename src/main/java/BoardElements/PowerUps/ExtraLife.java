package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import Structures.Position;

public class ExtraLife extends PowerUp {

    public ExtraLife(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_);
    }

    @Override
    public void affect(Bomberman player_) {

    }

    @Override
    public String getType() {
        return "ExtraLife";
    }
}
