package LeetCode._0222_Count_Complete_Tree_Nodes;
/*
222. Count Complete Tree Nodes

Given the root of a complete binary tree, return the number of the nodes in the tree.
According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

     1
   /  \
  2    3
 / \   /
4   5  6

完全二叉树只有两种情况，情况一：就是满二叉树，情况二：最后一层叶子节点没有满。
对于情况一，可以直接用 2^树深度 - 1 来计算，注意这里根节点深度为1。
对于情况二，分别递归左孩子，和右孩子，递归到某一深度一定会有左孩子或者右孩子为满二叉树，然后依然可以按照情况1来计算

time: O(logn * logn)
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

    static int countNodes(TreeNode root){
        if (root == null) return 0;

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);

        if (leftDepth == rightDepth){ // left tree is a full binary tree, and countNodes for right tree
            return (1 << leftDepth) + countNodes(root.right);  // 1 << leftDepth - 1 + 1(root)
        }else{ // right tree is a full binary tree
            return (1 << rightDepth) + countNodes(root.left);
        }

    }

    static int getDepth(TreeNode root){
        if (root == null) return 0;

        int res = 0;
        while (root != null){
            res++;
            root = root.left;
        }

        return res;
    }

    static int countNodes2(TreeNode root){
        if (root == null) return 0;

        int leftDepth = getLeftDepth(root);
        int rightDepth = getRightDepth(root);

        if (leftDepth == rightDepth){
            return (1 << leftDepth) - 1 ;
        }else{
            return 1 + countNodes2(root.left) + countNodes2(root.right);
        }

    }

    static int getLeftDepth(TreeNode root){
        if (root == null) return 0;

        int res = 0;
        while (root != null){
            res++;
            root = root.left;
        }

        return res;
    }

    static int getRightDepth(TreeNode root){
        if (root == null) return 0;

        int res = 0;
        while (root != null){
            res++;
            root = root.right;
        }

        return res;
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
        node2.left = node5;
        System.out.println(countNodes(root));
    }
}
