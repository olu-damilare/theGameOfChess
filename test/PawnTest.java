import components.Pawn;
import components.Position;
import org.junit.jupiter.api.Test;

import static components.Colour.BLACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PawnTest {

    @Test
    void testThatPawnCanMoveOneStep(){
        Position position = new Position(2, 2);
        Pawn pawn = new Pawn(BLACK, position);
        assertEquals(new Position(2,2), pawn.getCurrentPosition());
        pawn.move();
        assertEquals(new Position(2,3), pawn.getCurrentPosition());
    }

    @Test
    void testThatPawnCanMoveTwoStepsOnFirstMove(){
        Position position = new Position(2, 2);
        Pawn pawn = new Pawn(BLACK, position);
        assertEquals(new Position(2,2), pawn.getCurrentPosition());
        assertFalse(pawn.hasMadeFirstMove());
        pawn.move();
        assertEquals(new Position(2,3), pawn.getCurrentPosition());
    }
}
