package D05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Utils.Utilities;

public class Solution {
    Map<Point, Integer> grid = new HashMap<Point, Integer>();

    public int part1() {
        List<String> input = Utilities.getInputAsStringList(this);
        for (String lineString : input) {
            String[] parts = lineString.split(" -> ");
            String[] x1y1 = parts[0].split(",");
            String[] x2y2 = parts[0].split(",");
            int x1 = Integer.parseInt(x1y1[0]);
            int y1 = Integer.parseInt(x1y1[1]);
            int x2 = Integer.parseInt(x2y2[0]);
            int y2 = Integer.parseInt(x2y2[1]);

            if (x1 == x2 || y1 == y2) {
                Line line = new Line(x1, y1, x2, y2);
                List<Point> points = line.getPointsOnLine();
                for (Point point : points) {
                    int value = 0;
                    if (grid.containsKey(point)) {
                        value = grid.get(point);
                    }
                    grid.put(point, value + 1);
                }
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

    public int part2() {
        return 0;
    }
    public static void main(String[] args) {
        Solution day05 = new Solution();
        System.out.println(day05.part1());
        System.out.println(day05.part2());
    }

    public class Line {
        Point one;
        Point two;

        public Line(int x1, int y1, int x2, int y2) {
            one = new Point(x1, y1);
            two = new Point(x2, y2);
        }

        public List<Point> getPointsOnLine() {
            List<Point> points = new ArrayList<Point>();
            //todo
            return points;
        }
    }

    public class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point point = (Point) obj;
            return point.x == x && point.y == y;
        }
    }
}