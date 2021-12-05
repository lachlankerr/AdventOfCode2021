package D05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Utils.Utilities;

public class Solution {
    public int part1() {
        List<String> input = Utilities.getInputAsStringList(this);
        for (String line : input) {
            String[] parts = line.split(" -> ");
            String[] x1y1 = parts[0].split(",");
            String[] x2y2 = parts[0].split(",");
            int x1 = Integer.parseInt(x1y1[0]);
            int y1 = Integer.parseInt(x1y1[1]);
            int x2 = Integer.parseInt(x2y2[0]);
            int y2 = Integer.parseInt(x2y2[1]);
            if (x1 == x2 || y1 == y2) {
                
            }
        }
        return 0;
    }

    public int part2() {
        return 0;
    }
    public static void main(String[] args) {
        Solution day05 = new Solution();
        System.out.println(day05.part1());
        System.out.println(day05.part2());
    }
}