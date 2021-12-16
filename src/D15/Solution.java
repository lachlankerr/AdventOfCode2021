package D15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import Utils.Benchmark;
import Utils.Grid;
import Utils.Input;
import Utils.Point;

public class Solution {
    HashMap<Point, Integer> dist = new HashMap<Point, Integer>(); //dist.getOrDefault(key, Integer.MAX_VALUE);
    HashMap<Point, Point> prev = new HashMap<Point, Point>();
    HashMap<Point, Point> cameFrom = new HashMap<Point, Point>();

    public int part1(Algorithm algo) {
        var grid = Input.getAsGrid(this);
        var source = new Point(0, 0);
        var target = new Point(grid.cols - 1, grid.rows - 1);
        if (algo == Algorithm.Astar) {
            astar(grid, source, target);
            return getLengthOfPathAstart(grid, source, target);
        }
        else {
            dijkstra(grid, source, target);
            return getLengthOfPathDijkstra(grid, source, target);
        }
    }
    
    public int part2(Algorithm algo) {
        var grid = Input.getAsGrid(this);
        grid.extendGrid(5);
        var source = new Point(0, 0);
        var target = new Point(grid.cols - 1, grid.rows - 1);
        if (algo == Algorithm.Astar) {
            astar(grid, source, target);
            return getLengthOfPathAstart(grid, source, target);
        }
        else {
            dijkstra(grid, source, target);
            return getLengthOfPathDijkstra(grid, source, target);
        }
    }

    public int getLengthOfPathDijkstra(Grid grid, Point source, Point target) {
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

    public void dijkstra(Grid grid, Point source, Point target) {
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
            if (u == target) {
                return;
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

    public int h(Point n, Point goal) {
        return Math.abs(n.x - goal.x) + Math.abs(n.y - goal.y);
    }

    public int getLengthOfPathAstart(Grid grid, Point start, Point goal) {
        var path = new Stack<Point>();
        var u = goal;
        while (u != null) {
            path.push(u);
            u = cameFrom.get(u);
        }

        var cost = 0;
        for (var next : path) {
            if (!next.equals(start)) { //first one isn't counted because we are already there
                cost += grid.grid[next.y][next.x];
            }
        }

        return cost;
    }

    public void astar(Grid grid, Point start, Point goal) {
        var openSet = new HashSet<Point>();
        openSet.add(start);
        cameFrom = new HashMap<Point, Point>();
        var gScore = new HashMap<Point, Integer>(); //gScore.getOrDefault(key, Integer.MAX_VALUE);
        gScore.put(start, 0);
        var fScore = new HashMap<Point, Integer>(); //fScore.getOrDefault(key, Integer.MAX_VALUE);
        fScore.put(start, h(start, goal)); //fScore[n] := gScore[n] + h(n)

        while (!openSet.isEmpty()) {
            Point current = null;
            int currentF = Integer.MAX_VALUE;
            for (var point : openSet) {
                var pointF = fScore.getOrDefault(point, Integer.MAX_VALUE);
                if (pointF < currentF) {
                    current = point;
                    currentF = pointF;
                }
            }
            if (current == goal) {
                return;
            }
            openSet.remove(current);

            for (var neighbor : grid.getValidNeighbours4(current)) {
                int tentative_gScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + grid.grid[neighbor.y][neighbor.x];
                if (tentative_gScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentative_gScore);
                    fScore.put(neighbor, tentative_gScore + h(neighbor, goal));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
    }

    public enum Algorithm {
        Astar,
        Dijkstra
    }

    public static void main(String[] args) {
        var day15 = new Solution();
        System.out.println("Astar");
        Benchmark.Run(() -> day15.part1(Algorithm.Astar));
        Benchmark.Run(() -> day15.part2(Algorithm.Astar));
        System.out.println("Dijkstra");
        Benchmark.Run(() -> day15.part1(Algorithm.Dijkstra));
        Benchmark.Run(() -> day15.part2(Algorithm.Dijkstra));
    }
}