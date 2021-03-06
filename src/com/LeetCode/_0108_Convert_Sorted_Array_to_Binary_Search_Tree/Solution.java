package com.LeetCode._0108_Convert_Sorted_Array_to_Binary_Search_Tree;
/*
108. Convert Sorted Array to Binary Search Tree

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
/*
分析：BST的中序遍历是升序的，因此本题等同于根据中序遍历的序列恢复二叉搜索树。
因此我们可以以升序序列中的任一个元素作为根节点，以该元素左边的升序序列构建左子树，以该元素右边的升序序列构建右子树，这样得到的树就是一棵二叉搜索树啦～ 又因为本题要求高度平衡，因此我们需要选择升序序列的中间元素作为根节点奥～

109. 有序链表转换二叉搜索树 将本题的数组换成了链表，做法完全一样，不过链表无法像数组一样直接索引到中间元素，链表找中间节点可以用快慢指针法，详见 876. 链表的中间结点。

作者：sweetiee
链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jian-dan-di-gui-bi-xu-miao-dong-by-sweetiee/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

time: o(n) space: o(log(n))

思路：
1. 数组是按照升序排序的有序数组 => 二叉搜索树的中序遍历是升序序列 => 给定中序遍历序列，还原平衡二叉树搜索树
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
    static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    static TreeNode helper(int[] nums, int left, int right){
        if (left > right) return null;

        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);

        return root;
    }

    static void midorder(TreeNode root){
        if (root == null) return;
        midorder(root.left);
        System.out.println(root.val);
        midorder(root.right);
    }

        public static void main(String[] args) {
        TreeNode root = sortedArrayToBST(new int[]{-10,-3,0,5,9});
        midorder(root);
    }
}
