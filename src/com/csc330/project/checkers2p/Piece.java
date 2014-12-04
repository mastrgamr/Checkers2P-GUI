package com.csc330.project.checkers2p;

import java.awt.*;

/**
 * Project: Checkers2P
 * Author: Stuart Smith, Jose Martinez
 * Date: 11/30/2014
 */
public class Piece {

    Point position;

    private void setPosition(int x, int y){
        position = new Point(x, y);
    }

    public void paintPiece(Graphics g, Board board, Rectangle[][] rect){

        Graphics2D g2d = (Graphics2D)g;

        for(int i = 0; i < rect.length; i++) {
            for (int j = 0; j < rect[i].length; j++) {
                setPosition((int)rect[i][j].getX() + 10, (int)rect[i][j].getY() + 10);

                if(board.getSquareMap().get(rect[i][j]) == CellEntry.RED) {
                    g2d.setColor(Color.RED);
                    g2d.fillOval((int)position.getX(), (int)position.getY(), 80, 80);
                }
                if(board.getSquareMap().get(rect[i][j]) == CellEntry.WHITE) {
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval((int)position.getX(), (int)position.getY(), 80, 80);
                }

                if(board.getSquareMap().get(rect[i][j]) == CellEntry.WHITE_KING) {
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval((int)position.getX(), (int)position.getY(), 80, 80);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval((int)position.getX() + 20, (int)position.getY() + 20, 30, 30);
                }
                if(board.getSquareMap().get(rect[i][j]) == CellEntry.RED_KING) {
                    g2d.setColor(Color.RED);
                    g2d.fillOval((int)position.getX(), (int)position.getY(), 80, 80);
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval((int)position.getX() + 20, (int)position.getY() + 20, 30, 30);
                }
            }
        }
    }
}
