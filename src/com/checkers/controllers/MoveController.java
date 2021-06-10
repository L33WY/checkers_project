package com.checkers.controllers;

import com.checkers.classes.MoveType;
import com.checkers.classes.Pawn;
import com.checkers.classes.Tile;

public class MoveController {

    private Pawn pawn;


    public MoveController(Pawn pawn) {
        this.pawn = pawn;
    }

    public void showAvalibleMoves(int x, int y) {
        System.out.println(pawn.getType());
        System.out.println(x + " | " + y);

        int yField = y + pawn.getMoveDir();
        int xRightField = x + 1;
        int xLeftField = x - 1;

        System.out.println(xLeftField + " X " + yField);
        System.out.println(xRightField + " X " + yField);

        if (checkLeftValueX(xLeftField) && checkValueY(yField)) {
            pawn.getBoard()[xLeftField][yField].showAvalibleField();
        }
        if (checkRightValueX(xRightField) && checkValueY(yField)) {
            pawn.getBoard()[xRightField][yField].showAvalibleField();
        }

    }

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

}
