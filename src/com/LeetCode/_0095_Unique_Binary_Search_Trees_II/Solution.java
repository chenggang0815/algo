package com.LeetCode._0095_Unique_Binary_Search_Trees_II;

import java.util.ArrayList;
import java.util.List;

/*
95. Unique Binary Search Trees II
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n.
Return the answer in any order.
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

    static List<TreeNode> generateTrees(int n) {
            return helper(1, n);
    }

    static List<TreeNode> helper(int start, int end) {
        List<TreeNode> list = new ArrayList<>();

        if (start > end) {
            list.add(null);
            return list;
        }

        if (start == end) {
            list.add(new TreeNode(start));
            return list;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
            }
        }
        return list;
    }

    static void print(TreeNode root){
        if (root == null) return;
        System.out.println(root.val);
        print(root.left);
        print(root.right);
    }

    public static void main(String[] args) {
        List<TreeNode> root = generateTrees(3);
        for (TreeNode r: root){
            print(r);
            System.out.println("====");
        }

    }
}
