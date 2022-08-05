package Other._1002_Sales_Path;
/*
     1
   / \ \
  5  2  4
    /\   \
   1 3   4
*/
public class Solution {
    static class Node {
        int cost;
        Node[] children;
        Node parent;

        Node(int cost) {
            this.cost = cost;
            children = null;
            parent = null;
        }
    }


    static int getCheapestCost(Node rootNode) {
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        dfs(rootNode, 0, res);

        return res[0];
    }

    static void dfs(Node root, int current, int[] res){
        if (root == null) return;

        current += root.cost;
        if(root.children == null){
            res[0] = Math.min(current, res[0]);
            return;
        }

        for (int i = 0; i < root.children.length; i++){
            dfs(root.children[i], current, res);
        }
    }

    /*
     1
   / \ \
  5  2  4
    /\
   1 3
     */

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        root.children = new Node[]{new Node(5), node2, new Node(4)};
        node2.children = new Node[]{new Node(1), new Node(3)};
        System.out.println(getCheapestCost(root));

    }
}
