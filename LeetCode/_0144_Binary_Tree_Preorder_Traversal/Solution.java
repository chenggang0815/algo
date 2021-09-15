package LeetCode._0144_Binary_Tree_Preorder_Traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/
public class Solution {
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

/*
解法1
*/
    static List<Integer> preorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    static void dfs(TreeNode root, List<Integer> res){
        if (root != null){
            res.add(root.val);
            dfs(root.left, res);
            dfs(root.right, res);
        }
    }

/*
    1
   / \
  2  4
 / \
5  7

1->2->5->7->4

解法2
 */
    static List<Integer> preorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.right = node1;
        node1.left = node2;

        System.out.println(preorderTraversal2(root));

    }
}
