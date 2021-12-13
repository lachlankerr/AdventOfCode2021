package D11;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import Utils.Input;
import Utils.Point;
import Utils.Grid;

public class Solution {
    Grid grid;
    int flashes = 0;

    public int part1() {
        List<String> input = Input.getAsStringList(this);
        grid = new Grid(input.size(), input.get(0).length());

        for (int i = 0; i < grid.rows; i++) {
            grid.setRow(i, Arrays.stream(input.get(i).split("(?!^)")).mapToInt(Integer::parseInt).toArray());
        }

        for (int step = 0; step < 100; step++) {
            Queue<Point> toFlash = new LinkedList<Point>();
            Set<Point> hasFlashed = new HashSet<Point>();
            increaseEnergyLevels(toFlash);
            flashOctopus(toFlash, hasFlashed);
            resetFlashed(hasFlashed);
        }
        
        return flashes;
    }

    public void increaseEnergyLevels(Queue<Point> toFlash) {
        for (int row = 0; row < grid.rows; row++ ) {
            for (int col = 0; col < grid.cols; col++ ) {
                grid.grid[row][col]++;
                if (grid.grid[row][col] > 9) {
                    toFlash.add(new Point(col, row));
                }
            }
        }
    }

    public void flashOctopus(Queue<Point> toFlash, Set<Point> hasFlashed) {
        while (toFlash.size() > 0) {
            Point next = toFlash.remove();
            hasFlashed.add(next);
            flashes++;
            List<Point> pointsToCheck = grid.getValidNeighbours8(next);
            for (Point toCheck : pointsToCheck) {
                grid.grid[toCheck.y][toCheck.x]++;
                if (grid.grid[toCheck.y][toCheck.x] > 9) {
                    if (!hasFlashed.contains(toCheck) && !toFlash.contains(toCheck)) {
                        toFlash.add(toCheck);
                    }
                }
            }
        }
    }

    public void resetFlashed(Set<Point> hasFlashed) {
        for (Point point : hasFlashed) {
            grid.setValue(point, 0);
        }
    }

    public int part2() {
        Set<Point> hasFlashed = new HashSet<Point>();
        int step = 100; // we've already done some
        while (hasFlashed.size() != grid.rows * grid.cols) {
            step++;
            Queue<Point> toFlash = new LinkedList<Point>();
            hasFlashed = new HashSet<Point>();
            increaseEnergyLevels(toFlash);
            flashOctopus(toFlash, hasFlashed);
            resetFlashed(hasFlashed);
        }
        return step;
    }

    public static void main(String[] args) {
        Solution day11 = new Solution();
        System.out.println(day11.part1());
        System.out.println(day11.part2());
    }
}
