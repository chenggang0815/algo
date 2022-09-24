package LeetCode._0111_minimum_depth_of_binary_tree;

import java.util.LinkedList;

/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
*/
/*
Solution
Approach 1. bfs

Approach 2. dfs time: O(n) space: O(H) H is the height of tree
1. record the depth of current node, if current node is a leaf node, compare it's depth with current minimum depth

*/
public class Solution {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }
    // approach 1 => bfs
    public int minDepth1(TreeNode root) {
        if(root == null) return 0;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while(!queue.isEmpty()){
            int cnt = queue.size();
            while(cnt > 0){
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null) return depth;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                cnt--;
            }
            depth++;
        }

        return depth;
    }

    // dfs 1 don't use dfs function
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;

        if(root.left == null) return minDepth2(root.right) + 1;
        if(root.right == null) return minDepth2(root.left) + 1;

        return Math.min(minDepth2(root.left), minDepth2(root.right)) + 1;
    }

    // dfs 2 dfs function return void
    int res = Integer.MAX_VALUE;
    public int minDepth3(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        dfs3(root, 1);

        return res;
    }

    void dfs3(TreeNode root, int depth){
        if(root == null) return;

        if(root.left == null && root.right == null){
            res = Math.min(res, depth);
            return;
        }

        dfs3(root.left, depth + 1);
        dfs3(root.right, depth + 1);
    }

    // dfs 3 dfs function return integer
    public int minDepth4(TreeNode root) {
        if(root == null) return 0;

        return dfs4(root, 1);
    }

    int dfs4(TreeNode root, int depth){
        if(root == null) return Integer.MAX_VALUE;

        if(root.left == null && root.right == null) return depth;

        return Math.min(dfs4(root.left, depth + 1), dfs4(root.right, depth + 1));
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(0);
        root.left = node1;
        //node1.right = node2;
        //node2.left = node3;
        //node3.left = node4;
        System.out.println(minDepth(root));
    }
}
