package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingMethod;
import Structures.Position;

public class ExtraLife extends PowerUp {

    DrawingMethod visual = new DrawingImage(new DrawingBlock[] {
            new DrawingBlock(new Position(1, 0), 1, 1, null, "#FFFFFF", '#'),
            new DrawingBlock(new Position(0, 1), 1, 1, null, "#FFFFFF", '_'),
            new DrawingBlock(new Position(1, 1), 1, 1, "#FFFFFF", "#F00000", '"'),
            new DrawingBlock(new Position(2, 1), 1, 1, null, "#FFFFFF", '*'),
            new DrawingBlock(new Position(1, 2), 1, 1, null, "#FFFFFF", '$'),
    });

    public ExtraLife(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_);
    }

    @Override
    public void affect(Bomberman player_) {
        player_.setLives(player_.getLives() + 1);
        player_.getBoard().getAudioPlayer().playPowerUpSound();
    }

    @Override
    public String getType() {
        return "ExtraLife";
    }

    @Override
    public DrawingMethod getVisual() {
        return visual;
    }
}
