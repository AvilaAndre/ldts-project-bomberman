package BoardComponents;

import BoardElements.Block;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import Structures.ColliderBox;
import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Board {
    TextGraphics graphics = null;
    private ArrayList<BoardElement> blocks = new ArrayList<>();
    //private ArrayList<Bomb> bombs = new ArrayList<>();
    private ArrayList<BoardElement> powerups = new ArrayList<>();
    private ArrayList<BoardElement> drawQueue = new ArrayList<>();

    public Board(String code) {
        int u = 0;
        DrawingImage dImage;
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 18; j++) {
                switch (code.charAt(i*18 + j)) {
                    case '0':
                        dImage = new DrawingImage(new DrawingBlock[]{
                                new DrawingBlock(new Position(0,0), 3, 3, "#300000", "#000000", ' ')
                        });
                        blocks.add(new Block(new Position(j, i), this, dImage, new ColliderBox[]{
                                new ColliderBox(new Position(0, 0), 3, 3)
                        }, false));
                        u++;
                        break;
                    case '1':
                        dImage = new DrawingImage(new DrawingBlock[]{
                                new DrawingBlock(new Position(0,0), 3, 3, "#ffffff", "#000000", '#')
                        });
                        blocks.add(new Block(new Position(j, i), this, dImage, new ColliderBox[]{
                                new ColliderBox(new Position(0, 0), 3, 3)
                        }, true));
                        u++;
                        break;
                }
            }
    }

    public ArrayList<BoardElement> getBoard() {
        return this.blocks;
    }

    public ArrayList<BoardElement> getDrawQueue() {
        return drawQueue;
    }
}
