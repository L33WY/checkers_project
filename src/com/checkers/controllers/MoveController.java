package com.checkers.controllers;

import com.checkers.Main;
import com.checkers.classes.King;
import com.checkers.classes.Pawn;
import com.checkers.classes.PawnType;
import com.checkers.classes.Tile;

import java.awt.*;
import java.util.ArrayList;

import static com.checkers.controllers.GameController.TILE_SIZE;



public class MoveController {

    private Pawn pawn;
    private Tile[][] board;
//    public static Tile PREVIOUS_TILES[];
    public static ArrayList <Tile> PREVIOUS_TILES = new ArrayList<>();
    public static Pawn CURENT_PAWN;
    public static ArrayList<Tile> TARGET_TILES = new ArrayList<>();



    public MoveController(Pawn pawn) {
        this.pawn = pawn;
        this.board = pawn.getBoard();
    }

    public MoveController() {
    }

    public void showAvalibleMoves(int x, int y) {

        if (pawn.getSelectedPawn() != pawn) {
            hidePreviousFields();
        }
        CURENT_PAWN = pawn;
        pawn.setSelectedPawn(pawn);

        System.out.println("OldX: " + CURENT_PAWN.getOldX());
        System.out.println("OldY: " + CURENT_PAWN.getOldY());
        System.out.println("NewX: " + CURENT_PAWN.getNewX());
        System.out.println("NewY: " + CURENT_PAWN.getNewY());

        System.out.println(pawn.getType());
        System.out.println(x + " | " + y);

        int yField = y + pawn.getMoveDir();
        int xRightField = x + 1;
        int xLeftField = x - 1;

        System.out.println("Obecna poozycja: x: " + x + " y: " + y);

        if (checkValueX(xLeftField) && checkValueY(yField)) {
            if (board[xLeftField][yField].hasPawn()){
                validatePawn(xLeftField, yField, -1);
            } else {
                board[xLeftField][yField].showAvalibleField();
                PREVIOUS_TILES.add(board[xLeftField][yField]);
            }
        }
        if (checkValueX(xRightField) && checkValueY(yField)) {
            if (board[xRightField][yField].hasPawn()) {
                validatePawn(xRightField, yField, +1);
            } else {
                board[xRightField][yField].showAvalibleField();
                PREVIOUS_TILES.add(board[xRightField][yField]);
            }
        }

    }

    public void showAvalibleMoves(int x, int y, int xDirection) {

        if (pawn.getSelectedPawn() != pawn) {
            hidePreviousFields();
        }
        CURENT_PAWN = pawn;
        pawn.setSelectedPawn(pawn);

        System.out.println("OldX: " + CURENT_PAWN.getOldX());
        System.out.println("OldY: " + CURENT_PAWN.getOldY());
        System.out.println("NewX: " + CURENT_PAWN.getNewX());
        System.out.println("NewY: " + CURENT_PAWN.getNewY());


        int yField = y + pawn.getMoveDir();
        int xField = x + xDirection;

        if (checkValueX(xField) && checkValueY(yField)) {
            if (board[xField][yField].hasPawn()) {
                validatePawn(xField, yField, +1);
            } else {
                board[xField][yField].showAvalibleField();
                PREVIOUS_TILES.add(board[xField][yField]);
            }
        }

    }

    //Show moves for king
    public void showAvalibleKingMoves(int oldX, int oldY) {

        if (pawn.getSelectedPawn() != pawn) {
            hidePreviousFields();
        }
        CURENT_PAWN = pawn;
        pawn.setSelectedPawn(pawn);

        System.out.println("OldX: " + CURENT_PAWN.getOldX());
        System.out.println("OldY: " + CURENT_PAWN.getOldY());
        System.out.println("NewX: " + CURENT_PAWN.getNewX());
        System.out.println("NewY: " + CURENT_PAWN.getNewY());

        int xLeft = oldX -1;
        int xRight = oldX +1;


        //Y TOP
        for (int yTop = oldY+1; checkValueY(yTop); yTop++) {
            //TOP LEFT
            if (checkValueX(xLeft) && checkValueY(yTop)) {
                if (board[xLeft][yTop].hasPawn()){
//                    validatePawn(xLeftField, yField, -1);
                    System.out.println("Pole zajete" + xLeft + yTop);
                } else {
                    board[xLeft][yTop].showAvalibleField();
                    PREVIOUS_TILES.add(board[xLeft][yTop]);
                }
                xLeft--;
            }

            //TOP RIGHT
            if (checkValueX(xRight) && checkValueY(yTop)) {
                if (board[xRight][yTop].hasPawn()){
//                    validatePawn(xLeftField, yField, -1);
                    System.out.println("Pole zajete" + xRight + yTop);
                } else {
                    board[xRight][yTop].showAvalibleField();
                    PREVIOUS_TILES.add(board[xRight][yTop]);
                }
                xRight++;
            }
        }

        //Y BOTTOM
        xLeft = oldX - 1;
        xRight = oldX + 1;

        for (int yBottom = oldY-1; checkValueY(yBottom); yBottom--) {
            //BOTTOM LEFT
            if (checkValueX(xLeft) && checkValueY(yBottom)) {
                if (board[xLeft][yBottom].hasPawn()){
//                    validatePawn(xLeftField, yField, -1);
                    System.out.println("Pole zajete" + xLeft + yBottom);
                } else {
                    board[xLeft][yBottom].showAvalibleField();
                    PREVIOUS_TILES.add(board[xLeft][yBottom]);
                }
                xLeft--;
            }

            //BOTTOM RIGHT
            if (checkValueX(xRight) && checkValueY(yBottom)) {
                if (board[xRight][yBottom].hasPawn()){
//                    validatePawn(xLeftField, yField, -1);
                    System.out.println("Pole zajete" + xRight + yBottom);
                } else {
                    board[xRight][yBottom].showAvalibleField();
                    PREVIOUS_TILES.add(board[xRight][yBottom]);
                }
                xRight++;
            }
        }
    }


