package Menu;

import Audio.AudioPlayer;
import AudioSettings.AudioSettings;
import DrawingMethods.DrawingAnimation;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingText;
import Structures.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MenuView {
    private final int width;
    private final int height;
    private final MenuModel model;
    private final String menuBackgroundColor = "#c8c8c8";
    private final String bombColor = "#2e2e2e";
    private final String lettersColor = "#1c5888";
    private int mainMenuBomb = 0;
    private final int bombRow = 9;
    private final int optionSize = 12;


    private final DrawingAnimation invincibleAnim = new DrawingAnimation(new DrawingImage[]{
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(2, 39), 1, 1, menuBackgroundColor, "#F00000", '=')
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(2, 39), 1, 1, menuBackgroundColor, "#F0F000", '='),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(2, 39), 1, 1, menuBackgroundColor, "#00F000", '='),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(2, 39), 1, 1, menuBackgroundColor, "#00F0F0", '='),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(2, 39), 1, 1, menuBackgroundColor, "#00000F", '='),
            }),
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(2, 39), 1, 1, menuBackgroundColor, "#F000F0", '='),
            })
    }, new int[]{2, 2}, false);


    private final DrawingImage bombermanText = new DrawingImage(new DrawingBlock[] {
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
            new DrawingBlock(new Position(79, 7), 1, 7, lettersColor, null, ' '),
            new DrawingBlock(new Position(82, 6), 1, 9, lettersColor, null, ' '),
            new DrawingBlock(new Position(77, 6), 2, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(80, 14), 2, 1, lettersColor, null, ' ')
    });

    private final DrawingImage playText = new DrawingImage(new DrawingBlock[] {
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

    private final DrawingImage optionsText = new DrawingImage(new DrawingBlock[] {
            //O
            new DrawingBlock(new Position(31, 30), 4, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(30, 31), 1, 7, lettersColor, null, ' '),
            new DrawingBlock(new Position(31, 38), 4, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(35, 31), 1, 7, lettersColor, null, ' '),
            //P
            new DrawingBlock(new Position(37, 30), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(38, 31), 1, 8, lettersColor, null, ' '),
            new DrawingBlock(new Position(42, 31), 1, 4, lettersColor, null, ' '),
            new DrawingBlock(new Position(37, 35), 5, 1, lettersColor, null, ' '),
            //T
            new DrawingBlock(new Position(44, 30), 7, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(47, 31), 1, 8, lettersColor, null, ' '),
            //I
            new DrawingBlock(new Position(52, 30), 7, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(55, 31), 1, 7, lettersColor, null, ' '),
            new DrawingBlock(new Position(52, 38), 7, 1, lettersColor, null, ' '),
            //O
            new DrawingBlock(new Position(61, 30), 4, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(60, 31), 1, 7, lettersColor, null, ' '),
            new DrawingBlock(new Position(61, 38), 4, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(65, 31), 1, 7, lettersColor, null, ' '),
            //N
            new DrawingBlock(new Position(67, 30), 3, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(67, 31), 1, 8, lettersColor, null, ' '),
            new DrawingBlock(new Position(70, 31), 1, 7, lettersColor, null, ' '),
            new DrawingBlock(new Position(71, 38), 3, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(74, 30), 1, 8, lettersColor, null, ' '),
            //S
            new DrawingBlock(new Position(77, 30), 5, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(76, 31), 1, 3, lettersColor, null, ' '),
            new DrawingBlock(new Position(77, 34), 4, 1, lettersColor, null, ' '),
            new DrawingBlock(new Position(81, 35), 1, 3, lettersColor, null, ' '),
            new DrawingBlock(new Position(76, 38), 5, 1, lettersColor, null, ' ')
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
            //OPTIONS
            //O
            graphics_.fillRectangle(new TerminalPosition(32, 30- option_ + 2), new TerminalSize( 4, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(31, 31- option_ + 2), new TerminalSize( 1, 7), ' ');
            graphics_.fillRectangle(new TerminalPosition(32, 38- option_ + 2), new TerminalSize( 4, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(36, 31- option_ + 2), new TerminalSize( 1, 7), ' ');
            //P
            graphics_.fillRectangle(new TerminalPosition(38, 30- option_ + 2), new TerminalSize( 5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(39, 31- option_ + 2), new TerminalSize( 1, 8), ' ');
            graphics_.fillRectangle(new TerminalPosition(43, 31- option_ + 2), new TerminalSize( 1, 4), ' ');
            graphics_.fillRectangle(new TerminalPosition(38, 35- option_ + 2), new TerminalSize( 5, 1), ' ');
            //T
            graphics_.fillRectangle(new TerminalPosition(45, 30- option_ + 2), new TerminalSize( 7, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(48, 31- option_ + 2), new TerminalSize( 1, 8), ' ');
            //I
            graphics_.fillRectangle(new TerminalPosition(53, 30- option_ + 2), new TerminalSize( 7, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(56, 31- option_ + 2), new TerminalSize( 1, 7), ' ');
            graphics_.fillRectangle(new TerminalPosition(53, 38- option_ + 2), new TerminalSize( 7, 1), ' ');
            //O
            graphics_.fillRectangle(new TerminalPosition(62, 30- option_ + 2), new TerminalSize( 4, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(61, 31- option_ + 2), new TerminalSize( 1, 7), ' ');
            graphics_.fillRectangle(new TerminalPosition(62, 38- option_ + 2), new TerminalSize( 4, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(66, 31- option_ + 2), new TerminalSize( 1, 7), ' ');
            //N
            graphics_.fillRectangle(new TerminalPosition(68, 30- option_ + 2), new TerminalSize( 3, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(68, 31- option_ + 2), new TerminalSize( 1, 8), ' ');
            graphics_.fillRectangle(new TerminalPosition(71, 31- option_ + 2), new TerminalSize( 1, 7), ' ');
            graphics_.fillRectangle(new TerminalPosition(72, 38- option_ + 2), new TerminalSize( 3, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(75, 30- option_ + 2), new TerminalSize( 1, 8), ' ');
            //S
            graphics_.fillRectangle(new TerminalPosition(78, 30- option_ + 2), new TerminalSize( 5, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(77, 31- option_ + 2), new TerminalSize( 1, 3), ' ');
            graphics_.fillRectangle(new TerminalPosition(78, 34- option_ + 2), new TerminalSize( 4, 1), ' ');
            graphics_.fillRectangle(new TerminalPosition(82, 35- option_ + 2), new TerminalSize( 1, 3), ' ');
            graphics_.fillRectangle(new TerminalPosition(77, 38- option_ + 2), new TerminalSize( 5, 1), ' ');
        }
        //DRAWING BOMBERMAN
        bombermanText.draw(graphics_, new Position(0,0), false);
        playText.draw(graphics_, new Position(0,0), false);
        optionsText.draw(graphics_, new Position(0,0), false);

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
        String bombWickColor = "#909090";
        String fireRedColor = "#ff2706";
        String fireYellowColor = "#ffee00";
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
            } else if (mainMenuBomb < 9) {
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireRedColor));
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 2), new TerminalSize(1, 2), ' ');
                graphics_.fillRectangle(new TerminalPosition(27, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireYellowColor));
                graphics_.fillRectangle(new TerminalPosition(27, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
            } else {
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireRedColor));
                graphics_.fillRectangle(new TerminalPosition(26, option_ * optionSize + bombRow - 2), new TerminalSize(1, 2), ' ');
                graphics_.fillRectangle(new TerminalPosition(27, option_ * optionSize + bombRow - 3), new TerminalSize(1, 1), ' ');
                graphics_.setBackgroundColor(TextColor.Factory.fromString(fireYellowColor));
                graphics_.fillRectangle(new TerminalPosition(27, option_ * optionSize + bombRow - 2), new TerminalSize(2, 1), ' ');
                mainMenuBomb = 0;
            }
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
            } else if (mainMenuBomb < 230) {
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
                        graphics_.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                        graphics_.fillRectangle(new TerminalPosition(31, 30- option_ + 2), new TerminalSize( 2, 1), ' ');
                        graphics_.fillRectangle(new TerminalPosition(30, 31- option_ + 2), new TerminalSize( 1, 7), ' ');
                        graphics_.fillRectangle(new TerminalPosition(31, 38- option_ + 2), new TerminalSize( 3, 1), ' ');
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


    public void drawOptions(TextGraphics graphics_, AudioSettings settings_, AudioPlayer player_) {
        graphics_.setBackgroundColor(TextColor.Factory.fromString(menuBackgroundColor));
        graphics_.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        optionsText.draw(graphics_, new Position(-3, -8), false);
        settings_.drawAudioSlider(graphics_,new Position(66, 26), player_, menuBackgroundColor);
        //Rules
        new DrawingText(new Position(10, 22), "Welcome to Bomberman", null, "#000000").draw(graphics_);
        new DrawingText(new Position(4, 24), "In this adapted version of the original game you can", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 25), "go back and blast your friends while having a laugh", null, "#000000").draw(graphics_);
        new DrawingText(new Position(4, 26), "The rules are simple", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 27), "last to stay alive in the game against a maximum", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 28), "of three players while picking up a various range", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 29), "of power ups scattered along the walls wins.", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 30), "Be careful where you put your bombs though...", null, "#000000").draw(graphics_);
        new DrawingText(new Position(4, 31), "they certainly hurt for everybody", null, "#500000").draw(graphics_);
        new DrawingText(new Position(2, 33), "List of power ups", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 34), "/ increases your concurrent bomb limit", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 35), ": increases the explosion range by one", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 36), "- allows you to push bombs", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 37), "'", null, "#F0F000").draw(graphics_);
        new DrawingText(new Position(4, 37), "gives you a shield that protects you once", null, "#000000").draw(graphics_);
        new DrawingText(new Position(2, 38), "\"", null, "#F00000").draw(graphics_);
        new DrawingText(new Position(4, 38), "gives you an additional life", null, "#000000").draw(graphics_);
        invincibleAnim.draw(graphics_, new Position(0,0), false);
        new DrawingText(new Position(4, 39), "you become invincible", null, "#000000").draw(graphics_);
    }

    public void drawDebugDeltaTime(TextGraphics graphics_, int deltaTime) {
        graphics_.setBackgroundColor(TextColor.Factory.fromString(menuBackgroundColor));
        graphics_.setForegroundColor(TextColor.Factory.fromString("#006414"));
        graphics_.enableModifiers(SGR.BOLD);
        graphics_.putString(new TerminalPosition(1, 0), " " + deltaTime + "FPS");
    }
}
