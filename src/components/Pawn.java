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
        boolean oneStepMove = isOneStepMove(destinationFloor);
        boolean twoStepMove = isTwoStepMove(destinationFloor);
        boolean occupiedFloor = destinationFloor.getCurrentOccupant() != null;
        boolean validMove = (hasMadeFirstMove && oneStepMove) || (!hasMadeFirstMove && (oneStepMove || twoStepMove))
                && !occupiedFloor;

        Piece enemy = null;
        boolean validCaptureMove = false;
        boolean matchingColours;

        if(occupiedFloor) {
            enemy = destinationFloor.getCurrentOccupant();
            matchingColours = enemy.getColour() == getColour();
            validCaptureMove = isValidCaptureMove(destinationFloor, matchingColours);
        }

        validateMove(validCaptureMove, validMove);

        if(!hasMadeFirstMove) {
            hasMadeFirstMove = true;
        }
        if(validCaptureMove){
            capture(enemy);
        }
        updateFloorsStatus(destinationFloor);
    }

    private void validateMove(boolean validCaptureMove, boolean validMove) {
        if(!validMove && !validCaptureMove) {
            throw new InvalidMoveException("Invalid move");
        }
    }

    private boolean isValidCaptureMove(Floor destinationFloor, boolean matchingColours) {
        boolean validCaptureMove;
        validCaptureMove = destinationFloor.getRank() - getCurrentFloor().getRank() == 1 &&
                (Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile()) == 1)
                && !matchingColours;
        return validCaptureMove;
    }

    private boolean isTwoStepMove(Floor destinationFloor) {
        boolean twoStepMove = destinationFloor.getRank() - getCurrentFloor().getRank() == 2 &&
                (destinationFloor.getFile() - getCurrentFloor().getFile() == 0);
        return twoStepMove;
    }

    private boolean isOneStepMove(Floor destinationFloor) {
        return (destinationFloor.getRank() - getCurrentFloor().getRank() == 1) &&
                    (destinationFloor.getFile() - getCurrentFloor().getFile() == 0);
    }

    private void updateFloorsStatus(Floor destinationFloor) {
        Move move = new Move(getCurrentFloor(), destinationFloor);
        addMove(move);
        getCurrentFloor().setOccupant(null);
        getCurrentFloor().setOccupyStatus(false);
        assignFloor(destinationFloor);
    }

    public boolean hasMadeFirstMove(){
        return hasMadeFirstMove;
    }

    @Override
    public String toString() {
        return "Pawn\n" + super.toString();
    }
}
