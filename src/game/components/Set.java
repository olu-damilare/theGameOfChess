package game.components;

import game.components.board.Board;
import game.components.board.Floor;
import game.pieces.*;
import game.pieces.king.King;

import java.util.ArrayList;
import java.util.List;

import static game.properties.Colour.*;

public class Set {
    private static Board board;
    private List<Piece> whitePieces = new ArrayList();
    private List<Piece> blackPieces = new ArrayList();
    private boolean hasGeneratedBlackPieces;
    private boolean hasGeneratedWhitePieces;


    public Set() {
        board = new Board(8,8);

    }

    public Board getBoard() {
        return board;
    }

    private void generateBlackPawns(){
        for (int i = 0; i < 8; i++) {
            Floor floor = board.getFloor(2, i);
            Piece pawn = new Pawn(BLACK, floor);
            blackPieces.add(pawn);
        }
    }

    private void generateWhitePawns(){
        for (int i = 0; i < 8; i++) {
            Floor floor = board.getFloor(7, i);
            Piece pawn = new Pawn(WHITE, floor);
            whitePieces.add(pawn);
        }
    }

    private void generateWhiteRooks(){
        Floor leftFloor = board.getFloor(8, 1);
        Floor rightFloor = board.getFloor(8, 8);

        Rook leftRook = new Rook(WHITE, leftFloor);
        Rook rightRook = new Rook(WHITE, rightFloor);

        whitePieces.add(leftRook);
        whitePieces.add(rightRook);
    }

    private void generateBlackRooks(){
        Floor leftFloor = board.getFloor(1, 1);
        Floor rightFloor = board.getFloor(1, 8);

        Rook leftRook = new Rook(BLACK, leftFloor);
        Rook rightRook = new Rook(BLACK, rightFloor);

        blackPieces.add(leftRook);
        blackPieces.add(rightRook);
    }

    private void generateBlackKnights(){
        Floor leftFloor = board.getFloor(1, 2);
        Floor rightFloor = board.getFloor(1, 7);

        Knight leftKnight = new Knight(BLACK, leftFloor);
        Knight rightKnight = new Knight(BLACK, rightFloor);

        blackPieces.add(leftKnight);
        blackPieces.add(rightKnight);
    }

    private void generateWhiteKnights(){
        Floor leftFloor = board.getFloor(8, 2);
        Floor rightFloor = board.getFloor(8, 7);

        Knight leftKnight = new Knight(WHITE, leftFloor);
        Knight rightKnight = new Knight(WHITE, rightFloor);

        whitePieces.add(leftKnight);
        whitePieces.add(rightKnight);
    }

    private void generateWhiteBishops(){
        Floor leftFloor = board.getFloor(8, 3);
        Floor rightFloor = board.getFloor(8, 6);

        Bishop leftBishop = new Bishop(WHITE, leftFloor);
        Bishop rightBishop = new Bishop(WHITE, rightFloor);

        whitePieces.add(leftBishop);
        whitePieces.add(rightBishop);
    }

    private void generateBlackBishops(){
        Floor leftFloor = board.getFloor(1, 3);
        Floor rightFloor = board.getFloor(1, 6);

        Bishop leftBishop = new Bishop(BLACK, leftFloor);
        Bishop rightBishop = new Bishop(BLACK, rightFloor);

        blackPieces.add(leftBishop);
        blackPieces.add(rightBishop);
    }

    public void generateBlackQueen(){
        Floor floor = board.getFloor(1, 4);
        Queen queen = new Queen(BLACK, floor);
        blackPieces.add(queen);
    }

    public void generateWhiteQueen(){
        Floor floor = board.getFloor(8, 4);
        Queen queen = new Queen(WHITE, floor);
        whitePieces.add(queen);
    }

    public void generateBlackKing(){
        Floor floor = board.getFloor(1, 5);
        King king = new King(BLACK, floor);
        blackPieces.add(king);
    }

    public void generateWhiteKing(){
        Floor floor = board.getFloor(8, 5);
        King king = new King(WHITE, floor);
        whitePieces.add(king);
    }

    public void generateBlackPieces(){
        if(!hasGeneratedBlackPieces) {
            generateBlackBishops();
            generateBlackKing();
            generateBlackKnights();
            generateBlackQueen();
            generateBlackPawns();
            generateBlackRooks();

            hasGeneratedBlackPieces = true;
        }

    }

    public void generateWhitePieces(){
        if(!hasGeneratedWhitePieces) {
            generateWhiteBishops();
            generateWhiteKing();
            generateWhiteKnights();
            generateWhiteQueen();
            generateWhitePawns();
            generateWhiteRooks();

            hasGeneratedWhitePieces = true;
        }
    }


    public static void main(String[] args) {
        Set set = new Set();
        set.generateWhiteKnights();
        System.out.println(set.getBoard());
    }


}
