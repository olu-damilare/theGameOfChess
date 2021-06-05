package components;

import gameExceptions.InvalidMoveException;

public class Pawn extends Piece{

    private boolean hasMadeFirstMove;

    public Pawn(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    public Pawn(Colour colour, Floor floor) {
        super(colour, floor);
    }

    @Override
    public void move(Floor destinationFloor) {
        boolean oneStepMove = destinationFloor.getRank() - getCurrentFloor().getRank() == 1;
        boolean twoStepsMove = destinationFloor.getRank() - getCurrentFloor().getRank() == 2;
        Piece destinationOccupant = null;
        if(destinationFloor.getCurrentOccupant() != null)
        destinationOccupant = destinationFloor.getCurrentOccupant();
        boolean diagonalMove = oneStepMove && Math.abs(getCurrentFloor().getFile() - destinationFloor.getFile()) == 1;
        boolean matchingColours = destinationOccupant != null && destinationOccupant.getColour().equals(getColour());
        boolean toCapture = diagonalMove && !matchingColours;
        boolean invalidCapture = diagonalMove && (destinationOccupant == null || matchingColours);
        if(!hasMadeFirstMove  && !(oneStepMove || twoStepsMove)){
            throw new InvalidMoveException("Invalid move");
        }else if(hasMadeFirstMove && !oneStepMove) {
            throw new InvalidMoveException("Invalid move");

        }else if(invalidCapture){
            throw new InvalidMoveException("Invalid move");
        }

        if(!hasMadeFirstMove && !toCapture){
            if(oneStepMove){
                getCurrentPosition().increaseValueOfY_coordinateBy(1);
            }else if(twoStepsMove){
                getCurrentPosition().increaseValueOfY_coordinateBy(2);
            }
            hasMadeFirstMove = true;
        }else if(oneStepMove && !toCapture) {
            getCurrentPosition().increaseValueOfY_coordinateBy(1);
            destinationFloor.setOccupyStatus(true);
        }else if(toCapture){
            getCurrentPosition().increaseValueOfY_coordinateBy(1);
            if(destinationFloor.getFile() < getCurrentFloor().getFile()){
                getCurrentPosition().decreaseValueOfX_coordinateBy(1);
            }else{
                getCurrentPosition().increaseValueOfX_coordinateBy(1);
            }
            capture(destinationOccupant);

        }
        Move move = new Move(getCurrentFloor(), destinationFloor);
        addMove(move);
        updateFloorsStatus(destinationFloor);
    }

    private void updateFloorsStatus(Floor destinationFloor) {
        getCurrentFloor().setOccupyStatus(false);
        getCurrentFloor().setOccupant(null);
        assignFloor(destinationFloor);
    }

    public boolean hasMadeFirstMove() {
        return hasMadeFirstMove;
    }
}
