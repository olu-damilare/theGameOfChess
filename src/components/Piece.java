package components;

import gameExceptions.CapturedPieceQueryException;

import java.util.Stack;

public abstract class Piece {

    private final Colour colour;
    private Position currentPosition;
    private Stack<Move> moves;
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
        moves.pop();
    }

    public abstract void move(Floor destinationFloor);

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
}
