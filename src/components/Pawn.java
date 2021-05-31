package components;

import gameExceptions.InvalidMoveException;

public class Pawn extends Piece{

    private boolean hasMadeFirstMove;

    public Pawn(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    @Override
    public void move(Floor destinationFloor) {
        boolean oneStepMove = destinationFloor.getRank() - getCurrentPosition().get_Y_coordinate() == 1;
        boolean twoStepsMove = destinationFloor.getRank() - getCurrentPosition().get_Y_coordinate() == 2;
        Piece destinationOccupant = destinationFloor.getCurrentOccupant();
        boolean matchingColours = destinationOccupant != null && destinationFloor.getCurrentOccupant().getColour().equals(getColour());
        boolean toCapture = oneStepMove && Math.abs(getCurrentFloor().getFile() - destinationFloor.getFile()) == 1 && !matchingColours;
        boolean invalidCapture = oneStepMove && Math.abs(getCurrentFloor().getFile() - destinationFloor.getFile()) == 1 && destinationFloor.getCurrentOccupant() == null;
        if(!hasMadeFirstMove && !(oneStepMove || twoStepsMove)){
            throw new InvalidMoveException("Invalid move");
        }else if(hasMadeFirstMove && !oneStepMove) {
            throw new InvalidMoveException("Invalid move");

        }else if(invalidCapture){
            throw new InvalidMoveException("Invalid move");
        }

        if(!hasMadeFirstMove){
            if(oneStepMove){
                getCurrentPosition().increaseValueOfY_coordinateBy(1);
            }else if(twoStepsMove){
                getCurrentPosition().increaseValueOfY_coordinateBy(2);
            }
            hasMadeFirstMove = true;
        }else if(oneStepMove) {
            getCurrentPosition().increaseValueOfY_coordinateBy(1);
            destinationFloor.setIsOccupied(true);
        }
        updateFloorsStatus(destinationFloor);
    }

    private void updateFloorsStatus(Floor destinationFloor) {
        getCurrentFloor().setIsOccupied(false);
        getCurrentFloor().setOccupant(null);
        destinationFloor.setOccupant(this);
        destinationFloor.setIsOccupied(true);
    }

    public boolean hasMadeFirstMove() {
        return hasMadeFirstMove;
    }
}
