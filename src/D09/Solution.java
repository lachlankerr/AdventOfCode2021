package D09;

import java.util.Arrays;
import java.util.List;

import Utils.Utilities;

public class Solution {
    int[][] grid;
    int rows;
    int cols;

    public int part1() {
        List<String> input = Utilities.getInputAsStringList(this);
        rows = input.size();
        cols = input.get(0).length();
        grid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = Arrays.stream(input.get(i).split("(?!^)")).mapToInt(Integer::parseInt).toArray();
        }

        int riskLevels = 0;

        for (int row = 0; row < rows; row++ ) {
            for (int col = 0; col < cols; col++ ) {
                if (isLowPoint(row, col)) {
                    riskLevels += grid[row][col] + 1;
                }
            }
        }

        return riskLevels;
    }

    public boolean isLowPoint(int row, int col) {
        int current = grid[row][col];
        if (row > 0         && grid[row - 1][col] <= current ) return false; //up
        if (row < rows - 1  && grid[row + 1][col] <= current ) return false; //down
        if (col > 0         && grid[row][col - 1] <= current ) return false; //left
        if (col < cols - 1  && grid[row][col + 1] <= current ) return false; //right

        return true;
    }

    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        Solution day09 = new Solution();
        System.out.println(day09.part1());
        System.out.println(day09.part2());
    }
}
