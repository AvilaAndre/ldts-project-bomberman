package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import Structures.Position;

public class ExtraBomb extends PowerUp {

    public ExtraBomb(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_);
    }

    @Override
    public void affect(Bomberman player_) {
        player_.setBombLimit(player_.getBombLimit() + 1);
    }

    @Override
    public String getType() {
        return "ExtraBomb";
    }
}
