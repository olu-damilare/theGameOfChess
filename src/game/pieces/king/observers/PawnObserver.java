package game.pieces.king.observers;

import game.components.board.Board;
import game.components.board.Floor;
import game.pieces.king.King;

public class PawnObserver {

    public void scanForCheck(Board board, King king) {
        boolean sideEdgeFloor = king.getCurrentFloor().getFile() == 1 || king.getCurrentFloor().getFile() == 8;
        boolean topEdgeFloor = king.getCurrentFloor().getRank() == 8;

        if (!topEdgeFloor && !sideEdgeFloor){
            Floor upperLeftFloor = board.getFloor(king.getCurrentFloor().getRank() + 1, king.getCurrentFloor().getFile() - 1);
            Floor upperRightFloor = board.getFloor(king.getCurrentFloor().getRank() + 1, king.getCurrentFloor().getFile() + 1);
            if (upperRightFloor.isOccupied()) {
                observeUpperRightFloor(king, upperRightFloor);
            } else if (upperLeftFloor.isOccupied()) {
                observeUpperLeftFloor(king, upperLeftFloor);
            }
        }
    }

    private void observeUpperLeftFloor(King king, Floor upperLeftFloor) {
        boolean upperLeftCheck = upperLeftFloor.getCurrentOccupant().getColour() != king.getColour() &&
                upperLeftFloor.getCurrentOccupant().getClass().toString().equals("class game.pieces.Pawn");
        if (upperLeftCheck) {
            king.setChecked(true);
        }
    }

    private void observeUpperRightFloor(King king, Floor upperRightFloor) {
        boolean upperRightCheck = upperRightFloor.getCurrentOccupant().getColour() != king.getColour() &&
                upperRightFloor.getCurrentOccupant().getClass().toString().equals("class game.pieces.Pawn");
        if (upperRightCheck) {
            king.setChecked(true);
        }
    }
}
