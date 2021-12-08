package D08;

import java.util.ArrayList;
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

            Set<String>[] signalPatternsSet = new HashSet[signalPatterns.length];
            Set<String>[] outputValuesSet = new HashSet[outputValues.length];

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

        Set<String>[] segmentOptions = (HashSet<String>[]) new HashSet[7];
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

            for (int i = 0; i < segmentOptions.length; i++) {
                segmentOptions[i] = new HashSet<String>();
            }

            // find options for segments c and f via digit 1
            segmentOptions[0].add(decodedSegments[0]);
            segmentOptions[2].addAll(decodedDigits[1]);
            segmentOptions[5].addAll(decodedDigits[1]);

            // find options for segments b and d via digit 4 - 1
            differenceSet = new HashSet<String>(decodedDigits[4]);
            differenceSet.removeAll(decodedDigits[1]);
            segmentOptions[1].addAll(differenceSet);
            segmentOptions[3].addAll(differenceSet);

            // find options for segments e and g via digits 8 - 4 - 7
            differenceSet = new HashSet<String>(decodedDigits[8]);
            differenceSet.removeAll(decodedDigits[4]);
            differenceSet.removeAll(decodedDigits[7]);
            segmentOptions[4].addAll(differenceSet);
            segmentOptions[6].addAll(differenceSet);

            // find digit 3
            Set<String>[] fiveSegDigits = (HashSet<String>[]) new HashSet[3];
            int i = 0;
            for (Set<String> pattern : signalPatterns) {
                if (pattern.size() == 5) {
                    fiveSegDigits[i++] = pattern;
                    Set<String> intersectSet = new HashSet<String>(pattern);
                    intersectSet.retainAll(decodedDigits[1]);
                    if (intersectSet.equals(decodedDigits[1])) {
                        decodedDigits[3] = pattern;
                    }
                }
            }

            // deduce segments b and e via intersect of all 5 segment digits - segment a
            Set<String> intersectSet = new HashSet<String>(fiveSegDigits[0]);
            intersectSet.retainAll(fiveSegDigits[1]);
            intersectSet.retainAll(fiveSegDigits[2]);
            intersectSet.removeAll(segmentOptions[0]);
            segmentOptions[1].removeAll(intersectSet);
            segmentOptions[4].removeAll(intersectSet);

            // deduce segments d and g via segments b and e
            segmentOptions[3].removeAll(segmentOptions[1]);
            segmentOptions[6].removeAll(segmentOptions[4]);

            // find digit 0 via 6 segment digits
            Set<String>[] sixSegDigits = (HashSet<String>[]) new HashSet[3];
            i = 0;
            for (Set<String> pattern : signalPatterns) {
                if (pattern.size() == 6) {
                    sixSegDigits[i] = pattern;
                    intersectSet = new HashSet<String>(pattern);
                    intersectSet.retainAll(segmentOptions[3]);
                    if (intersectSet.size() == 0) {
                        decodedDigits[0] = pattern;
                        sixSegDigits[i] = null;
                    }
                    i++;
                }
            }

            // find digit 9 via last two 6 segment digits - 1
            for (i = 0; i < sixSegDigits.length; i++) {
                if (sixSegDigits[i] != null) {
                    intersectSet = new HashSet<String>(sixSegDigits[i]);
                    intersectSet.retainAll(decodedDigits[1]);
                    if (intersectSet.size() == 2) {
                        decodedDigits[9] = sixSegDigits[i];
                        sixSegDigits[i] = null;
                    }
                }
            }

            // find digit 6 via remaining 6 segment digit
            for (Set<String> sixSegDigit : sixSegDigits) {
                if (sixSegDigit != null) {
                    decodedDigits[6] = sixSegDigit;
                }
            }

            // find segment c
            differenceSet = new HashSet<String>(decodedDigits[8]);
            differenceSet.removeAll(decodedDigits[6]);
            decodedSegments[2] = differenceSet.iterator().next();
            segmentOptions[5].remove(decodedSegments[2]);

            // find segment f
            segmentOptions[2].remove(segmentOptions[5].iterator().next());

            // find remaining digits 2 and 5
            for (Set<String> pattern : signalPatterns) {
                if (pattern.size() == 5) {
                    differenceSet = new HashSet<String>(pattern);
                    if (differenceSet.contains(segmentOptions[0].iterator().next()) && 
                        differenceSet.contains(segmentOptions[1].iterator().next()) && 
                        differenceSet.contains(segmentOptions[3].iterator().next()) && 
                        differenceSet.contains(segmentOptions[5].iterator().next()) && 
                        differenceSet.contains(segmentOptions[6].iterator().next())) {
                        decodedDigits[5] = pattern;
                    }
                    if (differenceSet.contains(segmentOptions[0].iterator().next()) && 
                        differenceSet.contains(segmentOptions[2].iterator().next()) && 
                        differenceSet.contains(segmentOptions[3].iterator().next()) && 
                        differenceSet.contains(segmentOptions[4].iterator().next()) && 
                        differenceSet.contains(segmentOptions[6].iterator().next())) {
                        decodedDigits[2] = pattern;
                    }
                }
            }
        }

        public int getResult() {
            //List<Integer> values = new ArrayList<Integer>();
            int result = 0;
            for (int j = 0; j < outputValues.length; j++) {
                for (int i = 0; i < decodedDigits.length; i++) {
                    if (outputValues[j].equals(decodedDigits[i])) {
                        result = 10 * result + i;
                        //values.add(i);
                    }
                }
            }
            return result;
        }
    }
}
