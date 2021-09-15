package LeetCode._0098_Validate_Binary_Search_Tree;

import java.util.Stack;

/*
98. Validate Binary Search Tree

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:
    1. The left subtree of a node contains only nodes with keys less than the node's key.
    2. The right subtree of a node contains only nodes with keys greater than the node's key.
    3. Both the left and right subtrees must also be binary search trees.

solution 1:
递归
将当前节点的值分别作为左子树的上界和右子树的下界，分别再对左右子树遍历判断
time:o(n) space:o(n)

solution 2:
中序遍历，判断是否依次递增
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

    // 递归
    //将当前节点的值分别作为左子树的上界和右子树的下界，分别再对左右子树遍历判断
    // time:o(n) space:o(n)
    static boolean helper(TreeNode root, Long lower, Long upper){
        if (root == null) return true;
        if (root.val >= upper || root.val <= lower){
            return false;
        }

        return helper(root.left, lower, (long) root.val) && helper(root.right, (long) root.val, upper);
    }
    static boolean isValidBST1(TreeNode root){
        if (root == null) return true;

        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 中序遍历，判断是否依次递增
    static boolean isValidBST2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        double pre = -Double.MAX_VALUE;
        while (!stack.isEmpty() || cur != null){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
//            if(cur.val > pre){
//                pre = cur.val;
//            }else{
//                return false;
//            }
            if (cur.val <= pre) return false;
            pre = cur.val;
            cur = cur.right;
        }

        return true;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(9);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        System.out.println(isValidBST2(root));
    }
}
