package LeetCode._0173_Binary_Search_Tree_Iterator;

// Approach 1: use an extra arraylist to store all nodes, and iterate the list
// time: O(N) space: O(N)

// Approach 2: similar to question 94. Binary Tree Inorder Traversal
// time: O(N)
// space: O(h) h is the height of tree

import java.util.Stack;

public class BSTIterator {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    Stack<TreeNode> stack;
    TreeNode node;
    public BSTIterator(TreeNode root){
        stack = new Stack<>();
        node = root;
    }

    public int next(){
        while (node != null){
            stack.push(node);
            node = node.left;
        }

        node = stack.pop();
        int res = node.val;
        node = node.right;

        return res;
    }

    public boolean hasNext(){
        return !stack.isEmpty() || node != null;
    }
}
