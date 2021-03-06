package com.checkers.controllers;

import com.checkers.Main;
import com.checkers.classes.King;
import com.checkers.classes.Pawn;
import com.checkers.classes.PawnType;
import com.checkers.classes.Tile;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.checkers.controllers.GameController.TILE_SIZE;



public class MoveController {

    private Pawn pawn;
    private Tile[][] board;
    public static ArrayList <Tile> PREVIOUS_TILES = new ArrayList<>();
    public static Pawn CURENT_PAWN;
    public static ArrayList<Tile> TARGET_TILES = new ArrayList<>();



    public MoveController(Pawn pawn) {
        this.pawn = pawn;
        this.board = pawn.getBoard();
    }

    public MoveController() {
    }

    // Show moves for pawn

    public void showAvalibleMoves(int x, int y) {

        if (pawn.getSelectedPawn() != pawn) {
            hidePreviousFields();
        }
        CURENT_PAWN = pawn;
        pawn.setSelectedPawn(pawn);

        int yField = y + pawn.getMoveDir();
        int xRightField = x + 1;
        int xLeftField = x - 1;

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
        int yBack;
        if (yField > y) {
            yBack = -1;
        } else {
            yBack = 1;
        }

        showBackwardMoves(x, y, -1, yBack);
        showBackwardMoves(x, y, 1, yBack);
    }

