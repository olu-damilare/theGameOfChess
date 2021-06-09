package components;

import gameExceptions.InvalidMoveException;

public class Pawn extends Piece {

    private boolean hasMadeFirstMove;

    public Pawn(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    public Pawn(Colour colour, Floor floor) {
        super(colour, floor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {


    }
}
