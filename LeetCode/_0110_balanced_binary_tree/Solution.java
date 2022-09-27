package LeetCode._0110_balanced_binary_tree;
/*
110. Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as:
a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 */
/*
Solution
Approach 1: multiple pass recursion
time: O(n^2)
space: O(H)

Approach 2: one pass recursion
time: O(n)
space: O(H)
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
    // approach 1: top-down recursion
    static int maxDepth(TreeNode root){
        if(root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    static boolean isBalanced(TreeNode root){
        if (root == null) return true;

        return isBalanced(root.right) && isBalanced(root.left) && (Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2);
    }

    // approach 2: one pass
    public boolean isBalanced2(TreeNode root) {
        if(root == null) return true;

        return helper(root) != -1;
    }

    int helper(TreeNode root){
        if(root == null) return 0;

        int left = helper(root.left);
        int right = helper(root.right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }
}
