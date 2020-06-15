package uk.gov.dwp.maze;

import uk.gov.dwp.maze.bean.ActiveNode;
import uk.gov.dwp.maze.bean.MazeCoordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Explorer to solve Maze using DFS method.
 * Using recursion method <method>deepTravel</method> to navigate through node
 * Start with S and going with north facing (-1,0) (as per requirement) and navigate to find path
 * When path is dead end, it returns back but looking for any other available path applying all four directions
 * @author Kavi
 */
public class Explorer {
    /**
     * The Solved path.
     */
    List<MazeCoordinate> solvedPath = new ArrayList<>();

    /**
     * Solve maze list.
     *
     * @param maze the maze
     * @return the list
     */
    protected List<MazeCoordinate> solveMaze(Maze maze) {
        // Let's pick up starting point as specified
        int startX = maze.getStart().getX();
        int startY = maze.getStart().getY();
        System.out.println("Starting from node on Row " + startX + " Col " + startY);
        deepTravel(startX, startY, maze);
        return solvedPath;
    }

    /**
     * Recursion method to navigate maze and find the path
     * solvedPath holds the result of the path found
     * Various conditions applied like check to see nodeIsValid, is it a wall and the node been already visited
     *
     * @param row
     * @param col
     * @param maze
     * @return
     */
    private boolean deepTravel(int row, int col, Maze maze) {
        maze.setActiveNode(new ActiveNode(row, col));
        if (!maze.isActiveValidLoc() || maze.isActiveNodeWall() || maze.isActiveAlreadyVisted()) {
            return false;
        }

        solvedPath.add(new MazeCoordinate(row, col, true));
        maze.setVisited(row, col, true);

        if (maze.isActiveNodeExit()) {
            return true;
        }

        // UserStory 2.Start point (facing north)
        for (int[] pathDir : MazeConstants.PATH_DIRECTIONS) {
            System.out.println("Now looking the direction " + pathDir[0] + "," + pathDir[1]);
            if (deepTravel(row + pathDir[0], col + pathDir[1], maze)) {
                return true;
            }
        }

        System.out.println("Removing route as there is not way out from here on ROW " + row + " COL " + col);
        solvedPath.remove(solvedPath.size() - 1);
        maze.setActiveNode(null);
        return false;
    }

}