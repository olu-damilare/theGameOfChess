package game.pieces.king.observers;

import game.components.board.Board;
import game.components.board.Floor;
import game.pieces.Queen;
import game.pieces.Rook;
import game.pieces.king.King;

public class RookObserver {

    private void scanForRookCheckNorthward(Board board, King king){
        if(king.getCurrentFloor().getRank() < 8) {
            int rankCounter = king.getCurrentFloor().getRank() + 1;
            int file = king.getCurrentFloor().getFile();

            while (rankCounter <= 8) {
                Floor floor = board.getFloor(rankCounter, file);
                if (floor.isOccupied()) {
                    boolean isChecked = observeFloor(king, floor);
                    if (isChecked) break;
                }
                rankCounter++;
            }
        }
    }

    private void scanForRookCheckSouthward(Board board, King king){
        if(king.getCurrentFloor().getRank() > 1) {
            int rankCounter = king.getCurrentFloor().getRank() - 1;
            int file = king.getCurrentFloor().getFile();

            while (rankCounter >= 1) {
                Floor floor = board.getFloor(rankCounter, file);
                if (floor.isOccupied()) {
                    boolean isChecked = observeFloor(king, floor);
                    if (isChecked) break;
                }
                rankCounter--;
            }
        }
    }

    private void scanForRookCheckEastward(Board board, King king){
        if(king.getCurrentFloor().getFile() < 8) {
            int rank = king.getCurrentFloor().getRank();
            int fileCounter = king.getCurrentFloor().getFile() + 1;

            while (fileCounter <= 8) {
                Floor floor = board.getFloor(rank, fileCounter);
                if (floor.isOccupied()) {
                    boolean isChecked = observeFloor(king, floor);
                    if (isChecked) break;
                }
                fileCounter++;
            }
        }
    }

    private void scanForRookCheckWestward(Board board, King king){
        if(king.getCurrentFloor().getFile() > 1) {
            int rank = king.getCurrentFloor().getRank();
            int fileCounter = king.getCurrentFloor().getFile() - 1;

            while (fileCounter >= 1) {
                Floor floor = board.getFloor(rank, fileCounter);
                if (floor.isOccupied()) {
                    boolean isChecked = observeFloor(king, floor);
                    if (isChecked) break;
                }
                fileCounter--;
            }
        }
    }

    private boolean observeFloor(King king, Floor floor) {
        if (floor.getCurrentOccupant().getColour() == king.getColour()) {
            return true;
        } else if (floor.getCurrentOccupant() instanceof Rook ||
                floor.getCurrentOccupant() instanceof Queen) {
            king.setChecked(true);
            return true;
        }
        return false;
    }

    public void scanForCheck(Board board, King king){
        scanForRookCheckNorthward(board, king);
        scanForRookCheckSouthward(board, king);
        scanForRookCheckEastward(board, king);
        scanForRookCheckWestward(board, king);
    }

}
