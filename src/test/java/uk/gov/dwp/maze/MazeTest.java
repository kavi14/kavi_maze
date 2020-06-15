package uk.gov.dwp.maze;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * Maze Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 2, 2020</pre>
 */
public class MazeTest {
    /**
     * The Maze to solve.
     */
    File mazeToSolve = null;
    URL resource = MazeRunner.class.getResource("/maze1/Maze1.txt");
    /**
     * The Maze.
     */
    Maze maze = null;

    /**
     * Before.
     *
     * @throws Exception the exception
     */
    @Before
    public void before() throws Exception {
        maze = new Maze();
        mazeToSolve = new File(resource.getFile());
    }

    /**
     * See initial coordinates loaded correctly by checking height and width of maze
     * Method: initialize(File mazeFile)
     *
     * @throws Exception the exception
     */
    @Test
    public void testInitialize() throws Exception {
        maze.initialize(mazeToSolve);
        Assert.assertEquals(maze.getMazeHeight(), 15);
        Assert.assertEquals(maze.getMazeWidth(), 15);
    }

    /**
     * Test start node loaded.
     *
     * @throws Exception the exception
     */
    @Test
    public void testStartNodeLoaded() throws Exception {
        maze.initialize(mazeToSolve);
        Assert.assertNotNull(maze.getStart());
    }

    /**
     * Test end node loaded.
     *
     * @throws Exception the exception
     */
    @Test
    public void testEndNodeLoaded() throws Exception {
        maze.initialize(mazeToSolve);
        Assert.assertNotNull(maze.getEnd());
    }

}
