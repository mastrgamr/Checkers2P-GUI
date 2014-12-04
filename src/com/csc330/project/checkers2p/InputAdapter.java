package com.csc330.project.checkers2p;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Project: Checkers2P
 * Author: Stuart Smith
 * Date: 11/28/2014
 *
 * Handles all game input logic, Mouse, KeyBoard, etc, etc.
 * All Mouse and Keyboard events are automatically passed to this class
 * by classes that use this as it's Listener.
 */
public class InputAdapter implements MouseInputListener {

    private int clickCounts = 0;
    private int whosTurn;

    private Rectangle positionBuffer;
    private Rectangle positionBuffer2;
    private Rectangle hopped;
    private HashMap<Rectangle, CellMovement> validMoveSquareMap = new HashMap<Rectangle, CellMovement>();

    private Board board;

    public void listenToBoard(Board board){
        this.board = board;
    }

    /**
     * The position buffer temporarily holds the position of the square the player clicks on.
     *
     * @param x X-position of the square clicked, based on InputAdapter's calculations.
     * @param y Y-position of the square clicked, based on InputAdapter's calculations.
     */
    private void setPositionBuffer(int x, int y){
        positionBuffer = board.getSquare(x, y);
    }
    private void setPositionBuffer2(int x, int y){
        positionBuffer2 = board.getSquare(x, y);
    }

    private void markValidKingMoves(){
        Rectangle tmp = null;

        int posX = (int)positionBuffer.getX();
        int posY = (int)positionBuffer.getY();

        //Check bottom right side for valid moves
        for (int i = 0; i < board.getSquareRect().length; i++){
            for (int j = 0; j < board.getSquareRect()[i].length; j++)
            {
                tmp = new Rectangle(posX+100, posY+100, 100, 100);
                if(board.getSquareMap().get(tmp) == CellEntry.EMPTY) {
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    posX += 100;
                    posY += 100;
                } else if ((board.getSquareMap().get(positionBuffer) == CellEntry.RED_KING) &&
                        board.getSquareMap().get(tmp) == CellEntry.WHITE){ //if red king evaluates a square top-right to it to be white, stop eval
                    hopped = new Rectangle(tmp);
                    validMoveSquareMap.put(hopped, CellMovement.INVALID);
                    tmp = new Rectangle(posX+200, posY+200, 100, 100);
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    break;
                } else if ((board.getSquareMap().get(positionBuffer) == CellEntry.WHITE_KING) &&
                        board.getSquareMap().get(tmp) == CellEntry.RED){ //if red king evaluates a square top-right to it to be white, stop eval
                    hopped = new Rectangle(tmp);
                    validMoveSquareMap.put(hopped, CellMovement.INVALID);
                    tmp = new Rectangle(posX+200, posY+200, 100, 100);
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    break;
                }
            }
        }

        //Check bottom left side for valid moves
        for (int i = 0; i < board.getSquareRect().length; i++){
            for (int j = 0; j < board.getSquareRect()[i].length; j++){
                tmp = new Rectangle(posX-100, posY+100, 100, 100);
                if(board.getSquareMap().get(tmp) == CellEntry.EMPTY) {
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    posX -= 100;
                    posY += 100;
                } else if ((board.getSquareMap().get(positionBuffer) == CellEntry.RED_KING) &&
                        board.getSquareMap().get(tmp) == CellEntry.WHITE){ //if red king evaluates a square top-right to it to be white, stop eval
                    hopped = new Rectangle(tmp);
                    validMoveSquareMap.put(hopped, CellMovement.INVALID);
                    tmp = new Rectangle(posX+200, posY+200, 100, 100);
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    break;
                } else if ((board.getSquareMap().get(positionBuffer) == CellEntry.WHITE_KING) &&
                        board.getSquareMap().get(tmp) == CellEntry.WHITE){ //if red king evaluates a square top-right to it to be white, stop eval
                    hopped = new Rectangle(tmp);
                    validMoveSquareMap.put(hopped, CellMovement.INVALID);
                    tmp = new Rectangle(posX+200, posY+200, 100, 100);
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    break;
                }
            }
        }

        //Check top right side for valid moves
        for (int i = 0; i < board.getSquareRect().length; i++){
            for (int j = 0; j < board.getSquareRect()[i].length; j++){
                tmp = new Rectangle(posX+100, posY-100, 100, 100);
                if(board.getSquareMap().get(tmp) == CellEntry.EMPTY) {
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    posX += 100;
                    posY -= 100;
                } else if ((board.getSquareMap().get(positionBuffer) == CellEntry.RED_KING) &&
                        board.getSquareMap().get(tmp) == CellEntry.WHITE || board.getSquareMap().get(tmp) == CellEntry.WHITE_KING){ //if red king evaluates a square top-right to it to be white, stop eval
                    hopped = new Rectangle(tmp);
                    validMoveSquareMap.put(hopped, CellMovement.INVALID);
                    tmp = new Rectangle(posX+200, posY-200, 100, 100);
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    break;
                }  else if ((board.getSquareMap().get(positionBuffer) == CellEntry.WHITE_KING) &&
                        board.getSquareMap().get(tmp) == CellEntry.RED || board.getSquareMap().get(tmp) == CellEntry.RED_KING){ //if red king evaluates a square top-right to it to be white, stop eval
                    hopped = new Rectangle(tmp);
                    validMoveSquareMap.put(hopped, CellMovement.INVALID);
                    tmp = new Rectangle(posX+200, posY-200, 100, 100);
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    break;
                }
            }
        }

        //Check top left side for valid moves
        for (int i = 0; i < board.getSquareRect().length; i++){
            for (int j = 0; j < board.getSquareRect()[i].length; j++){
                tmp = new Rectangle(posX-100, posY-100, 100, 100);
                if(board.getSquareMap().get(tmp) == CellEntry.EMPTY) {
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    posX -= 100;
                    posY -= 100;
                } else if ((board.getSquareMap().get(positionBuffer) == CellEntry.RED_KING) &&
                        board.getSquareMap().get(tmp) == CellEntry.WHITE || board.getSquareMap().get(tmp) == CellEntry.WHITE_KING){ //if red king evaluates a square top-left to it to be white, stop eval
                    hopped = new Rectangle(tmp);
                    validMoveSquareMap.put(hopped, CellMovement.INVALID);
                    tmp = new Rectangle(posX-200, posY-200, 100, 100);
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    break;
                } else if ((board.getSquareMap().get(positionBuffer) == CellEntry.WHITE_KING) &&
                        board.getSquareMap().get(tmp) == CellEntry.RED || board.getSquareMap().get(tmp) == CellEntry.RED_KING){ //if red king evaluates a square top-left to it to be white, stop eval
                    hopped = new Rectangle(tmp);
                    validMoveSquareMap.put(hopped, CellMovement.INVALID);
                    tmp = new Rectangle(posX-200, posY-200, 100, 100);
                    validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
                    break;
                }
            }
        }
    }

