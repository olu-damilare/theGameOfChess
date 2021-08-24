import game.components.Game;
import game.components.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    Game game;
    Set set;

    @BeforeEach
    void setUp(){
        game = new Game();
        set = new Set("olu", "slim");
    }

    @Test
    void testThatGameCanSetUpBoard(){
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
       game.setUp(set);
       assertEquals("""
               ♜		♞		♝		♛		♚		♝		♞		♜
               ♟		♟		♟		♟		♟		♟		♟		♟
               --		--		--		--		--		--		--		--
               --		--		--		--		--		--		--		--
               --		--		--		--		--		--		--		--
               --		--		--		--		--		--		--		--
               ♙		♙		♙		♙		♙		♙		♙		♙
               ♖		♘		♗		♕		♔		♗		♘		♖
               """, set.displayBoard());
    }
}
