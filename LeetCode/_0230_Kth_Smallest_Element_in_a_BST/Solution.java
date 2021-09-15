package LeetCode._0230_Kth_Smallest_Element_in_a_BST;

import java.util.ArrayList;
import java.util.Stack;

/*
230. Kth Smallest Element in a BST

Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1
 */

/*
参考 leetcode 94题
思路1：中序遍历-递归 time:o(n) space:o(n)

思路2 中序遍历-迭代 time:o(树的高度+k) space:o(树的高度+k)
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

    static void dfs(TreeNode root, ArrayList<Integer> res){
        if (root == null) return;

        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }
    // Runtime: 1 ms, faster than 37.10% of Java online submissions for Kth Smallest Element in a BST.
    static int kthSmallest1(TreeNode root, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        dfs(root, res);

        return res.get(k - 1);

    }

    static int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            k--;
            if (k == 0) return node.val;
            cur = node.right;
        }

        return -1;
    }
/*
          7
         / \
        3   8
       / \
      2   5
         / \
        4   6
 */

    static int findKSmallElement(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();
        while (root != null){
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            k--;
            if (k == 0) return node.val;
            TreeNode right = node.right;
            while (right != null){
                stack.push(right);
                right = right.left;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        System.out.println(findKSmallElement(root, 3));
    }

}