    private void markValidWhiteNormalMoves(){
        //If the first clicked square is White, validate the Top-Left & Top-Right Squares
        int posX = (int)positionBuffer.getX() - 100;
        int posY = (int)positionBuffer.getY() - 100;
        Rectangle tmp = new Rectangle(posX, posY, 100, 100);

        //check if new square is occupied
        if((board.getSquareMap().get(tmp) == CellEntry.RED || board.getSquareMap().get(tmp) == CellEntry.RED_KING) &&
                (board.getSquareMap().get(board.getSquare(posX-100, posY-100)) == CellEntry.EMPTY))
        {
            hopped = new Rectangle(tmp);
            validMoveSquareMap.put(hopped, CellMovement.INVALID);
            tmp = new Rectangle(posX - 100, posY - 100, 100, 100);
        }
        validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
        tmp = new Rectangle(posX + 200, posY, 100, 100);

        //check if new square is occupied
        if((board.getSquareMap().get(tmp) == CellEntry.RED || board.getSquareMap().get(tmp) == CellEntry.RED_KING) &&
                (board.getSquareMap().get(board.getSquare(posX+300, posY-100)) == CellEntry.EMPTY))
        {
            hopped = new Rectangle(tmp);
            validMoveSquareMap.put(hopped, CellMovement.INVALID);
            tmp = new Rectangle(posX+300, posY-100, 100, 100);
        }

        validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
    }

