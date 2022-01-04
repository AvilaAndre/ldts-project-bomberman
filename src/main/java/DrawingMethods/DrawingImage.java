package DrawingMethods;

import Structures.Position;
import java.util.ArrayList;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawingImage {
    Position pos = null;
    ArrayList<DrawingBlock> blocks = null;


    DrawingImage(Position pos_, ArrayList<DrawingBlock> blocks_) {
        this.pos = pos_;
        this.blocks = blocks_;
    }

    public void draw(TextGraphics graphics_) {
        for (DrawingBlock block : blocks)
            block.draw(graphics_, pos);
    }
}
