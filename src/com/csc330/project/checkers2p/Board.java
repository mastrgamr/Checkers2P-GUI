package com.csc330.project.checkers2p;

import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

/**
 * Project: Checkers2P
 * Author: Stuart Smith
 * Date: 11/30/2014
 *
 * This class handles drawing the game board itself and populates a hashmap of where pieces are supposed to go.
 * Also manages the pieces on the board.
 */
public class Board {
    private int rowCount = 0;
    private int whosTurn;
    private int redCaptureCount = 0;
    private int whiteCaptureCount = 0;
    private boolean gameOver = false;
    private boolean isSelected = false;

    private HashMap<Rectangle, CellEntry> squareMap = new HashMap<Rectangle, CellEntry>();

    private Rectangle squareRect[][] = new Rectangle[8][8];

    private Vector<Piece> redPieces;
    private Vector<Piece> whitePieces;

    public Board(){ }

    public Board(Vector<Piece> redPieces, Vector<Piece> whitePieces){
        this.redPieces = redPieces;
        this.whitePieces = whitePieces;
    }

    public void paintBoard(Graphics g){
        int squareX = 0;
        int squareY = 0;
        Color selectedColor = null;

        Graphics2D g2d[][] = new Graphics2D[8][8]; //TODO: Array not needed, lol

        for(int i = 0; i < squareRect.length; i++) {
            for(int j = 0; j < squareRect[i].length; j++) {

                g2d[i][j] = (Graphics2D)g;
                squareRect[i][j] = new Rectangle(squareX, squareY, 100, 100);

                //Alternating algorithm to handle coloring of board squares and pieces.
                switch (i % 2){
                    case 0: //row 0, 2 , 4, 6
                        if(j % 2 == 0) { //Set a color for every other square on the board.
                            selectedColor = Color.DARK_GRAY;
                            g2d[i][j].setColor(Color.DARK_GRAY);

                            //Set up hashmap for pieces
                            if (rowCount == 0 || rowCount == 2) {
                                squareMap.put(squareRect[i][j], CellEntry.RED);
                                redPieces.add(new Piece());
                            }
                            if (rowCount == 4)
                                squareMap.put(squareRect[i][j], CellEntry.EMPTY);
                            if (rowCount == 6) {
                                squareMap.put(squareRect[i][j], CellEntry.WHITE);
                                whitePieces.add(new Piece());
                            }

                        } else {
                            selectedColor = Color.GRAY;
                             g2d[i][j].setColor(Color.GRAY);

                            //Set up hashmap for pieces
                            if (rowCount == 0 || rowCount == 2 || rowCount == 4 || rowCount == 6)
                                squareMap.put(squareRect[i][j], CellEntry.INVALID);
                        }
                        break;
                    default: //row 1, 3, 5, 7
                        if(j % 2 == 0) {
                            selectedColor = Color.GRAY;
                            g2d[i][j].setColor(Color.GRAY);

                            if (rowCount == 5 || rowCount == 3 || rowCount == 7)
                                squareMap.put(squareRect[i][j], CellEntry.INVALID);

                        } else {
                            selectedColor = Color.DARK_GRAY;
                            g2d[i][j].setColor(Color.DARK_GRAY);

                            if (rowCount == 5 || rowCount == 7) {
                                squareMap.put(squareRect[i][j], CellEntry.WHITE);
                                whitePieces.add(new Piece());
                            }
                            if (rowCount == 3)
                                squareMap.put(squareRect[i][j], CellEntry.EMPTY);
                            if (rowCount == 1) {
                                squareMap.put(squareRect[i][j], CellEntry.RED);
                                redPieces.add(new Piece());
                            }
                        }
                        break;
                } //end switch

                if(isSelected) {
                    g2d[i][j].setColor(Color.BLUE);
                    g2d[i][j].fillRect(squareX, squareY, 100, 100);
                    g2d[i][j].setColor(selectedColor);
                    g2d[i][j].fillRect(squareX + 4, squareY + 4, 92, 92);
                } else {
                    g2d[i][j].fillRect(squareX, squareY, 100, 100);
                }

                squareX = (squareX + 100) % 800;
            }
            squareY = (squareY + 100) % 800;
            rowCount++;
        }
    }

    public boolean isSquareClicked(int x, int y){
        for(int i = 0; i < squareRect.length; i++) {
            for (int j = 0; j < squareRect[i].length; j++) {
                if (squareRect[i][j].contains(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Rectangle getSquare(int x, int y) {
        for(int i = 0; i < squareRect.length; i++) {
            for (int j = 0; j < squareRect[i].length; j++) {
                if (squareRect[i][j].contains(x, y)) {
                    return squareRect[i][j];
                }
            }
        }
        return null;
    }

    public void setSelected(boolean bool){
        isSelected = bool;
    }

    public boolean isGameOver(){
        if(redCaptureCount == 12 || whiteCaptureCount == 12)
            gameOver = true;
        return gameOver;
    }

    public Rectangle[][] getSquareRect(){
        return squareRect;
    }

    public CellEntry getCellType(Rectangle rect){
        return squareMap.get(rect);
    }

    public HashMap<Rectangle, CellEntry> getSquareMap(){
        return squareMap;
    }

    public int getWhosTurn() {
        return whosTurn;
    }

    public void setWhosTurn(int whosTurn) {
        this.whosTurn = whosTurn;
    }

    public void increaseRedCaptures(){
        redCaptureCount++;
    }
    public void increaseWhiteCaptures(){
        whiteCaptureCount++;
    }
    public int getRedCaptureCount(){
        return redCaptureCount;
    }
    public int getWhiteCaptureCount(){
        return whiteCaptureCount;
    }

}
