package com.LeetCode._0437_路径总和_3;
/*
437. Path Sum III Easy

You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:
1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

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
//因为起始结点不一定是由根结点开始，所以需要对每个结点为起始结点，遍历其左右结点，并且计算其每个结点的累计和
    static public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;

        return pathSum(root.left,sum) + pathSum(root.right,sum) + helper(root,sum);
    }

    static public int helper(TreeNode root, int sum){
        if (root == null) return 0;
        if (root.val == sum){
            // 因为结点的值可能是负数，所以需要遍历到叶子结点来判断所有满足的情况
            return 1 + helper(root.right,sum - root.val) + helper(root.left,sum - root.val);
        }else {
            return helper(root.right, sum - root.val) + helper(root.left, sum - root.val);
        }
    }

    public static void main(String[] args) {
 /*
       10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1
  */
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(-3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(-2);
        TreeNode node8 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.right = node8;

        System.out.println(pathSum(root,8));
    }
}
