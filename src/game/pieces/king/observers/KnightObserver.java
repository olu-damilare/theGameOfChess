package game.pieces.king.observers;

import game.components.board.Board;
import game.components.board.Floor;
import game.pieces.king.King;

public class KnightObserver {

    private void scanForKnightCheckFromOneSquareForwardAndTwoSquaresToTheLeft(Board board, King king){
        if(king.getCurrentFloor().getRank() < 8 && king.getCurrentFloor().getFile() > 2) {
            int rank = king.getCurrentFloor().getRank() + 1;
            int file = king.getCurrentFloor().getFile() - 2;

            scanFloor(board, king, rank, file);
        }

    }

    private void scanForKnightCheckFromOneSquareBackwardAndTwoSquaresToTheLeft(Board board, King king){
        if(king.getCurrentFloor().getRank() > 1 && king.getCurrentFloor().getFile() > 2) {
            int rank = king.getCurrentFloor().getRank() - 1;
            int file = king.getCurrentFloor().getFile() - 2;

            scanFloor(board, king, rank, file);
        }

    }

    private void scanForKnightCheckFromTwoSquaresForwardAndOneSquareToTheLeft(Board board, King king){
        if(king.getCurrentFloor().getRank() < 7 && king.getCurrentFloor().getFile() > 1) {
            int rank = king.getCurrentFloor().getRank() + 2;
            int file = king.getCurrentFloor().getFile() - 1;

            scanFloor(board, king, rank, file);
        }

    }

    private void scanForKnightCheckFromTwoSquaresBackwardAndOneSquareToTheLeft(Board board, King king){
        if(king.getCurrentFloor().getRank() > 2 && king.getCurrentFloor().getFile() > 1) {
            int rank = king.getCurrentFloor().getRank() - 2;
            int file = king.getCurrentFloor().getFile() - 1;

            scanFloor(board, king, rank, file);
        }

    }

    private void scanFloor(Board board, King king, int rank, int file) {
        Floor floor = board.getFloor(rank, file);
        if (floor.isOccupied())
            if (floor.getCurrentOccupant().getColour() != king.getColour())
                if (floor.getCurrentOccupant().getClass().toString().equals("class game.pieces.Knight"))
                    king.setChecked(true);
    }

    private void scanForKnightCheckFromTwoSquaresForwardAndOneSquareToTheRight(Board board, King king){
        if(king.getCurrentFloor().getRank() < 7 && king.getCurrentFloor().getFile() < 8) {
            int rank = king.getCurrentFloor().getRank() + 2;
            int file = king.getCurrentFloor().getFile() + 1;

            scanFloor(board, king, rank, file);
        }

    }

    private void scanForKnightCheckFromTwoSquaresBackwardAndOneSquareToTheRight(Board board, King king){
        if(king.getCurrentFloor().getRank() > 2 && king.getCurrentFloor().getFile() < 8) {
            int rank = king.getCurrentFloor().getRank() - 2;
            int file = king.getCurrentFloor().getFile() + 1;

            scanFloor(board, king, rank, file);
        }

    }

    private void scanForKnightCheckFromOneSquareForwardAndTwoSquaresToTheRight(Board board, King king){
        if(king.getCurrentFloor().getRank() < 8 && king.getCurrentFloor().getFile() < 7) {
            int rank = king.getCurrentFloor().getRank() + 1;
            int file = king.getCurrentFloor().getFile() + 2;

            scanFloor(board, king, rank, file);
        }

    }

    private void scanForKnightCheckFromOneSquareBackwardAndTwoSquaresToTheRight(Board board, King king){
        if(king.getCurrentFloor().getRank() > 1 && king.getCurrentFloor().getFile() < 7) {
            int rank = king.getCurrentFloor().getRank() - 1;
            int file = king.getCurrentFloor().getFile() + 2;

            scanFloor(board, king, rank, file);
        }

    }

    public void scanForCheck(Board board, King king){
        scanForKnightCheckFromOneSquareForwardAndTwoSquaresToTheLeft(board, king);
        scanForKnightCheckFromTwoSquaresForwardAndOneSquareToTheLeft(board, king);
        scanForKnightCheckFromTwoSquaresForwardAndOneSquareToTheRight(board, king);
        scanForKnightCheckFromOneSquareForwardAndTwoSquaresToTheRight(board, king);
        scanForKnightCheckFromOneSquareBackwardAndTwoSquaresToTheRight(board, king);
        scanForKnightCheckFromTwoSquaresBackwardAndOneSquareToTheRight(board, king);
        scanForKnightCheckFromTwoSquaresBackwardAndOneSquareToTheLeft(board, king);
        scanForKnightCheckFromOneSquareBackwardAndTwoSquaresToTheLeft(board, king);
    }
}
