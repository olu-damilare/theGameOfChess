package components;

import java.util.Stack;

public class Piece {

    private final Colour colour;
    private Position currentPosition;
    private Stack<Move> moves;

    public Piece(Colour colour, Position defaultPosition) {
        this.colour = colour;
        currentPosition = defaultPosition;
    }

    public Colour getColour() {
        return colour;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Floor getCurrentFloor() {
        Floor floor = new Floor(currentPosition);
        return floor;
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

    public void move(){
        if(this.getClass().getName().equals("components.Pawn")){
            currentPosition.increaseValueOfY_coordinateBy(1);
        }
    };
}
