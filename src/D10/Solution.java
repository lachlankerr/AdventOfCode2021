package D10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import Utils.Utilities;

public class Solution {
    public int part1() {
        List<String> input = Utilities.getInputAsStringList(this);
        Map<String, String> correspondingChars = new HashMap<String, String>();
        correspondingChars.put("(", ")");
        correspondingChars.put("[", "]");
        correspondingChars.put("{", "}");
        correspondingChars.put("<", ">");
        correspondingChars.put(")", "(");
        correspondingChars.put("]", "[");
        correspondingChars.put("}", "{");
        correspondingChars.put(">", "<");
        
        Map<String, Boolean> isOpening = new HashMap<String, Boolean>();
        isOpening.put("(", true);
        isOpening.put("[", true);
        isOpening.put("{", true);
        isOpening.put("<", true);
        isOpening.put(")", false);
        isOpening.put("]", false);
        isOpening.put("}", false);
        isOpening.put(">", false);

        Map<String, Integer> errorScores = new HashMap<String, Integer>();
        errorScores.put(")", 3);
        errorScores.put("]", 57);
        errorScores.put("}", 1197);
        errorScores.put(">", 25137);

        int totalSyntaxError = 0;

        for (String line : input) {
            Stack<String> stack = new Stack<String>();
            String[] chars = line.split("(?!^)");
            for (int i = 0; i < chars.length; i++) {
                if (isOpening.get(chars[i])) {
                    stack.push(chars[i]);
                }
                else {
                    String next = stack.pop();
                    if (!correspondingChars.get(next).equals(chars[i])) {
                        totalSyntaxError += errorScores.get(chars[i]);
                    }
                }
            }
        }

        return totalSyntaxError;
    }

    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        Solution day10 = new Solution();
        System.out.println(day10.part1());
        System.out.println(day10.part2());
    }
}
