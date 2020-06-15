package uk.gov.dwp.maze.bean;

/**
 * A node is being validated by Explorer.
 * @author Kavi
 */
public class ActiveNode extends MazeCoordinate {
    /**
     * The Path object.
     */
    String pathObject;

    /**
     * Instantiates a new Active node.
     *
     * @param x the x
     * @param y the y
     */
    public ActiveNode(int x, int y) {
        super(x, y);
    }

    /**
     * Instantiates a new Active node.
     *
     * @param x          the x
     * @param y          the y
     * @param pathObject the path object
     */
    public ActiveNode(int x, int y, String pathObject) {
        super(x, y);
        this.pathObject = pathObject;
    }
}
