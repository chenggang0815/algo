package com.leetcode._0007;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
104. Maximum Depth of Binary Tree

Given a binary tree, find its maximum depth.
Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its depth = 3.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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

    static int maxDepth(TreeNode root){
        if (root==null)return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    //广度优先遍历
    static int maxDepth2(TreeNode root){
        if (root==null) return 0;
        int depth=0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            int temp;
            temp = q.size();
            while (temp>0){
                temp--;
                TreeNode tempNode = q.poll();
                if (tempNode.right!=null){
                    q.add(tempNode.right);
                }
                if (tempNode.left!=null){
                    q.add(tempNode.left);
                }
            }
            depth++;
        }
    return depth;
    }

    public static void main(String[] args) {
        //Queue<Integer> q = new LinkedList<>();
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node2.right = node4;
        node2.left = node3;

        System.out.println(maxDepth2(root));










    }

}
