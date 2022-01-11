package Structures;

public class ColliderBox {
    private final Position position;
    private final int width;
    private final int height;

    public ColliderBox(Position pos_, int colWidth_, int colHeight_) {
        this.position = pos_;
        this.width = colWidth_;
        this.height = colHeight_;
    }

    public Position getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean collides(Position parentPosition, ColliderBox collider2, Position colliderPosition) {
        return (parentPosition.getX() + position.getX() < colliderPosition.getX() + collider2.getPosition().getX() + collider2.getWidth() &&
                parentPosition.getX() + position.getX() + width > colliderPosition.getX() + collider2.getPosition().getX() &&
                parentPosition.getY() + position.getY() < colliderPosition.getY() + collider2.getPosition().getY() + collider2.getHeight() &&
                parentPosition.getY() + position.getY() + height > colliderPosition.getY() + collider2.getPosition().getY());
    }
}
