package uk.gov.dwp.maze;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dwp.maze.bean.MazeCoordinate;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * Test method for Explorer
 */
public class ExploreTest {
    /**
     * The Maze to solve.
     */
    File mazeToSolve = null;
    URL resource = MazeRunner.class.getResource("/maze1/Maze1.txt");

    /**
     * The Maze.
     */
    Maze maze = null;
    Explorer explorer = null;

    /**
     * Before.
     *
     * @throws Exception the exception
     */
    @Before
    public void before() throws Exception {
        maze = new Maze();
        mazeToSolve = new File(resource.getFile());
        maze.initialize(mazeToSolve);
        explorer = new Explorer();
    }

    @Test
    public void testMazeRunSuccessfully() throws Exception {
        List<MazeCoordinate> mazeCoordinates = explorer.solveMaze(maze);
        Assert.assertEquals(mazeCoordinates.size(), 74);  // size 74 is expected as per input given
    }

}
