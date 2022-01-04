package BoardComponents;

import DrawingMethods.DrawingImage;
import Structures.ColliderBox;
import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public abstract class BoardElement {
    DrawingImage image = null;
    ArrayList<ColliderBox> collider = new ArrayList<>();
    Position position;
    Board board = null;


    BoardElement(Position pos_, Board gameBoard_) {
        this.position = pos_;
        this.board = gameBoard_;
    }

    public void draw(TextGraphics graphics_) {
        image.draw(graphics_);
    }
}
