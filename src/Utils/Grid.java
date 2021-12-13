package Utils;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    public int rows;
    public int cols;
    public int[][] grid;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols];
    }

    public void setRow(int index, int[] row) {
        grid[index] = row;
    }

    public int getValue(Point p) {
        return grid[p.y][p.x];
    }

    public void setValue(Point p, int value) {
        grid[p.y][p.x] = value;
    }

    public void incrementValue(Point p) {
        grid[p.y][p.x]++;
    }

    public List<Point> getValidNeighbours4(Point p) {
        List<Point> points = new ArrayList<Point>();

        if (p.y > 0)        points.add(new Point(p.x, p.y - 1)); //up
        if (p.y < rows - 1) points.add(new Point(p.x, p.y + 1)); //down
        if (p.x > 0)        points.add(new Point(p.x - 1, p.y)); //left
        if (p.x < cols - 1) points.add(new Point(p.x + 1, p.y)); //right

        return points;
    }

    public List<Point> getValidNeighbours8(Point p) {
        List<Point> points = getValidNeighbours4(p);

        if (p.y > 0 && p.x > 0)                 points.add(new Point(p.x - 1, p.y - 1)); //up left
        if (p.y > 0 && p.x < cols - 1)          points.add(new Point(p.x + 1, p.y - 1)); //up right
        if (p.y < rows - 1 && p.x > 0)          points.add(new Point(p.x - 1, p.y + 1)); //down left
        if (p.y < rows - 1 && p.x < cols - 1)   points.add(new Point(p.x + 1, p.y + 1)); //down right

        return points;
    }
}