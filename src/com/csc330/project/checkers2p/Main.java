package com.csc330.project.checkers2p;

import com.csc330.project.login.Tournament;

import javax.swing.*;

/**
 * Author: Stuart Smith
 * Date: 11/15/2014.
 */
public class Main extends JFrame {

    /**
     * The window that will host the game's set up.
     */
    public Main(){
        super("Checkers Tournament!");
        Board board = new Board();
        boolean gamePlaying = (!board.isGameOver() && Tournament.numberOfLogins == 2); //gameOver is false by default
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new GameBoard());
        pack();
        setResizable(false);
        setVisible(gamePlaying);
    }

    public static void main(String args[]) {
        new StartMenu();
    }
}
