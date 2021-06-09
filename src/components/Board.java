package components;

public class Board {
    Floor[][] floors;

    public Board(int numberOfRows, int numberOfColumns) {
        floors = new Floor[numberOfRows][numberOfColumns];
        for (int rank = 0; rank < numberOfRows; rank++) {
            for (int file = 0; file < numberOfColumns; file++) {
                floors[rank][file] = new Floor(rank, file);
            }
        }
    }

    public Floor[][] getFloors(){
        return floors;
    }

    public Floor getFloor(int rank, int file) {
        return floors[rank][file];
    }
}
