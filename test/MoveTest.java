import game.board.Board;
import game.board.Floor;
import game.properties.Move;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTest {

    @Test
    void testThatAMoveHasATimeStamp(){
        Board board = new Board(8, 8);
        Floor previousFloor = board.getFloor(2, 4);
        Floor currentFloor = board.getFloor(3, 4);
        Move move = new Move(previousFloor, currentFloor);
        System.out.println(move.getTimeStamp());
        assertTrue(move.getTimeStamp() instanceof LocalDateTime);
    }


}
