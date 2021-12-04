package D04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Utils.Utilities;

public class Solution {
    List<Integer> nums = new ArrayList<Integer>();
    List<Board> boards = new ArrayList<Board>();

    public void buildBoards() {
        List<String> input = Utilities.getInputAsStringList(this);

        String numbers = input.remove(0);
        nums = Arrays.asList(numbers.split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        
        Board currentBoard = null;

        for (String line : input) {
            if (line.equals("")) {
                if (currentBoard != null) {
                    boards.add(currentBoard);
                }
                currentBoard = new Board();
                continue;
            }
            currentBoard.addRow(line);
        }
        if (currentBoard != null) {
            boards.add(currentBoard);
        }
    }

    public int part1() {
        for (int num : nums) {
            for (Board board : boards) {
                board.addNumber(num);
                if (board.hasBingo()) { //could optimise a bit by only checking for bingo when at least SIZE numbers have been picked
                    return board.getFinalScore();
                }
            }
        }

        return -1;
    }

    public int part2() {
        int bingos = 1; //because we already bingoed once in part 1
        for (int num : nums) {
            for (Board board : boards) {
                board.addNumber(num);
                if (board.hasBingo()) { //could optimise a bit by only checking for bingo when at least SIZE numbers have been picked
                    bingos++;
                }
                if (bingos == boards.size()) {
                    return board.getFinalScore();
                }
            }
        }

        return -1;
    }

    public class Board {
        final int SIZE = 5;
        int lastNum = 0;
        int rowIndex = 0;
        int[] array = new int[SIZE * SIZE];
        Map<Integer, Boolean> hashMap = new HashMap<Integer, Boolean>();
        boolean alreadyBingoed = false;

        public void addRow(String row) {
            int[] rowArray = Arrays.stream(row.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int start = rowIndex * SIZE;
            int end = start + SIZE;
            for (int i = start; i < end; i++) {
                array[i] = rowArray[i % SIZE];
            }
            rowIndex++;
        }

        public void addNumber(int num) {
            hashMap.put(num, true);
            lastNum = num;
        }

        public boolean hasBingo() {
            if (alreadyBingoed)
                return false;
                
            int bingo = 0;
            //rows
            for (int j = 0; j < SIZE; j++) {
                for (int i = 0; i < SIZE; i++) {
                    int num = array[i + j * SIZE];
                    bingo += hashMap.get(num) != null ? 1 : 0;
                }
                if (bingo == SIZE) {
                    alreadyBingoed = true;
                    return true;
                }
                bingo = 0;
            }
            //cols
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    int num = array[i + j * SIZE];
                    bingo += hashMap.get(num) != null ? 1 : 0;
                }
                if (bingo == SIZE) {
                    alreadyBingoed = true;
                    return true;
                }
                bingo = 0;
            }
            return false;
        }

        public int getFinalScore() {
            int sumUnmarked = 0;
            for (int num : array) {
                if (hashMap.get(num) == null) {
                    sumUnmarked += num;
                }
            }
            return lastNum * sumUnmarked;
        }
    }

    public static void main(String[] args) {
        Solution day4 = new Solution();
        day4.buildBoards();
        System.out.println(day4.part1());
        System.out.println(day4.part2());
    }
}
