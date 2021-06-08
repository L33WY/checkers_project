package com.checkers.controllers;

import com.checkers.classes.Tile;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;


public class GameController {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Group tileGroup = new Group();
    private Group pawnGroup = new Group();

    public GameController() {
        this.createContent();
    }


    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        this.createBoard();

        root.getChildren().addAll(this.tileGroup, this.pawnGroup);

        return root;
    }

    private void createBoard() {
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);

                this.tileGroup.getChildren().add(tile);
            }
        }
    }
}
