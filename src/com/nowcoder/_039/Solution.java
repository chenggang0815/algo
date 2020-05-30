package com.nowcoder._039;

import java.util.ArrayList;

/*
二叉树中和为某一值的路径

输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
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
    static public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> array = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        helper(root, target, array, temp);

        return array;
    }

    static void helper(TreeNode root, int target, ArrayList<ArrayList<Integer>> array, ArrayList<Integer> temp){
        if (root == null) return;

        temp.add(root.val);
        if (root.left == null && root.right == null && root.val == target){
            array.add(new ArrayList<>(temp));
        }
        helper(root.left, target - root.val, array, temp);
        helper(root.right, target - root.val, array, temp);

        temp.remove(temp.size() - 1);
    }

    public static void main(String[] args) {

    }
}
