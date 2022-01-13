import BoardComponents.Board;
import BoardComponents.BoardElement;
import BoardElements.Block;
import BoardElements.Bomberman;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import Structures.ColliderBox;
import Structures.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
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
    public void BoardCreationTest() {
        DrawingImage dImage = new DrawingImage(new DrawingBlock[]{
                new DrawingBlock(new Position(0, 0), 1, 1, "#ffffff", "#000000", ' '),
                new DrawingBlock(new Position(1, 0), 1, 1, "#ffffff", "#000000", ' '),
                new DrawingBlock(new Position(2, 0), 1, 1, "#ffffff", "#000000", ' '),
                new DrawingBlock(new Position(0, 1), 1, 1, "#ffffff", "#000000", ' '),
                new DrawingBlock(new Position(1, 1), 1, 1, "#ffffff", "#000000", ' '),
                new DrawingBlock(new Position(2, 1), 1, 1, "#ffffff", "#000000", ' '),
                new DrawingBlock(new Position(0, 2), 1, 1, "#ffffff", "#000000", ' '),
                new DrawingBlock(new Position(1, 2), 1, 1, "#ffffff", "#000000", ' '),
                new DrawingBlock(new Position(2, 2), 1, 1, "#ffffff", "#000000", ' ')
                });
        Block testBlock = new Block(new Position(3, 1), testBoard, dImage, new ColliderBox[]{
                new ColliderBox(new Position(0, 0), 1, 1)}, true);
        assertEquals(testBlock.getVisual(), testBoard.getBoard().get(19).getVisual());
    }

    @Test
    public void MovementCollisionTest() {
        Bomberman playerOne = new Bomberman("PLAYER ONE", "#0000FF", new Position(1,1), testBoard);
        boolean canMove = testBoard.checkCollision(new Position(playerOne.getPosition().getX(), playerOne.getPosition().getY() + 1), playerOne.getCollider());
        assertTrue(canMove);
        playerOne.moveDown();
        assertEquals(new Position(1, 2), playerOne.getPosition());
    }

    @Test
    public void BlockDestroyTest() {
        //Test if a destructible block is destroyed.
        Block testBlock1 = (Block) testBoard.getBoard().get(19);
        testBlock1.destroy();
        assertTrue(testBlock1.getDestroyed());
        assertNotEquals(testBlock1, (Block) testBoard.getBoard().get(19));

        //Test if an indestructible block is destroyed.
        Block testBlock2 = (Block) testBoard.getBoard().get(10);
        testBlock2.destroy();
        assertFalse(testBlock2.getDestroyed());
        assertEquals(testBlock2, (Block) testBoard.getBoard().get(10));
    }

}