    public void showAvalibleMoves(int x, int y, int xDirection) {

        if (pawn.getSelectedPawn() != pawn) {
            hidePreviousFields();
        }
        CURENT_PAWN = pawn;
        pawn.setSelectedPawn(pawn);

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

    private void showBackwardMoves(int x, int y, int xDirection, int yDirection) {
        if (pawn.getSelectedPawn() != pawn) {
            hidePreviousFields();
        }
        CURENT_PAWN = pawn;
        pawn.setSelectedPawn(pawn);

        int yField = y + yDirection;
        int xField = x + xDirection;

        if (checkValueX(xField) && checkValueY(yField)) {
            if (board[xField][yField].hasPawn()) {
                validatePawn(xField, yField, +1, xDirection);
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


        int xLeft = oldX -1;
        int xRight = oldX +1;

        boolean bl = false;
        boolean br = false;
        boolean tl = false;
        boolean tr = false;

        //DIRECTION: Y BOTTOM
        for (int yTop = oldY+1; checkValueY(yTop); yTop++) {
            //BOTTOM LEFT
            if (checkValueX(xLeft) && checkValueY(yTop)) {
                if (board[xLeft][yTop].hasPawn() && !bl){
                    validatePawn(xLeft, yTop, oldY, -1);
                    bl = true;

                } if (!bl) {
                    board[xLeft][yTop].showAvalibleField();
                    PREVIOUS_TILES.add(board[xLeft][yTop]);
                }
                xLeft--;
            }

            //BOTTOM RIGHT
            if (checkValueX(xRight) && checkValueY(yTop)) {
                if (board[xRight][yTop].hasPawn() && !br){
                    validatePawn(xRight, yTop, oldY, 1);
                    br = true;

                } if (!br) {
                    board[xRight][yTop].showAvalibleField();
                    PREVIOUS_TILES.add(board[xRight][yTop]);
                }
                xRight++;
            }
        }

        //DIRECTION: Y TOP
        xLeft = oldX - 1;
        xRight = oldX + 1;

        for (int yBottom = oldY-1; checkValueY(yBottom); yBottom--) {
            //TOP LEFT
            if (checkValueX(xLeft) && checkValueY(yBottom)) {
                if (board[xLeft][yBottom].hasPawn() && !tl){
                    validatePawn(xLeft, yBottom, oldY, -1);
                    tl = true;

                } if (!tl) {
                    board[xLeft][yBottom].showAvalibleField();
                    PREVIOUS_TILES.add(board[xLeft][yBottom]);
                }
                xLeft--;
            }

            //TOP RIGHT
            if (checkValueX(xRight) && checkValueY(yBottom)) {
                if (board[xRight][yBottom].hasPawn() && !tr){
                    validatePawn(xRight, yBottom, oldY, 1);
                    tr = true;

                } if (!tr) {
                    board[xRight][yBottom].showAvalibleField();
                    PREVIOUS_TILES.add(board[xRight][yBottom]);
                }
                xRight++;
            }
        }
    }


    public void showAvalibleKingMoves(int x, int y, int xDirection, int yDirection) {

        if (pawn.getSelectedPawn() != pawn) {
            hidePreviousFields();
        }
        CURENT_PAWN = pawn;
        pawn.setSelectedPawn(pawn);

        int yField = y + yDirection;
        int xField = x + xDirection;

        if (checkValueX(xField) && checkValueY(yField)) {
            if (board[xField][yField].hasPawn()) {
                validatePawn(xField, yField, +1, xDirection);
            } else {
                board[xField][yField].showAvalibleField();
                PREVIOUS_TILES.add(board[xField][yField]);
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

                    CURENT_PAWN.getGameController().getPawnsOnBoard().remove(targetTile.getPawn());
                    CURENT_PAWN.getGameController().getPawnGroup().getChildren().remove(targetTile.getPawn());
                    targetTile.removePawn();
                }
            }

            if (isReadyForKing() && (!CURENT_PAWN.isKing())) {

                //Creating new king pawn
                King king = new King(CURENT_PAWN.getType(), CURENT_PAWN.getNewX(), CURENT_PAWN.getNewY(),
                        CURENT_PAWN.getGameController(), CURENT_PAWN.getOldX(), CURENT_PAWN.getOldY());
                //Removing old pawn
                king.getGameController().getPawnGroup().getChildren().remove(king.getBoard()[king.getOldX()][king.getOldY()].getPawn());
                king.getBoard()[king.getOldX()][king.getOldY()].removePawn();
                CURENT_PAWN.getGameController().getPawnsOnBoard().remove(CURENT_PAWN);

                //Placing king pawn on board
                CURENT_PAWN = king;
                CURENT_PAWN.getBoard()[CURENT_PAWN.getOldX()][CURENT_PAWN.getOldX()].setPawn(king);
                CURENT_PAWN.getGameController().getPawnGroup().getChildren().add(king);
                CURENT_PAWN.getGameController().getPawnsOnBoard().add(CURENT_PAWN);

            }

            hidePreviousFields();
        }
        CURENT_PAWN.getGameController().checkGameStatus(CURENT_PAWN);
    }

    private void validatePawn(int x, int y, int xDirection) {
        if (checkValueX(x + xDirection) && checkValueY(y + CURENT_PAWN.getMoveDir())) {
            if (CURENT_PAWN.getType() != board[x][y].getPawn().getType()) {

                if (board[x + xDirection][y + CURENT_PAWN.getMoveDir()].hasPawn()) {

                } else {
                    TARGET_TILES.add(board[x][y]);
                    this.showAvalibleMoves(x, y, xDirection);
                }
            }
        }
    }

    private void validatePawn(int x, int y, int oldY, int xDirection) {
        int yDirection = getBackwardYDir(oldY, y);

        if (checkValueX(x + xDirection) && checkValueY(y + yDirection)) {
            if (CURENT_PAWN.getType() != board[x][y].getPawn().getType()) {

                if (!board[x + xDirection][y +yDirection].hasPawn()) {
                    TARGET_TILES.add(board[x][y]);
                    this.showAvalibleKingMoves(x, y, xDirection, yDirection);
                }
            }
        }

    }

    private int getBackwardYDir(int oldY, int y) {
        if (oldY - y > 0) return -1;
        if (oldY - y < 0) return 1;
        else return 0;
    }


    private boolean isReadyForKing() {
        if (CURENT_PAWN.getType() == PawnType.GREEN && CURENT_PAWN.getNewY() == 0) {
            return true;
        } if (CURENT_PAWN.getType() == PawnType.RED && CURENT_PAWN.getNewY() == 7) {
            return true;
        } else return false;
    }

}
