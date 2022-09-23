package LeetCode._0111_minimum_depth_of_binary_tree;
/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
*/
/*
Solution
1.

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
    /*
    时间复杂度：O(N)，其中 N 是树的节点数。对每个节点访问一次。
    空间复杂度：O(H)，其中 H 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)。
    平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(logN)。
     */
    static int minDepth(TreeNode root){
        if(root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left == null || root.right == null){
            return minDepth(root.right) + minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(0);
        root.left = node1;
        //node1.right = node2;
        //node2.left = node3;
        //node3.left = node4;
        System.out.println(minDepth(root));
    }
}
