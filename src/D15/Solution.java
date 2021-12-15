package D15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import Utils.Grid;
import Utils.Input;
import Utils.Point;
import Utils.Tuple;

public class Solution {
    HashMap<Point, Integer> dist = new HashMap<Point, Integer>(); //dist.getOrDefault(key, Integer.MAX_VALUE);
    HashMap<Point, Point> prev = new HashMap<Point, Point>();

    public int part1() {
        var grid = Input.getAsGrid(this);
        var source = new Point(0, 0);
        var target = new Point(grid.cols - 1, grid.rows - 1);
        dijkstra(grid, source);
        return getLengthOfPath(grid, target, source);
    }

    public int getLengthOfPath(Grid grid, Point target, Point source) {
        var path = new Stack<Point>();
        var u = target;
        while (u != null) {
            path.push(u);
            u = prev.get(u);
        }

        var cost = 0;
        for (var next : path) {
            if (!next.equals(source)) { //first one isn't counted because we are already there
                cost += grid.grid[next.y][next.x];
            }
        }

        return cost;
    }

    public void dijkstra(Grid grid, Point source) {
        var queue = new HashSet<Point>();
        queue.addAll(grid.getAllPoints());
        dist.put(source, 0);

        while (!queue.isEmpty()) {
            Point u = null;
            int uDist = Integer.MAX_VALUE;
            for (var point : queue) {
                var pDist = dist.getOrDefault(point, Integer.MAX_VALUE);
                if (pDist < uDist) {
                    u = point;
                    uDist = pDist;
                }
            }
            queue.remove(u);

            for (var v : grid.getValidNeighbours4(u)) {
                if (queue.contains(v)) {
                    int alt = dist.get(u) + grid.grid[v.y][v.x];
                    if (alt < dist.getOrDefault(v, Integer.MAX_VALUE)) {
                        dist.put(v, alt);
                        prev.put(v, u);
                    }
                }
            }
        }
    }
    
    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        var day15 = new Solution();
        System.out.println(day15.part1());
        System.out.println(day15.part2());
    }
}