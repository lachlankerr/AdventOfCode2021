package D03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Utils.Utilities;

public class Solution {
    //String gammaBinaryString;
    //String epsilonBinaryString;
    public int n;

    public String getGammaBinaryByList(List<String> input) {
        int numLines = input.size();
        int[] gammaArray = new int[n];
        StringBuilder gammaString = new StringBuilder(new String(new char[n]).replace('\0', '0'));

        for (String value : input) {
            for (int i = 0; i < n; i++) {
                if (value.charAt(i) == '1') {
                    gammaArray[i]++;
                }
                if (gammaArray[i] > numLines / 2) {
                    gammaString.setCharAt(i, '1');
                }
            }
        }
        return gammaString.toString();
    }

    public String getEpsilonBinaryByList(List<String> input) {
        StringBuilder gammaString = new StringBuilder(getGammaBinaryByList(input));
        for (int i = 0; i < n; i++) {
            gammaString.setCharAt(i, (char)(gammaString.charAt(i) ^ 1));
        }
        return gammaString.toString();
    }

    public int part1(List<String> input) {
        /*int n = input.get(0).length();
        int numLines = input.size();
        int[] gammaArray = new int[n];
        StringBuilder gammaString = new StringBuilder(new String(new char[n]).replace('\0', '0'));

        for (String value : input) {
            for (int i = 0; i < n; i++) {
                if (value.charAt(i) == '1') {
                    gammaArray[i]++;
                }
                if (gammaArray[i] > numLines / 2) {
                    gammaString.setCharAt(i, '1');
                }
            }
        }
        String gammaBinaryString = gammaString.toString();
        int gamma = Integer.parseInt(gammaString.toString(), 2);
        for (int i = 0; i < n; i++) {
            gammaString.setCharAt(i, (char)(gammaString.charAt(i) ^ 1));
        }
        String epsilonBinaryString = gammaString.toString();
        int epsilon = Integer.parseInt(gammaString.toString(), 2);*/

        int gamma = Integer.parseInt(getGammaBinaryByList(input), 2);
        int epsilon = Integer.parseInt(getEpsilonBinaryByList(input), 2);

        return gamma * epsilon;
    }

    public int part2() {
        /*List<String> input = Utilities.getInputAsStringList(this);
        Queue<String> OGRqueue = new PriorityQueue<String>(input);
        Queue<String> OGRnewQueue = new PriorityQueue<String>();
        
        Queue<String> CSRqueue = new PriorityQueue<String>(input);
        Queue<String> CSRnewQueue = new PriorityQueue<String>();
        int n = input.get(0).length();
        for (int i = 0; i < n; i++) {
            while (OGRqueue.peek() != null) {
                String next = OGRqueue.poll();
                if (next.charAt(i) == gammaBinaryString.charAt(i)) {
                    OGRnewQueue.add(next);
                }
            }
            OGRqueue = new PriorityQueue<String>(OGRnewQueue);
            OGRnewQueue = new PriorityQueue<String>();
            while (CSRqueue.peek() != null) {
                String next = CSRqueue.poll();
                if (next.charAt(i) == epsilonBinaryString.charAt(i)) {
                    CSRnewQueue.add(next);
                }
            }
            CSRqueue = new PriorityQueue<String>(CSRnewQueue);
            CSRnewQueue = new PriorityQueue<String>();
            part1() 
        }

        */
        return 0;
    }

    public static void main(String[] args) {
        Solution day3 = new Solution();
        List<String> input = Utilities.getInputAsStringList(day3);
        day3.n = input.get(0).length();
        System.out.println(day3.part1(input));
        System.out.println(day3.part2());
    }
}