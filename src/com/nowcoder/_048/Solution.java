package com.nowcoder._048;

import java.util.LinkedList;
import java.util.Queue;

/*
输入一棵二叉树，判断该二叉树是否是平衡二叉树。

在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
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

    static boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;

        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right) && (Math.abs(helper(root.left) - helper(root.right)) < 2);
    }

    static int helper(TreeNode root){
        if (root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int count = queue.size();
            while (count > 0){
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
                count--;
            }
            depth++;
        }

        return depth;
    }
    public static void main(String[] args) {

    }
}
