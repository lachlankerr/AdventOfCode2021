package D01;

import java.util.List;

import Utils.Utilities;

/**
 * Counts the number of times a depth measurement increases
 */
public class Solution {

    /**
     * @return The number of times a depth measurement increases
     */
    public int Part1() {
        List<Integer> input = Utilities.getInputAsIntegerList(this);

        int previous = Integer.MAX_VALUE;
        int count = 0;

        for (int value : input) {
            if (value > previous) {
                count++;
            }
            previous = value;
        }
        return count;
    }

    /**
     * @return The number of times a depth measurement increases based on a three-measurement sliding window
     */
    public int Part2() {
        List<Integer> input = Utilities.getInputAsIntegerList(this);

        int previous = Integer.MAX_VALUE;
        int n = 3;
        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            if (i == input.size() - n + 1) {
                break;
            }
            int window = 0;

            for (int j = 0; j < n; j++) {
                window += input.get(i + j);
            }

            if (window > previous) {
                count++;
            }
            previous = window;
        }
        return count;
    }

    /**
     * Optimal solution, based off reddit answers
     * @param n window size
     */
    public int Part1or2(int n) {
        List<Integer> input = Utilities.getInputAsIntegerList(this);

        int count = 0;

        for (int i = n; i < input.size(); i++) { //use n to prevent index out of range
            if (input.get(i) > input.get(i - n)) { //since the windows will have the same numbers apart from the ends
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution day1 = new Solution();
        System.out.println(day1.Part1());
        System.out.println(day1.Part2());
        System.out.println("===");
        System.out.println(day1.Part1or2(1));
        System.out.println(day1.Part1or2(3));
    }
}