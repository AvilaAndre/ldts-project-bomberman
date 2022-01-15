package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import Structures.Position;

public class PushTheBomb extends PowerUp {
    public PushTheBomb(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_);
    }

    @Override
    public void affect(Bomberman player_) {

    }

    @Override
    public String getType() {
        return "PushTheBomb";
    }
}
