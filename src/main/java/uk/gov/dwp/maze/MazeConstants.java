package uk.gov.dwp.maze;

/**
 * The type Maze constants.
 * @author Kavi
 */
public class MazeConstants {

    /**
     * The constant PATH.
     */
    public static final String PATH = "PATH";
    /**
     * The constant WALL.
     */
    public static final String WALL = "WALL";
    /**
     * The constant START.
     */
    public static final String START = "START";
    /**
     * The constant EXIT.
     */
    public static final String EXIT = "EXIT";
    /**
     * The constant ROAD.
     */
    public static final String ROAD = "ROAD";
    /**
     * The constant PATH_DIRECTIONS.
     */
// String to look to north facing as (0, -1) direct to north
    public static final int[][] PATH_DIRECTIONS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

}
