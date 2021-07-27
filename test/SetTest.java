import game.components.Set;
import game.components.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {
    Set set;

    @BeforeEach
    void setUp(){
        set = new Set();

    }

    @Test
    void testThatABoardIsIncludedInASetOfChessGame(){
        assertTrue(set.getBoard() instanceof Board);
    }




}
