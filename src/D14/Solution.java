package D14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import Utils.Input;

public class Solution {
    public long part1or2(int steps) {
        var input = Input.getAsStringList(this);

        // get our polymer template
        var polymerTemplate = input.remove(0);
        input.remove(0); //remove newline

        // construct insertion pairs from input
        var pairInsertions = new ArrayList<PairInsertion>();
        for (var line : input) {
            var parts = line.split(" -> ");
            var pairInsertion = new PairInsertion(parts[0], parts[1]);
            pairInsertions.add(pairInsertion);
        }

        // initial fill of our pair map
        var map = new HashMap<Pair, Long>();
        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            var pair = new Pair(polymerTemplate.substring(i, i + 2));
            var count = map.getOrDefault(pair, 0l);
            map.put(pair, count + 1);
        }

        // create new pairs from the insertion for our original pairs, for the given amount of steps
        for (int i = 0; i < steps; i++) {
            var newMap = new HashMap<Pair, Long>();
            for (var entrySet : map.entrySet()) {
                var key = entrySet.getKey();
                var value = entrySet.getValue();
                var pairInsertion = pairInsertions.stream().filter(pi -> pi.pair.equals(key)).findFirst().orElse(null);
                var leftValue = newMap.getOrDefault(pairInsertion.leftPair, 0l) + value;
                var rightValue = newMap.getOrDefault(pairInsertion.rightPair, 0l) + value;
                newMap.put(pairInsertion.leftPair, leftValue);
                newMap.put(pairInsertion.rightPair, rightValue);
            }
            map = newMap;
        }

        // construct frequency map
        var freqMap = new HashMap<Character, Long>();
        for (var entrySet : map.entrySet()) {
            var key = entrySet.getKey();
            var value = entrySet.getValue();
            freqMap.put(key.left, freqMap.getOrDefault(key.left, 0l) + value);
            freqMap.put(key.right, freqMap.getOrDefault(key.right, 0l) + value);
        }

        // adjust frequencies due to pairs
        var smallest = Long.MAX_VALUE;
        var largest = 0l;
        for (var count : freqMap.values()) {
            if (count % 2 == 1)     count++;
            count /= 2;
            if (count > largest)    largest = count;
            if (count < smallest)   smallest = count;
        }

        return largest - smallest;
    }

    public static void main(String[] args) {
        var day14 = new Solution();
        System.out.println(day14.part1or2(10));
        System.out.println(day14.part1or2(40));
    }

    public class PairInsertion {
        public Pair pair;
        public Pair leftPair;
        public Pair rightPair;
        public char middle;

        public PairInsertion(String combined, String middle) {
            this.pair = new Pair(combined);
            this.middle = middle.charAt(0);
            this.leftPair = new Pair(combined.substring(0, 1) + middle);
            this.rightPair = new Pair(middle + combined.substring(1, 2));
        }
    }

    public class Pair {
        public char left;
        public char right;

        public Pair(String combined) {
            left = combined.charAt(0);
            right = combined.charAt(1);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair pair = (Pair) obj;
            return pair.left == left && pair.right == right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }
}