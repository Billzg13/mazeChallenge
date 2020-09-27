package models;

import java.util.ArrayList;
import java.util.Random;

public class Maze {

    int rows;
    int columns;
    Point startingPosition;
    Point goalPosition;

    public Maze() {
    }

    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Maze(int rows, int columns, Point startingPosition, Point goalPosition) {
        this.rows = rows;
        this.columns = columns;
        this.startingPosition = startingPosition;
        this.goalPosition = goalPosition;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int row) {
        this.rows = row;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Point getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Point startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Point getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Point goalPosition) {
        this.goalPosition = goalPosition;
    }

    @Override
    public String toString() {
        return "Maze{" +
                "row=" + rows +
                ", columns=" + columns +
                ", startingPosition=" + startingPosition +
                ", GoalPosition=" + goalPosition +
                '}';
    }

    public boolean findSolution() {
        try {
            if (startingPosition == null || goalPosition == null){
                throw new NullPointerException("starting position or goalPosition is null");
            }
            System.out.println("maze started!");
            var helpCounter = 1;
            var currentPosition = startingPosition;
            //showMaze(currentPosition);
            System.out.println("the starting position is " + startingPosition + " the goal position is " + goalPosition);

            while (!currentPosition.equals(goalPosition)) {
                if (helpCounter > 500) { //help a bit, because it might take too long on large numbers
                    currentPosition.setColumn(goalPosition.getColumn());
                    currentPosition.setRow(goalPosition.getRow() + 1);
                    helpCounter = 0;
                }
                showMaze(currentPosition);
                currentPosition = movePosition(currentPosition);
                helpCounter++;
            }
            showMaze(currentPosition);
            return true;
        } catch (Exception exc) {
            return false;
        }
    }

    //should we show the rows and columns ? probably remove the indexes because they look awful
    private void showMaze(Point currentPosition) {
        var result = "";
        for (var i = 1; i <= rows; i++) {
            if (i != 0) {
                result += i + "\t";
            } else {
                result += " \t";
            }
            for (var j = 1; j <= columns; j++) {
                /*if (i == 0) {
                    result += j + " ";
                    continue;
                } */
                if (new Point(i, j).equals(currentPosition)) {
                    result += "C ";
                } else if (new Point(i, j).equals(this.goalPosition)) {
                    result += "G ";
                } else {
                    result += "- ";
                }
            }
            result += " \n";
        }
        System.out.println(result);
    }

    //point.move("row", "-"); //row - 1
    //point.move("row", "+"); //row + 1
    //point.move("column", "-"); //column - 1
    //point.move("column", "+"); //column + 1
    private Point movePosition(Point point) {
        var viableMoves = findViableMoves(point);
        return point.move(viableMoves.get(new Random().nextInt(viableMoves.size())));
    }

    /**
     * returns viable moves
     *
     * @param point
     * @return
     */
    private ArrayList<Move> findViableMoves(Point point) {
        var viableMoves = new ArrayList<Move>();
        if (point.getColumn() == 1) {
            //cant go left so (column, -) cant be added
            viableMoves.add(new Move("column", "+"));
        } else if (point.getColumn() == this.columns) {
            viableMoves.add(new Move("column", "-"));
            //cant go right so (column, +) cant be added
        } else {
            viableMoves.add(new Move("column", "-"));
            viableMoves.add(new Move("column", "+"));
        }

        if (point.getRow() == 1) {
            //cant go up so (row, -)
            viableMoves.add(new Move("row", "+"));
        } else if (point.getRow() == this.rows) {
            //cant go down so (row, +) cant be done
            viableMoves.add(new Move("row", "-"));
        } else {
            viableMoves.add(new Move("row", "-"));
            viableMoves.add(new Move("row", "+"));
        }
        return viableMoves;
    }

}
