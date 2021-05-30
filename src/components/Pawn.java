package components;

public class Pawn extends Piece{

    private boolean hasMadeFirstMove;

    public Pawn(Colour colour, Position defaultPosition) {
        super(colour, defaultPosition);
    }

    public boolean hasMadeFirstMove() {
        return hasMadeFirstMove;
    }
}
