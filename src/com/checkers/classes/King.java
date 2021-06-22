package com.checkers.classes;

import com.checkers.controllers.GameController;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static com.checkers.controllers.GameController.TILE_SIZE;

public class King extends Pawn{


    public King(PawnType type, int x, int y, GameController gameController, int oldX, int oldY) {
        super(type, x, y, gameController);

        this.setOldX(oldX);
        this.setOldY(oldY);

        this.setKingText();

        this.setKing(true);

        this.setOnMouseClicked(event ->{
            this.getMoveController().showAvalibleKingMoves(this.getOldX(), this.getOldY());
            });

    }

    private void setKingText() {
        Text text = new Text("KING");
        text.setTranslateX((TILE_SIZE * 0.3125 * 2) / 2.8) ;
        text.setTranslateY((TILE_SIZE * 0.26 * 2) / 2);
        text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        text.setFill(Color.WHITE);
        this.getChildren().addAll(text);
    }

}
