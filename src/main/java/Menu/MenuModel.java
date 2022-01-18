package Menu;

import Audio.AudioPlayer;
import Game.GameController;
import com.googlecode.lanterna.screen.Screen;

import java.util.Objects;

public class MenuModel {
    private int width;
    private int height;
    private Screen screen;

    public enum STATE {MAIN_MENU, GAME, END}
    private STATE state = STATE.MAIN_MENU;
    AudioPlayer MenuPlayer = new AudioPlayer();

    //players
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;

    private String[] playerColors = {"#58b06c", "#1c5888", "#a03c50", "#dbcd0f", "#db7b0f"};

    //game
    public GameController game;


    //MAIN_MENU
    private int mainMenuOpt = 0;
    private boolean mainMenuState = false;

    public MenuModel(Screen screen_, int width_, int height_) {
        this.screen = screen_;
        this.width = width_;
        this.height = height_;
        MenuPlayer.startMenuMusic();
    }

    public STATE getState() {
        return this.state;
    }

    public void setState(STATE state_){this.state = state_;}


    public int getMenuOption() {
        return mainMenuOpt;
    }

    public boolean getMenuState() {
        return mainMenuState;
    }

    public void setMenuState(){
        mainMenuState = false;
        if (mainMenuOpt == 1 && playersNum() > 1) {
            this.state = STATE.GAME;
            this.game = new GameController(this.screen, this.width, this.height, this.playerOne, this.playerTwo, this.playerThree, this.playerFour);
            MenuPlayer.stopMenuMusic();
        }
    }

    private int playersNum() {
        int res = 0;
        if (playerOne != null)
            res++;
        if (playerTwo != null)
            res++;
        if (playerThree != null)
            res++;
        if (playerFour != null)
            res++;
        return res;
    }


    public void mainMenuOptUp() {
        if (this.mainMenuOpt > 0 && !mainMenuState)
            this.mainMenuOpt--;
    }

    public void mainMenuOptDown() {
        if (this.mainMenuOpt < 2 && !mainMenuState)
            this.mainMenuOpt++;
    }

    public void mainMenuSelect(){
        if (this.mainMenuOpt == 1)
            mainMenuState = true;
    }

    private boolean validColor(String color_) {
        return (!Objects.equals(playerOne, color_))
                && (!Objects.equals(playerTwo, color_))
                && (!Objects.equals(playerThree, color_))
                && (!Objects.equals(playerFour, color_));
    }

    private String previousColor(String playerColor_) {
        int startNum = 0;
        String res = null;
        if (playerColor_ != null) {
            res = playerColor_;
            for (int i = 0; i < playerColors.length; i++) {
                if (playerColor_.equals(playerColors[i])) {
                    startNum = i;
                    break;
                }
            }
        }
        for (int i = 1; i < playerColors.length+1; i++) {
            if (validColor(playerColors[startNum]))
                return playerColors[startNum];
            if (startNum == 0)
                startNum = playerColors.length-1;
            else
                startNum--;
        }
        return res;
    }

    private String nextColor(String playerColor_) {
        int startNum = 0;
        String res = null;
        if (playerColor_ != null) {
            res = playerColor_;
            for (int i = 0; i < playerColors.length; i++) {
                if (playerColor_.equals(playerColors[i])) {
                    startNum = i;
                    break;
                }
            }
        }
        for (int i = 1; i < playerColors.length+1; i++) {
            if (validColor(playerColors[(startNum + i) % playerColors.length]))
                return playerColors[(startNum + i) % playerColors.length];
        }
        return res;
    }

    public void playerJoin(int player_) {
        if (mainMenuState)
            return;
        switch (player_){
            case 1: {
                if (playerOne == null){
                    if (validColor(playerColors[0]))
                        playerOne = playerColors[0];
                    else
                        playerOne = nextColor(playerOne);
                } else {
                    playerOne = null;
                }
                break;
            }
            case 2: {
                if (playerTwo == null) {
                    if (validColor(playerColors[1]))
                        playerTwo = playerColors[1];
                    else
                        playerTwo = nextColor(playerTwo);
                } else {
                    playerTwo = null;
                }
                break;
            }
            case 3: {
                if (playerThree == null) {
                    if (validColor(playerColors[2]))
                        playerThree = playerColors[2];
                    else
                        playerThree = nextColor(playerThree);
                } else {
                    playerThree = null;
                }
                break;
            }
            case 4: {
                if (playerFour == null) {
                    if (validColor(playerColors[3]))
                        playerFour = playerColors[3];
                    else
                        playerFour = nextColor(playerFour);
                } else {
                    playerFour = null;
                }
                break;
            }
        }
    }

    public void playerPreviousColor(int player_) {
        if (mainMenuState)
            return;
        switch (player_){
            case 1: {
                if (playerOne != null) {
                    playerOne = previousColor(playerOne);
                }
                break;
            }
            case 2: {
                if (playerTwo != null) {
                    playerTwo = previousColor(playerTwo);
                }
                break;
            }
            case 3: {
                if (playerThree != null) {
                    playerThree = previousColor(playerThree);
                }
                break;
            }
            case 4: {
                if (playerFour != null) {
                    playerFour = previousColor(playerFour);
                }
                break;
            }
        }
    }

    public void playerNextColor(int player_) {
        if (mainMenuState)
            return;
        switch (player_){
            case 1: {
                if (playerOne != null) {
                    playerOne = nextColor(playerOne);
                }
                break;
            }
            case 2: {
                if (playerTwo != null) {
                    playerTwo = nextColor(playerTwo);
                }
                break;
            }
            case 3: {
                if (playerThree != null) {
                    playerThree = nextColor(playerThree);
                }
                break;
            }
            case 4: {
                if (playerFour != null) {
                    playerFour = nextColor(playerFour);
                }
                break;
            }
        }
    }

    public String[] getPlayers() {
        return new String[] {playerOne, playerTwo, playerThree, playerFour};
    }
}
