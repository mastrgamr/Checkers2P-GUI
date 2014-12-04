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
    private String playersInTournament = "1";
    private int numberOfLogins = 2;

    public Login(){

        setSize(200, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        newTournament = JOptionPane.showConfirmDialog(null, "Is this a new tournament?");
        if(newTournament == JOptionPane.YES_OPTION) {

            boolean a = Integer.parseInt(playersInTournament) != 2;
            boolean b = Integer.parseInt(playersInTournament) != 4;
            boolean c = Integer.parseInt(playersInTournament) != 8;
            while( a || b || c) { //MAX of 8 players allowed to play
                playersInTournament = JOptionPane.showInputDialog(null, "How many players are playing in this tournament?");
                if(Integer.parseInt(playersInTournament) == 2){
                    Tournament.numberOfGames = 1;
                } else {
                    Tournament.numberOfGames = (Integer.parseInt(playersInTournament) / 2) + 1; //Simple case: 4 players = 3 games
                }
            }

            while(usersCreated != Integer.parseInt(playersInTournament)) {
                new NewUsersDialog(null, this).setVisible(true);
                usersCreated++;
            }
        } else if(newTournament == JOptionPane.NO_OPTION){
            while(numberOfLogins != Tournament.numberOfLoginWindows) {
                new LoginDialog(null).setVisible(true);
                Tournament.numberOfLoginWindows++;
            }
        } else if(newTournament == JOptionPane.CANCEL_OPTION){
            dispose();
        }
    }

    public static boolean authenticate(String username, String password, BufferedReader br) throws IOException {
        if(Tournament.numberOfLogins == 0) {
            Tournament.gameID = Integer.parseInt(br.readLine());
        }
        if (Tournament.numberOfLogins != 2 && Tournament.numberOfLogins == 1) {
            for(int i = 0; i < 3;i++)
                br.readLine(); //skip some lines so it does not re-read the file from the beginning

            String user = br.readLine();
            System.out.println(user); //debug
            String pass = br.readLine();
            System.out.println(pass); //debug
            return (username.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass));
        } else {
            Tournament.numberOfLogins = 0;
        }
        return false;
    }
}
