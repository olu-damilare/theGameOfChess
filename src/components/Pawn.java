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
        boolean oneStepMove = destinationFloor.getRank() - getCurrentFloor().getRank() == 1;
        boolean twoStepMove = destinationFloor.getRank() - getCurrentFloor().getRank() == 2;
        Piece enemy;
        boolean validCaptureMove = false;
        boolean matchingColours;

        if(destinationFloor.getCurrentOccupant() != null) {
            enemy = destinationFloor.getCurrentOccupant();
            matchingColours = enemy.getColour() == getColour();
            validCaptureMove = oneStepMove && (Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank()) == 1) && !matchingColours;
        }

        boolean validMove = (hasMadeFirstMove && oneStepMove) || (!hasMadeFirstMove && (oneStepMove || twoStepMove));


        if(!validMove ){
            throw new InvalidMoveException("Invalid move");
        }
        if(validCaptureMove){
            throw new InvalidMoveException("Invalid move");
        }



        if(!hasMadeFirstMove) {
                updateFloorsStatus(destinationFloor);
                hasMadeFirstMove = true;
        }else{
            updateFloorsStatus(destinationFloor);
        }

    }

    private void updateFloorsStatus(Floor destinationFloor) {
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
