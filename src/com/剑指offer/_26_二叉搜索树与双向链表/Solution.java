package com.剑指offer._26_二叉搜索树与双向链表;
/*
二叉搜索树与双向链表

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

思路：根据二叉搜索树和排序的链表，从中序遍历开始对当前节点 cur有：
    pre.right = cur
    cur.left = pre
    pre = cur
    第一次遍历需要初始化head为当前节点（根据pre==null判断） => head = cur
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

    //time:o(n) space:o(n)
    TreeNode head, pre;
    public TreeNode Convert(TreeNode root) {
        if (root == null) return root;

        helper(root);
        //head.left = pre;
        //pre.right = head;
        return head;
    }

    public void helper(TreeNode cur){
        if (cur == null) return;

        helper(cur.left);
        if (pre == null){
            head = cur;
        }else {
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;
        helper(cur.right);
    }

    public static void main(String[] args) {

    }
}
