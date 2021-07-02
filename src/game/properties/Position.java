package game.properties;

public class Position {

    private int x_coordinate;
    private int y_coordinate;

    public Position(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x_coordinate == position.x_coordinate && y_coordinate == position.y_coordinate;
    }

    public int get_X_coordinate() {
        return x_coordinate;
    }

    public int get_Y_coordinate() {
        return y_coordinate;
    }

    public void increaseValueOfY_coordinateBy(int numberOfSteps) {
        y_coordinate += numberOfSteps;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x_coordinate=" + x_coordinate +
                ", y_coordinate=" + y_coordinate +
                '}';
    }

    public void decreaseValueOfX_coordinateBy(int numberOfSteps) {
        x_coordinate -= numberOfSteps;
    }

    public void increaseValueOfX_coordinateBy(int numberOfSteps) {
        x_coordinate += numberOfSteps;
    }
}
