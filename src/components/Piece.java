package components;

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
        currentFloor = floor;
        floor.setOccupant(this);
        floor.setIsOccupied(true);
    }

    public Colour getColour() {
        return colour;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void addMove(Move move){
        moves.push(move);
    }

    public Move getLastMove(){
        return moves.peek();
    }

    public void undoMove(){
        moves.pop();
    }

    public abstract void move(Floor destinationFloor);

    public boolean isCaptured(){
        return isCaptured;
    }

    public void capture(Piece capturedPiece){
        capturedPiece.setCapturedStatus(true);
        capturedPiece = null;
    }

    private void setCapturedStatus(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }
}
