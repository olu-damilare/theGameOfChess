import components.*;
import gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static components.Colour.*;
import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    Board board;
    @BeforeEach
    void setUp(){
        board = new Board(8, 8);
    }

    @Test
    void testThatRookCanMoveEastward(){
        Floor floor = board.getFloor(1,1);
        Floor destinationFloor = board.getFloor(1,8);
        Rook rook = new Rook(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(rook, floor.getCurrentOccupant());
        assertEquals(floor, rook.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        rook.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(rook, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, rook.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatRookCanMoveWestward(){
        Floor floor = board.getFloor(1,8);
        Floor destinationFloor = board.getFloor(1,1);
        Rook rook = new Rook(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(rook, floor.getCurrentOccupant());
        assertEquals(floor, rook.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        rook.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(rook, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, rook.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatRookCanMoveNorthward(){
        Floor floor = board.getFloor(1,1);
        Floor destinationFloor = board.getFloor(8,1);
        Rook rook = new Rook(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(rook, floor.getCurrentOccupant());
        assertEquals(floor, rook.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        rook.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(rook, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, rook.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatRookCanMoveSouthward(){
        Floor floor = board.getFloor(1,1);
        Floor destinationFloor = board.getFloor(1,8);
        Rook rook = new Rook(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(rook, floor.getCurrentOccupant());
        assertEquals(floor, rook.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        rook.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(rook, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, rook.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatRookMovingDiagonally_throwsInvalidMoveException(){
        Floor rookFloor = board.getFloor(4,4);
        Floor firstFloor = board.getFloor(6,2);
        Floor secondFloor = board.getFloor(6,2);
        Floor thirdFloor = board.getFloor(6,2);
        Floor fourthFloor = board.getFloor(6,2);
        Rook rook = new Rook(BLACK, rookFloor);
        assertTrue(rookFloor.isOccupied());
        assertEquals(rook, rookFloor.getCurrentOccupant());
        assertEquals(rookFloor, rook.getCurrentFloor());

       assertThrows(InvalidMoveException.class, ()-> rook.move(firstFloor, board));
       assertThrows(InvalidMoveException.class, ()-> rook.move(secondFloor, board));
       assertThrows(InvalidMoveException.class, ()-> rook.move(thirdFloor, board));
       assertThrows(InvalidMoveException.class, ()-> rook.move(fourthFloor, board));
    }

    @Test
    void testThatRookMovingToFloorObstructedByAPiece_throwsInvalidMoveException(){
        Floor rookFloor = board.getFloor(4, 4);
        Floor firstFloor = board.getFloor(4, 5);
        Floor secondFloor = board.getFloor(4, 3);
        Floor thirdFloor = board.getFloor(5, 4);
        Floor fourthFloor = board.getFloor(3,4);
        Piece pawn = new Pawn(BLACK, firstFloor);
        Piece secondPawn = new Rook(BLACK, secondFloor);
        Piece thirdPawn = new Bishop(BLACK, thirdFloor);
        Piece fourthPawn = new Pawn(WHITE, fourthFloor);
        Rook rook = new Rook(BLACK, rookFloor);
        assertTrue(firstFloor.isOccupied());
        assertTrue(secondFloor.isOccupied());
        assertTrue(thirdFloor.isOccupied());
        assertTrue(fourthFloor.isOccupied());

        Floor fifthFloor = board.getFloor(4, 1);
        Floor sixthFloor = board.getFloor(4, 8);
        Floor seventhFloor = board.getFloor(1, 4);
        Floor eighthFloor = board.getFloor(8,4);

        assertThrows(InvalidMoveException.class, ()-> rook.move(fifthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> rook.move(sixthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> rook.move(seventhFloor, board));
        assertThrows(InvalidMoveException.class, ()-> rook.move(eighthFloor, board));
    }

    @Test
    void testThatRookCanCaptureEnemy(){
        Floor floor = board.getFloor(2,1);
        Floor enemyFloor = board.getFloor(2, 6);
        Rook rook = new Rook(BLACK, floor);
        Piece enemy = new Bishop(WHITE, enemyFloor);

        rook.move(enemyFloor, board);
        assertTrue(enemy.isCaptured());
        assertNull(floor.getCurrentOccupant());
        assertFalse(floor.isOccupied());
        assertEquals(rook, enemyFloor.getCurrentOccupant());
    }

    @Test
    void testThatRookMovingToFloorOccupiedByPieceOfMatchingColour_throwsInvalidMoveException(){
        Floor floor = board.getFloor(4, 4);
        Floor secondFloor = board.getFloor(6, 6);
        Rook rook = new Rook(BLACK, floor);
        Piece teamMate = new Pawn(BLACK, secondFloor);

        assertThrows(InvalidMoveException.class, ()-> rook.move(secondFloor, board));
    }

    @Test
    void testThatRookCanUndoMove(){
        Floor floor = board.getFloor(2, 2);
        assertEquals("b2", floor.toString());
        Floor secondFloor = board.getFloor(3, 2);
        assertEquals("b3", secondFloor.toString());
        Floor thirdFloor = board.getFloor(4, 2);
        assertEquals("b4", thirdFloor.toString());

        Rook rook = new Rook(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertEquals(floor, rook.getCurrentFloor());
        assertEquals(rook, floor.getCurrentOccupant());
        assertNull(secondFloor.getCurrentOccupant());

        rook.move(secondFloor, board);

        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, rook.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());

        Move move = new Move(floor, secondFloor);
        assertEquals("b2 b3", move.toString());
        assertEquals(move, rook.getLastMove());

        rook.move(thirdFloor, board);
        assertTrue(thirdFloor.isOccupied());
        assertEquals(thirdFloor, rook.getCurrentFloor());
        assertNull(secondFloor.getCurrentOccupant());

        Move secondMove = new Move(secondFloor, thirdFloor);
        assertEquals("b3 b4", secondMove.toString());
        assertEquals(secondMove, rook.getLastMove());

        rook.undoMove();
        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, rook.getCurrentFloor());
        assertEquals(rook, secondFloor.getCurrentOccupant());
        assertFalse(thirdFloor.isOccupied());
        assertEquals(move, rook.getLastMove());

    }
}
