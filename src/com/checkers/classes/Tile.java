package com.checkers.classes;

import com.checkers.controllers.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Pawn pawn;
    private Boolean isLight;

    public boolean hasPawn() {
        return this.pawn != null;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public Tile(Boolean isLight, int x, int y) {
        this.setTileParametrs();
        this.isLight = isLight;
        this.setTileColor(isLight);

        relocate(x * GameController.TILE_SIZE, y * GameController.TILE_SIZE);
    }

    private void setTileParametrs() {
        this.setWidth(GameController.TILE_SIZE);
        this.setHeight(GameController.TILE_SIZE);

    }

    public Boolean getLight() {
        return isLight;
    }

    public void setTileColor(boolean isLight) {
        if (isLight) {
            this.setFill(Color.rgb(210, 209, 209));
        } else {
            this.setFill(Color.rgb(51, 51, 51));
        }
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void showAvalibleField() {
            setFill(Color.rgb(102, 178, 255));
    }

}
