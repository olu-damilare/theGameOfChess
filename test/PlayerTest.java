import game.components.board.Board;
import game.components.board.Floor;
import game.components.Player;
import game.pieces.Pawn;
import game.pieces.Piece;
import org.junit.jupiter.api.*;

import static game.properties.Colour.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player;
    Board board;

    @BeforeEach
    void setUp() {
        player = new Player("slimDaddy");
        board = new Board(8,8);
    }

    @AfterEach
    void tearDown(){
        player.resetId();
    }

    @Test
    void testThatPlayerHasAnId(){
        assertEquals(1, player.getId());
    }

    @Test
    void testThatPlayerCanMakeMove(){
        Floor pieceFloor = board.getFloor(2,2);
        Piece piece = new Pawn(BLACK, pieceFloor);
        assertEquals(pieceFloor, piece.getCurrentFloor());
        Floor destinationFloor = board.getFloor(3, 2);

        player.makeMove(board, piece, destinationFloor);
        assertEquals(destinationFloor, piece.getCurrentFloor());
    }

    @Test
    void testThatPlayerCanUndoMove(){
        Floor pieceFloor = board.getFloor(2,2);
        Piece piece = new Pawn(BLACK, pieceFloor);
        assertEquals(pieceFloor, piece.getCurrentFloor());
        Floor destinationFloor = board.getFloor(3, 2);

        player.makeMove(board, piece, destinationFloor);
        assertEquals(destinationFloor, piece.getCurrentFloor());

        player.undoMove();
        assertEquals(pieceFloor, piece.getCurrentFloor());

    }

    @Test
    void testThatPlayerCanMakeMoveToCapturePiece(){
        Floor pieceFloor = board.getFloor(2,2);
        Piece piece = new Pawn(BLACK, pieceFloor);
        assertEquals(pieceFloor, piece.getCurrentFloor());

        Floor enemyFloor = board.getFloor(3, 3);
        Piece enemy = new Pawn(WHITE, enemyFloor);
        assertEquals(enemyFloor, enemy.getCurrentFloor());


        player.makeMove(board, piece, enemyFloor);
        assertEquals(enemyFloor, piece.getCurrentFloor());
        assertTrue(enemy.isCaptured());

    }
}
