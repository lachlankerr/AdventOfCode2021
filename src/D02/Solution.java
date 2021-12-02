package D02;

import java.util.List;

import Utils.Utilities;

public class Solution {
    public int Part1() {
        List<String> input = Utilities.getInputAsStringList(this);
        int horizontal = 0;
        int depth = 0;
        for (String value : input) {
            String[] parts = value.split(" ");
            int x = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "up": depth -= x; break;
                case "down": depth += x; break;
                case "forward": horizontal += x; break;
            }
        }
        return horizontal * depth;
    }
    
    public int Part2() {
        List<String> input = Utilities.getInputAsStringList(this);
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (String value : input) {
            String[] parts = value.split(" ");
            int x = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "up": aim -= x; break;
                case "down": aim += x; break;
                case "forward": horizontal += x; depth += aim * x; break;
            }
        }
        return horizontal * depth;
    }

    public static void main(String[] args) {
        Solution day1 = new Solution();
        System.out.println(day1.Part1());
        System.out.println(day1.Part2());
    }
}
