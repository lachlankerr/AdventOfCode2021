package D09;

import java.util.Arrays;
import java.util.List;

import Utils.Utilities;

public class Solution {
    int[][] grid;

    public int part1() {
        List<String> input = Utilities.getInputAsStringList(this);
        grid = new int[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            grid[i] = Arrays.stream(input.get(i).split("(?!^)")).mapToInt(Integer::parseInt).toArray();
        }

        int riskLevels = 0;

        for (int row = 0; row < input.size(); row++ ) {
            for (int col = 0; col < input.get(0).length(); col++ ) {
                if (isLowPoint(row, col)) {
                    riskLevels += grid[row][col] + 1;
                }
            }
        }

        return riskLevels;
    }

    public boolean isLowPoint(int row, int col) {
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
