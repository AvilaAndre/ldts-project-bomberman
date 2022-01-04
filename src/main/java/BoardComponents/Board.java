package BoardComponents;

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
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 18; j++) {
                switch (code.charAt(i*18 + j)) {
                    case '0':
                        //TODO: Create blocks
                        //blocks.add(new Block("#300000", new Position(j, i), false, this));
                        u++;
                        break;
                    case '1':
                        //blocks.add(new Block("#ffffff", new Position(j, i), true, this));
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
