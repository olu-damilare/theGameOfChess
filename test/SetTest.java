import game.components.Set;
import game.components.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testThatSetCanSetUpBoard(){
        assertEquals("""
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                """, set.displayBoard());

    }

    @Test
    void testThatSetCanSetUpWhitePawns(){
        set.generateWhitePawns();
        assertEquals("""
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                W-P		W-P		W-P		W-P		W-P		W-P		W-P		W-P
                nil		nil		nil		nil		nil		nil		nil		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackPawns(){
        set.generateBlackPawns();
        assertEquals("""
                nil		nil		nil		nil		nil		nil		nil		nil
                B-P		B-P		B-P		B-P		B-P		B-P		B-P		B-P
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackRooks(){
        set.generateBlackRooks();
        assertEquals("""
                B-R		nil		nil		nil		nil		nil		nil		B-R
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpWhiteRooks(){
        set.generateWhiteRooks();
        assertEquals("""
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                W-R		nil		nil		nil		nil		nil		nil		W-R
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpWhiteKnights(){
        set.generateWhiteKnights();
        assertEquals("""
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		W-k		nil		nil		nil		nil		W-k		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackKnights(){
        set.generateBlackKnights();
        assertEquals("""
                nil		B-k		nil		nil		nil		nil		B-k		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackBishops(){
        set.generateBlackBishops();
        assertEquals("""
                nil		nil		B-B		nil		nil		B-B		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpWhiteBishops(){
        set.generateWhiteBishops();
        assertEquals("""
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		W-B		nil		nil		W-B		nil		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpWhiteQueen(){
        set.generateWhiteQueen();
        assertEquals("""
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		W-Q		nil		nil		nil		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackQueen(){
        set.generateBlackQueen();
        assertEquals("""
                nil		nil		nil		B-Q		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackKing(){
        set.generateBlackKing();
        assertEquals("""
                nil		nil		nil		nil		B-K		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                """,set.displayBoard()
        );
    }


    @Test
    void testThatSetCanSetUpWhiteKing(){
        set.generateWhiteKing();
        assertEquals("""
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		nil		nil		nil		nil
                nil		nil		nil		nil		W-K		nil		nil		nil
                """,set.displayBoard()
        );
    }
}
