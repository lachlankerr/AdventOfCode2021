package D06;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Utils.Utilities;

public class Solution {
    public long part1or2(int days) {
        List<String> input = Utilities.getInputAsStringList(this);
        HashMap<Integer, Integer> fishHash = new HashMap<Integer, Integer>();
        List<Integer> initialFish = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        for (int fishTimer : initialFish) {
            if (fishHash.containsKey(fishTimer)) {
                fishHash.put(fishTimer, fishHash.get(fishTimer) + 1);
            }
            else {
                fishHash.put(fishTimer, 1);
            }
        }

        for (int i = 0; i < days; i++) {
            HashMap<Integer, Integer> oldFishHash = new HashMap<Integer,Integer>(fishHash);
            for (int j = 0; j < 9; j++) {
                if (fishHash.containsKey(j)) {
                    if (j == 0) {
                        int seven = 0;
                        if (oldFishHash.containsKey(7)) {
                            seven = oldFishHash.get(7);
                        }
                        fishHash.put(6, oldFishHash.get(0) + seven);
                    }
                    else if (j != 6) {
                        if (oldFishHash.containsKey(j - 1)) {
                            fishHash.put(j, oldFishHash.get(j - 1));
                        }
                    }
                }
            }
        }
        long count = 0;
        for (int value : fishHash.values()) {
            count += value;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution day06 = new Solution();
        System.out.println(day06.part1or2(80));
        System.out.println(day06.part1or2(256));
    }
}
