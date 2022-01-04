package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

import javafx.scene.layout.Background;

public class DrawingBlock {
    Position pos = null;
    int width = 1;
    int height = 1;

    String backColor = null; //Background Color
    String frontColor = null; //Front Color

    char character;

    DrawingBlock(Position pos_, int width_, int height_, String backColor_, String frontColor_, char character_) {
        this.pos = pos_;
        this.width = width_;
        this.height = height_;
        this.backColor = backColor_;
        this.frontColor = frontColor_;
        this.character = character_;
    }

    public void draw(TextGraphics graphics_, Position offset){

        if(backColor !=null) graphics_.setBackgroundColor(TextColor.Factory.fromString(this.backColor));

        if(frontColor !=null) graphics_.setBackgroundColor(TextColor.Factory.fromString(this.frontColor));
        graphics_.fillRectangle( new TerminalPosition(this.pos.getX() +offset.getX(),this.pos.getY()+offset.getY()), new TerminalSize(width, height), this.character);
    }
}
