import BoardComponents.Board;
import BoardElements.Bomb;
import BoardElements.Bomberman;
import Structures.ColliderBox;
import Structures.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BombTest {
    Board testBoard;

    @BeforeEach
    public void BoardCreation() {
        testBoard = new Board(
                "000000000000000000" +
                        "0  111000000111  0" +
                        "0 01110000001110 0" +
                        "010000000000000010" +
                        "01  1  1111  1  10" +
                        "01011100  00111010" +
                        "0  1110    0111  0" +
                        "0  1110    0111  0" +
                        "0  1110    0111  0" +
                        "0  1110    0111  0" +
                        "01011100  00111010" +
                        "01  1  1111  1  10" +
                        "010000000000000010" +
                        "0 01110000001110 0" +
                        "0  111000000111  0" +
                        "000000000000000000");
    }

    @Test
    public void ExplosionRadiusTest() {
        Bomberman playerOne = new Bomberman("PLAYER ONE", "#0000FF", new Position(1,1), testBoard);
        playerOne.setBombRadius(3);

        playerOne.action();

        //Checks if the bomb created by the player is setup correctly;
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        assertEquals(3, bombs.get(0).getRadius());

        //Check if the bomb explodes correctly
        Bomb testBomb = bombs.get(0);
        for (int i = 0; i < testBomb.getTicksLeft(); i++) {
            testBomb.action();
        }
        assertTrue(bombs.get(0).getExploded());
        assertEquals(new ColliderBox[] { new ColliderBox(new Position(0,0),2,1), new ColliderBox(new Position(0,1),1,1) }, bombs.get(0).getCollider());
    }

    @Test
    public void ExplosionPlayerTest() {
        Bomberman playerOne = new Bomberman("PLAYER ONE", "#0000FF", new Position(1,1), testBoard);
        playerOne.setBombRadius(3);

        playerOne.action();

        //Checks if the bomb created by the player damages it on explosion.
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        Bomb testBomb = bombs.get(0);
        for (int i = 0; i < testBomb.getTicksLeft(); i++) {
            testBoard.loop();
        }
        assertTrue(bombs.get(0).getExploded());
        assertFalse(playerOne.isAlive());
    }

    @Test
    public void ExplosionBlockTest() {
        Bomberman playerOne = new Bomberman("PLAYER ONE", "#0000FF", new Position(1,1), testBoard);
        playerOne.setPosition(new Position(3, 4));
        playerOne.action();

        //Checks if the bomb created by the player damages it on explosion.
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        Bomb testBomb = bombs.get(0);
        for (int i = 0; i < testBomb.getTicksLeft(); i++) {
            testBomb.action();
        }
        assertTrue(bombs.get(0).getExploded());
        assertFalse(playerOne.isAlive());
    }

    /* This code will only be tested when Power Ups are created.
    @Test
    public void ExplosionPowerUpTest() {
        Bomberman playerOne = new Bomberman("PLAYER ONE", "#0000FF", new Position(1,1), testBoard);
        playerOne.setBombRadius(3);

        playerOne.setBombLimit(3);

        playerOne.action();
        playerOne.action();
        playerOne.action();

        //Checks if the bomb created by the player damages it on explosion.
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(3, bombs.size());
        Bomb testBomb = bombs.get(0);
        for (int i = 0; i < testBomb.getTicksLeft(); i++) {
            testBoard.loop();
        }
        assertEquals(0, testBoard.getBombs().size());
    }
     */

}

