package LeetCode._0226_Invert_Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

/*
226	Invert Binary Tree
 */

/**
 * Approach 1: pre-order and post-order
 * 1. we can recursion to traverse the tree, and at the same time, for each node, we can swap it's left node and right node
 * 2. but we can use in-order traversal to do this, think about it, why?
 *
 * Approach 2: bfs
 */
public class Solution {
    static class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode(int val){
            this.val = val;
        }
    }
    // dfs 1
    static TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;

        TreeNode right = invertTree1(root.right);
        TreeNode left = invertTree1(root.left);
        root.left = right;
        root.right = left;

        return root;
    }

    // dfs2
    static TreeNode invertTree2(TreeNode root){
        if (root == null) return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree2(root.left);
        invertTree2(root.right);

        return root;
    }

    //dfs3
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        dfs3(root);

        return root;
    }

    void dfs3(TreeNode root){
        if(root.left == null && root.right == null) return;
        if(root.left == null){
            root.left = root.right;
            root.right = null;
            dfs3(root.left);
            return;
        }
        if(root.right == null){
            root.right = root.left;
            root.left = null;
            dfs3(root.right);
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        dfs3(root.left);
        dfs3(root.right);
    }

    // dfs 4
    public TreeNode invertTree4(TreeNode root) {
        if(root == null) return root;

        dfs4(root);

        return root;
    }

    void dfs4(TreeNode root){
        if(root == null) return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        dfs4(root.left);
        dfs4(root.right);
    }

    // bfs
    static TreeNode invertTree5(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left!=null) queue.add(current.left);
            if (current.right!=null)queue.add(current.right);
        }

        return root;
    }

    static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<TreeNode> queue = new ArrayList<>();
        if(root==null) return list;
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode temp = queue.remove(0);
            if(temp.left!=null) queue.add(temp.left);
            if(temp.right!=null) queue.add(temp.right);
            list.add(temp.val);
        }
        return list;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(2);

        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node1.left = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node3.right = node8;
        //System.out.println(PrintFromTopToBottom(root));
        //System.out.println(PrintFromTopToBottom(invertTree3(root)));
        invertTree2(root);

        node8.left = root;
        System.out.println(node8.left);


    }
}
