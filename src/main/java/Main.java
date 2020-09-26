import models.Maze;
import models.Point;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final int MAX_VALUE = 100;
    private static final int MIN_VALUE = 2;

    public static void main(String[] args) {
        var userInputRows = getValidUserInput("rows");
        var userInputColumns = getValidUserInput("columns");

        //if input is correct we go ahead and initialize the maze
        var maze = new Maze(userInputRows, userInputColumns);

        //we then go ahead and initialize the random points according to our userInputs (startingPoint, goalPoint)
        var startingPosition = findRandomPoint(maze.getRows(), maze.getColumns());
        var goalPosition = findRandomPoint(maze.getRows(), maze.getColumns());

        while (startingPosition.equals(goalPosition)){
            goalPosition = findRandomPoint(maze.getRows(), maze.getColumns());
        }

        maze.setStartingPosition(startingPosition);
        maze.setGoalPosition(goalPosition);

        if (maze.findSolution()) {
            System.out.println("solution found!");
            return;
        }
        System.out.println("solution not found, something went wrong");
    }

    /**
     * Finds a random Point for the provided rows and columns
     * @param rows
     * @param col
     * @return
     */
    public static Point findRandomPoint(int rows, int col) {
        if (rows == 0 || col == 0)
            throw new NullPointerException("null exception");
        var rand = new Random();
        return new Point(rand.nextInt(rows - 1)+1, rand.nextInt(col -1)+1); // nextInt returns a random number from a range of 0 - value
    }

    /**
     * validate the userInput, you can change min value or max value
     * @param value
     * @return
     */
    public static Boolean validateInput(int value) {
        if (value == 0)
            return false;
        if (value < MIN_VALUE)
            return false;
        if (value > MAX_VALUE)
            return false;
        return true;
    }

    /**
     * returns a valid user input according to MIN_VALUE and MAX_VALUE
     * @return
     */
    public static int getValidUserInput(String name){
        System.out.println("enter number of "+name+" (" + MIN_VALUE + " - " + MAX_VALUE + "): ");
        int userInput;
        while (true) {
            try {
                var myObj = new Scanner(System.in);
                userInput = myObj.nextInt();

                if (validateInput(userInput) ) break;

                throw new RuntimeException("wrong range of number");
            } catch (InputMismatchException inputExc) {
                System.out.println("input can only be an integer between "+ MIN_VALUE +" - "+ MAX_VALUE +"");
            }catch (RuntimeException exc){
                System.out.println("input must be beetween "+MIN_VALUE+" and "+MAX_VALUE);
            }
        }
        return userInput;
    }
}
