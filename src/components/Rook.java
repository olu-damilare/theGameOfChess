package components;

import gameExceptions.InvalidMoveException;

public class Rook extends Piece{
    public Rook(Colour colour, Floor floor) {
        super(colour, floor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {
        validateDirection(destinationFloor);

        if(destinationFloor.isOccupied()){
            if(destinationFloor.getCurrentOccupant().getColour() == getColour()){
                throw new InvalidMoveException("Invalid move");
            }
        }

        boolean isNorthDirection = isNorthDirection(destinationFloor);
        boolean isSouthDirection = isSouthDirection(destinationFloor);
        boolean isEastDirection = isEastDirection(destinationFloor);
        boolean isWestDirection = isWestDirection(destinationFloor);


        validateForMoveObstruction(destinationFloor, board, isNorthDirection, isSouthDirection, isEastDirection, isWestDirection);
        if(destinationFloor.isOccupied()){
            if(destinationFloor.getCurrentOccupant().getColour() != getColour()){
                capture(destinationFloor.getCurrentOccupant());
            }
        }
        updateFloorsStatus(destinationFloor);
    }

    private void validateForMoveObstruction(Floor destinationFloor, Board board, boolean isNorthDirection, boolean isSouthDirection, boolean isEastDirection, boolean isWestDirection) {
        if(isNorthDirection){
            checkForObstructionNorthWard(destinationFloor, board);
        }else if(isSouthDirection){
            checkForObstructionSouthWard(destinationFloor, board);
        }else if(isWestDirection){
            checkForObstructionWestWard(destinationFloor, board);
        } else if (isEastDirection) {
            checkForObstructionEastWard(destinationFloor, board);
        }
    }

    private void updateFloorsStatus(Floor destinationFloor) {
        Move move = new Move(getCurrentFloor(), destinationFloor);
        addMove(move);
        getCurrentFloor().setOccupant(null);
        getCurrentFloor().setOccupyStatus(false);
        assignFloor(destinationFloor);
    }

    private void checkForObstructionEastWard(Floor destinationFloor, Board board) {
        int rank = getCurrentFloor().getRank();
        int file = getCurrentFloor().getFile() + 1;

        while(file < destinationFloor.getFile()){
            Floor floor = board.getFloor(rank, file);
            if(floor.isOccupied()){
                throw new InvalidMoveException("Invalid move");
            }
            file++;
        }
    }

    private void checkForObstructionWestWard(Floor destinationFloor, Board board) {
        int rank = getCurrentFloor().getRank();
        int file = getCurrentFloor().getFile() - 1;

        while(file > destinationFloor.getFile()){
            Floor floor = board.getFloor(rank, file);
            if(floor.isOccupied()){
                throw new InvalidMoveException("Invalid move");
            }
            file--;
        }
    }

    private void checkForObstructionSouthWard(Floor destinationFloor, Board board) {
        int rank = getCurrentFloor().getRank() - 1;
        int file = getCurrentFloor().getFile();

        while(rank > destinationFloor.getRank()){
            Floor floor = board.getFloor(rank, file);
            if(floor.isOccupied()){
                throw new InvalidMoveException("Invalid move");
            }
            rank--;
        }
    }

    private void checkForObstructionNorthWard(Floor destinationFloor, Board board) {
        int rank = getCurrentFloor().getRank() + 1;
        int file = getCurrentFloor().getFile();

        while(rank < destinationFloor.getRank()){
            Floor floor = board.getFloor(rank, file);
            if(floor.isOccupied()){
                throw new InvalidMoveException("Invalid move");
            }
            rank++;
        }
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

    private void validateDirection(Floor destinationFloor) {
        int rankDifference = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank());
        int fileDifference = Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile());
        boolean isDiagonalMove = fileDifference == rankDifference;
        if(isDiagonalMove)
            throw new InvalidMoveException("Invalid move");
    }
}