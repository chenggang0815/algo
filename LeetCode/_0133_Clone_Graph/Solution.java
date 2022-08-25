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

Example 1:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
*/
/*
Solution
Approach 1: DFS

Approach 2: BFS
for example: adjList = [[2,4],[1,3],[2,4],[1,3]]
1 => [2,,4]
2 => [1,3]
3 => [2,4]
4 => [1,3]

1. use a hashMap<node.val, newNode> to store new node and avoid duplicate
2. if neighbor not in map => create a new node and put into map and queue,
   if neighbor in map => put neighbor into current node's neighbors

q=[1] node1 = 1 map<1,node1=[]>
1 => (1) 2 not in the map,
        a. create node2 and put into map, map<2,node2>
        b. put int queue q=[2]
        c. map.get(1).neighbors.add(2) => map<1,node1=[2]>
  => (2) 4 not in the map
        a. create node4 and put int map, map<4,node4>
        b. put into queue, q=[2,4]
        c. map.get(1).neighbors.add(4) => map<1,node1=[2,4]>
2 => (1) 1 in map
       a. map.get(2).neighbors.add(1) => map<2,node2=[1]>
     (2) 3 not in map
        a. create node3 and put int map, map<3,node3>
        b. put into queue, q=[4,3]
        c. map.get(2).neighbors.add(3) => map<2,node2=[1,3]>

....
map<1,node1=[2,4]>
map<2,node2=[1,3]>
map<3,node3=[2,4]>
map<4,node4=[1,3]>

return map.get(node.val)
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

    // dfs
    static public Node cloneGraph1(Node node) {
        if(node == null) return null;

        HashMap<Integer, Node> map = new HashMap<>();
        dfs(node, map);

        return map.get(node.val);
    }

    static Node dfs(Node node, HashMap<Integer, Node> map){
        if(map.containsKey(node.val)) return map.get(node.val);

        map.put(node.val, new Node(node.val, new ArrayList<>()));
        for(Node n : node.neighbors){
            map.get(node.val).neighbors.add(dfs(n, map));
        }

        return map.get(node.val);
    }

    static Node cloneGraph2(Node node){
        if(node == null) return null;

        HashMap<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        map.put(node.val, new Node(node.val, new ArrayList<>()));
        /*
        错误写法,
        list.add(node)
        map.put(curNode.val, new Node(curNode.val, list));
        使用的还是旧的node，没有实现deep copy

        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            System.out.print(curNode.val);
            Node newNode = new Node(curNode.val, new ArrayList());
            map.put(curNode.val, newNode);
            //ArrayList<Node> list = new ArrayList<>();
            for(Node neighbor : curNode.neighbors){
                list.add(neighbor);
                if(!map.containsKey(neighbor.val)){
                    queue.add(neighbor);
                }
            }

            map.put(curNode.val, new Node(curNode.val, list));
        }
        */
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            //map.put(curNode.val, new Node(curNode.val, new ArrayList<>())); => 会把已有的node重置为空
            for(Node neighbor : curNode.neighbors){

                if(!map.containsKey(neighbor.val)){
                    queue.add(neighbor);
                    map.put(neighbor.val, new Node(neighbor.val, new ArrayList<>()));

                }
                map.get(curNode.val).neighbors.add(map.get(neighbor.val));
            }
        }

        return map.get(node.val);
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
