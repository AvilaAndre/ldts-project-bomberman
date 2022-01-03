package Game;

import com.googlecode.lanterna.screen.Screen;

public class GameController {
    Screen screen;
    GameModel model;
    GameView view;

    public GameController(Screen screen_, int width_, int height_, String playerOne_, String playerTwo_, String playerThree_, String playerFour_) {
        this.model = new GameModel(playerOne_, playerTwo_, playerThree_, playerFour_);
        this.view = new GameView(width_, height_);
        this.screen = screen_;
    }
}
