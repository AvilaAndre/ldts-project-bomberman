import Game.GameController;
import Interface.GAME;
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

public class Manager {

    GAME MenuState;
    GAME GameState;

    GAME STATE;

    private Screen screen;
    private final int width = 98;
    private final int height = 52;
    private final int framesPerSecond = 15;


    public Manager() throws IOException, URISyntaxException, FontFormatException, InterruptedException {

            setUp();

            MenuState = new MenuController(screen, width, height);
            GameState = new GameController(screen, width, height, "", "", "", "");

            //OtherState = new OtherController()


    }


    void setUp() throws IOException, URISyntaxException, FontFormatException {
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

        this.screen = new TerminalScreen(terminal);
    }


    void start() throws IOException, InterruptedException {

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        screen.clear();
        screen.refresh();

        STATE = MenuState;
        while (true) {
            long timePerFrame = 1000 / framesPerSecond;
            long startTime = 0;
            long endTime = 0;
            do {
                startTime = System.currentTimeMillis();
                STATE.updateView();
                endTime = System.currentTimeMillis();
                if (endTime - startTime < timePerFrame)
                    Thread.sleep(timePerFrame - (endTime - startTime));
                //Input
            } while (STATE.getInput());
            if (true) break;
        }

        screen.stopScreen();}
}
