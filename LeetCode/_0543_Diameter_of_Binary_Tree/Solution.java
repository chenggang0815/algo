package LeetCode._0543_Diameter_of_Binary_Tree;
/*
543. Diameter of Binary Tree  Easy
Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

Example:
Given a binary tree

          1
         / \
        2   3
       / \
      4   5

Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class Solution {
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }
    // 2022-10-15
    public int diameterOfBinaryTree1(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);

        return res[0];
    }

    int dfs(TreeNode root, int[] res){
        if(root == null) return 0;

        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        res[0] = Math.max(left + right, res[0]);

        return 1 + Math.max(left, right);
    }

    // 2022-09-30
    public int diameterOfBinaryTree2(TreeNode root) {
        if(root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left + right, Math.max(diameterOfBinaryTree2(root.left), diameterOfBinaryTree2(root.right)));
    }

    int height(TreeNode node){
        if(node == null) return 0;

        return Math.max(height(node.left) + 1, height(node.right) + 1);
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node4.left = node5;
        //preOrder(root);
        //System.out.println(depth(root.right));
        //System.out.println(depth(root.left));
        //System.out.println(depth(root));




    }
}
