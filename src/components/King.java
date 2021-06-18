package components;

import gameExceptions.InvalidMoveException;

public class King extends Piece {


    public King(Colour colour, Floor defaultFloor) {
        super(colour, defaultFloor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {
       boolean northAndSouth =  Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank()) == 1 &&
               (destinationFloor.getFile() - getCurrentFloor().getFile() == 0);
        boolean eastAndWest =  Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile()) == 1 &&
                (destinationFloor.getRank() - getCurrentFloor().getRank() == 0);

        int rankDifference = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank());
        int fileDifference = Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile());
        boolean isDiagonalMove = (fileDifference == 1) && (rankDifference == 1);
        if(!isDiagonalMove && !northAndSouth && !eastAndWest)
            throw new InvalidMoveException("Invalid move");


        updateFloorsStatus(destinationFloor);
    }
}
