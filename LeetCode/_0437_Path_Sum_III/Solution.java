package LeetCode._0437_Path_Sum_III;
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
/*
Approach 1: time: O(N^2) space: O(H)

Approach 2: time: O(N) space: O(N)
1. Prefix Sum
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
    static public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;

        return pathSum(root.left, targetSum) + pathSum(root.right, targetSum) + dfs(root, targetSum);
    }

    static int dfs(TreeNode root, long targetSum){
        if(root == null) return 0;

        if(root.val == targetSum){
            return 1 + dfs(root.left, targetSum - root.val) + dfs(root.right, targetSum - root.val);
        }else{
            return dfs(root.left, targetSum - root.val) + dfs(root.right, targetSum - root.val);
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
