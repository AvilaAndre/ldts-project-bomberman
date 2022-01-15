package BoardComponents;

import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class BoardElement {
    private ColliderBox[] collider;
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

    public void setCollider(ColliderBox[] collider_) {
        this.collider = collider_;
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

    public void moveUp() {
        setPosition(new Position(getPosition().getX(), getPosition().getY() - 1));
    }

    public void moveDown() {
        setPosition(new Position(getPosition().getX(), getPosition().getY() + 1));
    }

    public void moveLeft() {
        setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
    }

    public void moveRight() {
        setPosition(new Position(getPosition().getX() + 1, getPosition().getY()));
    }
}
