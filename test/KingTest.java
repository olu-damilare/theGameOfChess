import components.*;
import gameExceptions.InvalidMoveException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static components.Colour.BLACK;
import static components.Colour.WHITE;
import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

    Board board;

    @BeforeEach
    void setUp(){
        board = new Board(8,8);
    }

    @AfterEach
    void tearDown(){
        board = null;
    }

    @Test
    void testThatKingCanMoveOneStepForward(){
        Floor floor = board.getFloor(1,4);
        Floor destinationFloor = board.getFloor(2,4);
        King king = new King(BLACK, floor);
        assertEquals(floor, king.getCurrentFloor());
        assertEquals(king, floor.getCurrentOccupant());

        king.move(destinationFloor, board);
        assertEquals(destinationFloor, king.getCurrentFloor());
        assertEquals(king, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKingCanMoveOneStepBackward(){
        Floor floor = board.getFloor(2,4);
        Floor destinationFloor = board.getFloor(1,4);
        King king = new King(BLACK, floor);
        assertEquals(floor, king.getCurrentFloor());
        assertEquals(king, floor.getCurrentOccupant());

        king.move(destinationFloor, board);
        assertEquals(destinationFloor, king.getCurrentFloor());
        assertEquals(king, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKingCanMoveOneStepEastward(){
        Floor floor = board.getFloor(1,4);
        Floor destinationFloor = board.getFloor(1,5);
        King king = new King(BLACK, floor);
        assertEquals(floor, king.getCurrentFloor());
        assertEquals(king, floor.getCurrentOccupant());

        king.move(destinationFloor, board);
        assertEquals(destinationFloor, king.getCurrentFloor());
        assertEquals(king, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKingCanMoveOneStepWestward(){
        Floor floor = board.getFloor(1,4);
        Floor destinationFloor = board.getFloor(1,3);
        King king = new King(BLACK, floor);
        assertEquals(floor, king.getCurrentFloor());
        assertEquals(king, floor.getCurrentOccupant());

        king.move(destinationFloor, board);
        assertEquals(destinationFloor, king.getCurrentFloor());
        assertEquals(king, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKingCanMoveOneStepDiagonallyInUpperLeftDirection(){
        Floor floor = board.getFloor(1,4);
        Floor destinationFloor = board.getFloor(2,3);
        King king = new King(BLACK, floor);
        assertEquals(floor, king.getCurrentFloor());
        assertEquals(king, floor.getCurrentOccupant());

        king.move(destinationFloor, board);
        assertEquals(destinationFloor, king.getCurrentFloor());
        assertEquals(king, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKingCanMoveOneStepDiagonallyInUpperRightDirection(){
        Floor floor = board.getFloor(1,4);
        Floor destinationFloor = board.getFloor(2,5);
        King king = new King(BLACK, floor);
        assertEquals(floor, king.getCurrentFloor());
        assertEquals(king, floor.getCurrentOccupant());

        king.move(destinationFloor, board);
        assertEquals(destinationFloor, king.getCurrentFloor());
        assertEquals(king, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKingCanMoveOneStepDiagonallyInLowerLeftDirection(){
        Floor floor = board.getFloor(2,4);
        Floor destinationFloor = board.getFloor(1,3);
        King king = new King(BLACK, floor);
        assertEquals(floor, king.getCurrentFloor());
        assertEquals(king, floor.getCurrentOccupant());

        king.move(destinationFloor, board);
        assertEquals(destinationFloor, king.getCurrentFloor());
        assertEquals(king, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKingCanMoveOneStepDiagonallyInLowerRightDirection(){
        Floor floor = board.getFloor(2,4);
        Floor destinationFloor = board.getFloor(1,5);
        King king = new King(BLACK, floor);
        assertEquals(floor, king.getCurrentFloor());
        assertEquals(king, floor.getCurrentOccupant());

        king.move(destinationFloor, board);
        assertEquals(destinationFloor, king.getCurrentFloor());
        assertEquals(king, destinationFloor.getCurrentOccupant());
        assertNull(floor.getCurrentOccupant());
    }

    @Test
    void testThatKingNotMovingOneStep_throwsInvalidMoveException(){
        Floor floor = board.getFloor(4,4);
        King king = new King(BLACK, floor);

        Floor firstFloor = board.getFloor(6,2);
        Floor secondFloor = board.getFloor(2,4);
        Floor thirdFloor = board.getFloor(4,6);
        Floor fourthFloor = board.getFloor(2,4);
        Floor fifthFloor = board.getFloor(6,6);
        Floor sixthFloor = board.getFloor(2,6);
        Floor seventhFloor = board.getFloor(2,2);
        Floor eighthFloor = board.getFloor(2,2);

        assertThrows(InvalidMoveException.class, ()-> king.move(firstFloor, board));
        assertThrows(InvalidMoveException.class, ()-> king.move(secondFloor, board));
        assertThrows(InvalidMoveException.class, ()-> king.move(thirdFloor, board));
        assertThrows(InvalidMoveException.class, ()-> king.move(fourthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> king.move(fifthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> king.move(sixthFloor, board));
        assertThrows(InvalidMoveException.class, ()-> king.move(seventhFloor, board));
        assertThrows(InvalidMoveException.class, ()-> king.move(eighthFloor, board));


    }

    @Test
    void testThatKingCanMoveToCapturePiece(){
        Floor floor = board.getFloor(1, 4);
        Floor enemyFloor = board.getFloor(2, 3);
        King king = new King(BLACK, floor);
        Piece enemy = new Knight(WHITE, enemyFloor);
        assertFalse(enemy.isCaptured());

        king.move(enemyFloor, board);

        assertTrue(enemy.isCaptured());

    }

    @Test
    void testThatKingCanMovingToFloorOccupiedByPieceWithMatchingColour_throwsInvalidMoveException(){
        Floor floor = board.getFloor(1, 4);
        Floor enemyFloor = board.getFloor(2, 3);
        King king = new King(BLACK, floor);
        Piece enemy = new Knight(BLACK, enemyFloor);
        assertFalse(enemy.isCaptured());

       assertThrows(InvalidMoveException.class, ()->  king.move(enemyFloor, board));
    }

    @Test
    void testThatKingCanCastleQueenSideWhenKingAndCorrespondingRookHaveNotMadeFirstMove(){
        Floor floor = board.getFloor(1, 5);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(1,1);
        Rook rook = new Rook(BLACK, secondFloor);

        assertFalse(king.hasMadeFirstMove());
        assertFalse(rook.hasMadeFirstMove());

        king.castle(rook, board );

        assertTrue(king.hasMadeFirstMove());
        assertTrue(rook.hasMadeFirstMove());
        assertEquals(board.getFloor(1, 3), king.getCurrentFloor());
        assertEquals(board.getFloor(1, 4), rook.getCurrentFloor());
        assertTrue(king.hasCastled());
        assertTrue(rook.hasCastled());

    }

    @Test
    void testThatKingCanCastleKingSideWhenKingAndCorrespondingRookHaveNotMadeFirstMove(){
        Floor floor = board.getFloor(1, 5);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(1,8);
        Rook rook = new Rook(BLACK, secondFloor);

        assertFalse(king.hasMadeFirstMove());
        assertFalse(rook.hasMadeFirstMove());

        king.castle(rook, board );

        assertTrue(king.hasMadeFirstMove());
        assertTrue(rook.hasMadeFirstMove());
        assertEquals(board.getFloor(1, 7), king.getCurrentFloor());
        assertEquals(board.getFloor(1, 6), rook.getCurrentFloor());
        assertTrue(king.hasCastled());
        assertTrue(rook.hasCastled());

    }

    @Test
    void testThatKingCanCastleWhenKingHasMadeFirstMove_throwsInvalidMoveException(){
        Floor floor = board.getFloor(1, 5);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(1,8);
        Rook rook = new Rook(BLACK, secondFloor);

        assertFalse(king.hasMadeFirstMove());
        assertFalse(rook.hasMadeFirstMove());

        Floor kingDestinationFloor = board.getFloor(1,4);
        king.move(kingDestinationFloor, board);

        assertTrue(king.hasMadeFirstMove());

       assertThrows(InvalidMoveException.class, ()-> king.castle(rook, board ));

    }

    @Test
    void testThatKingCanCastleWhenCorrespondingRookHasMadeFirstMove_throwsInvalidMoveException(){
        Floor floor = board.getFloor(1, 5);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(1,8);
        Rook rook = new Rook(BLACK, secondFloor);

        assertFalse(king.hasMadeFirstMove());
        assertFalse(rook.hasMadeFirstMove());

        Floor rookDestinationFloor = board.getFloor(1,7);
        rook.move(rookDestinationFloor, board);

        assertTrue(rook.hasMadeFirstMove());
        assertThrows(InvalidMoveException.class, ()-> king.castle(rook, board ));
    }

    @Test
    void testThatPawnCanCheckKing(){
        Floor floor = board.getFloor(1, 5);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(2,6);
        Pawn pawn = new Pawn(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatBishopCanCheckKingFromUpperLeftDirection(){
        Floor floor = board.getFloor(1, 5);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(4,2);
        Bishop bishop = new Bishop(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatBishopCanCheckKingFromUpperRightDirection(){
        Floor floor = board.getFloor(1, 5);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(4,8);
        Bishop bishop = new Bishop(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatBishopCanCheckKingFromLowerRightDirection(){
        Floor floor = board.getFloor(4, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(1,7);
        Bishop bishop = new Bishop(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatBishopCanCheckKingFromLowerLeftDirection(){
        Floor floor = board.getFloor(4, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(1,1);
        Bishop bishop = new Bishop(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatKnightCanCheckKingFromOneSquareForwardAndTwoSquaresToTheLeft(){
        Floor floor = board.getFloor(1, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(2,2);
        Knight knight = new Knight(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatKnightCanCheckKingFromOneSquareBackwardAndTwoSquaresToTheLeft(){
        Floor floor = board.getFloor(4, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(3,2);
        Knight knight = new Knight(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatKnightCanCheckKingFromTwoSquaresForwardAndOneSquareToTheLeft(){
        Floor floor = board.getFloor(1, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(3,3);
        Knight knight = new Knight(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatKnightCanCheckKingFromTwoSquaresBackwardAndOneSquareToTheLeft(){
        Floor floor = board.getFloor(4, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(2,3);
        Knight knight = new Knight(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatKnightCanCheckKingFromTwoSquaresForwardAndOneSquareToTheRight(){
        Floor floor = board.getFloor(1, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(3,5);
        Knight knight = new Knight(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatKnightCanCheckKingFromTwoSquaresBackwardAndOneSquareToTheRight(){
        Floor floor = board.getFloor(4, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(2,5);
        Knight knight = new Knight(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatKnightCanCheckKingFromOneSquareForwardAndTwoSquaresToTheRight(){
        Floor floor = board.getFloor(1, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(2,6);
        Knight knight = new Knight(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }

    @Test
    void testThatKnightCanCheckKingFromOneSquareBackwardAndTwoSquaresToTheRight(){
        Floor floor = board.getFloor(4, 4);
        King king = new King(BLACK, floor);
        Floor secondFloor = board.getFloor(3,6);
        Knight knight = new Knight(WHITE, secondFloor);

        king.scanForChecked(board);
        assertTrue(king.isChecked());
    }


}
