package components;

import gameExceptions.InvalidMoveException;

public class King extends Piece {


    private boolean isChecked;
    private boolean hasMadeFirstMove;
    private boolean hasCastled;

    public King(Colour colour, Floor defaultFloor) {
        super(colour, defaultFloor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {
        validateMove(destinationFloor);

        if (destinationFloor.isOccupied()) {
            if (destinationFloor.getCurrentOccupant().getColour() == getColour()) {
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
        boolean northAndSouth = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank()) == 1 &&
                (destinationFloor.getFile() - getCurrentFloor().getFile() == 0);
        boolean eastAndWest = Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile()) == 1 &&
                (destinationFloor.getRank() - getCurrentFloor().getRank() == 0);

        int rankDifference = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank());
        int fileDifference = Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile());
        boolean isDiagonalMove = (fileDifference == 1) && (rankDifference == 1);

        if (!isDiagonalMove && !northAndSouth && !eastAndWest)
            throw new InvalidMoveException("Invalid move");
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void castle(Rook rook, Board board) {
        boolean queenSizeCastle = getCurrentFloor().getFile() > rook.getCurrentFloor().getFile();
        boolean kingSideCastle = getCurrentFloor().getFile() < rook.getCurrentFloor().getFile();

        if (hasMadeFirstMove || rook.hasMadeFirstMove()) {
            throw new InvalidMoveException("Invalid move");
        }


        if (queenSizeCastle) {
            Floor floor = board.getFloor(1, 3);
            assignFloor(floor);
            int rookFloorFile = 4;
            rook.castle(rookFloorFile, board);
        } else if (kingSideCastle) {
            Floor floor = board.getFloor(1, 7);
            assignFloor(floor);
            int rookFloorFile = 6;
            rook.castle(rookFloorFile, board);
        }
        hasMadeFirstMove = true;
        hasCastled = true;


    }

    public boolean hasMadeFirstMove() {
        return hasMadeFirstMove;
    }


    public boolean hasCastled() {
        return hasCastled;
    }

    public void scanForChecked(Board board) {
        scanForPawnCheck(board);
        scanForBishopCheck(board);
    }

    private void scanForPawnCheck(Board board) {
        Floor upperLeftFloor = board.getFloor(getCurrentFloor().getRank() + 1, getCurrentFloor().getFile() - 1);
        Floor upperRightFloor = board.getFloor(getCurrentFloor().getRank() + 1, getCurrentFloor().getFile() + 1);
        if (upperRightFloor.isOccupied()) {
            boolean upperRightCheck = upperRightFloor.getCurrentOccupant().getColour() != getColour() &&
                    upperRightFloor.getCurrentOccupant().getClass().toString().equals("class components.Pawn");
            if (upperRightCheck) {
                isChecked = true;
            }
        } else if (upperLeftFloor.isOccupied()) {
            boolean upperLeftCheck = upperLeftFloor.getCurrentOccupant().getColour() != getColour() &&
                    upperLeftFloor.getCurrentOccupant().getClass().toString().equals("components.Pawn");
            if (upperLeftCheck) {
                isChecked = true;
            }
        }
    }

    private void scanForBishopCheckInUpperLeft(Board board){
        int rankCounter = getCurrentFloor().getRank() + 1;
        int fileCounter = getCurrentFloor().getFile() - 1;

        while(fileCounter >= 1){
            Floor floor = board.getFloor(rankCounter, fileCounter);
            if(floor.isOccupied()){
                if(floor.getCurrentOccupant().getColour() == getColour()) {
                    break;
                }else if(floor.getCurrentOccupant().getClass().toString().equals("class components.Bishop")){
                    isChecked = true;
                    break;
                }
            }
            rankCounter++;
            fileCounter--;
        }
    }

    private void scanForBishopCheckInUpperRight(Board board){
        int rankCounter = getCurrentFloor().getRank() + 1;
        int fileCounter = getCurrentFloor().getFile() + 1;

        while(fileCounter <= 8){
            Floor floor = board.getFloor(rankCounter, fileCounter);
            if(floor.isOccupied()){
                if(floor.getCurrentOccupant().getColour() == getColour()) {
                    break;
                }else if(floor.getCurrentOccupant().getClass().toString().equals("class components.Bishop")){
                    isChecked = true;
                    break;
                }
            }
            rankCounter++;
            fileCounter++;
        }
    }

    private void scanForBishopCheckInLowerRight(Board board){
        int rankCounter = getCurrentFloor().getRank() - 1;
        int fileCounter = getCurrentFloor().getFile() + 1;

        while(rankCounter >= 1){
            Floor floor = board.getFloor(rankCounter, fileCounter);
            if(floor.isOccupied()){
                if(floor.getCurrentOccupant().getColour() == getColour()) {
                    break;
                }else if(floor.getCurrentOccupant().getClass().toString().equals("class components.Bishop")){
                    isChecked = true;
                    break;
                }
            }
            rankCounter--;
            fileCounter++;
        }
    }

    private void scanForBishopCheckInLowerLeft(Board board){
        int rankCounter = getCurrentFloor().getRank() - 1;
        int fileCounter = getCurrentFloor().getFile() - 1;

        while(rankCounter >= 1){
            Floor floor = board.getFloor(rankCounter, fileCounter);
            if(floor.isOccupied()){
                if(floor.getCurrentOccupant().getColour() == getColour()) {
                    break;
                }else if(floor.getCurrentOccupant().getClass().toString().equals("class components.Bishop")){
                    isChecked = true;
                    break;
                }
            }
            rankCounter--;
            fileCounter--;
        }
    }

    private void scanForBishopCheck(Board board){
        scanForBishopCheckInUpperLeft(board);
        scanForBishopCheckInUpperRight(board);
        scanForBishopCheckInLowerRight(board);
        scanForBishopCheckInLowerLeft(board);
    }
}
