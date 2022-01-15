package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;

public class Bomberman extends BoardElement {
    String name;
    int lives = 0;
    int bombLimit = 1;
    int activeBombs = 0;
    int bombRadius = 1;
    boolean invincibility = false;
    int invincibilityTicks;
    boolean shield = false;
    boolean pushTheBomb = false;

    DrawingMethod moveUpVisual = null;
    DrawingMethod moveDownVisual = null;
    DrawingMethod moveLeftVisual = null;
    DrawingMethod moveRightVisual = null;

    enum STATE {UP, DOWN, LEFT, RIGHT, DEAD}

    STATE state = STATE.DOWN;

    public Bomberman(String name_, String color_, Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_, new ColliderBox[]{ new ColliderBox(new Position(0,0), 1, 1) });
        this.name = name_;
        if (color_ != null)
            this.lives = 1;

        moveUpVisual = new DrawingImage(new DrawingBlock[] {
                new DrawingBlock(new Position(0,0), 1, 1, null, color_, 'g'),
                new DrawingBlock(new Position(1,0), 1, 1, color_, "#000000", ' '),
                new DrawingBlock(new Position(2,0), 1, 1, null, color_, 'h'),
                new DrawingBlock(new Position(0,1), 1, 1, null, color_, 'a'),
                new DrawingBlock(new Position(1,1), 1, 1, null, color_, 'f'),
                new DrawingBlock(new Position(2,1), 1, 1, null, color_, 'c'),
                new DrawingBlock(new Position(0,2), 1, 1, null, color_, 'b'),
                new DrawingBlock(new Position(1,2), 1, 1, null, color_, 'e'),
                new DrawingBlock(new Position(2,2), 1, 1, null, color_, 'd'),
        });
        moveDownVisual = new DrawingImage(new DrawingBlock[] {
                new DrawingBlock(new Position(0,0), 1, 1, null, color_, 'g'),
                new DrawingBlock(new Position(1,0), 1, 1, color_, "#000000", 'i'),
                new DrawingBlock(new Position(2,0), 1, 1, null, color_, 'h'),
                new DrawingBlock(new Position(0,1), 1, 1, null, color_, 'a'),
                new DrawingBlock(new Position(1,1), 1, 1, null, color_, 'f'),
                new DrawingBlock(new Position(2,1), 1, 1, null, color_, 'c'),
                new DrawingBlock(new Position(0,2), 1, 1, null, color_, 'b'),
                new DrawingBlock(new Position(1,2), 1, 1, null, color_, 'e'),
                new DrawingBlock(new Position(2,2), 1, 1, null, color_, 'd'),
        });
        moveLeftVisual = new DrawingImage(new DrawingBlock[] {
                new DrawingBlock(new Position(0,0), 1, 1, null, color_, 'g'),
                new DrawingBlock(new Position(1,0), 1, 1, color_, "#000000", 'k'),
                new DrawingBlock(new Position(2,0), 1, 1, null, color_, 'h'),
                new DrawingBlock(new Position(0,1), 1, 1, null, color_, 'a'),
                new DrawingBlock(new Position(1,1), 1, 1, null, color_, 'f'),
                new DrawingBlock(new Position(2,1), 1, 1, null, color_, 'c'),
                new DrawingBlock(new Position(0,2), 1, 1, null, color_, 'b'),
                new DrawingBlock(new Position(1,2), 1, 1, null, color_, 'e'),
                new DrawingBlock(new Position(2,2), 1, 1, null, color_, 'd'),
        });
        moveRightVisual = new DrawingImage(new DrawingBlock[] {
                new DrawingBlock(new Position(0,0), 1, 1, null, color_, 'g'),
                new DrawingBlock(new Position(1,0), 1, 1, color_, "#000000", 'l'),
                new DrawingBlock(new Position(2,0), 1, 1, null, color_, 'h'),
                new DrawingBlock(new Position(0,1), 1, 1, null, color_, 'a'),
                new DrawingBlock(new Position(1,1), 1, 1, null, color_, 'f'),
                new DrawingBlock(new Position(2,1), 1, 1, null, color_, 'c'),
                new DrawingBlock(new Position(0,2), 1, 1, null, color_, 'b'),
                new DrawingBlock(new Position(1,2), 1, 1, null, color_, 'e'),
                new DrawingBlock(new Position(2,2), 1, 1, null, color_, 'd'),
        });
    }

    @Override
    public DrawingMethod getVisual() {
        switch (state) {
            case UP: {
                return moveUpVisual;
            }
            case DOWN: {
                return moveDownVisual;
            }
            case LEFT: {
                return moveLeftVisual;
            }
            case RIGHT: {
                return moveRightVisual;
            }
            case DEAD: {
                return null;
            }
            default:
                return moveDownVisual;
        }
    }

    @Override
    public void moveUp() {
        state = STATE.UP;
        setPosition(new Position(getPosition().getX(), getPosition().getY() - 1));
    }

    @Override
    public void moveDown() {
        state = STATE.DOWN;
        setPosition(new Position(getPosition().getX(), getPosition().getY() + 1));
    }

    @Override
    public void moveLeft() {
        state = STATE.LEFT;
        setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
    }

    @Override
    public void moveRight() {
        state = STATE.RIGHT;
        setPosition(new Position(getPosition().getX() + 1, getPosition().getY()));
    }

    @Override
    public boolean action() {
        if (bombLimit > activeBombs) {
            if (getBoard().addBomb(this.getPosition(), bombRadius, this))
                activeBombs++;
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public int getBombRadius() {
        return this.bombRadius;
    }

    public void setBombRadius(int newBombRadius_) {
        this.bombRadius = newBombRadius_;
    }

    public int getBombLimit() {
        return this.bombLimit;
    }

    public void setBombLimit(int limit_) {
        this.bombLimit = limit_;
    }

    public int getLives() {
        return this.lives;
    }

    public void setLives(int lives_) {
        this.lives = lives_;
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public int getActiveBombs() {
        return this.activeBombs;
    }

    public void setActiveBombs(int activeBombs) {
        this.activeBombs = activeBombs;
    }

    public void increaseActiveBombs() {

    }

    public void getHurt() {
        lives -= 1;
        invincibility = true;
        invincibilityTicks = 10;
    }

    public boolean getShield() {
        return this.shield;
    }

    public void setShield(boolean shield_) {
        this.shield = shield_;
    }

    public boolean getPushTheBomb() {
        return this.pushTheBomb;
    }

    public void setPushTheBomb(boolean push_) {
        this.pushTheBomb = push_;
    }

    public boolean getInvincibility() {
        return this.invincibility;
    }

    public void setInvincibility(boolean invincibility_) {
        this.invincibility = invincibility_;
    }

    public int getInvincibilityTicksLeft() {
        return this.invincibilityTicks;
    }

    public void setInvincibilityTicksLeft(int invincibilityTicks_) {
        this.invincibilityTicks = invincibilityTicks_;
    }
}
