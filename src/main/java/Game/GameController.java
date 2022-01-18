package Game;

import Audio.AudioPlayer;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GameController {
    Screen screen;
    GameModel model;
    GameView view;
    AudioPlayer audio = AudioPlayer.getInstance();
    private boolean paused = false;


    public GameController(Screen screen_, int width_, int height_, String playerOne_, String playerTwo_, String playerThree_, String playerFour_) {
        this.model = new GameModel(playerOne_, playerTwo_, playerThree_, playerFour_);
        this.view = new GameView(width_, height_);
        this.screen = screen_;
        audio.startGameMusic();
    }

    public boolean getInput() throws IOException {
        if (!processInput(screen.pollInput()))
            return false;
        if (!processInput(screen.pollInput()))
            return false;
        if (!processInput(screen.pollInput()))
            return false;
        return processInput(screen.pollInput());
    }

    private boolean processInput(KeyStroke key) {
        if (paused) {
            if (key == null)
                return true;
            if (key.getKeyType() == KeyType.EOF)
                return false;
            if (key.getKeyType() == KeyType.Escape) {
                paused = false;
            }
            return true;
        }
        if (key == null) return true;
        switch (key.getKeyType()) {
            case Escape:
                paused = true;
                break;
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
                return false;
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
        return true;
    }

    public void draw(TextGraphics graphics) {
        if (!paused)
            model.gameBoard.loop();
        view.draw(graphics, model.gameBoard.getDrawQueue(), model.playerOne, model.playerTwo, model.playerThree, model.playerFour, model.getGameBoard().getEliminationsQueue(), this.paused);
    }

    public void drawWinner(TextGraphics graphics){
        view.drawWinnerLetters(graphics);
    }

    public int getPlayersAlive(){
        int count = 0;
        if (model.getPlayerOne().isAlive() ) count++;
        if (model.getPlayerTwo().isAlive() ) count++;
        if (model.getPlayerThree().isAlive() ) count++;
        if (model.getPlayerFour().isAlive() ) count++;

        return count;
    }
}
