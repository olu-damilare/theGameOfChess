import components.Floor;
import components.Move;
import components.Pawn;
import components.Position;
import gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static components.Colour.*;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {


    @Test
    void testThatPawnCanMoveOneStepForwardOnFirstMove(){
        Position position = new Position(2, 2);
        Pawn pawn = new Pawn(BLACK, position);
        assertEquals(new Position(2,2), pawn.getCurrentPosition());
        Floor floor = new Floor(3, 2);
        assertFalse(pawn.hasMadeFirstMove());
        assertFalse(floor.isOccupied());
        pawn.move(floor);
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
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
        pawn.move(floor);
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
        pawn.move(floor);
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertEquals(new Position(2, 4), pawn.getCurrentPosition());

        pawn.move(secondFloor);
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
        pawn.move(floor);
        assertTrue(floor.isOccupied());
        assertEquals(floor, pawn.getCurrentFloor());
        assertEquals(pawn, floor.getCurrentOccupant());
        assertEquals(new Position(2,4), pawn.getCurrentPosition());

        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor));
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

        assertThrows(InvalidMoveException.class, ()-> pawn.move(floor));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(secondFloor));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(thirdFloor));
        assertThrows(InvalidMoveException.class, ()-> pawn.move(fourthFloor));
    }

    @Test
    void testThatPawnCanMoveOneStepDiagonallyToCaptureEnemy(){
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

        pawn.move(enemyFloor);
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

        pawn.move(secondFloor);
        assertTrue(secondFloor.isOccupied());
        assertEquals(secondFloor, pawn.getCurrentFloor());
        assertNull(floor.getCurrentOccupant());
        Move move = new Move(floor, secondFloor);
        assertEquals("b2 b3", move.toString());
        assertEquals(move, pawn.getLastMove());
        pawn.move(thirdFloor);
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
