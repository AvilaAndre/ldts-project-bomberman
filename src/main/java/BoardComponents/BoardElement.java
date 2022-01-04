package BoardComponents;

import DrawingMethods.DrawingImage;
import Structures.ColliderBox;
import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class BoardElement {
    private final DrawingImage image;
    private final ColliderBox[] collider;
    private final Position position;
    private final Board board;


    public BoardElement(Position pos_, Board gameBoard_, DrawingImage image_, ColliderBox[] collider_) {
        this.position = pos_;
        this.board = gameBoard_;
        this.image = image_;
        this.collider = collider_;
    }

    public void draw(TextGraphics graphics_) {
        image.draw(graphics_, this.position);
    }

    public DrawingImage getImage() {
        return image;
    }

    public ColliderBox[] getCollider() {
        return collider;
    }

    public Position getPosition() {
        return position;
    }

    public boolean action() {
        return false;
    }
}
