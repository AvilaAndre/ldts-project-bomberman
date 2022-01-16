package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingAnimation;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingMethod;
import Structures.ColliderBox;
import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bomberman extends BoardElement {
    String name;
    String color;
    int lives = 0;
    int bombLimit = 1;
    int activeBombs = 0;
    int bombRadius = 1;
    boolean invincibility = false;
    int invincibilityTicks;
    boolean invincibilityEffect = true;
    boolean shield = false;
    boolean pushTheBomb = false;

    DrawingMethod moveUpVisual;
    DrawingMethod moveDownVisual;
    DrawingMethod moveLeftVisual;
    DrawingMethod moveRightVisual;
    DrawingMethod shieldVisual;

    enum STATE {UP, DOWN, LEFT, RIGHT, DEAD}

    STATE state = STATE.DOWN;

    public Bomberman(String name_, String color_, Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_, new ColliderBox[]{ new ColliderBox(new Position(0,0), 1, 1) });
        this.name = name_;
        if (color_ != null)
            this.lives = 1;
        else state = STATE.DEAD;
        this.color = color_;
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
        shieldVisual = new DrawingAnimation(new DrawingImage[] {
                new DrawingImage(new DrawingBlock[] {new DrawingBlock(new Position(2,0), 1, 1, null, color_, '?')}),
                new DrawingImage(new DrawingBlock[] {new DrawingBlock(new Position(2,0), 1, 1, null, color_, '!')}),
                new DrawingImage(new DrawingBlock[] {new DrawingBlock(new Position(2,0), 1, 1, null, color_, '@')}),
                new DrawingImage(new DrawingBlock[] {new DrawingBlock(new Position(2,0), 1, 1, null, color_, '!')})
        },new int[] {10, 10, 10, 10}, false);
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

    public void draw(TextGraphics graphics_) {
        if (invincibility)
            if (invincibilityEffect) {
                invincibilityEffect = false;
                return;
            }
            else
                invincibilityEffect = true;

        DrawingMethod visual = getVisual();
        graphics_.setBackgroundColor(getBoard().getBackColor());
        if (visual != null) {
            visual.draw(graphics_, getPosition(), true);
            if (shield)
                shieldVisual.draw(graphics_, getPosition(), true);
        }
    }

    @Override
    public void moveUp() {
        if (state == STATE.DEAD)
            return;
        state = STATE.UP;
        setPosition(new Position(getPosition().getX(), getPosition().getY() - 1));
    }

    @Override
    public void moveDown() {
        if (state == STATE.DEAD)
            return;
        state = STATE.DOWN;
        setPosition(new Position(getPosition().getX(), getPosition().getY() + 1));
    }

    @Override
    public void moveLeft() {
        if (state == STATE.DEAD)
            return;
        state = STATE.LEFT;
        setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
    }

    @Override
    public void moveRight() {
        if (state == STATE.DEAD)
            return;
        state = STATE.RIGHT;
        setPosition(new Position(getPosition().getX() + 1, getPosition().getY()));
    }

    @Override
    public boolean action() {
        if (state == STATE.DEAD)
            return false;
        if (bombLimit > activeBombs) {
            if (getBoard().addBomb(this.getPosition(), bombRadius, this))
                activeBombs++;
            return true;
        }
        return false;
    }

    public void loop() {
        if (invincibility) {
            invincibilityTicks--;
            if (invincibilityTicks < 0) {
                invincibility = false;
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getBombRadius() {
        return this.bombRadius;
    }

    public void setBombRadius(int newBombRadius_) {
        if (state == STATE.DEAD)
            return;
        this.bombRadius = newBombRadius_;
    }

    public int getBombLimit() {
        return this.bombLimit;
    }

    public void setBombLimit(int limit_) {
        if (state == STATE.DEAD)
            return;
        this.bombLimit = limit_;
    }

    public int getLives() {
        return this.lives;
    }

    public void setLives(int lives_) {
        if (state == STATE.DEAD)
            return;
        this.lives = lives_;
        if (lives < 1)
            state = STATE.DEAD;
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public int getActiveBombs() {
        return this.activeBombs;
    }

    public void setActiveBombs(int activeBombs) {
        if (state == STATE.DEAD)
            return;
        this.activeBombs = activeBombs;
    }

    public void getHurt() {
        if (!invincibility) {
            if (shield) {
                shield = false;
                invincibility = true;
                invincibilityTicks = 15;
            }
            else {
                lives -= 1;
                invincibility = true;
                invincibilityTicks = 15;
                if (lives < 1)
                    state = STATE.DEAD;
            }
        }
    }

    public boolean getShield() {
        return this.shield;
    }

    public void setShield(boolean shield_) {
        if (state == STATE.DEAD)
            return;
        this.shield = shield_;
    }

    public boolean getPushTheBomb() {
        return this.pushTheBomb;
    }

    public void setPushTheBomb(boolean push_) {
        if (state == STATE.DEAD)
            return;
        this.pushTheBomb = push_;
    }

    public boolean getInvincibility() {
        return this.invincibility;
    }

    public void setInvincibility(boolean invincibility_) {
        if (state == STATE.DEAD)
            return;
        this.invincibility = invincibility_;
    }

    public int getInvincibilityTicksLeft() {
        return this.invincibilityTicks;
    }

    public void setInvincibilityTicksLeft(int invincibilityTicks_) {
        if (state == STATE.DEAD)
            return;
        this.invincibilityTicks = invincibilityTicks_;
    }
}
