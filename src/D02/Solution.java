package D02;

import java.util.List;

import Utils.Utilities;

public class Solution {
    public int Part1() {
        List<Integer> input = Utilities.getInputAsIntegerList(this);
        int count = 0;
        for (int value : input) {
            count += value;
        }
        return count;
    }
    
    public int Part2() {
        List<Integer> input = Utilities.getInputAsIntegerList(this);
        int count = 0;
        for (int value : input) {
            count += value;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution day1 = new Solution();
        System.out.println(day1.Part1());
        System.out.println(day1.Part2());
    }
}
