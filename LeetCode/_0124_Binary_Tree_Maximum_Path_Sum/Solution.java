package LeetCode._0124_Binary_Tree_Maximum_Path_Sum;
/*
124. Binary Tree Maximum Path Sum
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any path.

      -10  max = -10 + 9 + 42 = 41
      /  \
     9    20 20 + max_left + max_right = 20 + 15 + 7 = 42
          / \
 max=15  15  7 max = 7 + max_left + max_right = 7 + 0(-2 < 0 => 0) + 0(-4 < 0 => 0)
        /\    / \
       0 0 -2  -4 max => -4 + 0 = -4
              / \
             0   0
max sum path = 15 + 20 + 7 => 42

for the last recursion, we consider node -4
for node -4 it's left node and right node are null, and we can make the value equal 0
 */
public class Solution {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }

    int maxPathSum(TreeNode root){
        int[] result = new int[]{Integer.MIN_VALUE};
        helper(root, result);

        return result[0];
    }

    int helper(TreeNode root, int[] result){
        if (root == null) return 0;

        int left = helper(root.left, result);
        int right = helper(root.right, result);

        left = left < 0 ? 0:left;
        right = right < 0 ? 0:right;
        result[0] = Math.max(result[0], root.val + left + right);

        return root.val + Math.max(left, right);
    }
    public static void main(String[] args) {

    }
}
