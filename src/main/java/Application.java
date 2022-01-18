import Menu.MenuController;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        try {
            Font font =  Font.createFont(Font.TRUETYPE_FONT,
                    new File(getClass().getClassLoader().getResource("GameFont.ttf").toURI()));

            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);

            DefaultTerminalFactory factory = new DefaultTerminalFactory();

            factory.setTerminalEmulatorFontConfiguration(
                    AWTTerminalFontConfiguration.newInstance(
                            font.deriveFont(Font.PLAIN, 12)));

            factory.setForceAWTOverSwing(true);
            factory.setInitialTerminalSize(new TerminalSize(98, 52));

            Terminal terminal = factory.createTerminal();
            ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
            });

            ((AWTTerminalFrame) terminal).setTitle("BOMBERMAN");
            ((AWTTerminalFrame) terminal).setIconImage(ImageIO.read(getClass().getClassLoader().getResource("icon.png")));
            ((AWTTerminalFrame) terminal).setResizable(false);
            
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            screen.clear();
            screen.refresh();

            new MenuController(screen, 98, 52);

            screen.stopScreen();

        } catch (IOException | InterruptedException | FontFormatException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}