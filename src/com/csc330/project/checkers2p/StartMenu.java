package com.csc330.project.checkers2p;

import com.csc330.project.login.Login;
import com.csc330.project.login.Tournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project: Checkers2P
 * Author: Stuart Smith
 * Date: 11/15/2014.
 */
public class StartMenu extends JFrame implements ActionListener {

    public static boolean isV = true; //Controls the visibility of this menu throughout the game.

    private JButton newGame = new JButton("New Game");
    private JButton justPlay = new JButton("Just Play");
    //private JButton aboutGame = new JButton("About");

    private Tournament tournament;

    public StartMenu(){
        setLayout(new GridLayout(2,1,3,4));
        add(newGame);
        add(justPlay);
        //add(aboutGame);

        newGame.addActionListener(this);
        justPlay.addActionListener(this);
        //aboutGame.addActionListener(this);

        tournament = new Tournament();

        setSize(200, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(isV);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New Game")){
            new Login(tournament); //Open the game window.
        }
        if(e.getActionCommand().equals("Just Play")){
            Tournament.numberOfLogins=2;
            new Main(); //Open the game window.
        }
    }
}