    private void markValidRedNormalMoves(){
        //If the first clicked square is White, validate the Bottom-Left & Bottom-Right Squares
        int posX = (int)positionBuffer.getX() + 100;
        int posY = (int)positionBuffer.getY() + 100;
        Rectangle tmp = new Rectangle(posX, posY, 100, 100);

        //check if new square is occupied
        if((board.getSquareMap().get(tmp) == CellEntry.WHITE || board.getSquareMap().get(tmp) == CellEntry.WHITE_KING) &&
                (board.getSquareMap().get(board.getSquare(posX+100, posY+100)) == CellEntry.EMPTY))
        {
            hopped = new Rectangle(tmp);
            validMoveSquareMap.put(hopped, CellMovement.INVALID);
            tmp = new Rectangle(posX+100, posY+100, 100, 100);
        }

        validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
        tmp = new Rectangle(posX - 200, posY, 100, 100);

        //check if new square is occupied
        if((board.getSquareMap().get(tmp) == CellEntry.WHITE || board.getSquareMap().get(tmp) == CellEntry.WHITE_KING) &&
                (board.getSquareMap().get(board.getSquare(posX-300, posY+100)) == CellEntry.EMPTY)){
            hopped = new Rectangle(tmp);
            validMoveSquareMap.put(hopped, CellMovement.INVALID);
            tmp = new Rectangle(posX-300, posY+100, 100, 100);
        }
        validMoveSquareMap.put(tmp, CellMovement.IS_VALID);
    }

