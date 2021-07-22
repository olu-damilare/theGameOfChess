package game.pieces;

import game.components.board.Board;
import game.components.board.Floor;
import game.gameExceptions.InvalidMoveException;
import game.properties.Colour;
import game.properties.Position;

public class Knight extends Piece{
    public Knight(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    public Knight(Colour colour, Floor defaultFloor) {
        super(colour, defaultFloor);
    }

    @Override
    public void move(Floor destinationFloor, Board board) {
        validateMoveDirection(destinationFloor);

        if(destinationFloor.isOccupied()){
            if(destinationFloor.getCurrentOccupant().getColour() != getColour())
                capture(destinationFloor.getCurrentOccupant());
            else
                throw new InvalidMoveException("Invalid move");
        }

        updateFloorsStatus(destinationFloor);
    }

    private void validateMoveDirection(Floor destinationFloor) {
        boolean isTwoSquaresFurtherVertically = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank()) == 2 &&
                Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile()) == 1;
        boolean isOneSquareFurtherVertically = Math.abs(destinationFloor.getRank() - getCurrentFloor().getRank()) == 1 &&
                Math.abs(destinationFloor.getFile() - getCurrentFloor().getFile()) == 2;

        if(!isOneSquareFurtherVertically && !isTwoSquaresFurtherVertically){
            throw new InvalidMoveException("Invalid move");
        }
    }

}
