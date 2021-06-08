package com.checkers.controllers;

import com.checkers.classes.Pawn;
import com.checkers.classes.PawnType;
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

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

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

                this.board[x][y] = tile;

                this.tileGroup.getChildren().add(tile);

                Pawn pawn = null;

                if (y < 2 && (x + y) % 2 != 0) {
                    pawn = this.createPawn(PawnType.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    pawn = this.createPawn(PawnType.GREEN, x, y);
                }

                if (pawn != null) {
                    tile.setPawn(pawn);
                    this.pawnGroup.getChildren().add(pawn);
                }

            }
        }
    }

    private Pawn createPawn(PawnType type, int x, int y) {
        Pawn pawn = new Pawn(type , x, y);

        return pawn;
    }
}
