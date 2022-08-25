import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        printBoard(board);

        while (true) {
            playerTurn(board, sc);
            if (isGameFinished(board))
                break;

            printBoard(board);

            computerTurn(board);
            if (isGameFinished(board))
                break;
            printBoard(board);
        }
        sc.close();
    }

    //checking if game is finished
    private static boolean isGameFinished(char[][] board) {
        //check if player won
        if (hasContestantWon(board, 'X')) {
            printBoard(board);
            System.out.println("Player wins!");
            return true;
        }

        //check if computer won
        if (hasContestantWon(board, 'O')) {
            printBoard(board);
            System.out.println("Computer wins!");
            return true;
        }


        //check if there is empty spot in a board
        //if so - the game is not finished
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        printBoard(board);
        System.out.println("It's a tie!");
        return true;
    }
    //hard-coded conditions whether one of players have won
    private static boolean hasContestantWon(char[][] board, char symbol) {
        return ((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol));
    }

    //generating move for a computer using Random
    //with a use of loop until computer generates valid number
    private static void computerTurn(char[][] board) {
        Random rand = new Random();
        int computerMove;
        while (true) {
            computerMove = rand.nextInt(9) + 1;
            if(isValidMove(board, Integer.toString(computerMove)))
                break;
        }
        System.out.println("Computer chose: " + computerMove);
        placeMove(board, Integer.toString(computerMove), 'O');
    }

    //check whether move is valid
    private static boolean isValidMove(char[][] board, String position) {
        return switch (position) {
            case "1" -> (board[0][0] == ' ');
            case "2" -> (board[0][1] == ' ');
            case "3" -> (board[0][2] == ' ');
            case "4" -> (board[1][0] == ' ');
            case "5" -> (board[1][1] == ' ');
            case "6" -> (board[1][2] == ' ');
            case "7" -> (board[2][0] == ' ');
            case "8" -> (board[2][1] == ' ');
            case "9" -> (board[2][2] == ' ');
            default -> false;
        };
    }

    //asking user for input and calling placeMove method if move is valid
    private static void playerTurn(char[][] board, Scanner sc) {
        String userInput;

        while (true) {
            System.out.println("Where would you like to play? (1-9)");
            userInput = sc.nextLine();
            if (isValidMove(board, userInput))
                break;
            else
                System.out.println("invalid move");
        }

        placeMove(board, userInput, 'X');
    }

    //print the symbol in a specific place (provided by user)
    private static void placeMove(char[][] board, String userInput, char symbol) {
        switch (userInput) {
            case "1" -> board[0][0] = symbol;
            case "2" -> board[0][1] = symbol;
            case "3" -> board[0][2] = symbol;
            case "4" -> board[1][0] = symbol;
            case "5" -> board[1][1] = symbol;
            case "6" -> board[1][2] = symbol;
            case "7" -> board[2][0] = symbol;
            case "8" -> board[2][1] = symbol;
            case "9" -> board[2][2] = symbol;
            default -> System.out.println(":(");
        }
    }
    // print the board (as 2D array)
    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }
}
