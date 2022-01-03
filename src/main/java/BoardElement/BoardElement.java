package BoardElement;

import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class BoardElement {
    DrawingImage image = null;
    ArrayAsList<ColliderBox> = new ArrayAsList<>();
    Position position;
    Board board = null;


    BoardElement(String color_, Position pos_, Board gameBoard_) {
        this.color = color_;
        this.position = pos_;
        this.board = gameBoard_;
    }

    public void draw(TextGraphics graphics_) {
        image.draw(graphics_);
    }

}
