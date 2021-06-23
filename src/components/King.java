package components;

import gameExceptions.InvalidMoveException;

public class King extends Piece {


    private boolean isChecked;
    private boolean hasMadeFirstMove;

    public King(Colour colour, Floor defaultFloor) {
        super(colour, defaultFloor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {
        validateMove(destinationFloor);

        if(destinationFloor.isOccupied()){
            if(destinationFloor.getCurrentOccupant().getColour() == getColour()){
                throw new InvalidMoveException("Invalid move.");
            }
            capture(destinationFloor.getCurrentOccupant());

        }
        if (!hasMadeFirstMove) {
            hasMadeFirstMove = true;
        }
        updateFloorsStatus(destinationFloor);
    }

    private void validateMove(Floor destinationFloor) {
        boolean northAndSouth =  Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank()) == 1 &&
                (destinationFloor.getFile() - getCurrentFloor().getFile() == 0);
        boolean eastAndWest =  Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile()) == 1 &&
                (destinationFloor.getRank() - getCurrentFloor().getRank() == 0);

        int rankDifference = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank());
        int fileDifference = Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile());
        boolean isDiagonalMove = (fileDifference == 1) && (rankDifference == 1);

        if(!isDiagonalMove && !northAndSouth && !eastAndWest)
            throw new InvalidMoveException("Invalid move");
    }
    
    public void setChecked(boolean checked){
        isChecked = checked;
    }

    public void castle(Rook rook, Board board) {

    }

    public boolean hasMadeFirstMove() {
        return hasMadeFirstMove;
    }
}
