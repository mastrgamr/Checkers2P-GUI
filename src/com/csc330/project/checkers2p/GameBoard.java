package com.csc330.project.checkers2p;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Project: Checkers2P
 * Author: Stuart Smith
 * Date: 11/28/2014
 *
 * Handles drawing the checkers board, and the pieces on the checkers board.
 * Manages which players are playing and who's turn it is.
 */
public class GameBoard extends JPanel {

    private Board board;
    private Vector<Piece> redPieces;
    private Vector<Piece> whitePieces;
    private InputAdapter inputAdapter;

    /**
     * Class that contains and handles all the game's action.
     * This IS the game.
     */
    public GameBoard() {
        initialize();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 800));
        setBounds(0, 0, 800, 800);
        addMouseListener(inputAdapter);
        setFocusable(true);
    }

    /**
     * Used to initialize the game's Pieces on the game board and the game board itself.
     */
    private void initialize() {
        redPieces = new Vector<Piece>(12);
        whitePieces = new Vector<Piece>(12);
        board = new Board(redPieces, whitePieces);
        inputAdapter = new InputAdapter();
        inputAdapter.listenToBoard(board);
    }

    /**
     * Used to draw out the actual game board which is made up of an 8 by 8 square board.
     * @param g Graphics are handled by Java's Swing SDK.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(!board.isGameOver()) {
            board.paintBoard(g);
            for(Piece p : redPieces){
                p.paintPiece(g, board, board.getSquareRect());
            }
            for(Piece p : whitePieces){
                p.paintPiece(g, board, board.getSquareRect());
            }
            repaint();
        }
    }
}
