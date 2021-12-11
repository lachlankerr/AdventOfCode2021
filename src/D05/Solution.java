package D05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.Input;
import Utils.Point;

public class Solution {

    public int part1or2(boolean isPart2) {
        Map<Point, Integer> grid = new HashMap<Point, Integer>();
        List<String> input = Input.getAsStringList(this);
        for (String lineString : input) {
            String[] parts = lineString.split(" -> ");
            String[] x1y1 = parts[0].split(",");
            String[] x2y2 = parts[1].split(",");
            int x1 = Integer.parseInt(x1y1[0]);
            int y1 = Integer.parseInt(x1y1[1]);
            int x2 = Integer.parseInt(x2y2[0]);
            int y2 = Integer.parseInt(x2y2[1]);

            Line line = new Line(x1, y1, x2, y2);
            List<Point> points = line.getPointsOnLine(isPart2);
            for (Point point : points) {
                int value = 0;
                if (grid.containsKey(point)) {
                    value = grid.get(point);
                }
                grid.put(point, value + 1);
            }
        }

        int count = 0;

        for (Integer value : grid.values()) {
            if (value > 1) {
                count++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
        Solution day05 = new Solution();
        System.out.println(day05.part1or2(false));
        System.out.println(day05.part1or2(true));
    }

    public class Line {
        Point one;
        Point two;

        public Line(int x1, int y1, int x2, int y2) {
            one = new Point(x1, y1);
            two = new Point(x2, y2);
        }

        public List<Point> getPointsOnLine(boolean isPart2) {
            List<Point> points = new ArrayList<Point>();

            //only works for horizontal and vertical lines
            int xDis = Math.abs(one.x - two.x);
            int yDis = Math.abs(one.y - two.y);

            if (xDis == 0) {
                for (int i = 0; i <= yDis; i++) {
                    points.add(new Point(one.x, Math.min(one.y, two.y) + i));
                }
            }
            else if (yDis == 0) {
                for (int i = 0; i <= xDis; i++) {
                    points.add(new Point(Math.min(one.x, two.x) + i, one.y));
                }
            }
            else if (xDis == yDis && isPart2) {
                int directionX = -( one.x - two.x ) / Math.abs( one.x - two.x );
                int directionY = -( one.y - two.y ) / Math.abs( one.y - two.y );
                for (int i = 0; i <= xDis; i++) {
                    points.add(new Point(one.x + i * directionX, one.y + i * directionY));
                }
            }
            
            return points;
        }
    }
}