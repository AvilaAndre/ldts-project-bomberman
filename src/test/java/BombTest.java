import BoardComponents.Board;
import BoardComponents.BoardElement;
import BoardElements.Bomb;
import BoardElements.Bomberman;
import Game.GameModel;
import Structures.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BombTest {
    Board testBoard;
    GameModel testModel;

    @BeforeEach
    public void BoardCreation() {
        testModel = new GameModel("0000FF", null, null, null);
        testBoard = testModel.getGameBoard();
    }

    @Test
    public void ExplosionRadiusTest() {
        Bomberman playerOne = new Bomberman("PLAYER ONE", "#0000FF", new Position(1,1), testBoard);
        playerOne.setBombRadius(3);

        assertTrue(playerOne.action());

        //Checks if the bomb created by the player is set up correctly;
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        assertEquals(3, bombs.get(0).getRadius());

        //Check if the bomb explodes correctly
        Bomb testBomb = bombs.get(0);
        int bombTicksToExplode = testBomb.getTicksLeft() + 1;
        for (int i = 0; i < bombTicksToExplode; i++) {
            testBomb.action();
        }
        assertTrue(bombs.get(0).getExploded());
        int[] explosionRange = testBoard.bombExplosion(testBomb.getRadius(), testBomb.getPosition());
        //Compared one by one as comparing the arrays would compare the object instead of the content.
        assertEquals(0, explosionRange[0]);
        assertEquals(0, explosionRange[1]);
        assertEquals(3, explosionRange[2]);
        assertEquals(3, explosionRange[3]);
    }

    @Test
    public void ExplosionPlayerTest() {
        Bomberman playerOne = testModel.getPlayerOne();
        playerOne.setBombRadius(3);

        assertTrue(playerOne.action());

        //Checks if the bomb created by the player damages it on explosion.
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        Bomb testBomb = bombs.get(0);
        int bombTicksToExplode = testBomb.getTicksLeft() + 1;
        for (int i = 0; i < bombTicksToExplode; i++) {
            testBoard.loop();
        }
        assertTrue(testBomb.getExploded());
        assertFalse(playerOne.isAlive());
    }

    @Test
    public void ExplosionBlockTest() {
        Bomberman playerOne = new Bomberman("PLAYER ONE", "#0000FF", new Position(1,1), testBoard);
        playerOne.setPosition(new Position(3, 4));
        playerOne.action();
        //indestructible block
        BoardElement testBlock1 = testBoard.getBoard().get(18);
        //destructible block
        BoardElement testBlock2 = testBoard.getBoard().get(19);

        //Checks if the bomb created by the player damages it on explosion.
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        Bomb testBomb = bombs.get(0);
        int bombTicksToExplode = testBomb.getTicksLeft() + 1;
        for (int i = 0; i < bombTicksToExplode; i++) {
            testBoard.loop();
        }
        assertTrue(bombs.get(0).getExploded());
        assertEquals(testBlock1, testBoard.getBoard().get(18));
        assertNotEquals(testBlock1, testBoard.getBoard().get(19));
    }

}

