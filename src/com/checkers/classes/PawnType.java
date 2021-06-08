package com.checkers.classes;

public enum PawnType {
    RED(1), GREEN(-1);

    final int moveDirection;

    PawnType(int moveDirection) {
        this.moveDirection = moveDirection;
    }
}
