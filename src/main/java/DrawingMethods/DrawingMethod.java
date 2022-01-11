package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class DrawingMethod {
    public abstract void draw(TextGraphics graphics_, Position pos_, boolean boardOffset_);
}
