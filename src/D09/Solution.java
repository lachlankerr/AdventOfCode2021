package D09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import Utils.Input;
import Utils.Point;

public class Solution {
    int[][] grid;
    int rows;
    int cols;
    List<Point> lowPoints = new ArrayList<Point>();

    public int part1() {
        List<String> input = Input.getAsStringList(this);
        rows = input.size();
        cols = input.get(0).length();
        grid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = Arrays.stream(input.get(i).split("(?!^)")).mapToInt(Integer::parseInt).toArray();
        }

        int riskLevels = 0;

        for (int row = 0; row < rows; row++ ) {
            for (int col = 0; col < cols; col++ ) {
                Point p = new Point(col, row);
                if (isLowPoint(p)) {
                    riskLevels += grid[row][col] + 1;
                    lowPoints.add(p);
                }
            }
        }

        return riskLevels;
    }

    public boolean isLowPoint(Point p) {
        int current = grid[p.y][p.x];
        
        // x = col, y = row
        if (p.y > 0         && grid[p.y - 1][p.x] <= current ) return false; //up
        if (p.y < rows - 1  && grid[p.y + 1][p.x] <= current ) return false; //down
        if (p.x > 0         && grid[p.y][p.x - 1] <= current ) return false; //left
        if (p.x < cols - 1  && grid[p.y][p.x + 1] <= current ) return false; //right

        return true;
    }

    public int part2() {
        List<Integer> basinSizes = new ArrayList<Integer>();

        for (Point lp : lowPoints) {
            Set<Point> seen = new HashSet<Point>();
            Queue<Point> queue = new LinkedList<Point>();
            queue.add(lp);
            while (queue.size() > 0) {
                Point current = queue.poll();
                List<Point> validPoints = getValidPointsAroundPoint(current);
                for (Point validPoint : validPoints) {
                    if (!seen.contains(validPoint)) {
                        seen.add(validPoint);
                        queue.add(validPoint);
                    }
                }
            }
            basinSizes.add(seen.size());
        }

        Collections.sort(basinSizes, Collections.reverseOrder());

        return basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);
    }

    public List<Point> getValidPointsAroundPoint(Point p) {
        List<Point> validPoints = new ArrayList<Point>();
        int boundary = 9;

        // x = col, y = row
        if (p.y > 0         && grid[p.y - 1][p.x] < boundary ) validPoints.add(new Point(p.x, p.y - 1)); //up
        if (p.y < rows - 1  && grid[p.y + 1][p.x] < boundary ) validPoints.add(new Point(p.x, p.y + 1)); //down
        if (p.x > 0         && grid[p.y][p.x - 1] < boundary ) validPoints.add(new Point(p.x - 1, p.y)); //left
        if (p.x < cols - 1  && grid[p.y][p.x + 1] < boundary ) validPoints.add(new Point(p.x + 1, p.y)); //right

        return validPoints;
    }

    public static void main(String[] args) {
        Solution day09 = new Solution();
        System.out.println(day09.part1());
        System.out.println(day09.part2());
    }
}
