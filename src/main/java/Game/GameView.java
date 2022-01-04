package Game;

import BoardComponents.BoardElement;
import BoardElements.Bomberman;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class GameView {
    int width;
    int height;

    public GameView(int width_, int height_) {
        this.width = width_;
        this.height = height_;
    }

    public void draw(TextGraphics graphics_, ArrayList<BoardElement> drawQueue, Bomberman playerOne_, Bomberman playerTwo_, Bomberman playerThree_, Bomberman playerFour_) {
        for (BoardElement element : drawQueue)
            element.draw(graphics_);
    }
}
