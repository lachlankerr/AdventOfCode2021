package D18;

import java.util.ArrayList;
import java.util.Stack;

import Utils.Benchmark;
import Utils.Input;

public class Solution {
    public int part1() {
        var input = Input.getAsStringList(this);
        var stacks = new ArrayList<Stack<Snailfish>>();
        for (var line : input) {
            var stack = new Stack<Snailfish>();
            for (int i = 0; i < line.length(); i++) {
                var currChar = line.charAt(i);
                Snailfish currSnailfish = null;
                if (stack.size() > 0) {
                    currSnailfish = stack.peek();
                }
                if (currChar == '[') {
                    var newSnailfish = new Snailfish();
                    if (currSnailfish != null) {
                        if (currSnailfish.left == null && currSnailfish.leftValue == -1) {
                            currSnailfish.left = newSnailfish;
                        }
                        else {
                            currSnailfish.right = newSnailfish;
                        }
                    }
                    stack.push(newSnailfish);
                }
                else if (Character.isDigit(currChar)) {
                    int value = Integer.parseInt(String.valueOf(currChar));
                    if (currSnailfish.left == null && currSnailfish.leftValue == -1) {
                        currSnailfish.leftValue = value;
                    }
                    else {
                        currSnailfish.rightValue = value;
                    }
                }
                else if (currChar == ']') {
                    if (stack.size() > 1) {
                        stack.pop();
                    }
                }
            }
            stacks.add(stack);
        }
        return 0;
    }

    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        var day18 = new Solution();
        Benchmark.Run(() -> day18.part1());
        Benchmark.Run(() -> day18.part2());
    }

    public class Snailfish {
        public Snailfish left = null;
        public Snailfish right = null;

        public int leftValue = -1;
        public int rightValue = -1;
    }
}
