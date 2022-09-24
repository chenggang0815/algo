package LeetCode._0113_Path_Sum_II;

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

    static public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(root, targetSum, res, new ArrayList<>());

        return res;
    }

    static void dfs(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> path){
        if(root == null) return;

        path.add(root.val);
        if(root.left == null && root.right == null && root.val == targetSum){
            res.add(new ArrayList<>(path));
            // return;  // we can't just return at this position.
            // We remove the node in the path after all the children are processed, so that when we switch to another branch, the node will not be double count.
        }

        dfs(root.left, targetSum - root.val, res, path);
        dfs(root.right, targetSum - root.val, res, path);
        path.remove(path.size() - 1);
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
