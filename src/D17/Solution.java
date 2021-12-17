package D17;

import java.util.regex.Pattern;

import Utils.Benchmark;
import Utils.Input;
import Utils.Point;

public class Solution {
    public boolean part1() {
        var input = Input.getAsStringList(this);
        var match = Pattern.compile("x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)").matcher(input.get(0));
        match.find();
        var topLeft = new Point(Integer.parseInt(match.group(1)), Integer.parseInt(match.group(3)));
        var bottomRight = new Point(Integer.parseInt(match.group(2)), Integer.parseInt(match.group(4)));
        return Point.within(topLeft, bottomRight, new Point(25, -7));
    }
    
    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        var day17 = new Solution();
        Benchmark.Run(() -> day17.part1());
        Benchmark.Run(() -> day17.part2());
    }
}
