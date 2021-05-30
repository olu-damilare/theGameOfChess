package components;


public class Floor {

    private final char file;
    private int rank;
    private char[] files = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public Floor(Position position) {
        rank = position.get_Y_coordinate();
        file = files[position.get_X_coordinate() - 1];
    }
    public Floor(int rank, char file){
        this.rank = rank;
        this.file = file;
    }

    @Override
    public String toString() {
        return "" + file + rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return file == floor.file && rank == floor.rank;
    }

}
