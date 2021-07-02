package game.properties;

import game.board.Floor;

import java.util.Objects;

public class Move {
    private final Floor previousFloor;
    private final Floor currentFloor;

    public Move(Floor previousFloor, Floor currentFloor) {
        this.previousFloor = previousFloor;
        this.currentFloor = currentFloor;
    }

    @Override
    public String toString() {
        return previousFloor + " " + currentFloor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(previousFloor, move.previousFloor) && Objects.equals(currentFloor, move.currentFloor);
    }


    public Floor getPreviousFloor() {
        return previousFloor;
    }
}
