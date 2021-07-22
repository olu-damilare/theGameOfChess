import game.components.board.Board;
import game.components.board.Floor;
import game.pieces.Bishop;
import game.pieces.Knight;
import game.pieces.Piece;
import game.properties.Move;
import game.gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static game.properties.Colour.BLACK;
import static game.properties.Colour.WHITE;
import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(8, 8);
    }

    @Test
    void testThatKnightCanJumpToFloorPositionedOneSquareForwardAndTwoSquaresToTheLeft(){
        Floor floor = board.getFloor(4,4);
        Floor destinationFloor = board.getFloor(5, 2);
        Knight knight = new Knight(WHITE, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(knight, floor.getCurrentOccupant());
        assertEquals(floor, knight.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        knight.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(knight, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKnightCanJumpToFloorPositionedTwoSquaresForwardAndOneSquareToTheLeft(){
        Floor floor = board.getFloor(4,4);
        Floor destinationFloor = board.getFloor(6, 3);
        Knight knight = new Knight(WHITE, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(knight, floor.getCurrentOccupant());
        assertEquals(floor, knight.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        knight.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(knight, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKnightCanJumpToFloorPositionedOneSquareBackwardAndTwoSquaresToTheLeft(){
        Floor floor = board.getFloor(4,4);
        Floor destinationFloor = board.getFloor(3, 2);
        Knight knight = new Knight(WHITE, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(knight, floor.getCurrentOccupant());
        assertEquals(floor, knight.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        knight.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(knight, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKnightCanJumpToFloorPositionedTwoSquaresBackwardAndOneSquareToTheLeft(){
        Floor floor = board.getFloor(4,4);
        Floor destinationFloor = board.getFloor(2, 3);
        Knight knight = new Knight(WHITE, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(knight, floor.getCurrentOccupant());
        assertEquals(floor, knight.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        knight.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(knight, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKnightCanJumpToFloorPositionedOneSquareForwardAndTwoSquaresToTheRight(){
        Floor floor = board.getFloor(4,4);
        Floor destinationFloor = board.getFloor(5, 6);
        Knight knight = new Knight(WHITE, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(knight, floor.getCurrentOccupant());
        assertEquals(floor, knight.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        knight.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(knight, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKnightCanJumpToFloorPositionedTwoSquaresForwardAndOneSquareToTheRight(){
        Floor floor = board.getFloor(4,4);
        Floor destinationFloor = board.getFloor(6, 5);
        Knight knight = new Knight(WHITE, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(knight, floor.getCurrentOccupant());
        assertEquals(floor, knight.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        knight.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(knight, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKnightCanJumpToFloorPositionedOneSquareBackwardAndTwoSquaresToTheRight(){
        Floor floor = board.getFloor(4,4);
        Floor destinationFloor = board.getFloor(3, 6);
        Knight knight = new Knight(WHITE, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(knight, floor.getCurrentOccupant());
        assertEquals(floor, knight.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        knight.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(knight, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKnightCanJumpToFloorPositionedTwoSquaresBackwardAndOneSquareToTheRight(){
        Floor floor = board.getFloor(4,4);
        Floor destinationFloor = board.getFloor(2, 5);
        Knight knight = new Knight(WHITE, floor);
        assertTrue(floor.isOccupied());
        assertFalse(destinationFloor.isOccupied());
        assertEquals(knight, floor.getCurrentOccupant());
        assertEquals(floor, knight.getCurrentFloor());
        assertNull(destinationFloor.getCurrentOccupant());

        knight.move(destinationFloor, board);

        assertFalse(floor.isOccupied());
        assertTrue(destinationFloor.isOccupied());

        assertEquals(knight, destinationFloor.getCurrentOccupant());
        assertEquals(destinationFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKnightMovingToFloorNotL_Shaped_throwsInvalidMoveException(){
        Floor knightFloor = board.getFloor(4,4);
        Knight knight = new Knight(WHITE, knightFloor);

        Floor firstFloor = board.getFloor(3,4);
        Floor secondFloor = board.getFloor(5,4);
        Floor thirdFloor = board.getFloor(5,4);
        Floor fourthFloor = board.getFloor(5,4);
        Floor fifthFloor = board.getFloor(5,4);
        Floor sixthFloor = board.getFloor(5,4);
        Floor seventhFloor = board.getFloor(5,4);
        Floor eighthFloor = board.getFloor(5,4);

        assertThrows(InvalidMoveException.class, ()-> knight.move(firstFloor, board));
        assertThrows(InvalidMoveException.class, ()-> knight.move(secondFloor, board));
        assertThrows(InvalidMoveException.class, ()-> knight.move(thirdFloor, board));
        assertThrows(InvalidMoveException.class, ()-> knight.move(fourthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> knight.move(fifthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> knight.move(sixthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> knight.move(seventhFloor, board));
        assertThrows(InvalidMoveException.class, ()-> knight.move(eighthFloor, board));
    }

    @Test
    void testThatKnightCanCaptureEnemy(){
        Floor floor = board.getFloor(4,4);
        Floor enemyFloor = board.getFloor(6, 3);
        Knight knight = new Knight(BLACK, floor);
        Piece enemy = new Bishop(WHITE, enemyFloor);

        knight.move(enemyFloor, board);
        assertTrue(enemy.isCaptured());
        assertNull(floor.getCurrentOccupant());
        assertFalse(floor.isOccupied());
        assertEquals(knight, enemyFloor.getCurrentOccupant());
    }

    @Test
    void testThatKnightJumpingToFloorOccupiedByPieceWithMatchingColour_throwsInvalidMoveException(){
        Floor floor = board.getFloor(4,4);
        Floor enemyFloor = board.getFloor(6, 3);
        Knight knight = new Knight(BLACK, floor);
        Piece teamMate = new Bishop(BLACK, enemyFloor);

       assertThrows(InvalidMoveException.class, ()-> knight.move(enemyFloor, board));
    }

    @Test
    void testThatKnightCanUndoMove(){
        Floor floor = board.getFloor(2, 2);
        assertEquals("b2", floor.toString());
        Floor secondFloor = board.getFloor(3, 4);
        assertEquals("d3", secondFloor.toString());
        Floor thirdFloor = board.getFloor(5, 5);
        assertEquals("e5", thirdFloor.toString());

        Knight knight = new Knight(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertEquals(floor, knight.getCurrentFloor());
        assertEquals(knight, floor.getCurrentOccupant());
        assertNull(secondFloor.getCurrentOccupant());

        knight.move(secondFloor, board);

        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, knight.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());

        Move move = new Move(floor, secondFloor);
        assertEquals("b2 d3", move.toString());
        assertEquals(move, knight.getLastMove());

        knight.move(thirdFloor, board);
        assertTrue(thirdFloor.isOccupied());
        assertEquals(thirdFloor, knight.getCurrentFloor());
        assertNull(secondFloor.getCurrentOccupant());

        Move secondMove = new Move(secondFloor, thirdFloor);
        assertEquals("d3 e5", secondMove.toString());
        assertEquals(secondMove, knight.getLastMove());

        knight.undoMove();
        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, knight.getCurrentFloor());
        assertEquals(knight, secondFloor.getCurrentOccupant());
        assertFalse(thirdFloor.isOccupied());
        assertEquals(move, knight.getLastMove());

    }



}
