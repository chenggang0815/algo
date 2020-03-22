package com.leetcode._0016;

public class Solution {
    static class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode(int val){
            this.val = val;
        }
    }

    static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {

    }
}
