package game;

import game.components.Game;
import game.components.Player;
import game.components.Set;
import game.components.board.Board;
import game.gameExceptions.InvalidMoveException;

public class MainApplication {

    public static void main(String[] args) {
        Board board = new Board(8,8);
        Game game = new Game();
        Set set = new Set("Olu", "Slim");
        Player player1 = set.getBlackPiecePlayer();
        Player player2 = set.getWhitePiecePlayer();
        game.setUp(set);

        System.out.println(player1.getUsername() + " owns the black pieces");
        System.out.println(player2.getUsername() + " owns the white pieces");
        System.out.println();


        System.out.println(set.displayBoard());
        System.out.println();
        System.out.println(player1.getUsername() + " make move");

        try {
            player1.makeMove(board, set.getBoard().getFloor(2, 2).getCurrentOccupant(), set.getBoard().getFloor(3, 2));
        }catch(InvalidMoveException e){
            System.out.println("Invalid move. try again");

        }
        System.out.println(set.displayBoard());
        System.out.println();
        try {
            player2.makeMove(board, set.getBoard().getFloor(3, 2).getCurrentOccupant(), set.getBoard().getFloor(3, 2));
        }catch(InvalidMoveException e){
            System.out.println("Invalid move. try again");

        }




    }
}
