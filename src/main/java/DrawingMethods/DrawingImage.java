package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Arrays;

public class DrawingImage extends DrawingMethod{
    DrawingBlock[] blocks;


    public DrawingImage(DrawingBlock[] blocks_) {
        this.blocks = blocks_;
    }

    public void draw(TextGraphics graphics_, Position imagePos_, boolean boardOffset_) {
        for (DrawingBlock block : blocks)
            block.draw(graphics_, imagePos_, boardOffset_);
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
