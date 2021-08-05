package game.pieces.king;

import game.components.board.Board;
import game.gameExceptions.ChessGameExceptions;
import game.pieces.Piece;
import game.pieces.Rook;
import game.pieces.king.observers.KingObserver;
import game.properties.Colour;
import game.components.board.Floor;
import game.gameExceptions.InvalidMoveException;

public class King extends Piece {

    private boolean isChecked;
    private boolean hasMadeFirstMove;
    private boolean hasCastled;
    private final KingObserver observer;
    private boolean isCheckMated;

    public King(Colour colour, Floor defaultFloor) {

        super(colour, defaultFloor);
        observer = new KingObserver();
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

    @Override
    public String getPseudoName() {
        if(getColour() == Colour.BLACK)
            return "\u265A";
        else
            return "\u2654";
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
        boolean queenSideCastle = getCurrentFloor().getFile() > rook.getCurrentFloor().getFile();
        boolean kingSideCastle = getCurrentFloor().getFile() < rook.getCurrentFloor().getFile();

        if (hasMadeFirstMove || rook.hasMadeFirstMove()) {
            throw new InvalidMoveException("Invalid move");
        }

        if (queenSideCastle) {
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

    public boolean isCheckMated() {
        return isCheckMated;
    }

    public void counterCheckMove(Board board){
        int rank = getCurrentFloor().getRank();
        int file = getCurrentFloor().getFile();

        if(isChecked()) {
            if (file == 8 && (rank > 1 && rank < 8)) {
                scanForRightSideCheckMate(board, rank, file);
            } else if (file == 1 && (rank > 1 && rank < 8)) {
                scanForLeftSideCheckMate(board, rank, file);

            } else if (rank == 8 && (file > 1 && file < 8)) {
                scanForTopSideCheckMate(board, rank, file);
            }else if (rank == 1 && (file > 1 && file < 8)) {
                scanForDownSideCheckMate(board, rank, file);
            }else if(rank == 8 && file == 8){
                scanForTopRightEdgeCheckMate(board, rank, file);
            }else if(rank == 1 && file == 1){
                scanForBottomLeftEdgeCheckMate(board, rank, file);
            }else if(rank == 8 && file == 1){
                scanForTopLeftEdgeCheckMate(board, rank, file);
            }else if(rank == 1 && file == 8){
                scanForBottomRightEdgeCheckMate(board, rank, file);
            }else scanForCentreBoardCheckMate(board, rank, file);
        }
    }

    private void scanForDownSideCheckMate(Board board, int rank, int file) {
        Floor[] downSidePositionValidFloors = {board.getFloor(rank, file + 1), board.getFloor(rank + 1, file + 1),
                board.getFloor(rank + 1, file), board.getFloor(rank + 1, file - 1),
                board.getFloor(rank, file - 1)};
        for (int i = 0; i < downSidePositionValidFloors.length; i++) {
            try {
                move(downSidePositionValidFloors[i], board);
                scanForChecked(board);
                if (isChecked()) undoMove();
                else break;
            } catch (ChessGameExceptions ignored) {
            }
        }
        if (isChecked()) isCheckMated = true;
    }

    private void scanForTopSideCheckMate(Board board, int rank, int file) {
        Floor[] topSidePositionValidFloors = {board.getFloor(rank, file + 1), board.getFloor(rank - 1, file + 1),
                board.getFloor(rank - 1, file), board.getFloor(rank - 1, file - 1),
                board.getFloor(rank, file - 1)};
        for (int i = 0; i < topSidePositionValidFloors.length; i++) {
            try {
                move(topSidePositionValidFloors[i], board);
                scanForChecked(board);
                if (isChecked()) undoMove();
                else break;
            } catch (ChessGameExceptions ignored) {
            }
        }
        if (isChecked()) isCheckMated = true;
    }

    private void scanForLeftSideCheckMate(Board board, int rank, int file) {
        Floor[] leftSidePositionValidFloors = {board.getFloor(rank + 1, file), board.getFloor(rank + 1, file + 1),
                board.getFloor(rank, file + 1), board.getFloor(rank - 1, file + 1),
                board.getFloor(rank - 1, file)};
        for (int i = 0; i < leftSidePositionValidFloors.length; i++) {
            try {
                move(leftSidePositionValidFloors[i], board);
                scanForChecked(board);
                if (isChecked()) undoMove();
                else break;
            } catch (ChessGameExceptions ignored) {
            }
        }
        if (isChecked()) isCheckMated = true;
    }

    private void scanForRightSideCheckMate(Board board, int rank, int file) {
        Floor[] rightSidePositionValidFloors = {board.getFloor(rank + 1, file), board.getFloor(rank + 1, file - 1),
                board.getFloor(rank, file - 1), board.getFloor(rank - 1, file - 1),
                board.getFloor(rank - 1, file)};
        for (int i = 0; i < rightSidePositionValidFloors.length; i++) {
            try {
                move(rightSidePositionValidFloors[i], board);
                scanForChecked(board);
                if (isChecked()) undoMove();
                else break;
            } catch (ChessGameExceptions ignored) {
            }
        }
        if (isChecked()) isCheckMated = true;
    }

    private void scanForTopRightEdgeCheckMate(Board board, int rank, int file) {
        Floor[] rightSidePositionValidFloors = {board.getFloor(rank, file - 1), board.getFloor(rank - 1, file - 1),
                board.getFloor(rank - 1, file)};
        for (int i = 0; i < rightSidePositionValidFloors.length; i++) {
            try {
                move(rightSidePositionValidFloors[i], board);
                scanForChecked(board);
                if (isChecked()) undoMove();
                else break;
            } catch (ChessGameExceptions ignored) {
            }
        }
        if (isChecked()) isCheckMated = true;
    }

    private void scanForBottomLeftEdgeCheckMate(Board board, int rank, int file) {
        Floor[] rightSidePositionValidFloors = {board.getFloor(rank, file + 1), board.getFloor(rank + 1, file + 1),
                board.getFloor(rank + 1, file)};
        for (int i = 0; i < rightSidePositionValidFloors.length; i++) {
            try {
                move(rightSidePositionValidFloors[i], board);
                scanForChecked(board);
                if (isChecked()) undoMove();
                else break;
            } catch (ChessGameExceptions ignored) {
            }
        }
        if (isChecked()) isCheckMated = true;
    }

    private void scanForTopLeftEdgeCheckMate(Board board, int rank, int file) {
        Floor[] rightSidePositionValidFloors = {board.getFloor(rank, file + 1), board.getFloor(rank - 1, file + 1),
                board.getFloor(rank - 1, file)};
        for (int i = 0; i < rightSidePositionValidFloors.length; i++) {
            try {
                move(rightSidePositionValidFloors[i], board);
                scanForChecked(board);
                if (isChecked()) undoMove();
                else break;
            } catch (ChessGameExceptions ignored) {
            }
        }
        if (isChecked()) isCheckMated = true;
    }

    private void scanForBottomRightEdgeCheckMate(Board board, int rank, int file) {
        Floor[] rightSidePositionValidFloors = {board.getFloor(rank, file - 1), board.getFloor(rank + 1, file - 1),
                board.getFloor(rank + 1, file)};
        for (int i = 0; i < rightSidePositionValidFloors.length; i++) {
            try {
                move(rightSidePositionValidFloors[i], board);
                scanForChecked(board);
                if (isChecked()) undoMove();
                else break;
            } catch (ChessGameExceptions ignored) {
            }
        }
        if (isChecked()) isCheckMated = true;
    }

    private void scanForCentreBoardCheckMate(Board board, int rank, int file){
        scanForTopSideCheckMate(board, rank, file);
        scanForDownSideCheckMate(board, rank, file);
        if (isChecked()) isCheckMated = true;

    }


}



