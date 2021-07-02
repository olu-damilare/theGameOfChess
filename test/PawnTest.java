import game.board.Board;
import game.board.Floor;
import game.pieces.Pawn;
import game.pieces.Piece;
import game.properties.Move;
import game.gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static game.properties.Colour.*;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
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
    void testThatPawnCanMoveOneStepForwardOnFirstMove(){
        Floor firstFloor = board.getFloor(2, 2);
        Pawn pawn = new Pawn(BLACK, firstFloor);
        assertFalse(pawn.hasMadeFirstMove());
        assertTrue(firstFloor.isOccupied());
        assertEquals(firstFloor, pawn.getCurrentFloor());
        Floor destinationFloor = board.getFloor(3, 2);
        assertFalse(destinationFloor.isOccupied());
        assertNull(destinationFloor.getCurrentOccupant());

        pawn.move(destinationFloor, board);

        assertTrue(destinationFloor.isOccupied());
        assertFalse(firstFloor.isOccupied());
        assertNull(firstFloor.getCurrentOccupant());
        assertEquals(destinationFloor, pawn.getCurrentFloor());
        assertEquals(pawn, destinationFloor.getCurrentOccupant());
        assertTrue(pawn.hasMadeFirstMove());

    }

    @Test
    void testThatPawnCanMoveTwoStepsForwardOnFirstMove(){
        Floor firstFloor = board.getFloor(2, 2);
        Pawn pawn = new Pawn(BLACK, firstFloor);
        assertFalse(pawn.hasMadeFirstMove());
        assertTrue(firstFloor.isOccupied());
        assertEquals(firstFloor, pawn.getCurrentFloor());
        Floor destinationFloor = board.getFloor(4, 2);
        assertFalse(destinationFloor.isOccupied());
        assertNull(destinationFloor.getCurrentOccupant());

        pawn.move(destinationFloor, board);

        assertTrue(destinationFloor.isOccupied());
        assertFalse(firstFloor.isOccupied());
        assertNull(firstFloor.getCurrentOccupant());
        assertEquals(destinationFloor, pawn.getCurrentFloor());
        assertEquals(pawn, destinationFloor.getCurrentOccupant());
        assertTrue(pawn.hasMadeFirstMove());

    }
    @Test
    void testThatPawnCanMoveOneStepForwardAfterMakingFirstMove() {
        Floor firstFloor = board.getFloor(2, 2);
        Floor secondFloor = board.getFloor(4, 2);
        Floor thirdFloor = board.getFloor(5, 2);
        Pawn pawn = new Pawn(BLACK, firstFloor);
        assertFalse(pawn.hasMadeFirstMove());
        assertTrue(firstFloor.isOccupied());
        assertEquals(firstFloor, pawn.getCurrentFloor());

        pawn.move(secondFloor, board);

        assertTrue(secondFloor.isOccupied());
        assertFalse(firstFloor.isOccupied());
        assertNull(firstFloor.getCurrentOccupant());
        assertEquals(secondFloor, pawn.getCurrentFloor());
        assertEquals(pawn, secondFloor.getCurrentOccupant());
        assertTrue(pawn.hasMadeFirstMove());

        pawn.move(thirdFloor, board);

        assertTrue(thirdFloor.isOccupied());
        assertFalse(secondFloor.isOccupied());
        assertNull(secondFloor.getCurrentOccupant());
        assertEquals(thirdFloor, pawn.getCurrentFloor());
        assertEquals(pawn, thirdFloor.getCurrentOccupant());
    }

    @Test
    void testThatPawnMovingInAnyDirectionNotOneStepForwardAfterMakingFirstMove_throwsInvalidMoveException(){
        Floor defaultFloor = board.getFloor(2, 2);
        Floor secondFloor = board.getFloor(4, 2);
        Floor thirdFloor = board.getFloor(6, 2);
        Pawn pawn = new Pawn(BLACK, defaultFloor);
        assertFalse(pawn.hasMadeFirstMove());
        assertTrue(defaultFloor.isOccupied());
        assertFalse(secondFloor.isOccupied());
        assertFalse(thirdFloor.isOccupied());

        pawn.move(secondFloor, board);
        assertTrue(secondFloor.isOccupied());
        assertFalse(defaultFloor.isOccupied());
        assertEquals(secondFloor, pawn.getCurrentFloor());
        assertEquals(pawn, secondFloor.getCurrentOccupant());

        assertThrows(InvalidMoveException.class, ()-> pawn.move(thirdFloor, board));
    }

    @Test
    void testThatPawnMovingInAnyDirectionNotOneStepOrTwoStepsForwardOnFirstMove_throwsInvalidMoveException(){
        Floor defaultFloor = board.getFloor(2, 2);
        Pawn pawn = new Pawn(BLACK, defaultFloor);
        assertFalse(pawn.hasMadeFirstMove());
        Floor floor = board.getFloor(3, 3);
        assertNull(floor.getCurrentOccupant());

        Floor secondFloor = board.getFloor(5, 2);
        Floor thirdFloor = board.getFloor(1, 2);
        Floor fourthFloor = board.getFloor(2, 3);

        assertThrows(InvalidMoveException.class, ()-> pawn.move(floor, board));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor, board));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(thirdFloor, board));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(fourthFloor, board));
    }

    @Test
    void testThatPawnCanMoveOneStepForwardDiagonallyToCaptureEnemy(){
        Floor floor = board.getFloor(2, 2);
        Floor enemyFloor = board.getFloor(3, 1);
        Pawn pawn = new Pawn(BLACK, floor);
        Pawn enemyPawn = new Pawn(WHITE, enemyFloor);

        assertTrue(floor.isOccupied());
        assertTrue(enemyFloor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(enemyFloor, enemyPawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertEquals(enemyPawn, enemyFloor.getCurrentOccupant());

        pawn.move(enemyFloor, board);

        assertEquals(enemyFloor, pawn.getCurrentFloor());
        assertTrue(enemyPawn.isCaptured());
        assertEquals(pawn, enemyFloor.getCurrentOccupant());
        assertFalse(floor.isOccupied());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatPawnMovingOneStepForwardDiagonallyToUnoccupiedFloor_throwsInvalidMoveException(){
        Floor floor = board.getFloor(2, 2);
        Floor secondFloor = board.getFloor(3, 1);
        Pawn pawn = new Pawn(BLACK, floor);

        assertTrue(floor.isOccupied());
        assertFalse(secondFloor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertNull(secondFloor.getCurrentOccupant());

        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor, board));
    }

    @Test
    void testThatPawnMovingOneStepForwardDiagonallyToFloorOccupiedByPieceWithMatchingColour_throwsInvalidMoveException(){
        Floor floor = board.getFloor(2, 2);
        Floor secondFloor = board.getFloor(3, 1);
        Pawn pawn = new Pawn(BLACK, floor);
        Pawn enemyPawn = new Pawn(BLACK, secondFloor);

        assertTrue(floor.isOccupied());
        assertTrue(secondFloor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(secondFloor, enemyPawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertEquals(enemyPawn, secondFloor.getCurrentOccupant());

        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor, board));
    }

    @Test
    void testThatPawnMovingOneStepForwardToOccupiedFloor_throwsInvalidMoveException(){
        Floor floor = board.getFloor(2, 2);
        Floor secondFloor = board.getFloor(3, 2);
        Pawn pawn = new Pawn(BLACK, floor);
        Piece piece = new Pawn(BLACK, secondFloor);

        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());

        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, piece.getCurrentFloor());
        assertEquals(piece, secondFloor.getCurrentOccupant());

        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor, board));
    }

    @Test
    void testThatPawnMovingTwoStepsForwardToOccupiedFloorOnFirstMove_throwsInvalidMoveException(){
        Floor floor = board.getFloor(2, 2);
        Floor secondFloor = board.getFloor(4, 2);
        Pawn pawn = new Pawn(BLACK, floor);
        Piece piece = new Pawn(BLACK, secondFloor);

        assertFalse(pawn.hasMadeFirstMove());
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());

        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, piece.getCurrentFloor());
        assertEquals(piece, secondFloor.getCurrentOccupant());

        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor, board));
    }

    @Test
    void testThatPawnCanUndoMove(){
        Floor floor = board.getFloor(2, 2);
        assertEquals("b2", floor.toString());
        Floor secondFloor = board.getFloor(3, 2);
        assertEquals("b3", secondFloor.toString());
        Floor thirdFloor = board.getFloor(4, 2);
        assertEquals("b4", thirdFloor.toString());

        Pawn pawn = new Pawn(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertNull(secondFloor.getCurrentOccupant());

        pawn.move(secondFloor, board);

        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, pawn.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());

        Move move = new Move(floor, secondFloor);
        assertEquals("b2 b3", move.toString());
        assertEquals(move, pawn.getLastMove());

        pawn.move(thirdFloor, board);
        assertTrue(thirdFloor.isOccupied());
        assertEquals(thirdFloor, pawn.getCurrentFloor());
        assertNull(secondFloor.getCurrentOccupant());

        Move secondMove = new Move(secondFloor, thirdFloor);
        assertEquals("b3 b4", secondMove.toString());
        assertEquals(secondMove, pawn.getLastMove());

        pawn.undoMove();
        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, pawn.getCurrentFloor());
        assertEquals(pawn, secondFloor.getCurrentOccupant());
        assertFalse(thirdFloor.isOccupied());
        assertEquals(move, pawn.getLastMove());
    }





}
