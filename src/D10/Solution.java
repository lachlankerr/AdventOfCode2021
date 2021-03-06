package D10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import Utils.Input;

public class Solution {
    List<Stack<String>> incompleteLineStacks = new ArrayList<Stack<String>>();
    Map<String, String> correspondingChars = new HashMap<String, String>();
    Map<String, Integer> errorScores = new HashMap<String, Integer>();

    public int part1() {
        List<String> input = Input.getAsStringList(this);
        
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
                        stack = new Stack<String>();
                        break;
                    }
                }
            }
            if (stack.size() > 0) {
                incompleteLineStacks.add(stack);
            }
        }

        return totalSyntaxError;
    }

    public long part2() {
        errorScores.put(")", 1);
        errorScores.put("]", 2);
        errorScores.put("}", 3);
        errorScores.put(">", 4);

        List<Long> scores = new ArrayList<Long>();

        for (Stack<String> stack : incompleteLineStacks) {
            long lineScore = 0;
            while (stack.size() > 0) {
                String next = stack.pop();
                String corr = correspondingChars.get(next);
                lineScore *= 5;
                lineScore += errorScores.get(corr);
            }

            scores.add(lineScore);
        }

        Collections.sort(scores);

        return scores.get(scores.size()/2);
    }

    public static void main(String[] args) {
        Solution day10 = new Solution();
        System.out.println(day10.part1());
        System.out.println(day10.part2());
    }
}
