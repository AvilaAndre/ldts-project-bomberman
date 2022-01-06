package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;


public class DrawingBlock {
    private final Position pos;
    private final int width;
    private final int height;
    private final String backColor; //Background Color
    private final String frontColor; //Front Color
    private final char character;

    public DrawingBlock(Position pos_, int width_, int height_, String backColor_, String frontColor_, char character_) {
        this.pos = pos_;
        this.width = width_;
        this.height = height_;
        this.backColor = backColor_;
        this.frontColor = frontColor_;
        this.character = character_;
    }

    public void draw(TextGraphics graphics_, Position offset, boolean boardOffset_){
        Position boardOffset = new Position(0,0);
        if (boardOffset_)
            boardOffset = new Position(22, 2);

        if(backColor !=null) graphics_.setBackgroundColor(TextColor.Factory.fromString(this.backColor));

        if(frontColor !=null) graphics_.setForegroundColor(TextColor.Factory.fromString(this.frontColor));
        graphics_.fillRectangle( new TerminalPosition(this.pos.getX() +offset.getX()*3 + boardOffset.getX(),this.pos.getY()+offset.getY()*3 + boardOffset.getY()), new TerminalSize(width, height), this.character);
    }

    public char getCharacter() {
        return character;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getBackColor() {
        return backColor;
    }

    public String getFrontColor() {
        return frontColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        DrawingBlock p = (DrawingBlock) o;
        System.out.println(this.backColor.equals(p.getBackColor()) && this.frontColor.equals(p.getFrontColor()) && this.character == p.getCharacter() && this.width == p.getWidth() && this.height == p.getHeight());
        return this.backColor.equals(p.getBackColor()) && this.frontColor.equals(p.getFrontColor()) && this.character == p.getCharacter() && this.width == p.getWidth() && this.height == p.getHeight();
    }
}
