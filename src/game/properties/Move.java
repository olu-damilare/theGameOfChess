package game.properties;

import game.components.board.Floor;

import java.time.LocalDateTime;
import java.util.Objects;

public class Move {
    private final Floor previousFloor;
    private final Floor currentFloor;
    private final LocalDateTime timeStamp;

    public Move(Floor previousFloor, Floor currentFloor) {
        this.previousFloor = previousFloor;
        this.currentFloor = currentFloor;
        timeStamp = LocalDateTime.now();
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
