package Game;

import BoardComponents.BoardElement;
import BoardElements.Bomberman;
import DrawingMethods.DrawingAnimation;
import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import DrawingMethods.DrawingText;
import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Objects;

public class GameView {
    int width;
    int height;
    ArrayList<DrawingAnimation> eliminationsPanel = new ArrayList<>();
    DrawingImage viewBackground;

    private final DrawingAnimation winnerLetters = new DrawingAnimation(
            new DrawingImage[] {
                    new DrawingImage(
                            new DrawingBlock[] {//26 -> 32
                                    new DrawingBlock(new Position(26, 8), 1, 8, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(32, 8), 1, 8, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(27, 16), 1, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(28, 15), 1, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(29, 14), 1, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(30, 15), 1, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(31, 16), 1, 1, "#ffd700", null, ' '),
                                    //35 -> 39
                                    new DrawingBlock(new Position(35, 8), 5, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(35, 16), 5, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(37, 8), 1, 8, "#ffd700", null, ' '),
                                    //43->48
                                    new DrawingBlock(new Position(42, 8), 1, 9, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(47, 8), 1, 9, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(43, 10), 1, 2, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(44, 11), 1, 2, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(45, 12), 1, 2, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(46, 13), 1, 2, "#ffd700", null, ' '),
                                    //51->56
                                    new DrawingBlock(new Position(50, 8), 1, 9, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(55, 8), 1, 9, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(51, 10), 1, 2, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(52, 11), 1, 2, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(53, 12), 1, 2, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(54, 13), 1, 2, "#ffd700", null, ' '),
                                    //60->
                                    new DrawingBlock(new Position(58, 8), 6, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(58, 12), 6, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(58, 16), 6, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(59, 8), 1, 8, "#ffd700", null, ' '),

                                    new DrawingBlock(new Position(66, 8), 5, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(67, 8), 1, 9, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(71, 9), 1, 2, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(70, 11), 1, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(68, 12), 2, 1, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(70, 13), 1, 2, "#ffd700", null, ' '),
                                    new DrawingBlock(new Position(71, 15), 1, 2, "#ffd700", null, ' ')
                            }

                    ),
                    new DrawingImage(
                            new DrawingBlock[] {}
                    )
            }, new int[] {30, 20}, false);

    private final DrawingAnimation pausedLetters = new DrawingAnimation(
            new DrawingImage[] {
                    new DrawingImage(
                            new DrawingBlock[] {
                                    new DrawingBlock(new Position(22, 7), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(22, 8), 8, 2, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(23, 10), 3, 2, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(27, 10), 3, 2, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(22, 12), 8, 2, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(22, 14), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(23, 15), 3, 3, "#20209c", null, ' '),
                                    //innerP
                                    new DrawingBlock(new Position(23, 8), 5, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(23, 13), 5, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(24, 9), 1, 8, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(28, 9), 1, 4, "#1c5888", null, ' '),
                                    //A
                                    new DrawingBlock(new Position(33, 7), 5, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(32, 8), 7, 2, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(32, 10), 3, 8, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(36, 10), 3, 8, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(31, 12), 9, 3, "#20209c", null, ' '),
                                    //innerA
                                    new DrawingBlock(new Position(34, 8), 3, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(33, 9), 1, 8, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(37, 9), 1, 8, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(32, 13), 7, 1, "#1c5888", null, ' '),
                                    //U
                                    new DrawingBlock(new Position(41, 7), 3, 8, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(46, 7), 3, 8, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(41, 15), 8, 2, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(42, 17), 6, 1, "#20209c", null, ' '),
                                    //innerU
                                    new DrawingBlock(new Position(42, 8), 1, 8, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(47, 8), 1, 8, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(43, 16), 4, 1, "#1c5888", null, ' '),
                                    //S
                                    new DrawingBlock(new Position(51, 7), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(50, 8), 8, 2, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(50, 10), 3, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(50, 11), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(50, 12), 8, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(51, 13), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(55, 14), 3, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(50, 15), 8, 2, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(50, 17), 7, 1, "#20209c", null, ' '),
                                    //innerS
                                    new DrawingBlock(new Position(52, 8), 5, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(51, 9), 1, 3, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(52, 12), 4, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(56, 13), 1, 4, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(51, 16), 5, 1, "#1c5888", null, ' '),
                                    //E
                                    new DrawingBlock(new Position(59, 7), 8, 3, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(60, 10), 3, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(59, 11), 7, 3, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(60, 14), 3, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(59, 15), 8, 3, "#20209c", null, ' '),
                                    //innerE
                                    new DrawingBlock(new Position(60, 8), 6, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(60, 12), 5, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(60, 16), 6, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(61, 9), 1, 7, "#1c5888", null, ' '),
                                    //D
                                    new DrawingBlock(new Position(68, 7), 6, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(68, 8), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(68, 9), 8, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(69, 10), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(69, 11), 3, 3, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(73, 11), 3, 3, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(69, 14), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(68, 15), 8, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(68, 16), 7, 1, "#20209c", null, ' '),
                                    new DrawingBlock(new Position(68, 17), 6, 1, "#20209c", null, ' '),
                                    //innerD
                                    new DrawingBlock(new Position(69, 8), 4, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(69, 16), 4, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(70, 9), 1, 7, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(73, 9), 1, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(73, 15), 1, 1, "#1c5888", null, ' '),
                                    new DrawingBlock(new Position(74, 10), 1, 5, "#1c5888", null, ' ')
                            }
                    ),
                    new DrawingImage(
                            new DrawingBlock[] {}
                    )
            }, new int[] {30, 20}, false);

