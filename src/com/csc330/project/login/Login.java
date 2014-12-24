package com.csc330.project.login;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Project: Checkers2P
 * Author: Stuart Smith
 * Date: 12/2/2014
 */
public class Login extends JFrame {

    private int newTournament;
    private int usersCreated = 0;
    private int numberOfLogins = 2;

    private Tournament tournament;

    public Login(Tournament tournament){

        setSize(200, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.tournament = tournament;

        newTournament = JOptionPane.showConfirmDialog(null, "Is this a new tournament?");
        if(newTournament == JOptionPane.YES_OPTION) {

            boolean a = Integer.parseInt(Tournament.numberOfPlayers) != 2;
            boolean b = Integer.parseInt(Tournament.numberOfPlayers) != 4;
            boolean c = Integer.parseInt(Tournament.numberOfPlayers) != 8;
            while( a || b || c) { //MAX of 8 players allowed to play
                Tournament.numberOfPlayers = JOptionPane.showInputDialog(null, "How many players are playing in this tournament?");
                switch (Integer.parseInt(Tournament.numberOfPlayers)){
                    case 2:
                        Tournament.roundOneGames = 1;
                        break;
                    case 4:
                        Tournament.roundOneGames = 2;
                        break;
                    case 8:
                        Tournament.roundOneGames = 4;
                        break;
                    default:
                        System.err.println("Could not determine amount of rounds in the Tournament.");
                        break;
                }
                if(a || b || c)
                    break;
            } //TODO:Extremely buggy, need a better condition

            while(usersCreated != Integer.parseInt(Tournament.numberOfPlayers)) {
                new NewUsersDialog(null, this, tournament).setVisible(true);
                usersCreated++;
            }
        } else if(newTournament == JOptionPane.NO_OPTION){
            while(numberOfLogins != Tournament.numberOfLoginWindows) {
                new LoginDialog(null, this).setVisible(true);
                Tournament.numberOfLoginWindows++;
            }
        } else if(newTournament == JOptionPane.CANCEL_OPTION){
            dispose();
        }
    }

    public boolean authenticate(String username, String password, BufferedReader br) throws IOException {
        if (Tournament.numberOfLogins != 2 && Tournament.numberOfLogins == 1) {
//            for(int i = 0; i < 2;i++)
//                br.readLine(); //skip some lines so it does not re-read the file from the beginning
//
//            String user = br.readLine();
//            System.out.println(user); //debug
//            String pass = br.readLine();
//            System.out.println(pass); //debug
//            return (username.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass));
            return tournament.hasUser(username, password); //TODO:get true value of bool, issue: all logins invalid -_-*
        } else {
            Tournament.numberOfLogins = 0;
        }
        return false;
    }
}
