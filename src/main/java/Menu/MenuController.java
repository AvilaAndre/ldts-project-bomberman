package Menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.Thread.sleep;

public class MenuController {

    private Screen screen;
    private TextGraphics graphics;
    private MenuModel model;
    private MenuView view;
    private int deltaTime = 0;
    private int framesPerSecond = 15;

    public MenuController(Screen screen_, int width_, int height_) throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        this.screen = screen_;
        this.graphics = screen_.newTextGraphics();
        this.model = new MenuModel(screen_, width_, height_);
        this.view = new MenuView(width_, height_, this.model);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#f7ffc9"));
        graphics.fillRectangle(new TerminalPosition(0, height_), new TerminalSize(width_, height_+2), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#960000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, height_), "DELTA:" + deltaTime + "s");

        run();
    }

    private void updateView() throws IOException {
        screen.clear();
        switch (model.getState()) {
            case MAIN_MENU: {
                view.drawMainMenu(graphics, model.getMenuOption(), model.getMenuState(), model.getPlayers());
                break;
            }
            case GAME: {
                model.game.draw(graphics);
                break;
            }
        }
        view.drawDebugDeltaTime(graphics, framesPerSecond);
        screen.refresh();
    }

    private boolean getInput() throws IOException {
        if (model.getState() == MenuModel.STATE.GAME) {
            model.game.getInput();
            return true;
        }
        KeyStroke key = screen.pollInput();
        if (key == null) return true;
        if (key.getKeyType() == KeyType.Character) {
            System.out.println(key.getCharacter());
        }
        if (model.getState() == MenuModel.STATE.MAIN_MENU) {
            switch (key.getKeyType()) {
                case ArrowUp:
                    model.mainMenuOptUp();
                    break;
                case ArrowDown:
                    model.mainMenuOptDown();
                    break;
                case ArrowLeft:
                    model.playerPreviousColor(1);
                    break;
                case ArrowRight:
                    model.playerNextColor(1);
                    break;
                case Enter:
                    model.mainMenuSelect();
                    break;
                case EOF:
                case Escape:
                    return false;
                case Character: {
                    switch (key.getCharacter()) {
                        case '1':
                            model.playerJoin(1);
                            break;
                        case '2':
                            model.playerJoin(2);
                            break;
                        case '3':
                            model.playerJoin(3);
                            break;
                        case '4':
                            model.playerJoin(4);
                            break;
                        case 'a':
                            model.playerPreviousColor(2);
                            break;
                        case 'f':
                            model.playerPreviousColor(3);
                            break;
                        case 'j':
                            model.playerPreviousColor(4);
                            break;
                        case 'd':
                            model.playerNextColor(2);
                            break;
                        case 'h':
                            model.playerNextColor(3);
                            break;
                        case 'l':
                            model.playerNextColor(4);
                            break;

                    }
                    break;
                }
                default:
                    System.out.println("Not an option");
            }
        }
        return key.getKeyType() != KeyType.EOF;
    }


    private void run() throws IOException, InterruptedException {
        long timePerFrame = 1000/framesPerSecond;
        long startTime = 0;
        long endTime = 0;
        do {
            startTime = System.currentTimeMillis();
            updateView();
            endTime = System.currentTimeMillis();
            if (endTime-startTime < timePerFrame)
                Thread.sleep(timePerFrame - (endTime - startTime));
            //Input
        } while (getInput());
    }
}
