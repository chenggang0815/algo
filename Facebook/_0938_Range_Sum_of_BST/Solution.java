package Facebook._0938_Range_Sum_of_BST;
/*
938. Range Sum of BST

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

Example 1:
            10
           /  \
          5    15
        /  \     \
       3    7    18
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
*/

/*
Solution
Approach 1: recursion-dfs
1. if root.val < low => only try right, because all the left node < root
2. if root.val > high => only try left
3. else try both left and right

Approach 2: iterative
*/
public class Solution {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        int[] res = new int[1];
        dfs(root, low, high, res);

        return res[0];
    }

    void dfs(TreeNode root, int low, int high, int[] res){
        if(root == null) return;

        if(root.val <= high && root.val >= low) res[0] += root.val;

        if(root.val > low){
            dfs(root.right, low, high, res);
        }else if(root.val > high){
            dfs(root.left, low, high, res);
        }else{
            dfs(root.right, low, high, res);
            dfs(root.left, low, high, res);
        }
    }

    public static void main(String[] args) {

    }
}
