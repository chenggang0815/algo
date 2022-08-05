package LeetCode._0230_Kth_Smallest_Element_in_a_BST;
import java.util.ArrayList;
import java.util.Stack;
/*
230. Kth Smallest Element in a BST

Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.

Example 1:
        3
       / \
      1  4
      \
       2
Input: root = [3,1,4,null,2], k = 1
Output: 1
*/

/*
similar to question 94
Solution
Approach 1：dfs time:o(n) space:o(n)

Approach 2：iterative time:o(h+k) space:o(h+k) h denote the height of the tree, which means reach the leaf of tree, and then iterate k nodes again
1. This way one could speed up the solution because there is no need to build the entire inorder traversal, and one could stop after the kth element

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

    static int kthSmallest1(TreeNode root, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        dfs(root, res);

        return res.get(k - 1);
    }

    static void dfs(TreeNode root, ArrayList<Integer> res){
        if (root == null) return;

        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
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
        System.out.println(kthSmallest2(root, 3));
    }

}