    /**
     * A click triggers a complete turn. A complete turn involves two clicks.
     * First click passes the information to the first buffer, if the square clicked contains a piece it is evaluated for valid moves.
     * Second click passes the information to the second buffer, moves a piece if the second box clicked is a valid move.
     * After two clicks all data is reset to begin a new turn.
     *
     * @param e gets the information about where the mouse clicked.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        clickCounts++;
        int x = e.getX();
        int y = e.getY();
        System.out.println(board.isSquareClicked(x, y) + ": is clicked");

        switch(clickCounts){
            case 1: //first click set up the surrounding valid moves map
                setPositionBuffer(x, y);

                if(board.getSquareMap().get(positionBuffer) == CellEntry.WHITE) {
                    markValidWhiteNormalMoves();

                } else if(board.getSquareMap().get(positionBuffer) == CellEntry.RED){
                    markValidRedNormalMoves();
                }

                //TODO: Evaluate if a king was clicked and mark valid moves
                if(board.getSquareMap().get(positionBuffer) == CellEntry.WHITE_KING) {
                    markValidKingMoves();

                } else if(board.getSquareMap().get(positionBuffer) == CellEntry.RED_KING){
                    markValidKingMoves();
                }

                break;
            case 2: // second click move the piece based on valid movements
                setPositionBuffer2(x, y);

                CellEntry lowerLeft = board.getSquareMap().get(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()+100));
                CellEntry lowerRight = board.getSquareMap().get(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()+100));
                CellEntry upperLeft = board.getSquareMap().get(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()-100));
                CellEntry upperRight = board.getSquareMap().get(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()-100));

                //if the first square clicked is a red or white piece AND the second square is an empty square
                if((board.getSquareMap().get(positionBuffer) == CellEntry.RED ||
                    board.getSquareMap().get(positionBuffer) == CellEntry.WHITE) &&
                    (board.getCellType(board.getSquare(x, y)) == CellEntry.EMPTY))
                {
                    //Move red piece based on validity
                    if(board.getSquareMap().get(positionBuffer) == CellEntry.RED &&
                            validMoveSquareMap.get(positionBuffer2) == CellMovement.IS_VALID) {
                        System.out.println();
                        System.out.println("Swapping spots.");

                        board.getSquareMap().put(positionBuffer2, CellEntry.RED);

                        //Avoids collateral captures when a piece moves behind a piece that could have been captured
                        if((upperLeft == CellEntry.WHITE || upperLeft == CellEntry.WHITE_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()-100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()-100), CellEntry.EMPTY);
                            board.increaseWhiteCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else if((upperRight == CellEntry.WHITE || upperRight == CellEntry.WHITE_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()-100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()-100), CellEntry.EMPTY);
                            board.increaseWhiteCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        }

                        //check for promotion, else just move the piece
                        if(board.getSquare(x, y).getY() == 700){
                            board.getSquareMap().put(positionBuffer2, CellEntry.RED_KING);
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                        } else {
                            board.getSquareMap().put(positionBuffer2, CellEntry.RED);
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                        }
                    }

                    //Move white piece based on validity
                    if(board.getSquareMap().get(positionBuffer) == CellEntry.WHITE &&
                            validMoveSquareMap.get(positionBuffer2) == CellMovement.IS_VALID) {
                        System.out.println();
                        System.out.println("Swapping spots.");

                        board.getSquareMap().put(positionBuffer2, CellEntry.WHITE);

                        //Avoids collateral captures when a piece moves behind a piece that could have been captured
                        if(lowerLeft == CellEntry.RED || lowerLeft == CellEntry.RED_KING &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()+100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()+100), CellEntry.EMPTY);
                            board.increaseRedCaptures();
                            System.out.println(board.getRedCaptureCount());
                        } else if(lowerRight == CellEntry.RED || lowerRight == CellEntry.RED_KING &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()+100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()+100), CellEntry.EMPTY);
                            board.increaseRedCaptures();
                            System.out.println(board.getRedCaptureCount());
                        }

                        if(board.getSquare(x, y).getY() == 0){
                            board.getSquareMap().put(positionBuffer2, CellEntry.WHITE_KING);
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                        } else {
                            board.getSquareMap().put(positionBuffer2, CellEntry.WHITE);
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                        }
                    }
                }

                //EVALUATE KING MOVEMENTS
                //if the first square clicked is a red or white piece AND the second square is an empty square
                if((board.getSquareMap().get(positionBuffer) == CellEntry.RED_KING ||
                        board.getSquareMap().get(positionBuffer) == CellEntry.WHITE_KING) &&
                        (board.getCellType(board.getSquare(x, y)) == CellEntry.EMPTY))
                {
                    if(board.getSquareMap().get(positionBuffer) == CellEntry.RED_KING &&
                            validMoveSquareMap.get(positionBuffer2) == CellMovement.IS_VALID)
                    {
                        board.getSquareMap().put(positionBuffer2, CellEntry.RED_KING);

                        //Avoids collateral captures when a piece moves behind a piece that could have been captured
                        if((upperLeft == CellEntry.WHITE || upperLeft == CellEntry.WHITE_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()-100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()-100), CellEntry.EMPTY);
                            board.increaseWhiteCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else if ((upperRight == CellEntry.WHITE || upperRight == CellEntry.WHITE_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()-100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()-100), CellEntry.EMPTY);
                            board.increaseWhiteCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else if ((lowerLeft == CellEntry.WHITE || lowerLeft == CellEntry.WHITE_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()+100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()+100), CellEntry.EMPTY);
                            board.increaseWhiteCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else if ((lowerRight == CellEntry.WHITE || lowerRight == CellEntry.WHITE_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()+100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()+100), CellEntry.EMPTY);
                            board.increaseWhiteCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                        }
                    }

                    if(board.getSquareMap().get(positionBuffer) == CellEntry.WHITE_KING &&
                            validMoveSquareMap.get(positionBuffer2) == CellMovement.IS_VALID)
                    {
                        board.getSquareMap().put(positionBuffer2, CellEntry.WHITE_KING);

                        //Avoids collateral captures when a piece moves behind a piece that could have been captured
                        if((upperLeft == CellEntry.RED || upperLeft == CellEntry.RED_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()-100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()-100), CellEntry.EMPTY);
                            board.increaseRedCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else if ((upperRight == CellEntry.RED || upperRight == CellEntry.RED_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()-100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()-100), CellEntry.EMPTY);
                            board.increaseRedCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else if ((lowerLeft == CellEntry.RED || lowerLeft == CellEntry.RED_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()+100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()-100, (int)positionBuffer2.getY()+100), CellEntry.EMPTY);
                            board.increaseRedCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else if ((lowerRight == CellEntry.RED || lowerRight == CellEntry.RED_KING) &&
                                validMoveSquareMap.get(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()+100)) == CellMovement.INVALID)
                        {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                            board.getSquareMap().put(board.getSquare((int)positionBuffer2.getX()+100, (int)positionBuffer2.getY()+100), CellEntry.EMPTY);
                            board.increaseRedCaptures();
                            System.out.println("whites captured:" + board.getWhiteCaptureCount());
                        } else {
                            board.getSquareMap().put(positionBuffer, CellEntry.EMPTY);
                        }
                    }
                }

                //Reset all board information for next move
                System.out.println();
                System.out.println("Resetting positions.");
                clickCounts = 0;
                board.setSelected(false);
                positionBuffer = null;
                positionBuffer2 = null;
                validMoveSquareMap.clear();
                break;
            default:
                System.err.println("FATAL ERROR: Could not determine Movement."); //If this triggers, the universe broke.
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseDragged(MouseEvent e) { }
    @Override
    public void mouseMoved(MouseEvent e) { }
}
