package LeetCode._0133_Clone_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
133. Clone Graph

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}


Test case format:
For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
*/
public class Solution {
    static class Node {
        public int val;
        public ArrayList<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        public void setNeighbors(ArrayList<Node> neighbors) {
            this.neighbors = neighbors;
        }
    }

    static public Node cloneGraph1(Node node) {
        if (node == null) return null;

        HashMap<Node, Node> map = new HashMap<>();

        return dfs(node, map);
    }

    static Node dfs(Node node, HashMap<Node, Node> map){
        if (node == null) return null;

        if (map.containsKey(node)) return map.get(node);

        Node copyNode = new Node(node.val, new ArrayList<>());
        map.put(node, copyNode);
        for (Node neighbor: node.neighbors){
            copyNode.neighbors.add(dfs(neighbor, map));
        }

        return copyNode;
    }

    static Node cloneGraph2(Node node){
        if (node == null) return null;

        Node copyNode = new Node(node.val, new ArrayList<>());
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node, copyNode);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node originalNode = queue.poll();
            for (Node neighbor: originalNode.neighbors){
                if (!map.containsKey(neighbor)){
                    Node copyNeighbor = new Node(neighbor.val, new ArrayList<>());
                    map.put(neighbor, copyNeighbor);
                    queue.add(originalNode);
                }

                map.get(originalNode).neighbors.add(map.get(neighbor));
            }
        }

        return copyNode;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        ArrayList<Node> neighbors1 = new ArrayList<>();
        neighbors1.add(node2);
        neighbors1.add(node4);
        node1.setNeighbors(neighbors1);

        ArrayList<Node> neighbors2 = new ArrayList<>();
        neighbors2.add(node1);
        neighbors2.add(node3);
        node2.setNeighbors(neighbors2);

        ArrayList<Node> neighbors3 = new ArrayList<>();
        neighbors3.add(node2);
        neighbors3.add(node4);
        node3.setNeighbors(neighbors3);

        ArrayList<Node> neighbors4 = new ArrayList<>();
        neighbors4.add(node1);
        neighbors4.add(node3);
        node4.setNeighbors(neighbors4);
        System.out.println(cloneGraph1(node1).val);
    }
}
