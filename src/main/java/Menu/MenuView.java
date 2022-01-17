package Menu;

import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import Structures.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MenuView {
    private int width;
    private int height;
    private MenuModel model;
    private String menuBackgroundColor = "#c8c8c8";
    private String bombColor = "#2e2e2e";
    private String bombWickColor = "#909090";
    private String fireRedColor = "#ff2706";
    private String fireYellowColor = "#ffee00";
    private String lettersColor = "#1c5888";
    private int mainMenuBomb = 0;
    private int bombRow = 9;
    private int optionSize = 12;


    private DrawingImage bombermanText = new DrawingImage(new DrawingBlock[] {
            //B
            new DrawingBlock(new Position(14, 6), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(14, 10), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(14, 14), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(15, 6), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(19, 7), 1, 3, lettersColor, null, ' '),
            new DrawingBlock(new Position(19, 11), 1, 3, lettersColor, null, ' '),
            //M
            new DrawingBlock(new Position(30, 6), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(36, 6), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(31, 7), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(35, 7), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(32, 8), 1, 2, lettersColor, null, ' '),
            new DrawingBlock(new Position(34, 8), 1, 2, lettersColor, null, ' '),
            new DrawingBlock(new Position(33, 10), 1, 2, lettersColor, null, ' '),
            //B
            new DrawingBlock(new Position(38, 6), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(38, 10), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(38, 14), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(39, 6), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(43, 7), 1, 3, lettersColor, null, ' '),
            new DrawingBlock(new Position(43, 11), 1, 3, lettersColor, null, ' '),
            //E
            new DrawingBlock(new Position(45, 6), 6, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(45, 10), 6, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(45, 14), 6, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(46, 7), 1, 7, lettersColor, null, ' '),
            //R
            new DrawingBlock(new Position(52, 6), 6, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(52, 10), 6, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(53, 7), 1, 8, lettersColor, null, ' '),
            new DrawingBlock(new Position(58, 7), 1, 3, lettersColor, null, ' '),
            new DrawingBlock(new Position(54, 10), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(55, 11), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(56, 12), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(57, 13), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(58, 14), 1, 1, lettersColor, null, ' '),
            //M
            new DrawingBlock(new Position(60, 6), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(66, 6), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(61, 7), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(65, 7), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(62, 8), 1, 2, lettersColor, null, ' '),
            new DrawingBlock(new Position(64, 8), 1, 2, lettersColor, null, ' '),
            new DrawingBlock(new Position(63, 10), 1, 2, lettersColor, null, ' '),
            //A
            new DrawingBlock(new Position(68, 10), 7, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(70, 6), 3, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(69, 7), 1, 8, lettersColor, null, ' '),
            new DrawingBlock(new Position(73, 7), 1, 8, lettersColor, null, ' '),
            //N
            new DrawingBlock(new Position(76, 6), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(79, 6), 1, 7, lettersColor, null, ' '),
            new DrawingBlock(new Position(82, 7), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(77, 7), 2, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(80, 8), 2, 1, lettersColor, null, ' ')
    });

    private DrawingImage playText = new DrawingImage(new DrawingBlock[] {
            //P
            new DrawingBlock(new Position(30, 18), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(30, 23), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(31, 18), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(35, 19), 1, 4, lettersColor, null, ' '),
            //L
            new DrawingBlock(new Position(36, 26), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(37, 18), 1, 8, lettersColor, null, ' '),
            //A
            new DrawingBlock(new Position(41, 23), 7, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(43, 18), 3, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(42, 19), 1, 8, lettersColor, null, ' '),
            new DrawingBlock(new Position(46, 19), 1, 8, lettersColor, null, ' '),
            //Y
            new DrawingBlock(new Position(49, 19), 1, 4, lettersColor, null, ' '),
            new DrawingBlock(new Position(53, 19), 1, 4, lettersColor, null, ' '),
            new DrawingBlock(new Position(50, 23), 3, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(51, 24), 1, 3, lettersColor, null, ' '),
            new DrawingBlock(new Position(48, 18), 1, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(54, 18), 1, 1, lettersColor, null, ' ')
    });

    MenuView(int width_, int height_, MenuModel model_){
        this.width = width_;
        this.height = height_;
        this.model = model_;
    }

    private void drawPlayerInMenu(TextGraphics graphics_, String player_, int playerNum){
        if (player_ != null) {
            graphics_.setBackgroundColor(TextColor.Factory.fromString(player_));
            graphics_.fillRectangle(new TerminalPosition(21 + 17*playerNum, 49), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(20 + 17*playerNum, 50), new TerminalSize(7, 2), ' ');
            graphics_.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
            graphics_.fillRectangle(new TerminalPosition(21 + 17*playerNum, 51), new TerminalSize(2, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(24 + 17*playerNum, 51), new TerminalSize(2, 1), ' ');
            graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics_.fillRectangle(new TerminalPosition(((playerNum > 1)? 21 : 22) + 17*playerNum, 51), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(((playerNum > 1)? 24 : 25) + 17*playerNum, 51), new TerminalSize(1, 1), ' ');
            graphics_.setBackgroundColor(TextColor.Factory.fromString(menuBackgroundColor));
            graphics_.setForegroundColor(TextColor.Factory.fromString("#006414"));
            graphics_.enableModifiers(SGR.BOLD);
            switch (playerNum) {
                case 0: {
                    graphics_.putString(new TerminalPosition(20, 45), "^");
                    graphics_.putString(new TerminalPosition(19, 46), "<");
                    graphics_.putString(new TerminalPosition(20, 46), "v");
                    graphics_.putString(new TerminalPosition(21, 46), ">");
                    graphics_.putString(new TerminalPosition(23, 46), "ENTER");
                    break;
                }
                case 1: {
                    graphics_.putString(new TerminalPosition(39, 45), "W");
                    graphics_.putString(new TerminalPosition(38, 46), "A");
                    graphics_.putString(new TerminalPosition(39, 46), "S");
                    graphics_.putString(new TerminalPosition(40, 46), "D");
                    graphics_.putString(new TerminalPosition(42, 46), "C");
                    break;
                }
                case 2: {
                    graphics_.putString(new TerminalPosition(56, 45), "T");
                    graphics_.putString(new TerminalPosition(55, 46), "F");
                    graphics_.putString(new TerminalPosition(56, 46), "G");
                    graphics_.putString(new TerminalPosition(57, 46), "H");
                    graphics_.putString(new TerminalPosition(59, 46), "B");
                    break;
                }
                case 3: {
                    graphics_.putString(new TerminalPosition(73, 45), "I");
                    graphics_.putString(new TerminalPosition(72, 46), "J");
                    graphics_.putString(new TerminalPosition(73, 46), "K");
                    graphics_.putString(new TerminalPosition(74, 46), "L");
                    graphics_.putString(new TerminalPosition(76, 46), ";");
                    break;
                }
                default:
                    break;
            }
        } else {
            graphics_.setBackgroundColor(TextColor.Factory.fromString(menuBackgroundColor));
            graphics_.setForegroundColor(TextColor.Factory.fromString("#006414"));
            graphics_.enableModifiers(SGR.BOLD);
            switch (playerNum) {
                case 0: {
                    graphics_.putString(new TerminalPosition(18, 45), "PLAYER ONE");
                    graphics_.putString(new TerminalPosition(19, 46), "PRESS 1");
                    break;
                }
                case 1: {
                    graphics_.putString(new TerminalPosition(35, 45), "PLAYER TWO");
                    graphics_.putString(new TerminalPosition(36, 46), "PRESS 2");
                    break;
                }
                case 2: {
                    graphics_.putString(new TerminalPosition(51, 45), "PLAYER THREE");
                    graphics_.putString(new TerminalPosition(54, 46), "PRESS 3");
                    break;
                }
                case 3: {
                    graphics_.putString(new TerminalPosition(69, 45), "PLAYER FOUR");
                    graphics_.putString(new TerminalPosition(71, 46), "PRESS 4");
                    break;
                }
                default:
                    break;
            }
        }
    }

    private void drawBomb(TextGraphics graphics_, int option_) {
        graphics_.setBackgroundColor(TextColor.Factory.fromString(bombColor));
        graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow), new TerminalSize(4, 1), ' ');
        graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow + 5), new TerminalSize(4, 1), ' ');
        graphics_.fillRectangle(new TerminalPosition(21, option_ * optionSize + bombRow + 1), new TerminalSize(6, 4), ' ');
    }

    public void drawMainMenu(TextGraphics graphics_,  int option_, boolean optionSelected, String[] players_) {
        if (optionSelected && mainMenuBomb < 100)
            mainMenuBomb = 200;
        else if (!optionSelected && mainMenuBomb > 100)
            mainMenuBomb = 0;
        mainMenuBomb++;
        //BACKGROUND DRAW
        graphics_.setBackgroundColor(TextColor.Factory.fromString(menuBackgroundColor));
        graphics_.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        //DRAWING SHADOW
        if (mainMenuBomb < 234) {
            graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            //BOMBERMAN
            // B
            graphics_.fillRectangle(new TerminalPosition(13, 6- option_), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(13, 10- option_), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(13, 14- option_), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(14, 6- option_), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(18, 7- option_), new TerminalSize(1, 3), ' ');
            graphics_.fillRectangle(new TerminalPosition(18, 11- option_), new TerminalSize(1, 3), ' ');
            // M
            graphics_.fillRectangle(new TerminalPosition(31, 6- option_), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(37, 6- option_), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(32, 7- option_), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(36, 7- option_), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(33, 8- option_), new TerminalSize(1, 2), ' ');
            graphics_.fillRectangle(new TerminalPosition(35, 8- option_), new TerminalSize(1, 2), ' ');
            graphics_.fillRectangle(new TerminalPosition(34, 10- option_), new TerminalSize(1, 2), ' ');
            // B
            graphics_.fillRectangle(new TerminalPosition(39, 6- option_), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(39, 10- option_), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(39, 14- option_), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(40, 6- option_), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(44, 7- option_), new TerminalSize(1, 3), ' ');
            graphics_.fillRectangle(new TerminalPosition(44, 11- option_), new TerminalSize(1, 3), ' ');
            // E
            graphics_.fillRectangle(new TerminalPosition(46, 6- option_), new TerminalSize(6, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(46, 10- option_), new TerminalSize(6, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(46, 14- option_), new TerminalSize(6, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(47, 7- option_), new TerminalSize(1, 7), ' ');
            // R
            graphics_.fillRectangle(new TerminalPosition(53, 6- option_), new TerminalSize(6, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(53, 10- option_), new TerminalSize(6, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(54, 7- option_), new TerminalSize(1, 8), ' ');
            graphics_.fillRectangle(new TerminalPosition(59, 7- option_), new TerminalSize(1, 3), ' ');
            graphics_.fillRectangle(new TerminalPosition(55, 10- option_), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(56, 11- option_), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(57, 12- option_), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(58, 13- option_), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(59, 14- option_), new TerminalSize(1, 1), ' ');
            // M
            graphics_.fillRectangle(new TerminalPosition(61, 6- option_), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(67, 6- option_), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(62, 7- option_), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(66, 7- option_), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(63, 8- option_), new TerminalSize(1, 2), ' ');
            graphics_.fillRectangle(new TerminalPosition(65, 8- option_), new TerminalSize(1, 2), ' ');
            graphics_.fillRectangle(new TerminalPosition(64, 10- option_), new TerminalSize(1, 2), ' ');
            // A
            graphics_.fillRectangle(new TerminalPosition(69, 10- option_), new TerminalSize(7, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(71, 6- option_), new TerminalSize(3, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(70, 7- option_), new TerminalSize(1, 8), ' ');
            graphics_.fillRectangle(new TerminalPosition(74, 7- option_), new TerminalSize(1, 8), ' ');
            // N
            graphics_.fillRectangle(new TerminalPosition(77, 6- option_), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(80, 7- option_), new TerminalSize(1, 7), ' ');
            graphics_.fillRectangle(new TerminalPosition(83, 6- option_), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(78, 6- option_), new TerminalSize(2, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(81, 14- option_), new TerminalSize(2, 1), ' ');
            //PLAY
            // P
            graphics_.fillRectangle(new TerminalPosition(31, 18- option_ + 1), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(31, 23- option_ + 1), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(32, 18- option_ + 1), new TerminalSize(1, 9), ' ');
            graphics_.fillRectangle(new TerminalPosition(36, 19- option_ + 1), new TerminalSize(1, 4), ' ');
            //L
            graphics_.fillRectangle(new TerminalPosition(37, 26- option_ + 1), new TerminalSize(5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(38, 18- option_ + 1), new TerminalSize(1, 8), ' ');
            // A
            graphics_.fillRectangle(new TerminalPosition(42, 23- option_ + 1), new TerminalSize(7, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(44, 18- option_ + 1), new TerminalSize(3, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(43, 19- option_ + 1), new TerminalSize(1, 8), ' ');
            graphics_.fillRectangle(new TerminalPosition(47, 19- option_ + 1), new TerminalSize(1, 8), ' ');
            // Y
            graphics_.fillRectangle(new TerminalPosition(50, 19- option_ + 1), new TerminalSize(1, 4), ' ');
            graphics_.fillRectangle(new TerminalPosition(54, 19- option_ + 1), new TerminalSize(1, 4), ' ');
            graphics_.fillRectangle(new TerminalPosition(51, 23- option_ + 1), new TerminalSize(3, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(52, 24- option_ + 1), new TerminalSize(1, 3), ' ');
            graphics_.fillRectangle(new TerminalPosition(49, 18- option_ + 1), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(55, 18- option_ + 1), new TerminalSize(1, 1), ' ');
        }
        //DRAWING BOMBERMAN
        bombermanText.draw(graphics_, new Position(0,0), false);
        playText.draw(graphics_, new Position(0,0), false);

        drawPlayerInMenu(graphics_, players_[0], 0);
        drawPlayerInMenu(graphics_, players_[1], 1);
        drawPlayerInMenu(graphics_, players_[2], 2);
        drawPlayerInMenu(graphics_, players_[3], 3);
        if (mainMenuBomb < 232) {
            //BOMB SHADOW
            if (mainMenuBomb < 224) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                graphics_.fillRectangle(new TerminalPosition(21, option_ * optionSize + bombRow), new TerminalSize(4, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(21, option_ * optionSize + bombRow + 5), new TerminalSize(4, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(20, option_ * optionSize + bombRow + 1), new TerminalSize(6, 4), ' ');
            }
            drawBomb(graphics_, option_);
        }
        if (!optionSelected) {
            graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
            graphics_.setBackgroundColor(TextColor.Factory.fromString(bombWickColor));
            graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(24, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
            if (mainMenuBomb < 5) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireRedColor));
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireYellowColor));
                graphics_.fillRectangle(new TerminalPosition(27, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(28, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
            } else if (mainMenuBomb < 10) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireRedColor));
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 2), new TerminalSize(1, 2), ' ');
                graphics_.fillRectangle(new TerminalPosition(27, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireYellowColor));
                graphics_.fillRectangle(new TerminalPosition(27, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
            } else
                mainMenuBomb = 0;
        }
        else {
            if (mainMenuBomb < 206) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(bombWickColor));
                graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(24, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireRedColor));
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireYellowColor));
                graphics_.fillRectangle(new TerminalPosition(27, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(28, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
            } else if (mainMenuBomb < 212) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 2), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(bombWickColor));
                graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(24, option_ * optionSize + bombRow - 2), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireRedColor));
                graphics_.fillRectangle(new TerminalPosition(25, option_ * optionSize + bombRow - 2), new TerminalSize(1, 2), ' ');
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireYellowColor));
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
            } else if (mainMenuBomb < 218) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(bombWickColor));
                graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireRedColor));
                graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(24, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireYellowColor));
                graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(25, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
            } else if (mainMenuBomb < 224) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireRedColor));
                graphics_.fillRectangle(new TerminalPosition(24, option_ * optionSize + bombRow - 2), new TerminalSize(1, 2), ' ');
                graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 1), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireYellowColor));
                graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow - 2), new TerminalSize(1, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(25, option_ * optionSize + bombRow - 2), new TerminalSize(1, 1), ' ');
            }else if (mainMenuBomb < 230) {
            } else if (mainMenuBomb < 234) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString(bombColor));
                graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow - 1), new TerminalSize(4, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(21, option_ * optionSize + bombRow), new TerminalSize(6, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(21, option_ * optionSize + bombRow + 5), new TerminalSize(6, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow + 6), new TerminalSize(4, 1), ' ');
                graphics_.fillRectangle(new TerminalPosition(20, option_ * optionSize + bombRow + 1), new TerminalSize(8, 4), ' ');
            } else if (mainMenuBomb < 236) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
                graphics_.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
            } else if (mainMenuBomb < 260) {
                switch (option_){
                    case 1: {
                        graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                        graphics_.fillRectangle(new TerminalPosition(30, 18), new TerminalSize(3, 1), ' ');
                        graphics_.fillRectangle(new TerminalPosition(30, 23), new TerminalSize(4, 1), ' ');
                        graphics_.fillRectangle(new TerminalPosition(31, 18), new TerminalSize(1, 9), ' ');
                        graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow + 1), new TerminalSize(2, 1), ' ');
                        graphics_.fillRectangle(new TerminalPosition(23, option_ * optionSize + bombRow + 4), new TerminalSize(2, 1), ' ');
                        graphics_.fillRectangle(new TerminalPosition(22, option_ * optionSize + bombRow + 2), new TerminalSize(4, 2), ' ');
                        break;
                    }
                    case 2: {
                        break;
                    }
                    default:
                        break;
                }

            } else {
                mainMenuBomb = 200;
                model.setMenuState();
            }
        }
    }

    public void drawDebugDeltaTime(TextGraphics graphics_, int deltaTime) {
        graphics_.setBackgroundColor(TextColor.Factory.fromString(menuBackgroundColor));
        graphics_.setForegroundColor(TextColor.Factory.fromString("#006414"));
        graphics_.enableModifiers(SGR.BOLD);
        graphics_.putString(new TerminalPosition(1, 0), " " + deltaTime + "FPS");
    }
}
