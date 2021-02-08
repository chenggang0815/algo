package com.LeetCode._0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;
/*
105. Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    static int rootIndex(int[] preorder, int[] inorder){
        int root = preorder[0];
        for (int i = 0; i < inorder.length; i++){
            if (root == inorder[i]) return i;
        }

        return -1;
    }

    static TreeNode buildTree1(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);

        int rootIndex = rootIndex(preorder, inorder);

        root.left = buildTree1(Arrays.copyOfRange(preorder,1,rootIndex + 1),Arrays.copyOfRange(inorder,0,rootIndex));
        root.right = buildTree1(Arrays.copyOfRange(preorder,rootIndex + 1,preorder.length),Arrays.copyOfRange(inorder,rootIndex + 1,inorder.length));

        return root;
    }

    static TreeNode buildTree(int[] preOrder, int[] inorder){
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }

        return buildTree2(preOrder, inorder, map);
    }

    static TreeNode buildTree2(int[] preorder, int[] inorder, HashMap<Integer, Integer> map) {
        if(preorder.length == 0){
            System.out.println("hhh");
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);

        int rootIndex = map.get(preorder[0]);
        if (preorder[0] == 12) System.out.println(rootIndex);

        root.left = buildTree2(Arrays.copyOfRange(preorder,1,rootIndex + 1),Arrays.copyOfRange(inorder,0,rootIndex), map);
        root.right = buildTree2(Arrays.copyOfRange(preorder,rootIndex + 1,preorder.length),Arrays.copyOfRange(inorder,rootIndex + 1,inorder.length), map);

        return root;
    }



    /*
    遍历验证
     */
    static void preOrder(TreeNode root){
        if (root == null) return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    static void inOrder(TreeNode root){
        if (root == null) return;
        preOrder(root.left);
        System.out.println(root.val);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(12);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(15);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        int[] pre = new int[]{3,9,12,13,20,15,7};
        int[] in = new int[]{12,9,13,3,15,20,7};

        preOrder(root);
        System.out.println("=====");
        TreeNode node = buildTree(pre, in);
        preOrder(node);


    }
}
