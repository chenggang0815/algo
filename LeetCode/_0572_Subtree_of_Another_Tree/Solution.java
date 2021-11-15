package LeetCode._0572_Subtree_of_Another_Tree;
/*
572. Subtree of Another Tree
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
Example 1:
        3
       / \
      4   5          4
     / \            / \
    1   2          1  2
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Example 2:
        3
       / \
      4   5          4
     / \            / \
    1   2          1  2
       /
      0
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false
*/
/*
Solution
Approach 1 => similar with question 100, 101
*/
public class Solution {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }
    // version 1
    boolean f = false;
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null) return false;

        isSubtree(root.left, subRoot);
        if(dfs(root, subRoot)) f = true;

        isSubtree(root.right, subRoot);

        return f;
    }

    boolean dfs(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null) return true;
        if(root == null || subRoot == null) return false;
        if(root.val != subRoot.val) return false;

        return dfs(root.left, subRoot.left) && dfs(root.right, subRoot.right);
    }

   // version 2 don't need a variable f
   public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
       if(root == null) return false;

       return isSameTree(root, subRoot) || isSubtree2(root.right, subRoot) || isSubtree2(root.left, subRoot);
   }
    // similar with question 100
    boolean isSameTree(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null) return true;
        if(root == null || subRoot == null) return false;
        if(root.val != subRoot.val) return false;

        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }

    public static void main(String[] args) {

    }
}
