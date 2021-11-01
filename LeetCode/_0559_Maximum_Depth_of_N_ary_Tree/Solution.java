package LeetCode._0559_Maximum_Depth_of_N_ary_Tree;

import java.util.List;
/*
559. Maximum Depth of N-ary Tree

Given a n-ary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: 3

     1
   / | \
  3  2  4
 / \
5  6
*/
/*
Solution:
Approach 1: dfs

Approach 2: iteration
*/

public class Solution {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {
        if(root == null) return 0;

        int[] maxDepth = new int[1];
        dfs(root, 0, maxDepth);

        return maxDepth[0];
    }

    void dfs(Node root, int depth, int[] maxDepth){
        if(root == null) return;

        maxDepth[0] = Math.max(maxDepth[0], depth + 1);
        for(Node node : root.children){
            dfs(node, depth + 1, maxDepth);
        }
    }
    public static void main(String[] args) {

    }
}