    DrawingImage boardFrame = new DrawingImage(new DrawingBlock[] {
            new DrawingBlock(new Position(21, 2), 1, 48, "#000000", null, ' '),
            new DrawingBlock(new Position(76, 2), 1, 48, "#000000", null, ' '),
            new DrawingBlock(new Position(21, 1), 56, 1, "#000000", null, ' '),
            new DrawingBlock(new Position(21, 50), 56, 1, "#000000", null, ' ')
    });
    DrawingImage boardBackground = new DrawingImage(new DrawingBlock[] {
            new DrawingBlock(new Position(22, 2), 54, 48, "#999999", null, ' ')
    });

    private DrawingAnimation newEliminationPanel(Bomberman eliminator_, Bomberman eliminated_) {
        return new DrawingAnimation(new DrawingImage[] {
                new DrawingImage(new DrawingBlock[] {
                        new DrawingBlock(new Position(95, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(111, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(112, 23), 1, 9, eliminator_.getColor(), null, ' ')
                }),
                new DrawingImage(new DrawingBlock[] {
                        new DrawingBlock(new Position(92, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(92, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(108, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(92, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(92, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(109, 23), 1, 9, eliminator_.getColor(), null, ' ')
                }),
                new DrawingImage(new DrawingBlock[] {
                        new DrawingBlock(new Position(89, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(89, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(105, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(89, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(89, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(106, 23), 1, 9, eliminator_.getColor(), null, ' ')
                }),
                new DrawingImage(new DrawingBlock[] {
                        new DrawingBlock(new Position(86, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(86, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(102, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(86, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(86, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(103, 23), 1, 9, eliminator_.getColor(), null, ' ')
                }),
                new DrawingImage(new DrawingBlock[] {
                        new DrawingBlock(new Position(83, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(83, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(99, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(83, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(83, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(100, 23), 1, 9, eliminator_.getColor(), null, ' ')
                }),
                new DrawingImage(new DrawingBlock[]{
                        new DrawingBlock(new Position(79, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(96, 23), 1, 9, eliminator_.getColor(), null, ' ')
                }),
                new DrawingImage(new DrawingBlock[]{
                        new DrawingBlock(new Position(79, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(96, 23), 1, 9, eliminator_.getColor(), null, ' '),
                        //char
                        new DrawingBlock(new Position(88, 29), 5, 1, eliminated_.getColor(), null, ' ')
                }),
                new DrawingImage(new DrawingBlock[]{
                        //ELIMINATED
                        new DrawingBlock(new Position(82, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(83, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'L'),
                        new DrawingBlock(new Position(84, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(85, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'M'),
                        new DrawingBlock(new Position(86, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(87, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'N'),
                        new DrawingBlock(new Position(88, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'A'),
                        new DrawingBlock(new Position(89, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'T'),
                        new DrawingBlock(new Position(90, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(91, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'D'),

                        new DrawingBlock(new Position(79, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(96, 23), 1, 9, eliminator_.getColor(), null, ' '),
                        //char
                        new DrawingBlock(new Position(88, 28), 5, 1, eliminated_.getColor(), null, ' '),
                        new DrawingBlock(new Position(87, 29), 7, 1, eliminated_.getColor(), null, ' ')
                }),
                new DrawingImage(new DrawingBlock[]{
                        //ELIMINATED
                        new DrawingBlock(new Position(82, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(83, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'L'),
                        new DrawingBlock(new Position(84, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(85, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'M'),
                        new DrawingBlock(new Position(86, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(87, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'N'),
                        new DrawingBlock(new Position(88, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'A'),
                        new DrawingBlock(new Position(89, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'T'),
                        new DrawingBlock(new Position(90, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(91, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'D'),

                        new DrawingBlock(new Position(79, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(96, 23), 1, 9, eliminator_.getColor(), null, ' '),
                        //char
                        new DrawingBlock(new Position(88, 27), 5, 1, eliminated_.getColor(), null, ' '),
                        new DrawingBlock(new Position(87, 28), 7, 1, eliminated_.getColor(), null, ' '),
                        new DrawingBlock(new Position(87, 29), 7, 1, eliminated_.getColor(), null, ' '),
                        new DrawingBlock(new Position(88, 29), 1, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(91, 29), 1, 1, "#000000", null, ' '),
                }),
                new DrawingImage(new DrawingBlock[]{
                        //ELIMINATED
                        new DrawingBlock(new Position(82, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(83, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'L'),
                        new DrawingBlock(new Position(84, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(85, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'M'),
                        new DrawingBlock(new Position(86, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(87, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'N'),
                        new DrawingBlock(new Position(88, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'A'),
                        new DrawingBlock(new Position(89, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'T'),
                        new DrawingBlock(new Position(90, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(91, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'D'),

                        new DrawingBlock(new Position(79, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(96, 23), 1, 9, eliminator_.getColor(), null, ' '),
                        //char
                        new DrawingBlock(new Position(88, 27), 5, 1, eliminated_.getColor(), null, ' '),
                        new DrawingBlock(new Position(87, 28), 7, 1, eliminated_.getColor(), null, ' '),
                        new DrawingBlock(new Position(87, 29), 7, 1, eliminated_.getColor(), null, ' '),
                        new DrawingBlock(new Position(88, 29), 1, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(91, 29), 1, 1, "#000000", null, ' '),
                }),
                new DrawingImage(new DrawingBlock[]{
                        //ELIMINATED
                        new DrawingBlock(new Position(82, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(83, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'L'),
                        new DrawingBlock(new Position(84, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(85, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'M'),
                        new DrawingBlock(new Position(86, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(87, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'N'),
                        new DrawingBlock(new Position(88, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'A'),
                        new DrawingBlock(new Position(89, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'T'),
                        new DrawingBlock(new Position(90, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(91, 25), 1, 1, "#FFFFFF", eliminator_.getColor(), 'D'),

                        new DrawingBlock(new Position(79, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(96, 23), 1, 9, eliminator_.getColor(), null, ' '),
                        //char
                        new DrawingBlock(new Position(88, 28), 5, 1, eliminated_.getColor(), null, ' '),
                        new DrawingBlock(new Position(87, 29), 7, 1, eliminated_.getColor(), null, ' '),
                }),
                new DrawingImage(new DrawingBlock[]{
                        //ELIMINATED
                        new DrawingBlock(new Position(82, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(83, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'L'),
                        new DrawingBlock(new Position(84, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(85, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'M'),
                        new DrawingBlock(new Position(86, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'I'),
                        new DrawingBlock(new Position(87, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'N'),
                        new DrawingBlock(new Position(88, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'A'),
                        new DrawingBlock(new Position(89, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'T'),
                        new DrawingBlock(new Position(90, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'E'),
                        new DrawingBlock(new Position(91, 24), 1, 1, "#FFFFFF", eliminator_.getColor(), 'D'),

                        new DrawingBlock(new Position(79, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(96, 23), 1, 9, eliminator_.getColor(), null, ' '),
                        //char
                        new DrawingBlock(new Position(88, 29), 5, 1, eliminated_.getColor(), null, ' '),
                }),
                new DrawingImage(new DrawingBlock[]{
                        new DrawingBlock(new Position(79, 23), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 30), 17, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(95, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 24), 1, 6, "#000000", null, ' '),
                        new DrawingBlock(new Position(79, 31), 17, 1, eliminator_.getColor(), null, ' '),
                        new DrawingBlock(new Position(96, 23), 1, 9, eliminator_.getColor(), null, ' '),
                })
        }, new int[] {1, 1, 1, 1, 1, 1, 2, 2, 2, 24, 2, 2, 5}, true);
    }

    private void drawPlayerData(TextGraphics graphics_, int x, int y, Bomberman player_) {
        //frame
        new DrawingImage(new DrawingBlock[] {
                new DrawingBlock(new Position(x, y), 21, 1, "#000000", null, ' '),
                new DrawingBlock(new Position(x, y+13), 21, 1, "#000000", null, ' ')
        }).draw(graphics_, new Position(0,0), false);
        //character
        if (player_.isAlive()) {
            if (x < 50)
                new DrawingImage(new DrawingBlock[]{
                        new DrawingBlock(new Position(x + 4, y + 10), 5, 1, player_.getColor(), null, ' '),
                        new DrawingBlock(new Position(x + 3, y + 11), 7, 2, player_.getColor(), null, ' '),
                        new DrawingBlock(new Position(x + 5, y + 12), 1, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(x + 8, y + 12), 1, 1, "#000000", null, ' ')
                }).draw(graphics_, new Position(0, 0), false);
            else
                new DrawingImage(new DrawingBlock[]{
                        new DrawingBlock(new Position(x + 12, y + 10), 5, 1, player_.getColor(), null, ' '),
                        new DrawingBlock(new Position(x + 11, y + 11), 7, 2, player_.getColor(), null, ' '),
                        new DrawingBlock(new Position(x + 12, y + 12), 1, 1, "#000000", null, ' '),
                        new DrawingBlock(new Position(x + 15, y + 12), 1, 1, "#000000", null, ' ')
                }).draw(graphics_, new Position(0, 0), false);
            new DrawingText(new Position(x + 3, y + 4), "lives", "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + 3, y + 5), "bombs", "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + 3, y + 6), "blast radius", "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + 3, y + 7), "eliminations", "#FFFFFF", "#000000").draw(graphics_);
            new DrawingImage(new DrawingBlock[]{
                    new DrawingBlock(new Position(x + 2, y + 4), 1, 1, "#FFFFFF", "#F00000", '"'),
                    new DrawingBlock(new Position(x + 2, y + 5), 1, 1, "#FFFFFF", "#000000", '/'),
                    new DrawingBlock(new Position(x + 2, y + 6), 1, 1, "#FFFFFF", "#F08800", ':'),
                    new DrawingBlock(new Position(x + 2, y + 7), 1, 1, "#FFFFFF", "#444444", '\\'),
            }).draw(graphics_, new Position(0, 0), false);
            new DrawingText(new Position(x + ((player_.getLives() > 9)? 17 : 18), y + 4), String.valueOf(player_.getLives()), "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + ((player_.getBombLimit() > 9)? 17 : 18), y + 5), String.valueOf(player_.getBombLimit()), "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + ((player_.getBombRadius() > 9)? 17 : 18), y + 6), String.valueOf(player_.getBombRadius()), "#FFFFFF", "#000000").draw(graphics_);
            new DrawingText(new Position(x + ((player_.getEliminations() > 9)? 17 : 18), y + 7), String.valueOf(player_.getEliminations()), "#FFFFFF", "#000000").draw(graphics_);
            if (player_.getShield())
                new DrawingBlock(new Position(x + (x < 50? 14 : 4) , y + 10), 1, 1, "#FFFFFF", "#F0F000", '\'').draw(graphics_, new Position(0,0), false);
            if (player_.getPushTheBomb())
                new DrawingBlock(new Position(x + (x < 50? 16 : 6), y + 10), 1, 1, "#FFFFFF", "#000000", '-').draw(graphics_, new Position(0,0), false);
        }
        else {
            if (player_.getColor() != null) {
                new DrawingText(new Position(x + 8, y + 7), "dead", "#FFFFFF", player_.getColor()).draw(graphics_);
            }
        }


    }

    public GameView(int width_, int height_) {
        this.width = width_;
        this.height = height_;
        viewBackground = new DrawingImage(new DrawingBlock[] {new DrawingBlock(new Position(0,0), width, height, "#ffffff", null, ' ')});
    }

    public void draw(TextGraphics graphics_, ArrayList<BoardElement> drawQueue, Bomberman playerOne_, Bomberman playerTwo_, Bomberman playerThree_, Bomberman playerFour_, ArrayList<Pair<Bomberman, Bomberman>> eliminationsQueue, boolean paused) {
        if (eliminationsQueue.size() > 0)
            for (Pair<Bomberman, Bomberman> pair : eliminationsQueue)
                eliminationsPanel.add(newEliminationPanel(pair.getKey(), pair.getValue()));

        eliminationsPanel.removeIf(Objects::isNull);
        eliminationsPanel.removeIf(DrawingAnimation::getEnded);

        viewBackground.draw(graphics_, new Position(0,0), false);
        boardFrame.draw(graphics_, new Position(0,0), false);
        boardBackground.draw(graphics_, new Position(0,0), false);

        for (BoardElement element : drawQueue)
            element.draw(graphics_);

        drawPlayerData(graphics_, 0, 4, playerOne_);
        drawPlayerData(graphics_, 77, 4, playerTwo_);
        drawPlayerData(graphics_, 0, 34, playerThree_);
        drawPlayerData(graphics_, 77, 34, playerFour_);

        if (playerOne_.isAlive())
            playerOne_.draw(graphics_);
        if (playerTwo_.isAlive())
            playerTwo_.draw(graphics_);
        if (playerThree_.isAlive())
            playerThree_.draw(graphics_);
        if (playerFour_.isAlive())
            playerFour_.draw(graphics_);

        if (eliminationsPanel.size() > 0 && !paused)
            eliminationsPanel.get(0).draw(graphics_, new Position(0,0), false);

        if (paused)
            pausedLetters.draw(graphics_, new Position(0,0), false);
    }

    void drawWinnerLetters(TextGraphics graphics){
        winnerLetters.draw(graphics, new Position(0,0),false);
    }

}
