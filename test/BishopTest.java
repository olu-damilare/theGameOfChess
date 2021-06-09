import components.Bishop;
import components.Board;
import components.Floor;
import components.Pawn;
import gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static components.Colour.BLACK;
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
        Floor bishopFloor = new Floor(1, 3);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        Floor destinationFloor = new Floor(3, 1);
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
        Floor bishopFloor = new Floor(1, 3);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        Floor destinationFloor = new Floor(5, 7);
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
        Floor bishopFloor = new Floor(4, 2);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        Floor destinationFloor = new Floor(1, 5);
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
        Floor bishopFloor = new Floor(5, 7);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        Floor destinationFloor = new Floor(1, 3);
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
        Floor bishopFloor = new Floor(3, 3);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        assertEquals(bishopFloor, bishop.getCurrentFloor());
        assertEquals(bishop, bishopFloor.getCurrentOccupant());
        assertTrue(bishopFloor.isOccupied());

        Floor firstFloor = new Floor(3, 5);
        Floor secondFloor = new Floor(3, 1);
        Floor thirdFloor = new Floor(5, 3);
        Floor fourthFloor = new Floor(1, 3);

        assertThrows(InvalidMoveException.class, ()-> bishop.move(firstFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(secondFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(thirdFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(fourthFloor, board));
    }

    @Test
    void testThatBishopCannotMoveToAFloorObstructedByAPiece(){
        Floor bishopFloor = new Floor(4, 4);
        Floor firstFloor = new Floor(5, 3);
        Floor secondFloor = new Floor(5, 5);
        Floor thirdFloor = new Floor(3, 3);
        Floor fourthFloor = new Floor(3,5);
        Pawn pawn = new Pawn(BLACK, firstFloor);
        Pawn secondPawn = new Pawn(BLACK, secondFloor);
        Pawn thirdPawn = new Pawn(BLACK, thirdFloor);
        Pawn fourthPawn = new Pawn(BLACK, fourthFloor);
        Bishop bishop = new Bishop(BLACK, bishopFloor);
        assertTrue(firstFloor.isOccupied());
        assertTrue(secondFloor.isOccupied());
        assertTrue(thirdFloor.isOccupied());
        assertTrue(fourthFloor.isOccupied());

        Floor fifthFloor = new Floor(6, 2);
        Floor sixthFloor = new Floor(6, 6);
        Floor seventhFloor = new Floor(2, 2);
        Floor eighthFloor = new Floor(2,6);
        System.out.println(fifthFloor);
        assertThrows(InvalidMoveException.class, ()-> bishop.move(fifthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(sixthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(seventhFloor, board));
        assertThrows(InvalidMoveException.class, ()-> bishop.move(eighthFloor, board));

    }
}
