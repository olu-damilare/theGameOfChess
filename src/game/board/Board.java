package game.board;

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
}
