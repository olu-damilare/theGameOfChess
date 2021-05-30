package components;

import java.util.Arrays;
import java.util.Objects;

public class Floor {

    private final char file;
    private int rank;
    private char[] files = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public Floor(int x_coordinate, int y_coordinate) {
        rank = y_coordinate;
        file = files[x_coordinate - 1];
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
