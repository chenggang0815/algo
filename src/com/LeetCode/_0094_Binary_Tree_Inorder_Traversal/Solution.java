package com.LeetCode._0094_Binary_Tree_Inorder_Traversal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
二叉树的中序遍历
1. 递归
2. 非递归(迭代)
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
    /*
    从根结点开始，先往栈里面压入左结点，直到叶子结点。
    再把叶子结点出栈，把叶子结点的右节点作为根结点再次遍历
     */
    static List<Integer> midOrder(TreeNode root){
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        //4 -> 2 -> 5 -> 1 -> 3
        System.out.println(midOrder(root));
    }
}

