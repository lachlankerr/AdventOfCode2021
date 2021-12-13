package D13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.Grid;
import Utils.Input;
import Utils.Point;
import Utils.Tuple;

public class Solution {
    
    public String part1and2() {
        Map<Point, Boolean> grid = new HashMap<Point, Boolean>();
        List<String> input = Input.getAsStringList(this);
        List<Tuple<Boolean, Integer>> folds = new ArrayList<Tuple<Boolean, Integer>>();

        for (String line : input) {
            if (line.contains("fold")) {
                String[] parts = line.split("=");
                boolean isX = parts[0].substring(parts[0].length() - 1).equals("x");
                folds.add(new Tuple<Boolean, Integer>(isX, Integer.parseInt(parts[1])));
            }
            else if (!line.isEmpty()) {
                String[] parts = line.split(",");
                Point point = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                grid.put(point, true);
            }
        }

        int rows = 0;
        int cols = 0;

        for (int i = 0; i < folds.size(); i++) {
            Tuple<Boolean, Integer> fold = folds.get(i);
            Map<Point, Boolean> newGrid = new HashMap<Point, Boolean>();
            rows = 0;
            cols = 0;

            if (!fold.x) {
                for (Point key : grid.keySet()) {
                    if (key.y > fold.y) {
                        key.y = fold.y * 2 - key.y;
                    }
                    newGrid.put(key, true);
                    if (key.x > cols) cols = key.x;
                    if (key.y > rows) rows = key.y;
                }
            }
            else {
                for (Point key : grid.keySet()) {
                    if (key.x > fold.y) {
                        key.x = fold.y * 2 - key.x;
                    }
                    newGrid.put(key, true);
                    if (key.x > cols) cols = key.x;
                    if (key.y > rows) rows = key.y;
                }
            }
            grid = newGrid;

            if (i == 0) {
                int dots = 0;
                for (boolean value : newGrid.values()) {
                    if (value)
                        dots++;
                }
                System.out.println(dots);
            }
        }

        Grid display = new Grid(cols + 1, rows + 1);
        for (Point key : grid.keySet()) {
            display.grid[key.x][key.y] = 1;
        }

        for (int y = 0; y < rows + 1; y++) {
            for (int x = 0; x < cols + 1; x++) {
                if (display.grid[x][y] == 0) {
                    System.out.print(" ");
                }
                else {
                    System.out.print("█");
                }
            }
            System.out.println("");
        }

        return "HECRZKPR";
    }

    public static void main(String[] args) {
        Solution day13 = new Solution();
        System.out.println(day13.part1and2());
    }
}
