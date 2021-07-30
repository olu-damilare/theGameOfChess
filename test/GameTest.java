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
        set = new Set();
    }

    @Test
    void testThatGameCanSetUpBoard(){
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
       game.setUp(set);
       assertEquals("""
               B-R		B-k		B-B		B-Q		B-K		B-B		B-k		B-R
               B-P		B-P		B-P		B-P		B-P		B-P		B-P		B-P
               nil		nil		nil		nil		nil		nil		nil		nil
               nil		nil		nil		nil		nil		nil		nil		nil
               nil		nil		nil		nil		nil		nil		nil		nil
               nil		nil		nil		nil		nil		nil		nil		nil
               W-P		W-P		W-P		W-P		W-P		W-P		W-P		W-P
               W-R		W-k		W-B		W-Q		W-K		W-B		W-k		W-R
               """, set.displayBoard());
    }
}
