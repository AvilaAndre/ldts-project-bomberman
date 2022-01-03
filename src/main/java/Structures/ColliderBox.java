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

    public boolean collides(ColliderBox collider2) {
        return (position.getX() < collider2.getPosition().getX() + collider2.getWidth() &&
                position.getX() + width > collider2.getPosition().getX() &&
                position.getY() < collider2.getPosition().getY() + collider2.getHeight() &&
                position.getY() + height > collider2.getPosition().getY());
    }
}
