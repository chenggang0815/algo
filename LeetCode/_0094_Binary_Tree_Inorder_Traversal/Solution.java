package LeetCode._0094_Binary_Tree_Inorder_Traversal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
94. Binary Tree Inorder Traversal
Given the root of a binary tree, return the inorder traversal of its nodes' values.
*/
/*
Solution
Approach 1 => dfs

Approach 2 => iterative using stack
1. start from root node, push all the left children into the stack until reach left node
2. then we pop from the stack which we'd call current node
3. add current node to result list
4. start from the right child of the current node, push it into stack
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
    1. 从根结点开始，先往栈里面压入左结点，直到当前节点为空
    2. 再把叶子结点出栈，作为当前节点，（此时可以打印当前节点）
    3. 把当前节点的右节点作为根结点再次遍历
        1
       / \
      2  3
      \
       5

    left -> root -> right
    2->5->1->3
     */
    // Approach
    static List<Integer> inorderTraversal(TreeNode root){
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

