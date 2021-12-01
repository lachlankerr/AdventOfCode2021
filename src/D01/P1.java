package D01;

import java.util.List;

import Utils.Utilities;

public class P1 {

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

    public static void main(String[] args) {
        P1 p1 = new P1();
        System.out.println(p1.Part1());
        System.out.println(p1.Part2());
    }
}