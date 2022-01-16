import DrawingMethods.DrawingAnimation;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingText;
import Structures.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DrawingMethodsTest {
    @Test
    public void BlockTest() throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        factory.setInitialTerminalSize(new TerminalSize(98, 52));
        Terminal terminal = factory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TextGraphics graphics = screen.newTextGraphics();

        DrawingBlock testBlock1 = new DrawingBlock(new Position(0,0), 1, 1, "#FF0000", "#0000FF", '+');
        DrawingBlock testBlock2 = new DrawingBlock(new Position(0,0), 3, 2, "#000000", "#FF0000", '+');

        testBlock1.draw(graphics, new Position(0,0), false);
        assertEquals(TextColor.Factory.fromString("#FF0000"), screen.getBackCharacter(0,0).getBackgroundColor());
        assertNotEquals(TextColor.Factory.fromString("#000000"), screen.getBackCharacter(0,0).getForegroundColor());

        testBlock2.draw(graphics, new Position(0,0), false);
        assertNotEquals(TextColor.Factory.fromString("#FF0000"), screen.getBackCharacter(0,0).getBackgroundColor());
        assertEquals(TextColor.Factory.fromString("#FF0000"), screen.getBackCharacter(2,1).getForegroundColor());
    }

    @Test
    public void AnimationTest() throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        factory.setInitialTerminalSize(new TerminalSize(98, 52));
        Terminal terminal = factory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TextGraphics graphics = screen.newTextGraphics();

        DrawingImage testImage1 = new DrawingImage(new DrawingBlock[]{
                new DrawingBlock(new Position(0, 0), 1, 1, "#000000", null, '+'),
                new DrawingBlock(new Position(0, 0), 1, 1, "#000000", null, '+'),
                new DrawingBlock(new Position(0, 0), 1, 1, "#000000", null, '+')
        });
        DrawingImage testImage2 = new DrawingImage(new DrawingBlock[]{
                new DrawingBlock(new Position(0, 0), 1, 1, "#110000", null, ' '),
                new DrawingBlock(new Position(0, 0), 1, 1, "#110000", null, ' '),
        });
        DrawingImage testImage3 = new DrawingImage(new DrawingBlock[]{
                new DrawingBlock(new Position(0, 0), 1, 1, "#001100", null, 'u'),
        });
        DrawingImage testImage4 = new DrawingImage(new DrawingBlock[]{
                new DrawingBlock(new Position(0, 0), 1, 1, "#11FF00", null, 'o'),
        });

        DrawingAnimation testAnimation1 = new DrawingAnimation(new DrawingImage[] {testImage1, testImage2, testImage3, testImage4, testImage2}, new int[]{1, 2, 1, 4, 1}, false);

        testAnimation1.draw(graphics, new Position(0,0), false);
        assertEquals(TextColor.Factory.fromString("#000000"), screen.getBackCharacter(0,0).getBackgroundColor());
        testAnimation1.draw(graphics, new Position(0,0), false);
        assertEquals(TextColor.Factory.fromString("#110000"), screen.getBackCharacter(0,0).getBackgroundColor());
        testAnimation1.draw(graphics, new Position(0,0), false);
        assertNotEquals(TextColor.Factory.fromString("#001100"), screen.getBackCharacter(0,0).getBackgroundColor());

        for (int i = 0; i < 7; i++) {
            testAnimation1.draw(graphics, new Position(0, 0), false);
        }
        //Checking if it restarts after looping
        assertEquals(TextColor.Factory.fromString("#000000"), screen.getBackCharacter(0,0).getBackgroundColor());
        testAnimation1.restart();
        testAnimation1.setPaused(true);
        testAnimation1.draw(graphics, new Position(0, 0), false);
        testAnimation1.draw(graphics, new Position(0, 0), false);
        assertEquals(TextColor.Factory.fromString("#000000"), screen.getBackCharacter(0,0).getBackgroundColor());
        testAnimation1.setPaused(false);
        testAnimation1.draw(graphics, new Position(0, 0), false);
        assertEquals(TextColor.Factory.fromString("#000000"), screen.getBackCharacter(0,0).getBackgroundColor());
        testAnimation1.draw(graphics, new Position(0, 0), false);
        testAnimation1.draw(graphics, new Position(0, 0), false);
        testAnimation1.draw(graphics, new Position(0, 0), false);
        assertEquals(TextColor.Factory.fromString("#001100"), screen.getBackCharacter(0,0).getBackgroundColor());
        testAnimation1.restart();
        testAnimation1.draw(graphics, new Position(0, 0), false);
        assertEquals(TextColor.Factory.fromString("#000000"), screen.getBackCharacter(0,0).getBackgroundColor());
    }

    @Test
    public void TextTest() throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        factory.setInitialTerminalSize(new TerminalSize(98, 52));
        Terminal terminal = factory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TextGraphics graphics = screen.newTextGraphics();

        DrawingText testText1 = new DrawingText(new Position(0,0), "Test line!", "#FF0000", "#0000FF");

        testText1.draw(graphics, new Position(0,0), false);
        assertEquals(TextColor.Factory.fromString("#FF0000"), screen.getBackCharacter(2,0).getBackgroundColor());
        assertNotEquals(TextColor.Factory.fromString("#000000"), screen.getBackCharacter(2,0).getForegroundColor());
    }
}
