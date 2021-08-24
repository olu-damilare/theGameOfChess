import game.components.Player;
import game.components.Set;
import game.components.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static game.properties.Colour.BLACK;
import static game.properties.Colour.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {
    Set set;

    @BeforeEach
    void setUp(){
        set = new Set("olu", "slim");

    }

    @Test
    void testThatABoardIsIncludedInASetOfChessGame(){
        assertTrue(set.getBoard() instanceof Board);
    }

    @Test
    void testThatSetCanSetUpBoard(){
        assertEquals("""
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                """, set.displayBoard());

    }

    @Test
    void testThatSetCanSetUpWhitePawns(){
        set.generateWhitePawns();
        assertEquals("""
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                ♙		♙		♙		♙		♙		♙		♙		♙
                --		--		--		--		--		--		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackPawns(){
        set.generateBlackPawns();
        assertEquals("""
                --		--		--		--		--		--		--		--
                ♟		♟		♟		♟		♟		♟		♟		♟
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackRooks(){
        set.generateBlackRooks();
        assertEquals("""
                ♜		--		--		--		--		--		--		♜
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpWhiteRooks(){
        set.generateWhiteRooks();
        assertEquals("""
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                ♖		--		--		--		--		--		--		♖
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpWhiteKnights(){
        set.generateWhiteKnights();
        assertEquals("""
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		♘		--		--		--		--		♘		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackKnights(){
        set.generateBlackKnights();
        assertEquals("""
                --		♞		--		--		--		--		♞		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackBishops(){
        set.generateBlackBishops();
        assertEquals("""
                --		--		♝		--		--		♝		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpWhiteBishops(){
        set.generateWhiteBishops();
        assertEquals("""
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		♗		--		--		♗		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpWhiteQueen(){
        set.generateWhiteQueen();
        assertEquals("""
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		♕		--		--		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackQueen(){
        set.generateBlackQueen();
        assertEquals("""
                --		--		--		♛		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetCanSetUpBlackKing(){
        set.generateBlackKing();
        assertEquals("""
                --		--		--		--		♚		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                """,set.displayBoard()
        );
    }


    @Test
    void testThatSetCanSetUpWhiteKing(){
        set.generateWhiteKing();
        assertEquals("""
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		--		--		--		--
                --		--		--		--		♔		--		--		--
                """,set.displayBoard()
        );
    }

    @Test
    void testThatSetHasWhitePiecePlayer(){
        Player player = set.getWhitePiecePlayer();
        assertEquals(WHITE, player.getColour());
    }

    @Test
    void testThatSetHasBlackPiecePlayer(){
        Player player = set.getBlackPiecePlayer();
        assertEquals(BLACK, player.getColour());
    }
}
