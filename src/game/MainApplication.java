package game;

import game.components.Game;
import game.components.Player;
import game.components.Set;
import game.components.board.Board;
import game.gameExceptions.InvalidMoveException;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        Board board = new Board(8,8);
        Game game = new Game();
        Set set = new Set("Olu", "Slim");
        Player player1 = set.getWhitePiecePlayer();
        Player player2 = set.getBlackPiecePlayer();
        game.setUp(set);

        System.out.println(player1.getUsername() + " owns the white pieces");
        System.out.println(player2.getUsername() + " owns the black pieces");
        System.out.println();
        Scanner scanner = new Scanner(System.in);


        while(!set.isGameOver()) {
            System.out.println(set.displayBoard());
            System.out.println();
            System.out.println(player1.getUsername() + " make move");
            System.out.println("Enter the row and column of the piece to move");
            System.out.print("row: ");
            int row = scanner.nextInt();
            if(row < 1 || row > 8){
                System.out.println("Invalid row value. Try again");
                continue;
            }
            System.out.print("column: ");
            int column = scanner.nextInt();
            if(column < 1 || column > 8){
                System.out.println("Invalid column value. Try again");
                continue;
            }

            System.out.println("Enter the destination row and column.");
            System.out.print("destination row: ");
            int destinationRow = scanner.nextInt();
            if(destinationRow < 1 || destinationRow > 8){
                System.out.println("Invalid row value. Try again");
                continue;
            }
            System.out.print("column: ");
            int destinationColumn = scanner.nextInt();
            if(destinationColumn < 1 || destinationColumn > 8){
                System.out.println("Invalid column value. Try again");
                continue;
            }

            System.out.println(set.getBoard().getFloor(row, column).getCurrentOccupant());
            try {
                player1.makeMove(board, set.getBoard().getFloor(row, column).getCurrentOccupant(), set.getBoard().getFloor(destinationRow, destinationColumn));
            } catch (InvalidMoveException e) {
                System.out.println(e.getLocalizedMessage() + " try again");
                continue;
            }
            System.out.println(set.displayBoard());
            System.out.println();

            System.out.println(player2.getUsername() + " make move");
            System.out.println("Enter the row and column of the piece to move");
            System.out.print("row: ");
            row = scanner.nextInt();
            if(row < 1 || row > 8){
                System.out.println("Invalid row value. Try again");
                continue;
            }
            System.out.print("column: ");
            column = scanner.nextInt();
            if(column < 1 || column > 8){
                System.out.println("Invalid column value. Try again");
                continue;
            }

            System.out.println("Enter the destination row and column.");
            System.out.print("destination row: ");
            destinationRow = scanner.nextInt();
            if(destinationRow < 1 || destinationRow > 8){
                System.out.println("Invalid row value. Try again");
                continue;
            }
            System.out.print("destination column: ");
            destinationColumn = scanner.nextInt();
            if(destinationColumn < 1 || destinationColumn > 8){
                System.out.println("Invalid column value. Try again");
                continue;
            }

            try {
                player2.makeMove(board, set.getBoard().getFloor(row, column).getCurrentOccupant(), set.getBoard().getFloor(destinationRow, destinationColumn));
            } catch (InvalidMoveException e) {
                System.out.println(e.getLocalizedMessage() +  " Try again");

            }
        }




    }
}
