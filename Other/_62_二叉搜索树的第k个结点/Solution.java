package Other._62_二叉搜索树的第k个结点;

import java.util.Stack;

/*
二叉搜索树的第k个结点

给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。

思路: 二叉搜索树的中序遍历是按照结点升序排列。所以可dfs遍历二叉树，直到第k个结点

1. 递归遍历  time: o(n) space: o(n)

2. 非递归遍历，利用stack   time: o(n) space: o(n)

 */
public class Solution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    //递归遍历
    int num = 0;
    TreeNode res;
    TreeNode KthNode(TreeNode pRoot, int k){
        if(pRoot == null) return pRoot;

        helper(pRoot, k);

        return res;
    }

    void helper(TreeNode root, int k){
        if (root == null) return;

        helper(root.left, k);
        num++;
        if (k == num){
            res = root;
            return;
        }
        helper(root.right, k);
    }

    //非递归遍历
    TreeNode KthNode2(TreeNode pRoot, int k) {
        int num = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = pRoot;
        while (node != null || !stack.isEmpty()){
            if (node != null){
                stack.push(node);
                node = node.left;
            }else{
                node = stack.pop();
                num++;
                if (k == num){
                    return node;
                }
                node = node.right;
            }
        }

        return null;
    }
    public static void main(String[] args) {

    }
}
