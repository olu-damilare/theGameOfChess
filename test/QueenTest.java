import components.*;
import gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static components.Colour.*;
import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

    Board board;
    @BeforeEach
    void setUp() {
        board = new Board(8,8);
    }

    @Test
    void testThatQueenCanOccupyAFloor(){
        Floor floor = board.getFloor(1, 4);
        assertFalse(floor.isOccupied());
        Queen queen = new Queen(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertEquals(queen, floor.getCurrentOccupant());
        assertEquals(floor, queen.getCurrentFloor());
    }

    @Test
    void testThatQueenCanMoveNorthWard(){
        Floor floor = board.getFloor(1,4);
        Floor destinationFloor = board.getFloor(8,4);
        Queen queen = new Queen(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(queen, floor.getCurrentOccupant());
        assertEquals(floor, queen.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        queen.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(queen, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, queen.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatQueenCanMoveSouthWard(){
        Floor floor = board.getFloor(1,1);
        Floor destinationFloor = board.getFloor(1,8);
        Queen queen = new Queen(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(queen, floor.getCurrentOccupant());
        assertEquals(floor, queen.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        queen.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(queen, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, queen.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatQueenCanMoveEastWard(){
        Floor floor = board.getFloor(1,1);
        Floor destinationFloor = board.getFloor(1,8);
        Queen queen = new Queen(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(queen, floor.getCurrentOccupant());
        assertEquals(floor, queen.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        queen.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(queen, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, queen.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatQueenCanMoveWestWard(){
        Floor floor = board.getFloor(1,8);
        Floor destinationFloor = board.getFloor(1,1);
        Queen queen = new Queen(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(queen, floor.getCurrentOccupant());
        assertEquals(floor, queen.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        queen.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(queen, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, queen.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());

    }

    @Test
    void testThatQueenCanMoveDiagonallyThroughUpperLeftDirection(){
        Floor floor = board.getFloor(1, 3);
        Queen queen = new Queen(BLACK, floor);
        Floor destinationFloor = board.getFloor(3, 1);
        assertEquals(floor, queen.getCurrentFloor());
        assertEquals(queen, floor.getCurrentOccupant());
        assertNull(destinationFloor.getCurrentOccupant());
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());

        queen.move(destinationFloor, board);
        assertEquals(destinationFloor, queen.getCurrentFloor());
        assertEquals(queen, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());
    }

    @Test
    void testThatQueenCanMoveDiagonallyThroughUpperRightDirection(){
        Floor floor = board.getFloor(1, 3);
        Queen queen = new Queen(BLACK, floor);
        Floor destinationFloor = board.getFloor(5, 7);
        assertEquals(floor, queen.getCurrentFloor());
        assertEquals(queen, floor.getCurrentOccupant());
        assertNull(destinationFloor.getCurrentOccupant());
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());

        queen.move(destinationFloor, board);
        assertEquals(destinationFloor, queen.getCurrentFloor());
        assertEquals(queen, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());
    }

    @Test
    void testThatQueenCanMoveDiagonallyThroughLowerRightDirection(){
        Floor floor = board.getFloor(4, 2);
        Queen queen = new Queen(BLACK, floor);
        Floor destinationFloor = board.getFloor(1, 5);
        assertEquals(floor, queen.getCurrentFloor());
        assertEquals(queen, floor.getCurrentOccupant());
        assertNull(destinationFloor.getCurrentOccupant());
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());

        queen.move(destinationFloor, board);
        assertEquals(destinationFloor, queen.getCurrentFloor());
        assertEquals(queen, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());
    }

    @Test
    void testThatQueenCanMoveDiagonallyThroughLowerLeftDirection(){
        Floor floor = board.getFloor(5, 7);
        Queen queen = new Queen(BLACK, floor);
        Floor destinationFloor = board.getFloor(1, 3);
        assertEquals(floor, queen.getCurrentFloor());
        assertEquals(queen, floor.getCurrentOccupant());
        assertNull(destinationFloor.getCurrentOccupant());
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());

        queen.move(destinationFloor, board);
        assertEquals(destinationFloor, queen.getCurrentFloor());
        assertEquals(queen, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());
    }

    @Test
    void testThatQueenMovingInANonLinearDirection_throwsInvalidMoveException(){
        Floor floor = board.getFloor(4,4);
        Queen queen = new Queen(WHITE, floor);
        Floor firstFloor = board.getFloor(5, 2);
        Floor secondFloor = board.getFloor(6, 3);
        Floor thirdFloor = board.getFloor(3, 2);
        Floor fourthFloor = board.getFloor(2, 3);
        Floor fifthFloor = board.getFloor(5, 6);
        Floor sixthFloor = board.getFloor(6, 5);
        Floor seventhFloor = board.getFloor(3, 6);
        Floor eighthFloor = board.getFloor(2, 5);

        assertThrows(InvalidMoveException.class, ()-> queen.move(firstFloor, board));
        assertThrows(InvalidMoveException.class, ()-> queen.move(secondFloor, board));
        assertThrows(InvalidMoveException.class, ()-> queen.move(thirdFloor, board));
        assertThrows(InvalidMoveException.class, ()-> queen.move(fourthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> queen.move(fifthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> queen.move(sixthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> queen.move(seventhFloor, board));
        assertThrows(InvalidMoveException.class, ()-> queen.move(eighthFloor, board));
    }


}
