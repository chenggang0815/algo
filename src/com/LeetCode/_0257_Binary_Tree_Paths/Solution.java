package com.LeetCode._0257_Binary_Tree_Paths;


import java.util.ArrayList;
import java.util.List;

/*
257. Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.

Example:
Input:

   1
 /   \
2     3
 \
  5
Output: ["1->2->5", "1->3"]
Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    static List<String> res = new ArrayList<>(); // 使用全局变量，好像会影响leetcode的答案
    static StringBuilder sb = new StringBuilder();

    static void dfs(TreeNode root, StringBuilder sb){
        if (root == null) {
            sb.delete(sb.length() - 2, sb.length());
            return;
        }
        sb.append(root.val);
        if (root.left == null && root.right == null){
            res.add(sb.toString());
            return;
        }
        //dfs(root.left, sb.append("->"));
        //dfs(root.right, sb.append("->")); 为什么错误？

        dfs(root.left, new StringBuilder(sb).append("->"));
        dfs(root.right, new StringBuilder(sb).append("->"));

    }

    static List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return res;

        dfs(root, sb);

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        System.out.println(binaryTreePaths(null));
    }
}
