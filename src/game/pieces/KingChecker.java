package components;

public class KingChecker {

    public void scanForPawnCheck(Board board, King king) {
        if (king.getCurrentFloor().getRank() < 8 && (king.getCurrentFloor().getFile() > 1 && king.getCurrentFloor().getFile() < 8)) {
            Floor upperLeftFloor = board.getFloor(king.getCurrentFloor().getRank() + 1, king.getCurrentFloor().getFile() - 1);
            Floor upperRightFloor = board.getFloor(king.getCurrentFloor().getRank() + 1, king.getCurrentFloor().getFile() + 1);
            if (upperRightFloor.isOccupied()) {
                boolean upperRightCheck = upperRightFloor.getCurrentOccupant().getColour() != king.getColour() &&
                        upperRightFloor.getCurrentOccupant().getClass().toString().equals("class components.Pawn");
                if (upperRightCheck) {
                    king.setChecked(true);
                }
            } else if (upperLeftFloor.isOccupied()) {
                boolean upperLeftCheck = upperLeftFloor.getCurrentOccupant().getColour() != king.getColour() &&
                        upperLeftFloor.getCurrentOccupant().getClass().toString().equals("class components.Pawn");
                if (upperLeftCheck) {
                    king.setChecked(true);
                }
            }
        }
    }

    private void scanForBishopCheckInUpperLeft(Board board, King king) {
        if (king.getCurrentFloor().getRank() < 8 && king.getCurrentFloor().getFile() > 1) {
            int rankCounter = king.getCurrentFloor().getRank() + 1;
            int fileCounter = king.getCurrentFloor().getFile() - 1;

            while (fileCounter >= 1 ) {
                Floor floor = board.getFloor(rankCounter, fileCounter);
                if (floor.isOccupied()) {
                    if (floor.getCurrentOccupant().getColour() == king.getColour()) {
                        break;
                    } else if (floor.getCurrentOccupant().getClass().toString().equals("class components.Bishop")) {
                        king.setChecked(true);
                        break;
                    }
                }
                rankCounter++;
                fileCounter--;
            }
        }
    }

    private void scanForBishopCheckInUpperRight(Board board, King king){
        if (king.getCurrentFloor().getRank() < 8 && king.getCurrentFloor().getFile() < 8) {
            int rankCounter = king.getCurrentFloor().getRank() + 1;
            int fileCounter = king.getCurrentFloor().getFile() + 1;

            while (fileCounter <= 8) {
                Floor floor = board.getFloor(rankCounter, fileCounter);
                if (floor.isOccupied()) {
                    if (floor.getCurrentOccupant().getColour() == king.getColour()) {
                        break;
                    } else if (floor.getCurrentOccupant().getClass().toString().equals("class components.Bishop")) {
                        king.setChecked(true);
                        break;
                    }
                }
                rankCounter++;
                fileCounter++;
            }
        }
    }

    private void scanForBishopCheckInLowerRight(Board board, King king) {
        if (king.getCurrentFloor().getRank() > 1 && king.getCurrentFloor().getFile() < 8) {
            int rankCounter = king.getCurrentFloor().getRank() - 1;
            int fileCounter = king.getCurrentFloor().getFile() + 1;

            while (rankCounter >= 1) {
                Floor floor = board.getFloor(rankCounter, fileCounter);
                if (floor.isOccupied()) {
                    if (floor.getCurrentOccupant().getColour() == king.getColour()) {
                        break;
                    } else if (floor.getCurrentOccupant().getClass().toString().equals("class components.Bishop")) {
                        king.setChecked(true);
                        break;
                    }
                }
                rankCounter--;
                fileCounter++;
            }
        }
    }

    private void scanForBishopCheckInLowerLeft(Board board, King king) {
        if (king.getCurrentFloor().getRank() > 1 && king.getCurrentFloor().getFile() > 1) {

            int rankCounter = king.getCurrentFloor().getRank() - 1;
            int fileCounter = king.getCurrentFloor().getFile() - 1;

            while (rankCounter >= 1) {
                Floor floor = board.getFloor(rankCounter, fileCounter);
                if (floor.isOccupied()) {
                    if (floor.getCurrentOccupant().getColour() == king.getColour()) {
                        break;
                    } else if (floor.getCurrentOccupant().getClass().toString().equals("class components.Bishop")) {
                        king.setChecked(true);
                        break;
                    }
                }
                rankCounter--;
                fileCounter--;
            }
        }
    }

    public void scanForBishopCheck(Board board, King king){
        scanForBishopCheckInUpperLeft(board, king);
        scanForBishopCheckInUpperRight(board, king);
        scanForBishopCheckInLowerRight(board, king);
        scanForBishopCheckInLowerLeft(board, king);
    }

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
                if (floor.getCurrentOccupant().getClass().toString().equals("class components.Knight"))
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

    public void scanForKnightCheck(Board board, King king){
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