    // Check if next avalible field are in Array index range

    private boolean checkValueX(int x) {
        if (x > 7 || x < 0) {return false;}
        else return true;
    }
    private boolean checkValueY(int y) {
        if (y > 7 || y < 0) {return false;}
        else return true;
    }

    // Check if tile has a pawn

    public static void hidePreviousFields() {
        for (Tile tile : PREVIOUS_TILES) {
            tile.setTileColor(tile.getLight());
            tile.setAvalible(false);
        }
        PREVIOUS_TILES.clear();
        TARGET_TILES.clear();
    }

    public void movePawn(Tile tile, Boolean isAvalible, int xField, int yField) {

        if (isAvalible) {
            CURENT_PAWN.setNewPosition(xField, yField);

            tile.setPawn(CURENT_PAWN);

            CURENT_PAWN.getBoard()[CURENT_PAWN.getOldX()][CURENT_PAWN.getOldY()].removePawn();
            CURENT_PAWN.setNewPosition(xField, yField);
            CURENT_PAWN.relocate(xField * TILE_SIZE, yField * TILE_SIZE);

            for (Tile targetTile : TARGET_TILES) {
                if (targetTile.getPawn().getOldX() == xField+1 || targetTile.getPawn().getOldX() == xField-1) {
                    System.out.println("Usuwanie");

                    CURENT_PAWN.getGameController().getPawnGroup().getChildren().remove(targetTile.getPawn());
                    targetTile.removePawn();
                }
            }

            if (isReadyForKing() && (!CURENT_PAWN.isKing())) {
                System.out.println("usuwanie pod krola");
                //Creating new king pawn
                King king = new King(CURENT_PAWN.getType(), CURENT_PAWN.getNewX(), CURENT_PAWN.getNewY(),
                        CURENT_PAWN.getGameController(), CURENT_PAWN.getOldX(), CURENT_PAWN.getOldY());
                //Removing old pawn
                king.getGameController().getPawnGroup().getChildren().remove(king.getBoard()[king.getOldX()][king.getOldY()].getPawn());
                king.getBoard()[king.getOldX()][king.getOldY()].removePawn();

                //Placing king pawn on board
                CURENT_PAWN = king;
                CURENT_PAWN.getBoard()[CURENT_PAWN.getOldX()][CURENT_PAWN.getOldX()].setPawn(king);
                CURENT_PAWN.getGameController().getPawnGroup().getChildren().add(king);

                System.out.println("koniec tworzenia krola");
            }

            hidePreviousFields();
        }
    }

    private void validatePawn(int x, int y, int xDirection) {
        if (checkValueX(x + xDirection) && checkValueY(y + CURENT_PAWN.getMoveDir())) {
            if (CURENT_PAWN.getType() != board[x][y].getPawn().getType()) {

                if (board[x + xDirection][y + CURENT_PAWN.getMoveDir()].hasPawn()) {
                    System.out.println("Next pionek zajety");
                } else {
                    TARGET_TILES.add(board[x][y]);
                    System.out.println("Pionek do odstrzalu: " + x + " | " + y);
                    this.showAvalibleMoves(x, y, xDirection);
                }
            }
        }
    }


    private boolean isReadyForKing() {
        System.out.println("CURENT PAWN POSITION: " + CURENT_PAWN.getNewX() + CURENT_PAWN.getNewY());
        System.out.println(PawnType.GREEN == CURENT_PAWN.getType());
        if (CURENT_PAWN.getType() == PawnType.GREEN && CURENT_PAWN.getNewY() == 0) {
            return true;
        } if (CURENT_PAWN.getType() == PawnType.RED && CURENT_PAWN.getNewY() == 7) {
            return true;
        } else return false;
    }

}
