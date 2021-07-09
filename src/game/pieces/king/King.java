package game.pieces;

import game.board.Board;
import game.properties.Colour;
import game.board.Floor;
import game.gameExceptions.InvalidMoveException;

public class King extends Piece {

    private boolean isChecked;
    private boolean hasMadeFirstMove;
    private boolean hasCastled;
    private KingChecker observer = new KingChecker();

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
        observer.scanForPawnCheck(board, this);
        observer.scanForBishopCheck(board, this);
        observer.scanForKnightCheck(board, this);
        observer.scanForRookCheck(board, this);


    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
