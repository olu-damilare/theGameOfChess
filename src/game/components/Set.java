package game.components;

import game.components.board.Board;
import game.components.board.Floor;
import game.pieces.*;
import game.pieces.king.King;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.properties.Colour.*;

public class Set {
    private final Board board;
    private final List<Piece> whitePieces = new ArrayList();
    private final List<Piece> blackPieces = new ArrayList();
    private boolean hasGeneratedBlackPieces;
    private boolean hasGeneratedWhitePieces;
    private final Player whitePiecePlayer;
    private final Player blackPiecePlayer;


    public Set(String firstPlayer, String secondPlayer) {
        board = new Board(8,8);

        Random randomizer = new Random();
        int selector = randomizer.nextInt(2);
        if(selector == 0) {
            this.whitePiecePlayer = new Player(firstPlayer, WHITE);
            this.blackPiecePlayer = new Player(secondPlayer, BLACK);
        }else{
            this.whitePiecePlayer = new Player(secondPlayer, WHITE);
            this.blackPiecePlayer = new Player(firstPlayer, BLACK);

        }
    }

    public Board getBoard() {
        return board;
    }

    public void generateBlackPawns(){
        for (int i = 1; i <= 8; i++) {
            Floor floor = board.getFloor(2, i);
            Piece pawn = new Pawn(BLACK, floor);
            blackPieces.add(pawn);
        }
    }

    public void generateWhitePawns(){
        for (int i = 1; i <= 8; i++) {
            Floor floor = board.getFloor(7, i);
            Piece pawn = new Pawn(WHITE, floor);
            whitePieces.add(pawn);
        }
    }

    public void generateWhiteRooks(){
        Floor leftFloor = board.getFloor(8, 1);
        Floor rightFloor = board.getFloor(8, 8);

        Rook leftRook = new Rook(WHITE, leftFloor);
        Rook rightRook = new Rook(WHITE, rightFloor);

        whitePieces.add(leftRook);
        whitePieces.add(rightRook);
    }

    public void generateBlackRooks(){
        Floor leftFloor = board.getFloor(1, 1);
        Floor rightFloor = board.getFloor(1, 8);

        Rook leftRook = new Rook(BLACK, leftFloor);
        Rook rightRook = new Rook(BLACK, rightFloor);

        blackPieces.add(leftRook);
        blackPieces.add(rightRook);
    }

    public void generateBlackKnights(){
        Floor leftFloor = board.getFloor(1, 2);
        Floor rightFloor = board.getFloor(1, 7);

        Knight leftKnight = new Knight(BLACK, leftFloor);
        Knight rightKnight = new Knight(BLACK, rightFloor);

        blackPieces.add(leftKnight);
        blackPieces.add(rightKnight);
    }

    public void generateWhiteKnights(){
        Floor leftFloor = board.getFloor(8, 2);
        Floor rightFloor = board.getFloor(8, 7);

        Knight leftKnight = new Knight(WHITE, leftFloor);
        Knight rightKnight = new Knight(WHITE, rightFloor);

        whitePieces.add(leftKnight);
        whitePieces.add(rightKnight);
    }

    public void generateWhiteBishops(){
        Floor leftFloor = board.getFloor(8, 3);
        Floor rightFloor = board.getFloor(8, 6);

        Bishop leftBishop = new Bishop(WHITE, leftFloor);
        Bishop rightBishop = new Bishop(WHITE, rightFloor);

        whitePieces.add(leftBishop);
        whitePieces.add(rightBishop);
    }

    public void generateBlackBishops(){
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

    public String displayBoard(){
        Floor[][] floors = board.getFloors();
        StringBuilder display = new StringBuilder();
        for (Floor[] floor : floors) {
            for (int j = 0; j < floor.length; j++) {
                if (floor[j].isOccupied()) {
                    Piece occupant = floor[j].getCurrentOccupant();
                    display.append(occupant.getPseudoName());
                } else {
                    display.append("nil");
                }
                if(j != floors[1].length - 1) display .append("\t\t");
            }
            display.append("\n");
        }
        return display.toString();
    }

    public Player getWhitePiecePlayer() {
        return whitePiecePlayer;
    }

    public Player getBlackPiecePlayer() {
        return blackPiecePlayer;
    }
}
