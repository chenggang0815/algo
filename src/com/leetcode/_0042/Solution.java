package com.leetcode._0042;

import java.util.ArrayList;
import java.util.List;

/*
113. Path Sum II Medium
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
Note: A leaf is a node with no children.

Example:
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:

[
   [5,4,11,2],
   [5,8,4,5]
]

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
    //从根结点开始，回溯遍历每个结点。
    //参考链接：https://leetcode-cn.com/problems/path-sum-ii/solution/113java-hui-su-xiang-jie-da-bai-9998-by-ustcyyw/
    static public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        helper(root, sum, res, temp);

        return res;

    }

    static public void helper(TreeNode root, int sum, List<List<Integer>> res, ArrayList<Integer> temp){
        if (root == null) return;

        temp.add(root.val);
        if (root.left == null && root.right == null && root.val == sum){
            res.add(new ArrayList<>(temp));
        }
        helper(root.left, sum - root.val, res, temp);
        helper(root.right, sum - root.val, res, temp);

        temp.remove(temp.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);
        TreeNode node9 = new TreeNode(5);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        node5.left = node9;

        System.out.println(pathSum(root,22));

    }
}
