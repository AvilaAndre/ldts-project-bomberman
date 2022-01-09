import DrawingMethods.DrawingBlock;
import Structures.ColliderBox;
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
        DrawingBlock testBlock2 = new DrawingBlock(new Position(0,0), 1, 1, "#000000", null, '+');

        testBlock1.draw(graphics, new Position(0,0), false);
        assertEquals(TextColor.Factory.fromString("#FF0000"),graphics.getBackgroundColor());
        assertNotEquals(TextColor.Factory.fromString("#000000"),graphics.getForegroundColor());

        testBlock2.draw(graphics, new Position(0,0), false);

        assertNotEquals(TextColor.Factory.fromString("#FF0000"),graphics.getBackgroundColor());
        assertEquals(TextColor.Factory.fromString("#0000FF"),graphics.getForegroundColor());
        //This second test shows that when draw() is called on a block the TextGraphics color changes to the block's color, however, if the color is null, it does not change
        //so the color will be the same as the color currently on the TextGraphics.
    }
    @Test
    public void AnimationTest() {



        assertTrue(true);
    }
}
