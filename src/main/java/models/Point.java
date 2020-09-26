package models;

import java.util.Objects;

public class Point {
    int row;
    int column;


    public Point() {
    }

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Point{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return row == point.row &&
                Objects.equals(column, point.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }


    /**
     *
     * @param move
     * @return
     */
    public Point move(Move move){
        String axis = move.getNoNameYet();
        String operator = move.getOperator();

        if (axis.equals("row")){
            if (operator.equals("-"))
                //then its row - 1
                return new Point(--row, column);

            //then its row + 1
            return new Point(++row, column);
        }
        if (operator.equals("-"))
            //then its column - 1
            return new Point(row, --column);

        //then its column + 1
        return new Point(row, ++column);
    }

}
