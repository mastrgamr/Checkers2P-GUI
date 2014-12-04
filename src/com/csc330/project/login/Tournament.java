package com.csc330.project.login;

/**
 * Project: Checkers2P
 * Author: Stuart Smith
 * Date: 12/3/14
 *
 * Class of static methods to keep track of tournament progress.
 * Information does not persist, if the program is closed the tournament starts from square one!
 */
public class Tournament {

    //Keeps track of the number of games played.
    public static int numberOfGames = 0;
    //Keeps track of current game in play.
    public static int gameID = 0;
    //Two players must successfully log in to begin a match.
    public static int numberOfLogins = 0;
    public static int numberOfLoginWindows = 0;
    //Number of players in the tournament.
    public static int numberOfPlayers = 0;
    public static int newUserInstances = 0;
}
