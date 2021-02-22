package com.LeetCode._0199_Binary_Tree_Right_Side_View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
199. Binary Tree Right Side View

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
 */

/*
思路1： bfs 加入每层最后一个元素

思路2： dfs root -> right -> left 顺序，每层都是最右边
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

    static List<Integer> rightSideView1(TreeNode root) {
        if (root == null) return new ArrayList<>();

        ArrayList<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int count = queue.size();
            while (count > 0){
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
                if (count == 1) res.add(temp.val);
                count--;
            }
        }

        return res;
    }

    static void dfs(TreeNode root, ArrayList<Integer> res){
        if (root == null) return;

    }

    static List<Integer> rightSideView2(TreeNode root) {
        if (root == null) return new ArrayList<>();


        return ;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;

        System.out.println(rightSideView1(root).toString());
    }
}
