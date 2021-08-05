package game.pieces;

import game.components.board.Board;
import game.components.board.Floor;
import game.gameExceptions.InvalidMoveException;
import game.properties.Colour;
import game.properties.Position;

public class Queen extends Piece{
    public Queen(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    public Queen(Colour colour, Floor defaultFloor) {
        super(colour, defaultFloor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {
        validateDirection(destinationFloor);
        Bishop bishop  = new Bishop(Colour.BLACK, getCurrentFloor());
        Rook rook = new Rook(Colour.BLACK, getCurrentFloor());

        boolean isUpperLeft = isUpperLeftDirection(destinationFloor);
        boolean isUpperRight = isUpperRightDirection(destinationFloor);
        boolean isLowerLeft = isLowerLeftDirection(destinationFloor);
        boolean isLowerRight = isLowerRightDirection(destinationFloor);
        boolean isNorthDirection = isNorthDirection(destinationFloor);
        boolean isSouthDirection = isSouthDirection(destinationFloor);
        boolean isEastDirection = isEastDirection(destinationFloor);
        boolean isWestDirection = isWestDirection(destinationFloor);

        validateForMoveObstruction(destinationFloor, board, bishop, rook, isUpperLeft, isUpperRight, isLowerLeft,
                isLowerRight, isNorthDirection, isSouthDirection, isEastDirection, isWestDirection);

        if(destinationFloor.isOccupied()){
            if(destinationFloor.getCurrentOccupant().getColour() == getColour()){
                throw new InvalidMoveException("Invalid move");
            }
            capture(destinationFloor.getCurrentOccupant());
        }
        updateFloorsStatus(destinationFloor);

    }

    @Override
    public String getPseudoName() {
        if(getColour() == Colour.BLACK)
        return "\u265B";
        else
            return "\u2655";
    }

    private void validateForMoveObstruction(Floor destinationFloor, Board board, Bishop bishop, Rook rook, boolean isUpperLeft, boolean isUpperRight, boolean isLowerLeft, boolean isLowerRight, boolean isNorthDirection, boolean isSouthDirection, boolean isEastDirection, boolean isWestDirection) {
        if(isUpperLeft){
            bishop.checkForObstructionInUpperLeft(destinationFloor, board);
        }else if(isUpperRight){
            bishop.checkForObstructionInUpperRight(destinationFloor, board);
        }else if(isLowerRight){
            bishop.checkForObstructionInLowerRight(destinationFloor, board);
        }else if(isLowerLeft){
            bishop.checkForObstructionInLowerLeft(destinationFloor, board);
        }else {
            rook.validateForMoveObstruction(destinationFloor, board, isNorthDirection, isSouthDirection, isEastDirection, isWestDirection);
        }
    }



    private void validateDirection(Floor destinationFloor) {
        int rankDifference = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank());
        int fileDifference = Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile());
        boolean isDiagonalMove = fileDifference == rankDifference;
        boolean isVerticalMove = (Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank()) > 0) &&
                destinationFloor.getFile() == getCurrentFloor().getFile();
        boolean isHorizontalMove = (Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile()) > 0) &&
                destinationFloor.getRank() == getCurrentFloor().getRank();
        if(!isDiagonalMove && !isVerticalMove && !isHorizontalMove)
            throw new InvalidMoveException("Invalid move");
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

    private boolean isWestDirection(Floor destinationFloor) {
        return getCurrentFloor().getFile() - destinationFloor.getFile() > 0;
    }

    private boolean isEastDirection(Floor destinationFloor) {
        return destinationFloor.getFile() - getCurrentFloor().getFile() > 0;
    }

    private boolean isSouthDirection(Floor destinationFloor) {
        return getCurrentFloor().getRank() - destinationFloor.getRank() > 0;
    }

    private boolean isNorthDirection(Floor destinationFloor) {
        return destinationFloor.getRank() - getCurrentFloor().getRank() > 0;
    }


}
