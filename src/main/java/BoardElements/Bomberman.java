package BoardElements;

import BoardComponents.Board;
import BoardComponents.BoardElement;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import Structures.ColliderBox;
import Structures.Position;

public class Bomberman extends BoardElement {
    String name;
    int lives = 0;
    int bombLimit;
    int activeBombs;
    int bombRadius;


    public Bomberman(String name_, String color_, Position pos_, Board gameBoard_) {
        super(pos_, gameBoard_, new DrawingImage(new DrawingBlock[] {
                new DrawingBlock(new Position(0,0), 3, 3, color_, "#000000", ' ')
        })
                , new ColliderBox[]{ new ColliderBox(new Position(0,0), 3, 3) });
        this.name = name_;
        if (color_ != null)
            this.lives = 1;
    }

    public String getName() {
        return this.name;
    }

    public int getBombRadius() {
        return this.bombRadius;
    }

    @Override
    public boolean action() {
        return bombLimit > activeBombs;
    }

    public boolean isAlive() {
        return lives > 0;
    }
}
