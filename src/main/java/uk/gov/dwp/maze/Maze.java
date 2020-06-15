package uk.gov.dwp.maze;

import uk.gov.dwp.maze.bean.ActiveNode;
import uk.gov.dwp.maze.bean.MazeCoordinate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class holds all loaded coordinates, start and end nodes and keep tracks of all nodes visited
 * This object plays major roles in <class>Explorer</class> when searching through maze
 *
 * @author Kavi
 */
public class Maze {

    private String[][] maze;
    private boolean[][] visited;
    private MazeCoordinate start;
    private MazeCoordinate end;
    private ActiveNode activeNode;

    /**
     * Loads maze file and initialise maze coordinates
     *
     * @param mazeFile the maze file
     * @throws FileNotFoundException the file not found exception
     */
    protected void initialize(File mazeFile) throws Exception {
        Scanner scanner = new Scanner(mazeFile);
        int row = 0;
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        if (lines.isEmpty()) {
            throw new Exception("File is Empty");
        }
        maze = new String[lines.size()][lines.get(0).length()];
        visited = new boolean[lines.size()][lines.get(0).length()];

        for (String line : lines) {
            for (int col = 0; col < line.length(); col++) {
                initMaze(row, col, line.charAt(col));
            }
            row += 1;
        }

    }

    /**
     * Used internally to find and set start and end node
     * and find the correct node
     * @param row
     * @param col
     * @param value
     */
    private void initMaze(int row, int col, char value) {
        switch (value) {
            case 'S':
                maze[row][col] = MazeConstants.START;
                start = new MazeCoordinate(row, col);
                return;
            case 'F':
                end = new MazeCoordinate(row, col);
                maze[row][col] = MazeConstants.EXIT;
                return;
            case 'X':
                maze[row][col] = MazeConstants.WALL;
                return;
            default:
                maze[row][col] = MazeConstants.PATH;
        }

    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public MazeCoordinate getStart() {
        return start;
    }

    /**
     * Gets end.
     *
     * @return the end
     */
    public MazeCoordinate getEnd() {
        return end;
    }

    /**
     * Gets maze height.
     *
     * @return the maze height
     */
    public int getMazeHeight() {
        return maze.length;
    }

    /**
     * Gets maze width.
     *
     * @return the maze width
     */
    public int getMazeWidth() {
        return maze[0].length;
    }

    /**
     * Is exit boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isExit(int x, int y) {
        boolean res = x == end.getX() && y == end.getY();
        if (res)
            System.out.println("Found Exit on ROW " + x + " COL " + y);

        return res;
    }

    /**
     * Is wall boolean.
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    public boolean isWall(int row, int col) {
        boolean res = maze[row][col] == MazeConstants.WALL;
        if (res)
            System.out.println("Found WALL on ROW " + row + " COL " + col);
        return res;
    }

    /**
     * Sets visited.
     *
     * @param row   the row
     * @param col   the col
     * @param value the value
     */
    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    /**
     * Is valid location boolean.
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getMazeHeight() || col < 0 || col >= getMazeWidth()) {
            System.out.println("Not VALID location " + row + "," + col);
            return false;
        }
        return true;
    }

    /**
     * Sets active node.
     *
     * @param activeNode the active node
     */
    public void setActiveNode(ActiveNode activeNode) {
        this.activeNode = activeNode;
    }

    /**
     * Is active node wall boolean.
     *
     * @return the boolean
     */
    public boolean isActiveNodeWall() {
        if (activeNode != null) {
            return isWall(activeNode.getX(), activeNode.getY());
        }
        return false;
    }

    /**
     * Is active node exit boolean.
     *
     * @return the boolean
     */
    public boolean isActiveNodeExit() {
        if (activeNode != null) {
            return isExit(activeNode.getX(), activeNode.getY());
        }
        return false;
    }

    /**
     * Is active valid loc boolean.
     *
     * @return the boolean
     */
    public boolean isActiveValidLoc() {
        if (activeNode != null) {
            return isValidLocation(activeNode.getX(), activeNode.getY());
        }
        return false;
    }

    /**
     * Is active already visted boolean.
     *
     * @return the boolean
     */
    public boolean isActiveAlreadyVisted() {
        if (activeNode != null) {
            return isVisited(activeNode.getX(), activeNode.getY());
        }
        return false;
    }

    /**
     * Is visited boolean.
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    public boolean isVisited(int row, int col) {
        boolean res = visited[row][col];
        if (res)
            System.out.println("Already explored route on ROW " + row + " COL " + col);

        return visited[row][col];
    }

    /**
     * Is start boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isStart(int x, int y) {
        boolean res = x == start.getX() && y == start.getY();
        if (res)
            System.out.println("Found Entry on ROW " + x + " COL " + y);
        return res;
    }

    /**
     * Print maze result.
     *
     * @param path the path
     */
    public void printMazeResult(List<MazeCoordinate> path) {
        String[][] tempMaze = Arrays.stream(maze)
                .map(String[]::clone)
                .toArray(String[][]::new);
        for (MazeCoordinate coordinate : path) {
            if (isStart(coordinate.getX(), coordinate.getY()) || isExit(coordinate.getX(), coordinate.getY())) {
                continue;
            }
            if (coordinate.isRoad())
                tempMaze[coordinate.getX()][coordinate.getY()] = MazeConstants.ROAD;
        }
        System.out.println("***************************************************************\n");
        System.out.println(toString(tempMaze));
        System.out.println("***************************************************************");

    }

    /**
     * To string string.
     *
     * @param maze the maze
     * @return the string
     */
    public String toString(String[][] maze) {
        StringBuilder result = new StringBuilder(getMazeWidth() * (getMazeHeight() + 1));
        for (int row = 0; row < getMazeHeight(); row++) {
            for (int col = 0; col < getMazeWidth(); col++) {
                if (maze[row][col] == MazeConstants.PATH) {
                    result.append(' ');
                } else if (maze[row][col] == MazeConstants.WALL) {
                    result.append('X');
                } else if (maze[row][col] == MazeConstants.START) {
                    result.append('S');
                } else if (maze[row][col] == MazeConstants.EXIT) {
                    result.append('F');
                } else {
                    result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }
}
