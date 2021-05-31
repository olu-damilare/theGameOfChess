import components.Floor;
import components.Pawn;
import components.Position;
import gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static components.Colour.BLACK;
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
}
