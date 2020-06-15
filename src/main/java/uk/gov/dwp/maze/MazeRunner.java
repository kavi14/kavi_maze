package uk.gov.dwp.maze;

import uk.gov.dwp.maze.bean.MazeCoordinate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Root class which initialise and calls explorer to solve given maze
 *
 * @author Kavi
 */
public class MazeRunner {

    private static File mazeToSolve = null;

    public static void main(String[] args) throws FileNotFoundException {
        URL resource = MazeRunner.class.getResource("/maze1/Maze1.txt");
        mazeToSolve = new File(resource.getFile());

        System.out.println("***************************************************************");
        System.out.println("************** Solving Maze using DFS algorithm ***************");
        System.out.println("***************************************************************");
        // UserStory-1.1 - Load maze with start, end and path co ordinates
        // UserStory-1.2 - The maze is available and number of walls and spaces can be queries
        // UserStory-1.3 - I should be able ot put co ordinates and know what exists at that point

        Maze maze = new Maze();
        try {
            maze.initialize(mazeToSolve);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // UserStory 2 - Continues in Explorer
        Explorer mazeExplorer = new Explorer();
        List<MazeCoordinate> mazeCoordinates = mazeExplorer.solveMaze(maze);

        // Now print solved maze
        maze.printMazeResult(mazeCoordinates);

    }
}
