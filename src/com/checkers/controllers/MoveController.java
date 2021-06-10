package com.checkers.controllers;

import com.checkers.classes.MoveType;
import com.checkers.classes.Pawn;
import com.checkers.classes.Tile;
import com.checkers.controllers.GameController.*;

public class MoveController {

    private Pawn pawn;


    public MoveController(Pawn pawn) {
        this.pawn = pawn;
    }

    public void showAvalibleMoves(int x, int y) {
        System.out.println(pawn.getType());
        System.out.println(y + " | " + x);

        int fowardField = y + pawn.getMoveDir();
        int rightField = x + 1;
        int leftField = x - 1;

        System.out.println(fowardField + " X " + leftField);
        System.out.println(fowardField + " X " + rightField);
    }

}
