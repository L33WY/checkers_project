package com.checkers.controllers;

import com.checkers.classes.Pawn;
import com.checkers.classes.PawnType;
import com.checkers.classes.Tile;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GameController {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private final Group tileGroup = new Group();
    private final Group pawnGroup = new Group();

    private ArrayList<Pawn> pawnsOnBoard = new ArrayList<>();

    private final Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Pawn selectedPawn = null;
    int testRep = 0;

    public GameController() { this.createContent(); }


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
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);

                this.board[x][y] = tile;

                this.tileGroup.getChildren().add(tile);


                Pawn pawn = null;

                if (y <= 2 && (x + y) % 2 != 0) {
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
        Pawn pawn = new Pawn(type , x, y, this);
        this.pawnsOnBoard.add(pawn);
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

    public ArrayList<Pawn> getPawnsOnBoard() {
        return pawnsOnBoard;
    }

        public void checkGameStatus (Pawn pawn) {
        List<Pawn> enemyPawns =
                pawn.getGameController().getPawnsOnBoard().stream().filter(p -> p.getType() != pawn.getType()).collect(Collectors.toList());
        if (enemyPawns.isEmpty()) {
            System.out.println("WYGRYWA: " + pawn.getType());
        }
    }

}
