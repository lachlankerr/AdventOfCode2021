package D17;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Utils.Benchmark;
import Utils.Input;
import Utils.Point;

public class Solution {
    public int part1() {
        var input = Input.getAsStringList(this);
        var match = Pattern.compile("x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)").matcher(input.get(0));
        match.find();
        var bottomLeft = new Point(Integer.parseInt(match.group(1)), Integer.parseInt(match.group(3)));
        var topRight = new Point(Integer.parseInt(match.group(2)), Integer.parseInt(match.group(4)));

        int bestY = 0;

        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                Point point = new Point(x, y);
                if (velocityHitsTarget(bottomLeft, topRight, point) && highestY > bestY) {
                    bestY = highestY;
                }
            }
        }

        return bestY;
    }

    int highestY;

    public boolean velocityHitsTarget(Point bottomLeft, Point topRight, Point velocity) {
        highestY = 0;
        Point point = new Point(0, 0);
        while (!Point.beyondBottomRight(bottomLeft, topRight, point)) {
            if (point.y > highestY)
                highestY = point.y;
            point = new Point(point.x + velocity.x, point.y + velocity.y);
            if (Point.within(bottomLeft, topRight, point)) {
                return true;
            }
            if (velocity.x > 0) 
                velocity.x += -1;
            else if (velocity.x < 0)
                velocity.x += 1;
            velocity.y += -1;
        }
        return false;
    }
    
    public int part2() {
        var input = Input.getAsStringList(this);
        var match = Pattern.compile("x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)").matcher(input.get(0));
        match.find();
        var bottomLeft = new Point(Integer.parseInt(match.group(1)), Integer.parseInt(match.group(3)));
        var topRight = new Point(Integer.parseInt(match.group(2)), Integer.parseInt(match.group(4)));

        var points = new ArrayList<Point>();

        for (int x = 0; x <= topRight.x; x++) {
            for (int y = bottomLeft.y; y < Math.abs(bottomLeft.y); y++) {
                Point point = new Point(x, y);
                if (velocityHitsTarget(bottomLeft, topRight, point)) {
                    points.add(point);
                }
            }
        }

        int maxY = 0;
        for (Point point : points) {
            if (point.y > maxY)
                maxY = point.y;
        }

        return points.size();
    }

    public static void main(String[] args) {
        var day17 = new Solution();
        Benchmark.Run(() -> day17.part1());
        Benchmark.Run(() -> day17.part2());
    }
}
