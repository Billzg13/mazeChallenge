import models.Maze;
import models.Point;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final int MAX_VALUE = 100;
    private static final int MIN_VALUE = 2;

    public static void main(String[] args) {
        System.out.println("enter number of rows (" + MIN_VALUE + " - " + MAX_VALUE + "): ");
        var userInputRows = getUserInput();
        System.out.println("enter number of columns (" + MIN_VALUE + " - " + MAX_VALUE + "): ");
        var userInputColumns = getUserInput();

        //if input is correct we go ahead and initialize the maze
        var maze = new Maze(userInputRows, userInputColumns);

        //we then go ahead and initialize the random points according to our userInputs (startingPoint, goalPoint)
        var startingPosition = findRandomPoint(maze.getRows(), maze.getColumns());
        var goalPosition = findRandomPoint(maze.getRows(), maze.getColumns());

        while (startingPosition.equals(goalPosition)){
            System.out.println("how many times");
            goalPosition = findRandomPoint(maze.getRows(), maze.getColumns());
        }

        maze.setStartingPosition(startingPosition);
        maze.setGoalPosition(goalPosition);
        //we then start the maze game
        maze.findSolution();

        // at this point we have already checked that userInputRows/Columns are valid
        // valid means that we check that userInputRows/columns are between 2 - 100
    }

    /**
     * Finds a random Point for the provided rows and columns
     *
     * @param rows
     * @param col
     * @return
     */
    private static Point findRandomPoint(int rows, int col) { //TODO check nextInt docs
        if (rows == 0 || col == 0)
            return null;
        Random rand = new Random();
        //magic happens here and we return a point
        return new Point(rand.nextInt(rows - 1)+1, rand.nextInt(col -1)+1); // nextInt returns 0 - value
    }

    /**
     * validate the userInput, you can change min value or max value
     *
     * @param value
     * @return
     */
    private static Boolean validateInput(int value) {
        if (value == 0)
            return false;
        if (value < MIN_VALUE)
            return false;
        if (value > MAX_VALUE)
            return false;
        return true;
    }

    private static int getUserInput(){
        int userInput;
        while (true) {
            try {
                Scanner myObj = new Scanner(System.in);
                userInput = myObj.nextInt();

                if (validateInput(userInput) ) break;
            } catch (InputMismatchException inputExc) {
                System.out.println("input can only be an integer between "+ MIN_VALUE +" - "+ MAX_VALUE +"");
            }
        }
        return userInput;
    }
}
