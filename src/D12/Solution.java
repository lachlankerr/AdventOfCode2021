package D12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Utils.Input;
import Utils.Tuple;

public class Solution {
    Graph graph = new Graph();
    public int part1() {
        List<String> input = Input.getAsStringList(this);
        for (String line : input) {
            String[] parts = line.split("-");
            graph.addEdge(parts[0], parts[1]);
        }

        List<List<Node>> paths = new ArrayList<List<Node>>();

        Node start = graph.getNode("start");
        Node end = graph.getNode("end");

        Stack<Tuple<Node, List<Node>>> stack = new Stack<Tuple<Node, List<Node>>>();
        stack.push(new Tuple<Node, List<Node>>(start, new ArrayList<Node>()));
        while (stack.size() > 0) {
            Tuple<Node, List<Node>> next = stack.pop();
            if (next.x.isBig() || !next.y.contains(next.x)) {
                if (next.x.equals(end)) {
                    paths.add(next.y);
                    continue;
                }
                for (Node neighbour : graph.getNeighbours(next.x)) {
                    stack.push(new Tuple<Node, List<Node>>(neighbour, Stream.concat(next.y.stream(), Arrays.asList(next.x).stream()).collect(Collectors.toList())));
                }
            }
        }

        /*for (List<Node> path : paths) {
            String pathString = "";
            for (Node node : path) {
                pathString += node.id + ",";
            }
            System.out.println(pathString + "end");
            //System.out.println(pathString.substring(0, pathString.length() - 1));
        }*/


        return paths.size();
    }

    public int part2() {
        List<String> input = Input.getAsStringList(this);
        for (String line : input) {
            String[] parts = line.split("-");
            graph.addEdge(parts[0], parts[1]);
        }

        List<List<Node>> paths = new ArrayList<List<Node>>();

        Node start = graph.getNode("start");
        Node end = graph.getNode("end");

        Stack<Tuple<Node, List<Node>>> stack = new Stack<Tuple<Node, List<Node>>>();
        stack.push(new Tuple<Node, List<Node>>(start, new ArrayList<Node>()));
        while (stack.size() > 0) {
            Tuple<Node, List<Node>> next = stack.pop();
            if (next.x.isBig() || part2check(next.y, next.x)) {
                if (next.x.equals(end)) {
                    paths.add(next.y);
                    continue;
                }
                for (Node neighbour : graph.getNeighbours(next.x)) {
                    stack.push(new Tuple<Node, List<Node>>(neighbour, Stream.concat(next.y.stream(), Arrays.asList(next.x).stream()).collect(Collectors.toList())));
                }
            }
        }


        return paths.size();
    }

    public boolean part2check(List<Node> path, Node toAdd) {
        List<Node> newPath = Stream.concat(path.stream(), Arrays.asList(toAdd).stream()).collect(Collectors.toList());

        HashMap<Node, Integer> counts = new HashMap<Node, Integer>();
        
        for (Node node : newPath) {
            if (!node.isBig()) {
                int curr = 0;
                if (counts.containsKey(node)) {
                    curr = counts.get(node);
                }
                counts.put(node, curr + 1);
            }
        }

        int twocounts = 0;

        for (Map.Entry<Node, Integer> value : counts.entrySet()) {
            if (value.getValue() > 2) {
                return false;
            }
            if (value.getKey().id.equals("start") && value.getValue() > 1) {
                return false;
            }
            if (value.getKey().id.equals("end") && value.getValue() > 1) {
                return false;
            }
            if (value.getValue() > 1) {
                twocounts++;
            }
        }

        return twocounts < 2;
    }

    public static void main(String[] args) {
        Solution day12 = new Solution();
        System.out.println(day12.part1());
        System.out.println(day12.part2());
    }

    public class Graph {
        public Set<Node> nodes = new HashSet<Node>();
        public Set<Edge> edges = new HashSet<Edge>();

        public Graph() {

        }

        public void addEdge(String startId, String endId) {
            Node start = new Node(startId);
            Node end = new Node(endId);
            nodes.add(start);
            nodes.add(end);

            Edge edge1 = new Edge(start, end);
            Edge edge2 = new Edge(end, start);
            edges.add(edge1);
            edges.add(edge2);
        }

        public Node getNode(String id) {
            for (Node node : nodes) {
                if (node.id.equals(id)) {
                    return node;
                }
            }
            return null;
        }

        public List<Node> getNeighbours(Node node) {
            List<Node> neighbours = new ArrayList<Node>();
            for (Edge edge : edges) {
                if (edge.start.equals(node)) {
                    neighbours.add(edge.end);
                }
            }
            return neighbours;
        }

        public List<Node> getSmallCaves() {
            List<Node> smallCaves = new ArrayList<Node>();
            for (Node node : nodes) {
                if (!node.isBig() && !node.id.equals("start") && !node.id.equals("end")) {
                    smallCaves.add(node);
                }
            }
            return smallCaves;
        }
    }

    public class Node {
        public String id;

        public Node(String id) {
            this.id = id;
        }

        public boolean isBig() {
            return Character.isUpperCase(id.charAt(0));
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node node = (Node) obj;
            return node.id.equals(id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public class Edge {
        public Node start;
        public Node end;

        public Edge(Node start, Node end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge edge = (Edge) obj;
            return edge.start.equals(start) && edge.end.equals(end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
}
