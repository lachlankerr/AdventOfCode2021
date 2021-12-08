package D08;

import java.util.List;

import Utils.Utilities;

public class Solution {
    public int part1() {
        List<String> input = Utilities.getInputAsStringList(this);

        int count = 0;

        for (String line : input) {
            String[] parts = line.split(" \\| ");
            String[] outputValues = parts[1].split(" ");
            for (String outputValue : outputValues) {
                switch (outputValue.length()) {
                    case 2:                 // digit 1
                    case 4:                 // digit 4
                    case 3:                 // digit 7
                    case 7: count++; break; // digit 8
                }
            }
        }

        return count;
    }

    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        Solution day08 = new Solution();
        System.out.println(day08.part1());
        System.out.println(day08.part2());
    }
}
