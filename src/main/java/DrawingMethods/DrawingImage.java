package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Arrays;

public class DrawingImage {
    Position pos = null;
    DrawingBlock[] blocks = null;


    public DrawingImage(Position pos_, DrawingBlock[] blocks_) {
        this.pos = pos_;
        this.blocks = blocks_;
    }

    public void draw(TextGraphics graphics_) {
        for (DrawingBlock block : blocks)
            block.draw(graphics_, pos);
    }

    public DrawingBlock[] getDrawingBlocks() {
        return blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        DrawingImage p = (DrawingImage) o;
        return Arrays.equals(this.blocks, p.getDrawingBlocks());
    }
}
