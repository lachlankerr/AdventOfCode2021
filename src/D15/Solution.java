package D15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import Utils.Grid;
import Utils.Input;
import Utils.Point;
import Utils.Tuple;

public class Solution {
    public int part1() {
        var grid = Input.getAsGrid(this);

        return 0;
    }

    public void astar(Point start, Point goal, int h) {
        var openSet = new PriorityQueue<Point>();
        openSet.add(start);
        var cameFrom = new HashMap<Point, Point>();
        var gScore = new HashMap<Point, Integer>(); //gScore.getOrDefault(key, Integer.MAX_VALUE);
        gScore.put(start, 0);
        var fScore = new HashMap<Point, Integer>(); //fScore.getOrDefault(key, Integer.MAX_VALUE);
        fScore.put(start, h); //fScore[n] := gScore[n] + h(n)

        while (!openSet.isEmpty()) {
            //Node 
        }
    }
    
    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        var day15 = new Solution();
        System.out.println(day15.part1());
        System.out.println(day15.part1());
    }
}