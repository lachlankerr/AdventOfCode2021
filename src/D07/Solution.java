package D07;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import Utils.Input;

public class Solution {
    public int part1() {
        List<String> input = Input.getAsStringList(this);
        int[] positions = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(positions);
        int median = positions[positions.length/2];

        int fuel = 0;
        for (int position : positions) {
            fuel += Math.abs(position - median);
        }

        return fuel;
    }

    public int part2() {
        List<String> input = Input.getAsStringList(this);
        int[] positions = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        int sum = IntStream.of(positions).sum();
        int mean = sum / positions.length;
        
        int fuel = 0;
        for (int position : positions) {
            int n = Math.abs(position - mean);
            fuel += (n*(n+1))/2;
        }

        return fuel;
    }

    public static void main(String[] args) {
        Solution day07 = new Solution();
        System.out.println(day07.part1());
        System.out.println(day07.part2());
    }
}
