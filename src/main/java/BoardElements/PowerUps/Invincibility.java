package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import Structures.Position;

public class Invincibility extends PowerUp {

    public Invincibility(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_);
    }

    @Override
    public void affect(Bomberman player_) {
        player_.setInvincibility(true);
        player_.setInvincibilityTicksLeft(140);
    }

    @Override
    public String getType() {
        return "Invincibility";
    }
}
