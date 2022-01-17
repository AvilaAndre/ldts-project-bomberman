package Game;

import BoardComponents.BoardElement;
import BoardElements.Bomberman;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingText;
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

    private void drawPlayerData(TextGraphics graphics_, int x, int y, Bomberman player_) {
        //frame
        new DrawingImage(new DrawingBlock[] {
                new DrawingBlock(new Position(x, y), 21, 1, "#000000", null, ' '),
                new DrawingBlock(new Position(x, y+13), 21, 1, "#000000", null, ' ')
        }).draw(graphics_, new Position(0,0), false);
        //character
        if (player_.isAlive()) {
            if (x < 50)
                new DrawingImage(new DrawingBlock[]{
                        new DrawingBlock(new Position(x + 4, y + 10), 5, 1, player_.getColor(), null, ' '),
                        new DrawingBlock(new Position(x + 3, y + 11), 7, 2, player_.getColor(), null, ' '),
                        new DrawingBlock(new Position(x + 5, y + 12), 1, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(x + 8, y + 12), 1, 1, "#000000", null, ' ')
                }).draw(graphics_, new Position(0, 0), false);
            else
                new DrawingImage(new DrawingBlock[]{
                        new DrawingBlock(new Position(x + 12, y + 10), 5, 1, player_.getColor(), null, ' '),
                        new DrawingBlock(new Position(x + 11, y + 11), 7, 2, player_.getColor(), null, ' '),
                        new DrawingBlock(new Position(x + 12, y + 12), 1, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(x + 15, y + 12), 1, 1, "#000000", null, ' ')
                }).draw(graphics_, new Position(0, 0), false);
            new DrawingText(new Position(x + 3, y + 4), "lives", "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + 3, y + 5), "bombs", "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + 3, y + 6), "blast radius", "#FFFFFF", "#000000").draw(graphics_);
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(x + 2, y + 4), 1, 1, "#FFFFFF", "#F00000", '"'),
                    new DrawingBlock(new Position(x + 2, y + 5), 1, 1, "#FFFFFF", "#000000", '/'),
                    new DrawingBlock(new Position(x + 2, y + 6), 1, 1, "#FFFFFF", "#F08800", ':'),
            }).draw(graphics_, new Position(0, 0), false);
            new DrawingText(new Position(x + ((player_.getLives() > 10)? 17 : 18), y + 4), String.valueOf(player_.getLives()), "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + ((player_.getBombLimit() > 10)? 17 : 18), y + 5), String.valueOf(player_.getBombLimit()), "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + ((player_.getBombRadius() > 10)? 17 : 18), y + 6), String.valueOf(player_.getBombRadius()), "#FFFFFF", "#000000").draw(graphics_);
            if (player_.getShield())
                new DrawingBlock(new Position(x + (x < 50? 14 : 4) , y + 10), 1, 1, "#EEEEEE", "#F0F000", '\'').draw(graphics_, new Position(0,0), false);
            if (player_.getPushTheBomb())
                new DrawingBlock(new Position(x + (x < 50? 16 : 6), y + 10), 1, 1, "#EEEEEE", "#000000", '-').draw(graphics_, new Position(0,0), false);
        }
        else {
            if (player_.getColor() != null) {
                new DrawingText(new Position(x + 8, y + 7), "dead", "#FFFFFF", player_.getColor()).draw(graphics_);
            }
        }


    }

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

        drawPlayerData(graphics_, 0, 4, playerOne_);
        drawPlayerData(graphics_, 77, 4, playerTwo_);
        drawPlayerData(graphics_, 0, 34, playerThree_);
        drawPlayerData(graphics_, 77, 34, playerFour_);

        //TODO: Alternate order to be able to see everyone when on top of each other.
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
