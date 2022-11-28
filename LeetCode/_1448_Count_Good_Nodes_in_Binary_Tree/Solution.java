package LeetCode._1448_Count_Good_Nodes_in_Binary_Tree;
/*
Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

Example 1:
            3
           / \
          1   4
         /   / \
        3   1   5
Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
* */
public class Solution {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }
    public int goodNodes(TreeNode root) {
        if(root == null) return 0;

        return dfs(root, root.val);
    }

     int dfs(TreeNode root, int val){
         if(root == null) return 0;

         if(root.val >= val){
             return dfs(root.left, root.val) + dfs(root.right, root.val) + 1;
         }else{
             return dfs(root.left, val) + dfs(root.right, val);
         }
     }
    public static void main(String[] args) {

    }
}
