package Facebook._1644_Lowest_Common_Ancestor_of_a_Binary_Tree_II;
/*
1644. Lowest Common Ancestor of a Binary Tree II
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
If either node p or q does not exist in the tree, return null.
All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
Example 2:
                    3
                  /   \
                 5      1
               /   \   / \
              6    2  0   8
                  / \
                 7  4
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.

Example 3:
                    3
                  /   \
                 5      1
               /   \   / \
              6    2  0   8
                  / \
                 7  4
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.
Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
*/
/*
Solution
Approach 1: recursion  similar to question 236
1. because the p or q may not exists in the tree
2. the difference between question 236, we need to traverse the entire tree to check if the node exists in the tree

Approach 2: Euler Path
*/

import java.util.HashMap;

public class Solution {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int[] count = new int[1];
        TreeNode node = helper(root, p, q, count);

        return count[0] == 2 ? node : null;
    }

    TreeNode helper(TreeNode root, TreeNode p, TreeNode q, int[] count){
        if(root == null) return root;

        TreeNode left = helper(root.left, p, q, count);
        TreeNode right = helper(root.right, p, q, count);

        if(root == p || root == q) {
            count[0]++;
            return root;
        }

        if(left == null && right == null) return null;
        if(left != null && right != null) return root;

        return left == null ? right : left;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, Boolean> map = new HashMap<>();
        map.put(p, false);
        map.put(q, false);
        TreeNode node = helper2(root, p, q, map);

        if(map.get(p) && map.get(q)) return node;
        else return null;
    }

    TreeNode helper2(TreeNode root, TreeNode p, TreeNode q, HashMap<TreeNode, Boolean> map){
        if(root == null) return root;

        TreeNode left = helper2(root.left, p, q, map);
        TreeNode right = helper2(root.right, p, q, map);

        if(root == p || root == q) {
            map.put(root, true);
            return root;
        }

        if(left == null && right == null) return null;
        if(left != null && right != null) return root;

        return left == null ? right : left;
    }
    public static void main(String[] args) {

    }
}
