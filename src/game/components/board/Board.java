package game.components.board;

import java.util.Arrays;

public class Board {
    private Floor[][] floors;

    public Board(int numberOfRows, int numberOfColumns) {
        floors = new Floor[numberOfRows][numberOfColumns];
        for (int rank = 1; rank <= numberOfRows; rank++) {
            for (int file = 1; file <= numberOfColumns; file++) {
                floors[rank - 1][file - 1] = new Floor(rank, file);
            }
        }
    }

    public Floor[][] getFloors(){
        return floors;
    }

    public Floor getFloor(int rank, int file) {
        return floors[rank - 1][file - 1];
    }


    @Override
    public String toString() {
        String board = "";

        for (int i = 0; i < floors.length; i++) {
            for (int j = 0; j < floors[i].length; j++) {
                board += floors[i][j].getCurrentOccupant() + "\t";
                if(j == 7) board += "\n";
            }
        }
        return board;
    }
}
