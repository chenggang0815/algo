package com.leetcode._0617_合并二叉树;

import java.util.Stack;

/*
617. Merge Two Binary Trees
Easy

Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:

Input:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
Output:
Merged tree:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7
Note: The merging process must start from the root nodes of both trees.
 */
public class Solution {
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    static void preorder(TreeNode root){
        if (root==null) return;
        System.out.println(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    /*
    Time complexity : O(m) A total of m nodes need to be traversed. Here, m represents the minimum number of nodes from the two given trees.
    Space complexity : O(m) The depth of the recursion tree can go up to m in the case of a skewed tree. In average case, depth will be O(logm).
     */
    static public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if (t1==null) return t2;
        if (t2==null) return t1;

        t1.val = t1.val + t2.val;

        t1.left = mergeTrees1(t1.left,t2.left);
        t1.right = mergeTrees1(t1.right,t2.right);

        return t1;
    }

    static public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1==null) return t2;
        Stack<TreeNode[]> stack = new Stack<>();

        stack.push(new TreeNode[] {t1, t2});
        while (!stack.isEmpty()){
            TreeNode[] t = stack.pop();
            if (t[0]==null || t[1]==null){
                continue;
            }
            t[0].val = t[0].val + t[1].val;
            if (t[0].left==null){
                t[0].left = t[1].left;
            }else {
                stack.push(new TreeNode[]{t[0].left, t[1].left});
            }
            if (t[0].right==null){
                t[0].right = t[1].right;
            }else {
                stack.push(new TreeNode[]{t[0].right, t[1].right});
            }
        }
        return t1;
    }



    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);

        root1.left = node1;
        root1.right = node2;
        node1.left = node3;

        TreeNode root2 = new TreeNode(2);
        TreeNode node2_1 = new TreeNode(1);
        TreeNode node2_2 = new TreeNode(3);
        TreeNode node2_3 = new TreeNode(4);
        TreeNode node2_4 = new TreeNode(7);

        root2.left = node2_1;
        root2.right = node2_2;
        node2_1.right = node2_3;
        node2_2.right = node2_4;

        preorder(root1);
        System.out.println("====");
        preorder(root2);
        System.out.println("=====");
        preorder(mergeTrees2(root1,root2));


    }
}
