package com.LeetCode._0104_二叉树的最大深度;


import java.util.LinkedList;
import java.util.Queue;

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
    /*
    1. 如果一棵树只有一个结点，那么它的深度为1；
    2. 如果根结点只有左子树没有右子树，那么树的深度是左子树的深度加1，加1是加上根节点这一层
    3. 如果既有左子树又有右子树，那么树的深度应该是左、右子树中深度较大的值再加1
     */

    // time: o(n) space: o(n)
    static int maxDepth(TreeNode root){
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //宽度优先搜索(bfs)
    //time: o(n) space: o(n)
    static int maxDepth2(TreeNode root){
        if (root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int count = queue.size();
            while (count>0){
                TreeNode tempNode = queue.poll();
                if (tempNode.right != null){
                    queue.add(tempNode.right);
                }
                if (tempNode.left != null){
                    queue.add(tempNode.left);
                }
                count--;
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

        System.out.println(maxDepth(root));










    }

}
