package Game;

import BoardComponents.BoardElement;
import BoardElements.Bomberman;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class GameView {
    int width;
    int height;
    DrawingImage viewBackground;
    DrawingImage boardFrame = new DrawingImage(new DrawingBlock[] {
            new DrawingBlock(new Position(21, 2), 1, 48, "#000000", null, ' '),
            new DrawingBlock(new Position(76, 2), 1, 48, "#000000", null, ' '),
            new DrawingBlock(new Position(21, 1), 56, 1, "#000000", null, ' '),
            new DrawingBlock(new Position(21, 50), 56, 1, "#000000", null, ' ')
    });
    //TODO: Connect with Board.backColor
    DrawingImage boardBackground = new DrawingImage(new DrawingBlock[] {
            new DrawingBlock(new Position(22, 2), 54, 48, "#999999", null, ' ')
    });
    public GameView(int width_, int height_) {
        this.width = width_;
        this.height = height_;
        viewBackground = new DrawingImage(new DrawingBlock[] {new DrawingBlock(new Position(0,0), width, height, "#ffffff", null, ' ')});
    }

    public void draw(TextGraphics graphics_, ArrayList<BoardElement> drawQueue, Bomberman playerOne_, Bomberman playerTwo_, Bomberman playerThree_, Bomberman playerFour_) {
        viewBackground.draw(graphics_, new Position(0,0), false);
        boardFrame.draw(graphics_, new Position(0,0), false);
        boardBackground.draw(graphics_, new Position(0,0), false);

        for (BoardElement element : drawQueue)
            element.draw(graphics_);
        if (playerOne_.isAlive())
            playerOne_.draw(graphics_);
        if (playerTwo_.isAlive())
            playerTwo_.draw(graphics_);
        if (playerThree_.isAlive())
            playerThree_.draw(graphics_);
        if (playerFour_.isAlive())
            playerFour_.draw(graphics_);
    }
}
