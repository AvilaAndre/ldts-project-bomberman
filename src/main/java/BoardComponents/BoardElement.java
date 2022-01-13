package BoardComponents;

import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class BoardElement {
    private final ColliderBox[] collider;
    private Position position;
    private final Board board;


    public BoardElement(Position pos_, Board gameBoard_, ColliderBox[] collider_) {
        this.position = pos_;
        this.board = gameBoard_;
        this.collider = collider_;
    }

    public void draw(TextGraphics graphics_) {
        DrawingMethod visual = getVisual();
        graphics_.setBackgroundColor(board.getBackColor());
        if (visual != null)
            visual.draw(graphics_, this.position, true);
    }

    public abstract DrawingMethod getVisual();

    public ColliderBox[] getCollider() {
        return collider;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPos) {
        this.position = newPos;
    }

    public Board getBoard() { return board; }

    public boolean action() {
        return false;
    }

    public void moveUp() {}

    public void moveDown() {}

    public void moveLeft() {}

    public void moveRight() {}
}
