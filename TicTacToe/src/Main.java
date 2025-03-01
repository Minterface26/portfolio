import java.util.Arrays;

import java.util.Scanner;
import java.lang.*;
public class Main {
    public static int xCoor = 0;
    public static int yCoor = 0;
    public static String whichPlayer = "X";
    public static String ag[][] = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
    public static String[][] isItTaken = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
    public static String winMessage = null;
    public static void main(String[] args) {


        //prints the board to show the players initially. Should be empty.
        System.out.println("Welcome to tictactoe!");
        for (int i = 0; i < ag.length; i++) {
            System.out.println(Arrays.deepToString(ag[i]));
        }

        //  System.out.println("Type stop at any point to stop the game!");

        //prompts for the players to read
        String prompt = "Enter your coordinates of the 3 by 3. " +
                "Rows are the first number and columns are the second.";
        String prompt2 = "This means 1,1 would be the bottom left corner " +
                "and 3,2 would be the middle top position.";
        String prompt3 = "As an example, one should enter a number, say 1. HIT ENTER. " +
                "Then one should put another number, say 1, and press ENTER again";
        System.out.println(prompt);
        System.out.println(prompt2);
        System.out.println(prompt3);

        //Sets the coordinates

        // while(a) is not full OR someone gets a win,  keep going

        int finiteMoves = 9;
       for (int v = 0; v < finiteMoves; v++) {
            oneTurn();
            if(anyoneWin()) {
               System.out.println(winMessage);
               break;
            }
        }
        if(!anyoneWin()) {
            System.out.println("Nobody won : (");
        }
    }

    public static boolean anyoneWin() {
        //limited combinations of win for tictactoe
        String[][] winArr = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
        String[] horizontalX = {"x", "x", "x"};
        String[] horizontalO = {"o", "o", "o"};
        //just check if they won, not through set combinations but if the x and 0 are all the same
        //check if any horizontal wins
        if (Arrays.equals(ag[0], horizontalX) || Arrays.equals(ag[1], horizontalX) || Arrays.equals(ag[2], horizontalX)) {
            winMessage = "Player X wins";
            return true;
        } else if(Arrays.equals(ag[0], horizontalO) || Arrays.equals(ag[1], horizontalO) || Arrays.equals(ag[2], horizontalO)) {
            winMessage = "Player O wins";
            return true;
        }
        //check if any vertical wins. Iterate through every row keeping columns same
        for(int v = 0; v < ag.length; v++) {
            if(ag[0][v].equals(ag[1][v]) && ag[1][v].equals(ag[2][v])) {
                if(ag[0][v].equals("x")) {
                    winMessage = "Player X wins";
                    return true;
                } else if(ag[0][v].equals("o")) {
                    winMessage = "Player O wins";
                    return true;
                }
            }
        }
        //check diagonal
            if(ag[0][0].equals(ag[1][1]) && ag[1][1].equals(ag[2][2])) {
                if(ag[1][1].equals("x")) {
                    winMessage = "Player X wins";
                    return true;
                } else if(ag[1][1].equals("o")) {
                    winMessage = "Player O wins";
                }
            } else if(ag[2][0].equals(ag[1][1]) && ag[1][1].equals(ag[0][2])) {
                if(ag[1][1].equals("x")) {
                    winMessage = "Player X wins";
                    return true;
                } else if(ag[1][1].equals("o")) {
                    winMessage = "Player O wins";
                    return true;
                }
            }
            return false;
    }

    public static void oneTurn() {
        if(whichPlayer.equals("X")) {
            System.out.println("Player X, it is now your turn");
            setCoord();
            setPos((xCoor-1),(yCoor-1),ag,whichPlayer);
            //fixes the bug in which the game has to finish the old loop for if a player puts in an incorrect answer
            if(whichPlayer.equals("X")) {
                printArr(ag);
            }
            whichPlayer = "O"; //switches the turn then to the next person
        } else if (whichPlayer.equals("O")) {
            System.out.println("Player O, it is now your turn");
            setCoord();
            setPos((xCoor-1),(yCoor-1),ag,whichPlayer);
            //fixes the bug in which the game has to finish the old loop for if a player puts in an incorrect answer
            if(whichPlayer.equals("O")) {
                printArr(ag);
            }
            whichPlayer = "X";
        }
    }

    public static void printArr(String[][] a) {
        //upside down because then the "y" variable works as a correct column choice
        System.out.println(Arrays.deepToString(a[2]));
        System.out.println(Arrays.deepToString(a[1]));
        System.out.println(Arrays.deepToString(a[0]));
        /*rightside up
        System.out.println(Arrays.deepToString(a[0]));
        System.out.println(Arrays.deepToString(a[1]));
        System.out.println(Arrays.deepToString(a[2]));*/
    }

    public static void setPos(int rowChoice, int colChoice, String[][] board, String playID) {
        //(3-1) = 0, 2 = 1, (1-1) = 3
        //Resets all the row and column choices to the right orientation of the board

        /*for rows, in format of input to what user input is
        * 2 = 3 for y, 3-1
        * 1 = 2 for y, 2-1
        * 0 = 1 for y, 1-1
        * for columns in format of input to what user input is
        * 0 = 1 for x, 1-1
        * 1 = 2 for x, 2-1
        * 2 = 3 for x, 3-1
        *
        * since x is which element in the row, it must be chosen as the second index so rowChoice must be second
        *  */

            if(ag[colChoice][rowChoice].equals("-")) {
                if (playID.equals("X")) {
                    //xCoor is actually the column choice which must be chosen first as it is first array
                    //yCoor is actually the row choice so it must be chosen second as it is second array
                    board[colChoice][rowChoice] = "x";
                } else if (playID.equals("O")) {
                    board[colChoice][rowChoice] = "o";
                }
            } else if(ag[colChoice][rowChoice].equals("x") || ag[colChoice][rowChoice].equals("o")) {
                System.out.println("That spot is taken by you already or another player, please choose another");
                oneTurn();
            }

    }


    public static void setCoord() {


        System.out.println("What are your coordinates (in integers) ?");
        boolean xIsValid = false;
        boolean yIsValid = false;
        //setX(sc);
        //needs to check for int, then check for coordinates, then check for more
        while (!xIsValid) {
            setX();
            xIsValid = true;
        }
        System.out.println("You choose x: " + xCoor);
        while (!yIsValid) {
            setY();
            yIsValid = true;
        }
        System.out.println("You choose y: " + yCoor);

    }
public static void setX() {
    Scanner xRead = new Scanner(System.in);
    if(xRead.hasNextInt()) {
        int posX = xRead.nextInt();
        if(posX >= 1 && posX < 4) {
            xCoor = posX;
        } else {
            System.out.println("Invalid bound. Choose an integer between 1 and 3 inclusive");
            setX();
        }
    } else {
        System.out.println("Invalid coordinates in bound or type. Please reprint x coordinate");
        setX();
    }

}
public static void setY() {
    Scanner yRead = new Scanner(System.in);
    if(yRead.hasNextInt()) {
        int posY = yRead.nextInt();
        if(posY >= 1 && posY < 4) {
            yCoor = posY;
        } else {
            System.out.println("Invalid bound. Choose an integer between 1 and 3 inclusive");
            setY();
        }
    } else {
        System.out.println("Invalid coordinates in bound or type. Please reprint y coordinate");
        setY();
    }

    }


}




