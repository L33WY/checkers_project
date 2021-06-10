package com.checkers.controllers;

import com.checkers.classes.MoveType;
import com.checkers.classes.Pawn;
import com.checkers.classes.Tile;

import java.util.ArrayList;

public class MoveController {

    private Pawn pawn;
    private Tile[][] board;
//    public static Tile PREVIOUS_TILES[];
    public static ArrayList <Tile> PREVIOUS_TILES = new ArrayList<>();


    public MoveController(Pawn pawn) {
        this.pawn = pawn;
        this.board = pawn.getBoard();
    }

    public void showAvalibleMoves(int x, int y) {

        if (pawn.getSelectedPawn() != null) {
            if (pawn.getSelectedPawn() != pawn) {
                hidePreviousFields();
            }
        }


        pawn.setSelectedPawn(pawn);

        System.out.println(pawn.getSelectedPawn() == pawn);

        System.out.println(pawn.getType());
        System.out.println(x + " | " + y);

        int yField = y + pawn.getMoveDir();
        int xRightField = x + 1;
        int xLeftField = x - 1;

        System.out.println(xLeftField + " X " + yField);
        System.out.println(xRightField + " X " + yField);

        if (checkLeftValueX(xLeftField) && checkValueY(yField)) {
            if (board[xLeftField][yField].hasPawn()){

            } else {
                board[xLeftField][yField].showAvalibleField();
                PREVIOUS_TILES.add(board[xLeftField][yField]);
            }
        }
        if (checkRightValueX(xRightField) && checkValueY(yField)) {
            if (board[xRightField][yField].hasPawn()) {

            } else {
                board[xRightField][yField].showAvalibleField();
                PREVIOUS_TILES.add(board[xRightField][yField]);
            }
        }

    }


    // Check if next avalible field are in Array index range

    private boolean checkLeftValueX(int xLeft) {
        if (xLeft < 0) {return false;}
        else return true;
    }
    private boolean checkRightValueX(int xRight) {
        if (xRight > 7) {return false;}
        else return true;
    }
    private boolean checkValueY(int y) {
        if (y > 7 || y < 0) {return false;}
        else return true;
    }

    // Check if tile has a pawn

    public static void hidePreviousFields() {
        for (Tile tile : PREVIOUS_TILES) {
            tile.setTileColor(tile.getLight());
        }
    }

}
