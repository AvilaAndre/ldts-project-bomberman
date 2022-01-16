package BoardElements.PowerUps;

import BoardComponents.Board;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingMethod;
import Structures.Position;

public class BlastSize extends PowerUp {

    DrawingMethod visual = new DrawingImage(new DrawingBlock[] {
                    new DrawingBlock(new Position(1, 0), 1, 1, null, "#FFFFFF", '#'),
                    new DrawingBlock(new Position(0, 1), 1, 1, null, "#FFFFFF", '_'),
                    new DrawingBlock(new Position(1, 1), 1, 1, "#FFFFFF", "#F08800", ':'),
                    new DrawingBlock(new Position(2, 1), 1, 1, null, "#FFFFFF", '*'),
                    new DrawingBlock(new Position(1, 2), 1, 1, null, "#FFFFFF", '$'),
    });

    public BlastSize(Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_);
    }

    @Override
    public void affect(Bomberman player_) {
        player_.setBombRadius(player_.getBombRadius() + 1);
    }

    @Override
    public String getType() {
        return "BlastSize";
    }

    @Override
    public DrawingMethod getVisual() {
        return visual;
    }
}
