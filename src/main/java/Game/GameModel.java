package Game;

import BoardComponents.Board;
import BoardElements.Bomberman;
import Structures.Position;

public class GameModel {
    Bomberman playerOne;
    Bomberman playerTwo;
    Bomberman playerThree;
    Bomberman playerFour;
    Board gameBoard;


    public GameModel(String playerOne_, String playerTwo_, String playerThree_, String playerFour_) {
        gameBoard =  new Board(
                "000000000000000000" +
                        "0  111000000111  0" +
                        "0 01110000001110 0" +
                        "010000000000000010" +
                        "01  1  1111  1  10" +
                        "01011100  00111010" +
                        "0  1110    0111  0" +
                        "0  1110    0111  0" +
                        "0  1110    0111  0" +
                        "0  1110    0111  0" +
                        "01011100  00111010" +
                        "01  1  1111  1  10" +
                        "010000000000000010" +
                        "0 01110000001110 0" +
                        "0  111000000111  0" +
                        "000000000000000000");
        this.playerOne = new Bomberman("PLAYER ONE", playerOne_, new Position(1,1), gameBoard);
        this.playerTwo = new Bomberman("PLAYER TWO", playerTwo_, new Position(16,1), gameBoard);
        this.playerThree = new Bomberman("PLAYER THREE", playerThree_, new Position(1,14), gameBoard);
        this.playerFour = new Bomberman("PLAYER FOUR", playerFour_, new Position(16,14), gameBoard);
    }

    public void playerUp(int player_) {
        boolean canMove = true;
        switch (player_) {
            case 1:
                //Check if it can move
                //if (canMove)
//                    playerOne.moveUp();
                break;
            case 2:
                //Check if it can move
                //if (canMove)
//                    playerTwo.moveUp();
                break;
            case 3:
                //Check if it can move
                //if (canMove)
//                    playerThree.moveUp();
                break;
            case 4:
                //Check if it can move
                //if (canMove)
//                    playerFour.moveUp();
                break;
            default:
                break;
        }
    }

    public void playerDown(int player_) {
        boolean canMove = true;
        switch (player_) {
            case 1:
                //Check if it can move
                //if (canMove)
//                    playerOne.moveDown();
                break;
            case 2:
                //Check if it can move
                //if (canMove)
//                    playerTwo.moveDown();
                break;
            case 3:
                //Check if it can move
                //if (canMove)
//                    playerThree.moveDown();
                break;
            case 4:
                //Check if it can move
                //if (canMove)
//                    playerFour.moveDown();
                break;
            default:
                break;
        }
    }

    public void playerLeft(int player_) {
        boolean canMove = true;
        switch (player_) {
            case 1:
                //Check if it can move
                //if (canMove)
//                    playerOne.moveLeft();
                break;
            case 2:
                //Check if it can move
                //if (canMove)
//                    playerTwo.moveLeft();
                break;
            case 3:
                //Check if it can move
                //if (canMove)
//                    playerThree.moveLeft();
                break;
            case 4:
                //Check if it can move
                //if (canMove)
//                    playerFour.moveLeft();
                break;
            default:
                break;
        }
    }

    public void playerRight(int player_) {
        boolean canMove = true;
        switch (player_) {
            case 1:
                //Check if it can move
                //if (canMove)
//                    playerOne.moveRight();
                break;
            case 2:
                //Check if it can move
                //if (canMove)
//                    playerTwo.moveRight();
                break;
            case 3:
                //Check if it can move
                //if (canMove)
//                    playerThree.moveRight();
                break;
            case 4:
                //Check if it can move
                //if (canMove)
//                    playerFour.moveRight();
                break;
            default:
                break;
        }
    }

    public void playerAction(int player_) {
        switch (player_) {
            case 1:
                /*
                TODO: The code below
                if (playerOne.action()) {
                    //createBomb(playerOne.getPosition(), playerOne.getBombRadius());
                }
                */
                break;
            case 2:
                //TODO:
                break;
            case 3:
                //TODO:
                break;
            case 4:
                //TODO:
                break;
            default:
                break;
        }
    }

}
