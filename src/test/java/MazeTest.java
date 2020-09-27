import models.Maze;
import models.Point;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @org.junit.jupiter.api.Test
    void findSolution() {
        Maze maze = new Maze();
        maze.setColumns(5);
        maze.setRows(5);
        maze.setGoalPosition(new Point(4, 5));
        maze.setStartingPosition(new Point(1, 1));
        assertEquals(true, maze.findSolution());
    }

    @org.junit.jupiter.api.Test
    void failFindSolutionPositionNull(){
        Maze maze = new Maze();
        maze.setColumns(5);
        maze.setRows(5);
        maze.setGoalPosition(new Point(4, 5));
        assertEquals(false, maze.findSolution());
    }

    @org.junit.jupiter.api.Test
    void failFindSolutionGoalNull(){
        Maze maze = new Maze();
        maze.setColumns(5);
        maze.setRows(5);
        maze.setStartingPosition(new Point(1, 1));
        assertEquals(false, maze.findSolution());
    }
}