package com.checkers.classes;

import com.checkers.controllers.GameController;
import com.checkers.controllers.MoveController;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


import static com.checkers.controllers.GameController.TILE_SIZE;


public class Pawn extends StackPane {

    private PawnType type;
    private final Ellipse pawn = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
    private final GameController gameController;
    private int oldX, oldY, newX, newY;


    public Pawn(PawnType type, int x, int y,GameController gameController) {
        this.type = type;
        this.gameController = gameController;
        this.oldX = x;
        this.oldY = y;

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        this.pawn.setTranslateX((TILE_SIZE * 0.3125 * 2) / 2.8) ;
        this.pawn.setTranslateY((TILE_SIZE * 0.26 * 2) / 2);

        this.setPawnColor();

        this.getChildren().addAll(this.pawn);

        MoveController moveController = new MoveController(this);

        this.setOnMouseClicked(event ->{
            moveController.showAvalibleMoves(this.oldX, this.oldY);
        });

    }

    private void setPawnColor() {
        if (this.type == PawnType.RED) {
            this.pawn.setFill(Color.RED);
        } else {
            this.pawn.setFill(Color.GREEN);
        }
    }

    public int getMoveDir () {
        return this.type.moveDirection;
    }

    public PawnType getType() {
        return type;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setNewPosition(int newX, int newY) {
        this.oldX = newX;
        this.oldY = newY;
        this.newX = newX;
        this.newY = newY;
    }

    public Tile[][] getBoard() {
        return gameController.getBoard();
    }

    public Pawn getSelectedPawn() {
        return gameController.getSelectedPawn();
    }

    public void setSelectedPawn(Pawn pawn) {
        gameController.setSelectedPawn(pawn);
    }

}
