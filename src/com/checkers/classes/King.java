package com.checkers.classes;

import com.checkers.controllers.GameController;
import javafx.scene.paint.Color;

public class King extends Pawn{


    public King(PawnType type, int x, int y, GameController gameController, int oldX, int oldY) {
        super(type, x, y, gameController);

        this.setOldX(oldX);
        this.setOldY(oldY);
        this.setKingColor();

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
