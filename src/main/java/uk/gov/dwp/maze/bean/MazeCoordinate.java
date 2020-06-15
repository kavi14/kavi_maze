package uk.gov.dwp.maze.bean;

/**
 * The type Maze coordinate.
 * @author Kavi
 */
public class MazeCoordinate {

    private int x;
    private int y;
    private boolean road = false;

    /**
     * Instantiates a new Maze coordinate.
     *
     * @param x the x
     * @param y the y
     */
    public MazeCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Maze coordinate.
     *
     * @param x      the x
     * @param y      the y
     * @param isRoad the is road
     */
    public MazeCoordinate(int x, int y, boolean isRoad) {
        this.x = x;
        this.y = y;
        this.road = isRoad;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Is road boolean.
     *
     * @return the boolean
     */
    public boolean isRoad() {
        return road;
    }
}
