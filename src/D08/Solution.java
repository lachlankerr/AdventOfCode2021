package D08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Utils.Utilities;

public class Solution {
    public int part1() {
        List<String> input = Utilities.getInputAsStringList(this);

        int count = 0;

        for (String line : input) {
            String[] parts = line.split(" \\| ");
            String[] outputValues = parts[1].split(" ");
            for (String outputValue : outputValues) {
                switch (outputValue.length()) {
                    case 2:                 // digit 1
                    case 4:                 // digit 4
                    case 3:                 // digit 7
                    case 7: count++; break; // digit 8
                }
            }
        }

        return count;
    }

    @SuppressWarnings("unchecked")
    public int part2() {
        List<String> input = Utilities.getInputAsStringList(this);

        int count = 0;

        for (String line : input) {
            String[] parts = line.split(" \\| ");

            String[] signalPatterns = parts[0].split(" ");
            String[] outputValues = parts[1].split(" ");

            Set<String>[] signalPatternsSet = new HashSet[10];
            Set<String>[] outputValuesSet = new HashSet[10];

            for (int i = 0; i < signalPatterns.length; i++) {
                signalPatternsSet[i] = new HashSet<String>(Arrays.asList(signalPatterns[i].split("(?!^)")));
            }
            for (int i = 0; i < outputValues.length; i++) {
                outputValuesSet[i] = new HashSet<String>(Arrays.asList(outputValues[i].split("(?!^)")));
            }

            Entry entry = new Entry(signalPatternsSet, outputValuesSet);
            count += entry.getResult();
        }

        return count;
    }

    public static void main(String[] args) {
        Solution day08 = new Solution();
        System.out.println(day08.part1());
        System.out.println(day08.part2());
    }

    @SuppressWarnings("unchecked")
    public class Entry {
        Set<String>[] signalPatterns;
        Set<String>[] outputValues;

        Set<String>[] decodedDigits = (HashSet<String>[]) new HashSet[10];

        String[] decodedSegments = new String[7]; // 0=a, ..., 6=g

        public Entry(Set<String>[] signalPatterns, Set<String>[] outputValues) {
            this.signalPatterns = signalPatterns;
            this.outputValues = outputValues;

            // find digits 1, 4, 7, and 8 
            for (Set<String> pattern : signalPatterns) {
                switch (pattern.size()) {
                    case 2: decodedDigits[1] = pattern; break;
                    case 4: decodedDigits[4] = pattern; break;
                    case 3: decodedDigits[7] = pattern; break;
                    case 7: decodedDigits[8] = pattern; break;
                }
            }

            // assign segment a
            Set<String> differenceSet = new HashSet<String>(decodedDigits[7]);
            differenceSet.removeAll(decodedDigits[1]);
            decodedSegments[0] = differenceSet.iterator().next();

            // find digit 3
            for (Set<String> pattern : signalPatterns) {
                if (pattern.size() == 5) {
                    Set<String> intersectSet = new HashSet<String>(pattern);
                    intersectSet.retainAll(decodedDigits[1]);
                    if (intersectSet.equals(decodedDigits[1])) {
                        decodedDigits[3] = pattern;
                    }
                }
            }
        }

        public int getResult() {
            return 0;
        }
    }
}
