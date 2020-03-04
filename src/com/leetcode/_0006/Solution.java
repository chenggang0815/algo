package com.leetcode._0006;
/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3

But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
 */
public class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

     static boolean isSymmetric(TreeNode root){
            TreeNode t1 = root;
            TreeNode t2 = root;
            return is_Symmertric(t1, t2);
     }

     static boolean is_Symmertric(TreeNode t1, TreeNode t2){
            if (t1==null && t2==null) return true;
            if (t1==null || t2==null) return false;
            return t1.val==t2.val && is_Symmertric(t1.left,t2.right) && is_Symmertric(t1.right,t2.left);
     }


    public static void main(String[] args) {
        //int[] nums = new int[]{0,9,9,9,0,0,0,9,0};
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);

        root.right = node1;
        root.left = node2;
        node1.left = node3;
        node1.right = node5;
        node2.right = node4;
        node2.left = node6;

        System.out.println(isSymmetric(root));
    }
}
