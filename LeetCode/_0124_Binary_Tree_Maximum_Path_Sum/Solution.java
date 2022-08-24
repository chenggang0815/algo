package LeetCode._0124_Binary_Tree_Maximum_Path_Sum;
/*
124. Binary Tree Maximum Path Sum
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any path.
*/

/*
Solution:
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
        dfs(root, result);

        return result[0];
    }

    int dfs(TreeNode root, int[] result){
        if (root == null) return 0;

        int left = dfs(root.left, result);
        int right = dfs(root.right, result);
        left = left < 0 ? 0:left;
        right = right < 0 ? 0:right;
        result[0] = Math.max(result[0], root.val + left + right);
        /*
        If the current node is at level 2 and you want to go one level up, say level 1,
        you can NOT keep both the left paths and right paths,
        you just gotta choose one path out of these two.
        See the example 2 of the problem, if the current node is 20,
        then you wanna go up to -10 and compute the new sum,
        you can't consider both 15 and 7 when computing path_sum, since -10->20->15->7 is illegal.
        */

        return root.val + Math.max(left, right); //  because a path can not have branch, so we must choose the max sub node + current node
    }
    public static void main(String[] args) {

    }
}
