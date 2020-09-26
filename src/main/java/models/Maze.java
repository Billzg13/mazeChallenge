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

    public void findSolution() {
        System.out.println("maze started!");

        var currentPosition = startingPosition;
        //showMaze(currentPosition);
        System.out.println("the starting position is " + startingPosition + " the goal position is " + goalPosition);

        while (!currentPosition.equals(goalPosition)) {
            showMaze(currentPosition);
            currentPosition = movePosition(currentPosition);
        }
        //currentPosition = movePosition(currentPosition);
        System.out.println("goal found!");
        showMaze(currentPosition);
    }

    //should we show the rows and columns ?
    private void showMaze(Point currentPosition) {
        var result = "";
        for (var i = 0; i <= rows; i++) {
            if (i != 0) {
                result += i + "\t";
            } else {
                result += " \t";
            }
            for (var j = 1; j <= columns; j++) {
                if (i == 0) {
                    result += j + " ";
                    continue;
                }
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
        Random r = new Random();
        return point.move(viableMoves.get(r.nextInt(viableMoves.size())));
    }

    private ArrayList<Move> findViableMoves(Point point) {
        var viableMoves = new ArrayList<Move>();
        System.out.println("in findViableMoves: "+point.toString());
        System.out.println("this.cols= "+this.columns);
        System.out.println("this.rows= "+this.rows);
        if (point.getColumn() == 1) {
            //cant go left so (column, -) cant be added
            viableMoves.add(new Move("column", "+"));
        } else if (point.getColumn() == this.columns) {
            viableMoves.add(new Move("column", "-"));
            //cant go right so (column, +) cant be added
        } else {
            viableMoves.add(new Move("column", "-"));
            viableMoves.add(new Move("column", "+"));
            //every move is viable for point.getColumn
        }

        if (point.getRow() == 1) {
            //cant go up so (row, -)
            viableMoves.add(new Move("row", "+"));
        } else if (point.getRow() == this.rows) {
            //cant go down so (row, +) cant be done
            viableMoves.add(new Move("row", "-"));
        } else {
            //every move is viable for point.getRow()
            viableMoves.add(new Move("row", "-"));
            viableMoves.add(new Move("row", "+"));
        }
        return viableMoves;
    }
}
