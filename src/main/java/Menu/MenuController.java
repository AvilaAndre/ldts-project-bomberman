package Menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.Thread.sleep;

public class MenuController {

    Screen screen;
    TextGraphics graphics;
    int width;
    int height;
    int deltaTime = 0;
    int framesPerSecond = 0;

    public MenuController(Screen screen_, int width_, int height_) throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        this.screen = screen_;
        this.graphics = screen_.newTextGraphics();
        this.model = new MenuModel(screen_, width_, height_);
        this.view = new MenuView(width_, height_, this);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#f7ffc9"));
        graphics.fillRectangle(new TerminalPosition(0, height_), new TerminalSize(width_, height_+2), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#960000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, height_), "DELTA:" + deltaTime + "s");

        run();
    }



    private void run() throws IOException, InterruptedException {
        long lastTime = System.nanoTime();
        int frames = 0;
        int viewTime = 0;
        int viewSeconds = 0;
        do {
            long time = System.nanoTime();
            deltaTime = (int) ((time - lastTime) / 1000000);
            lastTime = time;
            viewTime += deltaTime;
            viewSeconds += deltaTime;
            //Output
            if (viewTime > 66) {
                viewTime = 0;
                frames++;
                updateView();
            }
            if (viewSeconds > 1000) {
                framesPerSecond = frames;
                viewSeconds = 0;
                frames = 0;
            }
            //Right now, the program runs SUPER fast soo this while would loop infinitely if this sleep didn't exist.
            sleep(1);
            //Input
        } while (getInput());
    }
}
