package DrawingMethods;

import Structures.Position;
import java.util.ArrayList;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawingImage {
    Position pos = null;
    ArrayList<DrawingBlock> blocks = null;


    DrawingImage(Position pos_, int width_, int height_, String backColor_, String frontColor_, char character_){}

    void draw(TextGraphics graphics_) {
        for (DrawingBlock block : blocks)
            block.draw(graphics_, pos);
    }
}
