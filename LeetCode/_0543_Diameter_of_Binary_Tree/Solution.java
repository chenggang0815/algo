package LeetCode._0543_Diameter_of_Binary_Tree;
/*

二叉树的直径
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

    static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    static int maxPath = 0;
    static int Diameter(TreeNode root){
        if (root == null) return 0;

        depth(root);

        return maxPath;
    }

    static int depth(TreeNode root){
        if (root == null) return 0;

        int left = depth(root.left);
        int right = depth(root.right);
        maxPath = Math.max(maxPath, left + right); //对于每个结点都要计算经过它的左右最大路径和

        return Math.max(left, right) + 1;
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
        System.out.println(Diameter(root));
        //System.out.println(depth(root));




    }
}
