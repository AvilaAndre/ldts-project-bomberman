package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import DrawingMethods.DrawingAnimation;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingMethod;
import Structures.Position;

public class Invincibility extends PowerUp {

    DrawingMethod visual = new DrawingAnimation(new DrawingImage[]{
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#FFFFFF", '#'),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#FFFFFF", '_'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#FFFFFF", "#F00000", '='),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#FFFFFF", '*'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#FFFFFF", '$'),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#FFFFFF", '#'),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#FFFFFF", '_'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#FFFFFF", "#F0F000", '='),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#FFFFFF", '*'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#FFFFFF", '$'),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#FFFFFF", '#'),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#FFFFFF", '_'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#FFFFFF", "#00F000", '='),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#FFFFFF", '*'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#FFFFFF", '$'),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#FFFFFF", '#'),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#FFFFFF", '_'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#FFFFFF", "#00F0F0", '='),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#FFFFFF", '*'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#FFFFFF", '$'),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#FFFFFF", '#'),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#FFFFFF", '_'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#FFFFFF", "#00000F", '='),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#FFFFFF", '*'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#FFFFFF", '$'),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#FFFFFF", '#'),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#FFFFFF", '_'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#FFFFFF", "#F000F0", '='),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#FFFFFF", '*'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#FFFFFF", '$'),
            })
    }, new int[]{2, 2}, false);

    public Invincibility(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_);
    }

    @Override
    public void affect(Bomberman player_) {
        player_.setInvincibility(true);
        player_.setInvincibilityTicksLeft(140);
        player_.getBoard().getAudioPlayer().playPowerUpSound();
    }

    @Override
    public String getType() {
        return "Invincibility";
    }

    @Override
    public DrawingMethod getVisual() {
        return visual;
    }
}
