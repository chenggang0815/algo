package com.leetcode._0040;
/*
100. Same Tree Easy

Given two binary trees, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
Example 1:
Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true

Example 2:
Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false

 */
public class Solution {
    static class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode(int val){
            this.val = val;
        }
    }

    // time : o(n)
    //sapce: the best: o(log(n)), the worst :o(n)
    static public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null) return true;
        if (p==null || q==null) return false;
        if (p.val!=q.val) return false;

        return isSameTree(p.right,q.right)&&isSameTree(p.left,q.left);
    }

    public static void main(String[] args) {

        TreeNode root1 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode root2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);

        System.out.println(isSameTree(root1,root2));

    }
}