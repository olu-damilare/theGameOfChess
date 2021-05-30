import components.Floor;
import components.Piece;
import components.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static components.Colour.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {
    Position position;
    Piece piece;

    @BeforeEach
    void setUp(){
        position = new Position(1,8);
        piece = new Piece(BLACK, position);
    }

    @Test
    void testThatChessPieceHasAColour(){
        assertEquals(BLACK, piece.getColour());
    }

    @Test
    void testThatChessPieceHasAPosition(){
        assertEquals(new Position(1, 8), piece.getCurrentPosition());
    }
    @Test
    void testThatAChessPieceHasAFloorItOccupies(){
        Floor floor = new Floor(8, 'a');
        assertEquals(floor, piece.getCurrentFloor());
    }

}
