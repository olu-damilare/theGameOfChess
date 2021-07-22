import game.components.board.Board;
import game.components.board.Floor;
import game.pieces.Bishop;
import game.pieces.Pawn;
import game.pieces.Piece;
import game.properties.Move;
import game.gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static game.properties.Colour.*;
import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {
    Board board;
    @BeforeEach
    void setUp(){
        board = new Board(8,8);
    }

    @AfterEach
    void tearDown(){
        board = null;
    }

    @Test
    void testThatBishopCanMoveDiagonallyThroughUpperLeftDirection(){
        Floor bishopFloor = board.getFloor(1, 3);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        Floor destinationFloor = board.getFloor(3, 1);
        assertEquals(bishopFloor, bishop.getCurrentFloor());
        assertEquals(bishop, bishopFloor.getCurrentOccupant());
        assertNull(destinationFloor.getCurrentOccupant());
        assertTrue(bishopFloor.isOccupied());
        assertFalse(destinationFloor.isOccupied());

        bishop.move(destinationFloor, board);
        assertEquals(destinationFloor, bishop.getCurrentFloor());
        assertEquals(bishop, destinationFloor.getCurrentOccupant());
        assertNull(bishopFloor.getCurrentOccupant());
        assertFalse(bishopFloor.isOccupied());
        assertTrue(destinationFloor.isOccupied());
    }

    @Test
    void testThatBishopCanMoveDiagonallyThroughUpperRightDirection(){
        Floor bishopFloor = board.getFloor(1, 3);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        Floor destinationFloor = board.getFloor(5, 7);
        assertEquals(bishopFloor, bishop.getCurrentFloor());
        assertEquals(bishop, bishopFloor.getCurrentOccupant());
        assertNull(destinationFloor.getCurrentOccupant());
        assertTrue(bishopFloor.isOccupied());
        assertFalse(destinationFloor.isOccupied());

        bishop.move(destinationFloor, board);
        assertEquals(destinationFloor, bishop.getCurrentFloor());
        assertEquals(bishop, destinationFloor.getCurrentOccupant());
        assertNull(bishopFloor.getCurrentOccupant());
        assertFalse(bishopFloor.isOccupied());
        assertTrue(destinationFloor.isOccupied());
    }

    @Test
    void testThatBishopCanMoveDiagonallyThroughLowerRightDirection(){
        Floor bishopFloor = board.getFloor(4, 2);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        Floor destinationFloor = board.getFloor(1, 5);
        assertEquals(bishopFloor, bishop.getCurrentFloor());
        assertEquals(bishop, bishopFloor.getCurrentOccupant());
        assertNull(destinationFloor.getCurrentOccupant());
        assertTrue(bishopFloor.isOccupied());
        assertFalse(destinationFloor.isOccupied());

        bishop.move(destinationFloor, board);
        assertEquals(destinationFloor, bishop.getCurrentFloor());
        assertEquals(bishop, destinationFloor.getCurrentOccupant());
        assertNull(bishopFloor.getCurrentOccupant());
        assertFalse(bishopFloor.isOccupied());
        assertTrue(destinationFloor.isOccupied());
    }

    @Test
    void testThatBishopCanMoveDiagonallyThroughLowerLeftDirection(){
        Floor bishopFloor = board.getFloor(5, 7);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        Floor destinationFloor = board.getFloor(1, 3);
        assertEquals(bishopFloor, bishop.getCurrentFloor());
        assertEquals(bishop, bishopFloor.getCurrentOccupant());
        assertNull(destinationFloor.getCurrentOccupant());
        assertTrue(bishopFloor.isOccupied());
        assertFalse(destinationFloor.isOccupied());

        bishop.move(destinationFloor, board);
        assertEquals(destinationFloor, bishop.getCurrentFloor());
        assertEquals(bishop, destinationFloor.getCurrentOccupant());
        assertNull(bishopFloor.getCurrentOccupant());
        assertFalse(bishopFloor.isOccupied());
        assertTrue(destinationFloor.isOccupied());
    }

    @Test
    void testThatBishopMovingInNonDiagonalDirection_throwsInvalidMoveException(){
        Floor bishopFloor = board.getFloor(3, 3);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        assertEquals(bishopFloor, bishop.getCurrentFloor());
        assertEquals(bishop, bishopFloor.getCurrentOccupant());
        assertTrue(bishopFloor.isOccupied());

        Floor firstFloor = board.getFloor(3, 5);
        Floor secondFloor = board.getFloor(3, 1);
        Floor thirdFloor = board.getFloor(5, 3);
        Floor fourthFloor = board.getFloor(1, 3);

        assertThrows(InvalidMoveException.class, ()-> bishop.move(firstFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(secondFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(thirdFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(fourthFloor, board));
    }

    @Test
    void testThatBishopMovingToAFloorObstructedByAPiece_throwsInvalidMoveException(){
        Floor bishopFloor = board.getFloor(4, 4);
        Floor firstFloor = board.getFloor(5, 3);
        Floor secondFloor = board.getFloor(5, 5);
        Floor thirdFloor = board.getFloor(3, 3);
        Floor fourthFloor = board.getFloor(3,5);
        Pawn pawn = new Pawn(BLACK, firstFloor);
        Pawn secondPawn = new Pawn(BLACK, secondFloor);
        Pawn thirdPawn = new Pawn(BLACK, thirdFloor);
        Pawn fourthPawn = new Pawn(BLACK, fourthFloor);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        assertTrue(firstFloor.isOccupied());
        assertTrue(secondFloor.isOccupied());
        assertTrue(thirdFloor.isOccupied());
        assertTrue(fourthFloor.isOccupied());

        Floor fifthFloor = board.getFloor(6, 2);
        Floor sixthFloor = board.getFloor(6, 6);
        Floor seventhFloor = board.getFloor(2, 2);
        Floor eighthFloor = board.getFloor(2,6);
        assertThrows(InvalidMoveException.class, ()-> bishop.move(fifthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(sixthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(seventhFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(eighthFloor, board));
    }

    @Test
    void testThatBishopCanCaptureEnemy(){
        Floor floor = board.getFloor(4, 4);
        Floor enemyFloor = board.getFloor(6, 6);
        Bishop bishop = new Bishop(BLACK, floor);
        Piece enemyPiece = new Pawn(WHITE, enemyFloor);

        bishop.move(enemyFloor, board);

        assertTrue(enemyPiece.isCaptured());
        assertNull(floor.getCurrentOccupant());
        assertFalse(floor.isOccupied());
        assertEquals(bishop, enemyFloor.getCurrentOccupant());
    }

    @Test
    void testThatBishopMovingToFloorOccupiedByPieceOfMatchingColour_throwsInvalidMoveException(){
        Floor floor = board.getFloor(4, 4);
        Floor secondFloor = board.getFloor(6, 6);
        Bishop bishop = new Bishop(BLACK, floor);
        Piece teamMate = new Pawn(BLACK, secondFloor);

        assertThrows(InvalidMoveException.class, ()-> bishop.move(secondFloor, board));
    }

    @Test
    void testThatBishopCanUndoMove(){
        Floor floor = board.getFloor(2, 2);
        assertEquals("b2", floor.toString());
        Floor secondFloor = board.getFloor(3, 3);
        assertEquals("c3", secondFloor.toString());
        Floor thirdFloor = board.getFloor(4, 4);
        assertEquals("d4", thirdFloor.toString());

        Bishop bishop = new Bishop(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertEquals(floor, bishop.getCurrentFloor());
        assertEquals(bishop, floor.getCurrentOccupant());
        assertNull(secondFloor.getCurrentOccupant());

        bishop.move(secondFloor, board);

        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, bishop.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());

        Move move = new Move(floor, secondFloor);
        assertEquals("b2 c3", move.toString());
        assertEquals(move, bishop.getLastMove());

        bishop.move(thirdFloor, board);
        assertTrue(thirdFloor.isOccupied());
        assertEquals(thirdFloor, bishop.getCurrentFloor());
        assertNull(secondFloor.getCurrentOccupant());

        Move secondMove = new Move(secondFloor, thirdFloor);
        assertEquals("c3 d4", secondMove.toString());
        assertEquals(secondMove, bishop.getLastMove());

        bishop.undoMove();
        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, bishop.getCurrentFloor());
        assertEquals(bishop, secondFloor.getCurrentOccupant());
        assertFalse(thirdFloor.isOccupied());
        assertEquals(move, bishop.getLastMove());

    }


}
