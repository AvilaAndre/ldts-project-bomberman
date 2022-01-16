package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.Locale;

public class DrawingText {
    DrawingBlock[] textChars;
    String backColor;
    String frontColor;

    public DrawingText(Position position_, String text_, String backColor_, String frontColor_) {
        text_ = text_.toUpperCase(Locale.ROOT);
        ArrayList<DrawingBlock> drawingBlocks = new ArrayList<>();
        for (int i = 0; i < text_.length(); i++) {
            drawingBlocks.add(new DrawingBlock(new Position(position_.getX() + i, position_.getY()), 1, 1, backColor_, frontColor_, text_.charAt(i)));
        }
        textChars = new DrawingBlock[text_.length()];
        textChars = drawingBlocks.toArray(textChars);
    }

    public void draw(TextGraphics graphics) {
        for (DrawingBlock block : textChars)
            block.draw(graphics, new Position(0,0), false);
    }
}
