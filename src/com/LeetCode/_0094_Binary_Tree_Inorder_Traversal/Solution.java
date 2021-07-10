package com.LeetCode._0094_Binary_Tree_Inorder_Traversal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
94. Binary Tree Inorder Traversal
Given the root of a binary tree, return the inorder traversal of its nodes' values.

1. 递归
2. 非递归(迭代)
I use pushAllLeft() to push all the left children of one Node into the stack, so that my idea looks clear:
    2.1 We push all the left children of root into the stack until there's no more nodes.
    2.2 Then we pop from the stack which we'd call cur.
    2.3 Add cur to result list
    2.4 Recursively call pushAllLeft() on cur's right child.
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
        1
       / \
      2  3
      \
       5

    left -> root -> right
    2->5->1->3
     */
    static List<Integer> inorderTraversal(TreeNode root){
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
        System.out.println(inorderTraversal(root));
    }
}

