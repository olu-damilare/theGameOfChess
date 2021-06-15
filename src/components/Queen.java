package components;

import gameExceptions.InvalidMoveException;

public class Queen extends Piece{
    public Queen(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    public Queen(Colour colour, Floor defaultFloor) {
        super(colour, defaultFloor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {


        getCurrentFloor().setOccupant(null);
        getCurrentFloor().setOccupyStatus(false);
        assignFloor(destinationFloor);
    }
}
