package com.leetcode._0102_二叉树的层序遍历;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
102. Binary Tree Level Order Traversal
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

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

    //广度优先搜索（Breath First Search-bfs），一般用一个队列来辅助
    static public List<List<Integer>> levelOrder(TreeNode root) {
           List<List<Integer>> res = new ArrayList<>();
           Queue<TreeNode> queue = new LinkedList<>();
           if (root==null) return res;
           queue.add(root);
           while (!queue.isEmpty()){
               int count = queue.size();
               ArrayList<Integer> temp = new ArrayList<>();
               while (count>0){
                   TreeNode node = queue.peek();
                   temp.add(node.val);
                   queue.poll();
                   if (node.left!=null) queue.add(node.left);
                   if (node.right!=null) queue.add(node.right);
                   count--;
               }

               res.add(temp);
           }
           return res;

    }

    //递归
    static List<List<Integer>> leverorder2(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root==null) return res;

        helper(root, 0, res);

        return res;
    }

    static void helper(TreeNode root, int level, List<List<Integer>> res){

        if (root==null) return;
        if (res.size()==level){
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);

        helper(root.left,level+1,res);
        helper(root.right,level+1,res);

    }

    public static void main(String[] args) {
   /*
    3
   / \
  9  20
    /  \
   15   7
    */
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);


        root.left = node1;
        root.right = node2;;
        node2.right = node3;
        node3.left = node4;

        System.out.println(leverorder2(root));


    }
}
