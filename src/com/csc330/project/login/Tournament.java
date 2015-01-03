package com.csc330.project.login;

import javax.swing.*;
import java.util.*;

/**
 * Project: Checkers2P
 * Author: Stuart Smith
 * Date: 12/3/14
 *
 * Class of static methods to keep track of tournament progress.
 * Information does not persist, if the program is closed the tournament starts from square one!
 */
public class Tournament {

    //Keeps track of current game in play.
    public static int gameID = 0;
    //Max amount of rounds is 3 for this implementation of tournament. Since 8 players are max.
    public static int roundOneGames;
    public static int roundTwoGames;
    public static int roundThreeGames;
    //Two players must successfully log in to begin a match.
    public static int numberOfLogins = 0;
    public static int numberOfNewUsers = 0;
    public static int numberOfLoginWindows = 0;
    //Number of players in the tournament.
    public static String numberOfPlayers = "1";
    public static int newUserInstances = 0;

    //HashMap of GameID and Users
    private HashMap<Integer, HashMap<String, String>> round =
            new HashMap<Integer, HashMap<String, String>>();
    private Vector<User> logins = new Vector<User>();

    private class User{
        private String username;
        private String password;

        public User(String username, String password){
            this.username = username;
            this.password = password;
        }

        public String getUsername() { return username; }

        public String getPassword() { return password; }
    }

    /**
     * Gather all player login information that will be put into a Vector and shuffled to randomly
     * generated teams.
     *
     * @param username Username of the player.
     * @param password Password of the player.
     */
    public void gatherPlayerInfo(String username, String password){
        //TODO:if new users = 2 then add hashmap to the vector, this will be one game
        this.logins.add(new User(username, password));
        System.out.println(logins.size() + ">> logins in list, gatherPlayerInfo()");
        Collections.shuffle(logins); //shuffle in place
        System.out.println("Leaving gatherPlayerInfo");
    }

    public void generateMatches(){
        int numberOfGames = Integer.parseInt(numberOfPlayers) - 1;
        int participants = Integer.parseInt(numberOfPlayers);

        HashMap<String, String> players = new HashMap<String, String>();

        switch (participants){
            case 2:
                System.out.println("geneterating 2 users");
                System.out.println(logins.size() + ">> logins to be added.");
                for(int i = 0; i < logins.size() - 1; i++){
                    players.put(logins.elementAt(i).getUsername(), logins.elementAt(i).getPassword());
                    System.out.println(logins.elementAt(i).getUsername() + ", " + logins.elementAt(i).getPassword());
                    players.put(logins.elementAt(i + 1).getUsername(), logins.elementAt(i + 1).getPassword());
                    System.out.println(logins.elementAt(i+1).getUsername() + ", " + logins.elementAt(i+1).getPassword());
                    round.put(i, players);
                }
                break;
            case 4:
                System.out.println("geneterating 4 users");
                for(int i = 0; i < logins.size(); i++){
                    players.put(logins.elementAt(i).getUsername(), logins.elementAt(i).getPassword());
                    players.put(logins.elementAt(i + 1).getUsername(), logins.elementAt(i + 1).getPassword());
                    round.put(i, players);
                }
                break;
            case 8:
                System.out.println("geneterating 8 users");
                for(int i = 0; i < logins.size()/2; i++){
                    players.put(logins.elementAt(i).getUsername(), logins.elementAt(i).getPassword());
                    players.put(logins.elementAt(i + 1).getUsername(), logins.elementAt(i + 1).getPassword());
                    round.put(i, players);
                }
                break;
            default:
                System.err.println("FATAL: If you see this, the universe might've exploded.");
        }

        StringBuilder matches = new StringBuilder();
        matches.append("Game 1:\n");

        Set<String> keys;
        System.out.println(round.size() + ">> size of round");
        for(int i = 0; i < round.size(); i++){
            //add list of users to matches
            keys = round.get(i).keySet();
            for(String users : keys) {
                System.out.println(users);
                matches.append(users + "\n");
            }
        }

        JOptionPane.showMessageDialog(null, "New tournament created:\n" + matches);
    }

    public HashMap<String, String> getBracketMap(int gameID){
        return round.get(gameID);
    }

    public boolean hasUser(String user, String pass){
        System.out.println(round.get(0).containsKey(user) + ">>Contains user: " + user);
        System.out.println(round.get(0).get(user).equals(pass) + ">>paswword matches?");

        return (round.get(0).containsKey(user) && round.get(0).get(user).equals(pass));
    }
}
