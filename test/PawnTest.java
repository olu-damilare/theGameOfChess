import components.*;
import gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static components.Colour.*;
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
        Position position = new Position(2, 2);
        Pawn pawn = new Pawn(BLACK, position);
        assertEquals(new Position(2,2), pawn.getCurrentPosition());
        Floor floor = new Floor(3, 2);
        assertFalse(pawn.hasMadeFirstMove());
        assertFalse(floor.isOccupied());
        pawn.move(floor, board);
        Floor boardFloor = board.getFloor(floor.getRank(), floor.getFile());
        assertTrue(boardFloor.isOccupied());
        assertEquals(boardFloor, pawn.getCurrentFloor());
        assertEquals(pawn, boardFloor.getCurrentOccupant());
        assertEquals(new Position(2,3), pawn.getCurrentPosition());
    }

    @Test
    void testThatPawnCanMoveTwoStepsForwardOnFirstMove(){
        Position position = new Position(2, 2);
        Pawn pawn = new Pawn(BLACK, position);
        assertEquals(new Position(2,2), pawn.getCurrentPosition());
        assertFalse(pawn.hasMadeFirstMove());
        Floor floor = new Floor(4, 2);
        assertFalse(floor.isOccupied());
        pawn.move(floor, board);
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertEquals(new Position(2,4), pawn.getCurrentPosition());
    }
    @Test
    void testThatPawnCanMoveOneStepForwardAfterMakingFirstMove() {
        Position position = new Position(2, 2);
        Pawn pawn = new Pawn(BLACK, position);
        assertEquals(new Position(2, 2), pawn.getCurrentPosition());
        assertFalse(pawn.hasMadeFirstMove());
        Floor floor = new Floor(4, 2);
        Floor secondFloor = new Floor(5, 2);

        assertFalse(floor.isOccupied());
        pawn.move(floor, board);
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertEquals(new Position(2, 4), pawn.getCurrentPosition());

        pawn.move(secondFloor, board);
        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, pawn.getCurrentFloor());
        assertEquals(pawn, secondFloor.getCurrentOccupant());
        assertEquals(new Position(2, 5), pawn.getCurrentPosition());

    }
    @Test
    void testThatPawnMovingInAnyDirectionNotOneStepForwardAfterMakingFirstMove_throwsInvalidMoveException(){
        Position position = new Position(2, 2);
        Pawn pawn = new Pawn(BLACK, position);
        assertEquals(new Position(2,2), pawn.getCurrentPosition());
        assertFalse(pawn.hasMadeFirstMove());
        Floor floor = new Floor(4, 2);
        Floor secondFloor = new Floor(6, 2);
        assertFalse(floor.isOccupied());
        pawn.move(floor, board);
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertEquals(new Position(2,4), pawn.getCurrentPosition());

        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor, board));
    }

    @Test
    void testThatPawnMovingInAnyDirectionNotOneStepOrTwoStepsForwardOnFirstMove_throwsInvalidMoveException(){
        Position position = new Position(2, 2);
        Pawn pawn = new Pawn(BLACK, position);
        assertEquals(new Position(2,2), pawn.getCurrentPosition());
        assertFalse(pawn.hasMadeFirstMove());
        Floor floor = new Floor(3, 3);
        Floor secondFloor = new Floor(5, 2);
        Floor thirdFloor = new Floor(1, 2);
        Floor fourthFloor = new Floor(2, 3);

        assertThrows(InvalidMoveException.class, ()-> pawn.move(floor, board));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor, board));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(thirdFloor, board));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(fourthFloor, board));
    }

    @Test
    void testThatPawnCanMoveOneStepForwardDiagonallyToCaptureEnemy(){
        Floor floor = new Floor(2, 2);
        Floor enemyFloor = new Floor(3, 1);
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
    void testThatPawnCanUndoMove(){
        Floor floor = new Floor(2, 2);
        assertEquals("b2", floor.toString());
        Floor secondFloor = new Floor(3, 2);
        assertEquals("b3", secondFloor.toString());
        Floor thirdFloor = new Floor(4, 2);
        assertEquals("b4", thirdFloor.toString());

        Pawn pawn = new Pawn(BLACK, floor);
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
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
        assertEquals(secondMove, pawn.getLastMove());

        pawn.undoMove();
        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, pawn.getCurrentFloor());
        assertEquals(pawn, secondFloor.getCurrentOccupant());
        assertEquals(move, pawn.getLastMove());


    }



}
