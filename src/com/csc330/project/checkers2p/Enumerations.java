package com.csc330.project.checkers2p;

/**
 * Project: Checkers2P
 * Author: Jose Martinez, Stuart Smith
 * Date: 11/29/2014
 */

enum CellEntry{ 
    INVALID,
    EMPTY,
    WHITE,
    WHITE_KING,
    RED,
    RED_KING,
}

enum CellMovement{
    IS_VALID,
    INVALID
}

enum Player{
    RED,
    WHITE
}