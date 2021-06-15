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
//    static Group pawnGroup = new Group();

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Pawn selectedPawn = null;
    int testPawnNumber = 0;
    int testRep = 0;

    public GameController() {
        this.createContent();
    }


    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        if (++ testRep == 1){
            this.createBoard();
        }

        root.getChildren().addAll(this.tileGroup, this.pawnGroup);

        return root;
    }

    private void createBoard() {
        System.out.println("Powtorzenia petli : " + ++ testRep);
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                System.out.println(x + " T " + y);
                Tile tile = new Tile((x + y) % 2 == 0, x, y);

                this.board[x][y] = tile;

                this.tileGroup.getChildren().add(tile);


                Pawn pawn = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    pawn = this.createPawn(PawnType.RED, x, y);
//                    System.out.println(test++);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    pawn = this.createPawn(PawnType.GREEN, x, y);
//                    System.out.println(test++);
                }

                if (pawn != null) {
                    tile.setPawn(pawn);
                    System.out.println("Pionkik: " + ++ testPawnNumber);
                    this.pawnGroup.getChildren().add(pawn);
                }

            }
        }
    }


    private Pawn createPawn(PawnType type, int x, int y) {
        Pawn pawn = new Pawn(type , x, y, this);

        return pawn;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Pawn getSelectedPawn() {
        return selectedPawn;
    }

    public void setSelectedPawn(Pawn selectedPawn) {
        this.selectedPawn = selectedPawn;
    }

    public Group getPawnGroup () {
        return this.pawnGroup;
    }

}
