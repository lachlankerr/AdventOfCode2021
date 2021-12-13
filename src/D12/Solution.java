package D12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import Utils.Input;

public class Solution {
    Graph graph = new Graph();
    public int part1() {
        List<String> input = Input.getAsStringList(this);
        for (String line : input) {
            String[] parts = line.split("-");
            graph.addEdge(parts[0], parts[1]);
        }

        List<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();

        ArrayList<Node> currentPath = new ArrayList<Node>();

        Node start = graph.getNode("start");
        Node end = graph.getNode("end");
        List<Node> neighbours = graph.getNeighbours(start);
        if (neighbours.contains(end)) {
            paths.add(currentPath);
        }

        return 0;
    }

    public int part2() {
        return 0;
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
