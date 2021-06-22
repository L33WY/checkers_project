package com.checkers.classes;

import com.checkers.controllers.GameController;
import javafx.scene.paint.Color;

public class King extends Pawn{


    public King(PawnType type, int x, int y, GameController gameController) {
        super(type, x, y, gameController);
    }

    public King(Pawn pawn) {
        super(pawn);
        this.setKingColor();
        this.setOldX(pawn.getOldX());
        this.setOldY(pawn.getOldY());
        this.setNewX(pawn.getNewX());
        this.setNewY(pawn.getNewY());

        this.setKing(true);

        this.setOnMouseClicked(event ->{
            System.out.println("jest krolem");
        });

    }

    private void setKingColor() {
        if (this.getType() == PawnType.RED) {
            this.getPawn().setFill(Color.GOLD);
        } else {
            this.getPawn().setFill(Color.GREEN);
        }
    }

}
