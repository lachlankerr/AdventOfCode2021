package D06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Utils.Utilities;

public class Solution {
    public int part1or2(int days) {
        List<String> input = Utilities.getInputAsStringList(this);
        List<Integer> fish = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        for (int i = 0; i < days; i++) {
            for (int j = 0; j < fish.size(); j++) {
                if (fish.get(j) == 0) {
                    fish.add(9); //9 because we have to do the minus
                    fish.set(j, 6);
                }
                else {
                    fish.set(j, fish.get(j) - 1);
                }
            }
        }
        return fish.size();
    }

    public static void main(String[] args) {
        Solution day06 = new Solution();
        System.out.println(day06.part1or2(80));
        System.out.println(day06.part1or2(256));
    }
}
