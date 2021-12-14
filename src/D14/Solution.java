package D14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Utils.Grid;
import Utils.Input;
import Utils.Point;
import Utils.Tuple;

public class Solution {
    public int part1() {
        var input = Input.getAsStringList(this);
        String polymerTemplate = input.remove(0);
        input.remove(0); //remove newline
        var pairInsertions = new ArrayList<PairInsertion>();
        for (String line : input) {
            String[] parts = line.split(" -> ");
            PairInsertion pairInsertion = new PairInsertion(parts[0], parts[1]);
            pairInsertions.add(pairInsertion);
        }

        var map = new HashMap<Pair, Integer>();
        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            var pair = new Pair(polymerTemplate.substring(i, i + 2));
            int count = 0;
            if (map.containsKey(pair)) {
                count = map.get(pair);
            }
            map.put(pair, count + 1);
        }
        return 0;
    }
    
    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        Solution day14 = new Solution();
        System.out.println(day14.part1());
        System.out.println(day14.part2());
    }

    public class PairInsertion {
        public char left;
        public char right;
        public char middle;

        public PairInsertion(String combined, String middle) {
            this.left = combined.charAt(0);
            this.right = combined.charAt(1);
            this.middle = middle.charAt(0);
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