package game.pieces;

import game.board.Board;
import game.board.Floor;
import game.gameExceptions.InvalidMoveException;
import game.properties.Colour;
import game.properties.Position;

public class Bishop extends Piece {
    public Bishop(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    public Bishop(Colour colour, Floor defaultFloor) {
        super(colour, defaultFloor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {
        validateMoveIsDiagonal(destinationFloor);
        boolean floorIsOccupied = destinationFloor.getCurrentOccupant() != null;
        validateForDestinationOccupant(destinationFloor, floorIsOccupied);

        boolean isUpperLeft = isUpperLeftDirection(destinationFloor);
        boolean isUpperRight = isUpperRightDirection(destinationFloor);
        boolean isLowerLeft = isLowerLeftDirection(destinationFloor);
        boolean isLowerRight = isLowerRightDirection(destinationFloor);

        validateForObstructionOfMove(destinationFloor, board, isUpperLeft, isUpperRight, isLowerLeft, isLowerRight);

        if(floorIsOccupied && destinationFloor.getCurrentOccupant().getColour() != getColour()){
            capture(destinationFloor.getCurrentOccupant());
        }

        updateFloorsStatus(destinationFloor);
    }

    private void validateForDestinationOccupant(Floor destinationFloor, boolean floorIsOccupied) {
        if(floorIsOccupied){
            if(destinationFloor.getCurrentOccupant().getColour() == getColour()){
                throw new InvalidMoveException("Invalid move");
            }
        }
    }

    private void validateForObstructionOfMove(Floor destinationFloor, Board board, boolean isUpperLeft, boolean isUpperRight, boolean isLowerLeft, boolean isLowerRight) {
        if(isUpperLeft){
            checkForObstructionInUpperLeft(destinationFloor, board);
        }else if(isUpperRight){
            checkForObstructionInUpperRight(destinationFloor, board);
        }else if(isLowerLeft){
            checkForObstructionInLowerLeft(destinationFloor, board);
        }else if(isLowerRight){
            checkForObstructionInLowerRight(destinationFloor, board);
        }
    }


    public void checkForObstructionInLowerRight(Floor destinationFloor, Board board) {
        int rankCounter = getCurrentFloor().getRank() - 1;
        int fileCounter = getCurrentFloor().getFile() + 1;

        while(rankCounter > destinationFloor.getRank()){
            Floor floor = board.getFloor(rankCounter, fileCounter);
            if(floor.isOccupied())
                throw new InvalidMoveException("Invalid move");
            rankCounter--;
            fileCounter++;
        }
    }

    public void checkForObstructionInLowerLeft(Floor destinationFloor, Board board) {
        int rankCounter = getCurrentFloor().getRank() - 1;
        int fileCounter = getCurrentFloor().getFile() - 1;

        while(rankCounter > destinationFloor.getRank()){
            Floor floor = board.getFloor(rankCounter, fileCounter);
            if(floor.isOccupied())
                throw new InvalidMoveException("Invalid move");
            rankCounter--;
            fileCounter--;
        }
    }

    public void checkForObstructionInUpperRight(Floor destinationFloor, Board board) {
        int rankCounter = getCurrentFloor().getRank() + 1;
        int fileCounter = getCurrentFloor().getFile() + 1;

        while(rankCounter < destinationFloor.getRank()){
            Floor floor = board.getFloor(rankCounter, fileCounter);
            if(floor.isOccupied())
                throw new InvalidMoveException("Invalid move");
            rankCounter++;
            fileCounter++;
        }
    }

    public void checkForObstructionInUpperLeft(Floor destinationFloor, Board board) {
        int rankCounter = getCurrentFloor().getRank() + 1;
        int fileCounter = getCurrentFloor().getFile() - 1;

        while(rankCounter < destinationFloor.getRank()){
            Floor floor = board.getFloor(rankCounter, fileCounter);
            if(floor.isOccupied())
                throw new InvalidMoveException("Invalid move");
        rankCounter++;
        fileCounter--;
        }
    }

    private boolean isLowerRightDirection(Floor destinationFloor) {
        return destinationFloor.getRank() < getCurrentFloor().getRank() &&
                destinationFloor.getFile() > getCurrentFloor().getFile();
    }

    private boolean isLowerLeftDirection(Floor destinationFloor) {
        return destinationFloor.getRank() < getCurrentFloor().getRank() &&
                destinationFloor.getFile() < getCurrentFloor().getFile();
    }

    private boolean isUpperRightDirection(Floor destinationFloor) {
        return destinationFloor.getRank() > getCurrentFloor().getRank() &&
                destinationFloor.getFile() > getCurrentFloor().getFile();
    }

    private boolean isUpperLeftDirection(Floor destinationFloor) {
        return destinationFloor.getRank() > getCurrentFloor().getRank() &&
                destinationFloor.getFile() < getCurrentFloor().getFile();
    }

    private void validateMoveIsDiagonal(Floor destinationFloor) {
        int rankDifference = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank());
        int fileDifference = Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile());
        boolean isDiagonalMove = fileDifference == rankDifference;
        if(!isDiagonalMove)
            throw new InvalidMoveException("Invalid move");
    }
}
