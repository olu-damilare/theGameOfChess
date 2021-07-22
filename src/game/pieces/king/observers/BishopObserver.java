package game.pieces.king.observers;

import game.components.board.Board;
import game.components.board.Floor;
import game.pieces.king.King;

public class BishopObserver {

    private void scanForBishopCheckInUpperLeft(Board board, King king) {
        if (king.getCurrentFloor().getRank() < 8 && king.getCurrentFloor().getFile() > 1) {
            int rankCounter = king.getCurrentFloor().getRank() + 1;
            int fileCounter = king.getCurrentFloor().getFile() - 1;

            while (fileCounter >= 1 && rankCounter < 8) {
                Floor floor = board.getFloor(rankCounter, fileCounter);
                if (observeFloor(king, floor)) break;
                rankCounter++;
                fileCounter--;
            }
        }
    }

    private boolean observeFloor(King king, Floor floor) {
        if (floor.isOccupied()) {
            if (floor.getCurrentOccupant().getColour() == king.getColour()) {
                return true;
            } else if (floor.getCurrentOccupant().getClass().toString().equals("class game.pieces.Bishop")) {
                king.setChecked(true);
                return true;
            }
        }
        return false;
    }

    private void scanForBishopCheckInUpperRight(Board board, King king){
        if (king.getCurrentFloor().getRank() < 8 && king.getCurrentFloor().getFile() < 8) {
            int rankCounter = king.getCurrentFloor().getRank() + 1;
            int fileCounter = king.getCurrentFloor().getFile() + 1;

            while (fileCounter <= 8 && rankCounter <= 8) {
                Floor floor = board.getFloor(rankCounter, fileCounter);
                if (observeFloor(king, floor)) break;
                rankCounter++;
                fileCounter++;
            }
        }
    }

    private void scanForBishopCheckInLowerRight(Board board, King king) {
        if (king.getCurrentFloor().getRank() > 1 && king.getCurrentFloor().getFile() < 8) {
            int rankCounter = king.getCurrentFloor().getRank() - 1;
            int fileCounter = king.getCurrentFloor().getFile() + 1;

            while (rankCounter >= 1 && fileCounter <= 8) {
                Floor floor = board.getFloor(rankCounter, fileCounter);
                if (observeFloor(king, floor)) break;
                rankCounter--;
                fileCounter++;
            }
        }
    }

    private void scanForBishopCheckInLowerLeft(Board board, King king) {
        if (king.getCurrentFloor().getRank() > 1 && king.getCurrentFloor().getFile() > 1) {

            int rankCounter = king.getCurrentFloor().getRank() - 1;
            int fileCounter = king.getCurrentFloor().getFile() - 1;

            while (rankCounter >= 1 && fileCounter >= 1) {
                Floor floor = board.getFloor(rankCounter, fileCounter);
                if (observeFloor(king, floor)) break;
                rankCounter--;
                fileCounter--;
            }
        }
    }

    public void scanForCheck(Board board, King king){
        scanForBishopCheckInUpperLeft(board, king);
        scanForBishopCheckInUpperRight(board, king);
        scanForBishopCheckInLowerRight(board, king);
        scanForBishopCheckInLowerLeft(board, king);
    }
}
