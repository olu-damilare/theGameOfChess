package game.components;

import game.components.board.Board;
import game.components.board.Floor;
import game.gameExceptions.InvalidMoveException;
import game.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {

    private String username;
    private static int id;
    private Stack<Piece> moves;
    private List<Piece> capturedPieces;

    public Player(String username) {
        this.username = username;
        id++;
        moves = new Stack<>();
        capturedPieces = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void makeMove(Board board, Piece piece, Floor destinationFloor) {
        Piece capturedPiece = null;
        if(destinationFloor.isOccupied()) {
            if (destinationFloor.getCurrentOccupant().getColour() != piece.getColour())
                 capturedPiece = destinationFloor.getCurrentOccupant();
        }
        piece.move(destinationFloor, board);

        moves.push(piece);
        capturedPieces.add(capturedPiece);

    }

    public void undoMove() {
        Piece piece = moves.pop();
        piece.undoMove();

    }

    public void resetId(){
        id = 0;
    }
}
