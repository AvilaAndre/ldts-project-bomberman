import BoardComponents.Board;
import BoardElements.Bomb;
import BoardElements.Bomberman;
import BoardElements.PowerUp;
import BoardElements.PowerUps.PowerUpFactory;
import Game.GameModel;
import Structures.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PowerUpTest {
    PowerUpFactory factory;
    Board testBoard;
    GameModel testModel;

    @BeforeEach
    public void BoardCreation() {
        testModel = new GameModel("0000FF", null, null, null);
        testBoard = testModel.getGameBoard();
        factory = new PowerUpFactory();
    }

    @Test
    public void createPowerUp() {
        PowerUp newPowerUp = factory.getPowerUp(new Position(0,0), testBoard, 6);

        assertNull(newPowerUp);

        newPowerUp = factory.getPowerUp(new Position(0,0), testBoard, 0);
        assertEquals("ExtraBomb", newPowerUp.getType());

        newPowerUp = factory.getPowerUp(new Position(0,0), testBoard, 1);
        assertEquals("BlastSize", newPowerUp.getType());

        newPowerUp = factory.getPowerUp(new Position(0,0), testBoard, 2);
        assertEquals("ExtraLife", newPowerUp.getType());

        newPowerUp = factory.getPowerUp(new Position(0,0), testBoard, 3);
        assertEquals("Shield", newPowerUp.getType());

        newPowerUp = factory.getPowerUp(new Position(0,0), testBoard, 4);
        assertEquals("PushTheBomb", newPowerUp.getType());

        newPowerUp = factory.getPowerUp(new Position(0,0), testBoard, 5);
        assertEquals("Invincibility", newPowerUp.getType());

    }

    @Test
    public void extraBombEffect() {
        Bomberman player = testModel.getPlayerOne();
        PowerUp extraBombPowerUp = factory.getPowerUp(new Position(0,0), testBoard, 0);

        extraBombPowerUp.affect(player);

        assertEquals(2, player.getBombLimit());
        player.moveDown();
        //Drops a bomb on his location
        player.action();
        assertEquals(1, testBoard.getBombs().size());
        testBoard.loop();
        //Drops a bomb on his location, this time a bomb is already on that place so no bomb is created.
        player.action();
        assertEquals(1, testBoard.getBombs().size());
        player.moveUp();
        //Drops a second bomb.
        player.action();
        assertEquals( 2, testBoard.getBombs().size());
        player.moveRight();
        //Tries to drop a third bomb, not being successful as his limit is 2.
        player.action();
        assertEquals( 2, testBoard.getBombs().size());
        ArrayList<Bomb> bombs = testBoard.getBombs();
        int ticks = bombs.get(1).getTicksLeft();
        int bombTicksToExplode = ticks + 1;
        for (int i = 0; i < bombTicksToExplode; i++) {
            testBoard.loop();
        }
        assertTrue(bombs.get(0).getExploded());
        assertFalse(bombs.get(1).getExploded());
    }

    @Test
    public void blastSizeEffect() {
        Bomberman player = testModel.getPlayerOne();
        PowerUp blastSizePowerUp = factory.getPowerUp(new Position(0, 0), testBoard, 1);

        assertEquals(1, player.getBombRadius());
        blastSizePowerUp.affect(player);
        assertEquals(2, player.getBombRadius());

        assertTrue(player.action());

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
        assertEquals(2, explosionRange[2]);
        assertEquals(2, explosionRange[3]);
    }



    @Test
    public void extraLifeEffect() {
        Bomberman player = testModel.getPlayerOne();
        PowerUp extraLifePowerUp = factory.getPowerUp(new Position(0, 0), testBoard, 2);

        assertEquals(1, player.getLives());
        extraLifePowerUp.affect(player);
        assertEquals(2, player.getLives());

        //This time taking damage shouldn't lead to death.
        player.action();
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        Bomb testBomb = bombs.get(0);
        int bombTicksToExplode = testBomb.getTicksLeft() + 1;
        for (int i = 0; i < bombTicksToExplode; i++) {
            testBoard.loop();
        }
        assertTrue(testBomb.getExploded());
        assertTrue(player.isAlive());
        assertEquals(1, player.getLives());
    }

    @Test
    public void shieldEffect() {
        Bomberman player = testModel.getPlayerOne();
        PowerUp shieldPowerUp = factory.getPowerUp(new Position(0, 0), testBoard, 3);

        assertFalse(player.getShield());
        shieldPowerUp.affect(player);
        assertTrue(player.getShield());

        //Taking damage shouldn't lead to death nor the loss of any life.
        player.action();
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        Bomb testBomb = bombs.get(0);
        int bombTicksToExplode = testBomb.getTicksLeft() + 1;
        for (int i = 0; i < bombTicksToExplode; i++) {
            testBoard.loop();
        }
        assertTrue(testBomb.getExploded());
        assertTrue(player.isAlive());
        assertEquals(1, player.getLives());
        // The player loses his shield after taking damage.
        assertFalse(player.getShield());
    }

    @Test
    public void pushTheBombEffect() {
        Bomberman player = testModel.getPlayerOne();
        PowerUp pushTheBombPowerUp = factory.getPowerUp(new Position(0, 0), testBoard, 4);
        player.setPosition(new Position(8,8));

        player.action();
        assertEquals(new Position(8,8), testBoard.getBombs().get(0).getPosition());
        player.moveLeft();
        player.moveRight();
        //Player doesn't move as there is a bomb there.
        assertEquals(new Position(7,8), player.getPosition());
        for (int i = 0; i < testBoard.getBombs().get(0).getMoveTicksLeft(); i++) {
            testBoard.loop();
        }
        //Pushing (moving towards) the bomb won't move it.
        assertEquals(new Position(8,8), testBoard.getBombs().get(0).getPosition());

        assertFalse(player.getPushTheBomb());
        pushTheBombPowerUp.affect(player);
        assertTrue(player.getPushTheBomb());

        player.moveRight();
        //Again, player doesn't move as there is a bomb there.
        assertEquals(new Position(7,8), player.getPosition());
        for (int i = 0; i < testBoard.getBombs().get(0).getMoveTicksLeft(); i++) {
            testBoard.loop();
        }
        //Pushing (moving towards) the bomb won't move it.
        assertNotEquals(new Position(8,8), testBoard.getBombs().get(0).getPosition());

        //Pushing the bomb in this scenario will make the player
        //outside the blast zone.
        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        Bomb testBomb = bombs.get(0);
        int bombTicksToExplode = testBomb.getTicksLeft() + 1;
        for (int i = 0; i < bombTicksToExplode; i++) {
            testBoard.loop();
        }
        assertTrue(testBomb.getExploded());
        assertTrue(player.isAlive());
    }

    @Test
    public void invincibleEffect() {
        Bomberman player = testModel.getPlayerOne();
        PowerUp invincibilityPowerUp = factory.getPowerUp(new Position(0, 0), testBoard, 5);

        assertFalse(player.getInvincible());
        newPowerUp.affect(player);
        assertTrue(player.getInvincible());

        //Invincibility is a power up that expires.
        int ticks = player.getInvincibilityTicksLeft() + 1;
        for (int i = 0; i < ticks; i++) {
            testBoard.loop();
        }

        assertFalse(player.getInvincibility());

        //Shields are not destroyed by bombs when the player is invincible.
        PowerUp shield = factory.getPowerUp(new Position(0, 0), testBoard, 3);
        assertFalse(player.getShield());
        shield.effect(player);
        assertTrue(player.getShield());

        ArrayList<Bomb> bombs = testBoard.getBombs();
        assertEquals(1, bombs.size());
        Bomb testBomb = bombs.get(0);
        int bombTicksToExplode = testBomb.getTicksLeft();
        for (int i = 0; i < bombTicksToExplode; i++) {
            testBoard.loop();
        }
        invincibilityPowerUp.affect(player);
        testBoard.loop();
        assertTrue(testBomb.getExploded());
        assertTrue(player.isAlive());
        assertEquals(1, player.getLives());
        assertTrue(player.getInvincibility());
        assertTrue(player.getShield());


    }
}