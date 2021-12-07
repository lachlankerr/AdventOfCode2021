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
        HashMap<Integer, Long> fishHash = new HashMap<Integer, Long>();
        List<Integer> initialFish = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        for (int fishTimer : initialFish) {
            if (fishHash.containsKey(fishTimer)) {
                fishHash.put(fishTimer, fishHash.get(fishTimer) + 1);
            }
            else {
                fishHash.put(fishTimer, 1l);
            }
        }

        for (int i = 0; i < days; i++) {
            HashMap<Integer, Long> newFishHash = new HashMap<Integer, Long>();
            for (int j = 0; j < 9; j++) {
                if (fishHash.containsKey(Integer.valueOf(j))) {
                    if (j == 0) {
                        long seven = 0;
                        if (fishHash.containsKey(Integer.valueOf(7))) {
                            seven = fishHash.get(Integer.valueOf(7));
                        }
                        fishHash.put(Integer.valueOf(7), fishHash.get(Integer.valueOf(0)) + seven);
                        newFishHash.put(Integer.valueOf(8), fishHash.get(Integer.valueOf(0)));
                    }
                    else {
                        if (fishHash.containsKey(Integer.valueOf(j))) {
                            newFishHash.put(Integer.valueOf(j - 1), fishHash.get(Integer.valueOf(j)));
                        }
                    }
                }
            }
            fishHash = newFishHash;
        }
        long count = 0;
        for (long value : fishHash.values()) {
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
