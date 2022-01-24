package Facebook._0865_Smallest_Subtree_with_all_the_Deepest_Nodes;

import java.util.LinkedList;
import java.util.Queue;

/*
865. Smallest Subtree with all the Deepest Nodes
Given the root of a binary tree, the depth of each node is the shortest distance to the root.
Return the smallest subtree such that it contains all the deepest nodes in the original tree.
A node is called the deepest if it has the largest depth possible among any node in the entire tree.
The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.
Example 1:
        3
       / \
      5    1
     / \   / \
    6   2  0  8
       / \
      7   4
Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest nodes of the tree.
Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.
*/
/*
Solution
Approach 1: Lowest Common Ancestor => similar to question 236
1. Perform BFS and store only the first and last node at the deepest level.
2. Find the LCA of first and last node.
3. Thus it is ensured that all the other nodes between the first and last node will be part of the LCA found.
*/
public class Solution {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        while(!queue.isEmpty()){
            int cnt = queue.size();
            firstNode = queue.peek();
            while(cnt > 0){
                TreeNode node = queue.poll();
                secondNode = node;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                cnt--;
            }
        }

        //System.out.print(firstNode.val + "\n");
        //System.out.print(secondNode.val + "\n");

        return lca(root, firstNode, secondNode);
    }

    TreeNode lca(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q) return root;

        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);

        if(left == null && right == null) return null;
        if(left != null && right != null) return root;

        return left == null ? right : left;
    }

    public static void main(String[] args) {

    }
}
