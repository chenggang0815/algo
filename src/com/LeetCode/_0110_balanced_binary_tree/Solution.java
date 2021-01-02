package com.LeetCode._0110_balanced_binary_tree;
/*
110. Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as:
a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
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
    static int maxDepth(TreeNode root){
        if(root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    static boolean isBalanced(TreeNode root){
        if (root == null) return true;

        return isBalanced(root.right) && isBalanced(root.left) && (Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2);
    }
}
