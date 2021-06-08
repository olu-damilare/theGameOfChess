package components;

import gameExceptions.InvalidMoveException;

public class Bishop extends Piece {
    public Bishop(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    public Bishop(Colour colour, Floor defaultFloor) {
        super(colour, defaultFloor);
    }

    @Override
    public void move(Floor destinationFloor) {
        int rankDifference = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank());
        int fileDifference = Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile());
        if(fileDifference != rankDifference)
            throw new InvalidMoveException("Invalid move");
        boolean isUpperLeft = destinationFloor.getRank() > getCurrentFloor().getRank() && destinationFloor.getFile() < getCurrentFloor().getFile();
//        boolean isUpperRight = destinationFloor.getRank() > getCurrentFloor().getRank() && destinationFloor.getFile() > getCurrentFloor().getFile();
//        boolean isLowerLeft = destinationFloor.getRank() < getCurrentFloor().getRank() && destinationFloor.getFile() < getCurrentFloor().getFile();
//        boolean isLowerRight = destinationFloor.getRank() < getCurrentFloor().getRank() && destinationFloor.getFile() > getCurrentFloor().getFile();

        if(isUpperLeft){
            int rankCounter = getCurrentFloor().getRank() + 1;
            int fileCounter = getCurrentFloor().getFile() - 1;
            while(rankCounter < destinationFloor.getRank()){
                Floor floor = new Floor(rankCounter, fileCounter);
                if(floor.isOccupied())
                    throw new InvalidMoveException("Invalid move");
            rankCounter++;
            fileCounter--;
            }
        }

        getCurrentFloor().setOccupant(null);
        getCurrentFloor().setOccupyStatus(false);
        assignFloor(destinationFloor);
    }
}
