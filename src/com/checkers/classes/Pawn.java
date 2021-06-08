package com.checkers.classes;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static com.checkers.controllers.GameController.TILE_SIZE;

public class Pawn extends StackPane {

    private PawnType type;
    private Ellipse pawn = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);

    public Pawn(PawnType type, int x, int y) {
        this.type = type;

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        this.pawn.setTranslateX((TILE_SIZE * 0.3125 * 2) / 2);
        this.pawn.setTranslateY((TILE_SIZE * 0.26 * 2) / 2);

//        Ellipse pawn = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        this.setPawnColor();

        this.getChildren().addAll(this.pawn);
    }

    private void setPawnColor() {
        if (this.type == PawnType.RED) {
            this.pawn.setFill(Color.RED);
        } else {
            this.pawn.setFill(Color.GREEN);
        }
    }
}
