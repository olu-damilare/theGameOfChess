package game.pieces;

import game.components.board.Board;
import game.components.board.Floor;
import game.gameExceptions.CapturedPieceQueryException;
import game.properties.Colour;
import game.properties.Move;
import game.properties.Position;

import java.util.Stack;

public abstract class Piece {

    private final Colour colour;
    private Position currentPosition;
    private Stack<Move> moves = new Stack();
    private boolean isCaptured;
    private Floor currentFloor;

    public Piece(Colour colour, Position defaultPosition) {
        this.colour = colour;
        currentPosition = defaultPosition;
        Floor floor = new Floor(currentPosition);
        assignFloor(floor);
    }

    public Piece(Colour colour, Floor defaultFloor) {
        this.colour = colour;
        currentPosition = new Position(defaultFloor.getFile(), defaultFloor.getRank());
        assignFloor(defaultFloor);
    }

    public void assignFloor(Floor floor) {
        if(isCaptured)
            throw new CapturedPieceQueryException("Captured piece cannot be invoked");
        currentFloor = floor;
        floor.setOccupant(this);
        floor.setOccupyStatus(true);

    }

    public void updateFloorsStatus(Floor destinationFloor) {
        Move move = new Move(getCurrentFloor(), destinationFloor);
        addMove(move);
        getCurrentFloor().setOccupant(null);
        getCurrentFloor().setOccupyStatus(false);
        assignFloor(destinationFloor);
    }

    public Colour getColour() {
        if (isCaptured)
            throw new CapturedPieceQueryException("Captured piece cannot be invoked");
        return colour;
    }

    public Position getCurrentPosition() {
        if(isCaptured)
            throw new CapturedPieceQueryException("Captured piece cannot be invoked");
        return currentPosition;
    }

    public Floor getCurrentFloor() {
        if(isCaptured)
            throw new CapturedPieceQueryException("Captured piece cannot be invoked");
        return currentFloor;
    }

    public void addMove(Move move){
        if(isCaptured)
            throw new CapturedPieceQueryException("Captured piece cannot be invoked");
        moves.push(move);
    }

    public Move getLastMove(){
        if(isCaptured)
            throw new CapturedPieceQueryException("Captured piece cannot be invoked");
        return moves.peek();
    }

    public void undoMove(){
        if(isCaptured)
            throw new CapturedPieceQueryException("Captured piece cannot be invoked");
        getCurrentFloor().setOccupant(null);
        getCurrentFloor().setOccupyStatus(false);
        Move lastMove = moves.pop();
        assignFloor(lastMove.getPreviousFloor());
    }

    public abstract void move(Floor destinationFloor, Board board);

    public boolean isCaptured(){
        return isCaptured;
    }

    public void capture(Piece capturedPiece){
        if(isCaptured)
            throw new CapturedPieceQueryException("Captured piece cannot be invoked");
        capturedPiece.setCapturedStatus(true);
    }

    private void setCapturedStatus(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }

    @Override
    public String toString() {
        return  "" + colour + '\n' +
                isCaptured + '\n' +
                currentFloor;
    }

    public abstract String getPseudoName();
}
