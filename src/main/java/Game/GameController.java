package Game;

import Audio.AudioPlayer;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GameController {
    Screen screen;
    GameModel model;
    GameView view;

    AudioPlayer audio = AudioPlayer.getInstance();


    public GameController(Screen screen_, int width_, int height_, String playerOne_, String playerTwo_, String playerThree_, String playerFour_) {
        this.model = new GameModel(playerOne_, playerTwo_, playerThree_, playerFour_);
        this.view = new GameView(width_, height_);
        this.screen = screen_;
        audio.startGameMusic();
    }

    public void getInput() throws IOException {
        processInput(screen.pollInput());
        processInput(screen.pollInput());
        processInput(screen.pollInput());
        processInput(screen.pollInput());
    }

    private void processInput(KeyStroke key) {
        if (key == null) return;
        switch (key.getKeyType()) {
            case ArrowUp:
                model.playerUp(1);
                break;
            case ArrowDown:
                model.playerDown(1);
                break;
            case ArrowLeft:
                model.playerLeft(1);
                break;
            case ArrowRight:
                model.playerRight(1);
                break;
            case Enter:
                model.playerAction(1);
                break;
            case EOF:
            case Character: {
                switch (key.getCharacter()) {
                    case 'W':
                    case 'w':
                        model.playerUp(2);
                        break;
                    case 'S':
                    case 's':
                        model.playerDown(2);
                        break;
                    case 'A':
                    case 'a':
                        model.playerLeft(2);
                        break;
                    case 'D':
                    case 'd':
                        model.playerRight(2);
                        break;
                    case 'T':
                    case 't':
                        model.playerUp(3);
                        break;
                    case 'G':
                    case 'g':
                        model.playerDown(3);
                        break;
                    case 'F':
                    case 'f':
                        model.playerLeft(3);
                        break;
                    case 'H':
                    case 'h':
                        model.playerRight(3);
                        break;
                    case 'I':
                    case 'i':
                        model.playerUp(4);
                        break;
                    case 'K':
                    case 'k':
                        model.playerDown(4);
                        break;
                    case 'J':
                    case 'j':
                        model.playerLeft(4);
                        break;
                    case 'L':
                    case 'l':
                        model.playerRight(4);
                        break;
                    case 'C':
                    case 'c':
                        model.playerAction(2);
                        break;
                    case 'B':
                    case 'b':
                        model.playerAction(3);
                        break;
                    case ',':
                    case ';':
                        model.playerAction(4);
                        break;
                }
                break;
            }
            default:
                System.out.println("Not an option");
        }
    }

    public void draw(TextGraphics graphics) {
        model.gameBoard.loop();
        view.draw(graphics, model.gameBoard.getDrawQueue(), model.playerOne, model.playerTwo, model.playerThree, model.playerFour);
    }
}
