package components;


public class Floor {

    private final int file;
    private int rank;
    private char[] files = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private boolean isOccupied;
    private Piece currentOccupant;

    public Floor(Position position) {
        rank = position.get_Y_coordinate();
        file = position.get_X_coordinate();
    }
    public Floor(int rank, int file){
        this.rank = rank;
        this.file = file;
    }

    @Override
    public String toString() {
        return "" + files[getFile() - 1] + rank;
    }

    public int getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return file == floor.file && rank == floor.rank;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupyStatus(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getRank() {
        return rank;
    }

    public Piece getCurrentOccupant() {
        return currentOccupant;
    }

    public void setOccupant(Piece piece) {
        currentOccupant = piece;
    }
}
