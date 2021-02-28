package com.LeetCode._0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.List;

/*
235. Lowest Common Ancestor of a Binary Search Tree
二叉搜索树的最近公共祖先

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants
(where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */

/*
思路1： 两次遍历
1. 根据二叉搜索树的性质，可以快速找出从根节点到该节点的路径
2. 分别得到了从根节点到p和q的路径之后，就可以很方便地找到它们的最近公共祖先了
3. 最近公共祖先 => 从根节点到它们路径上的「分岔点」，也就是最后一个相同的节点

思路2： 一次遍历
从根节点开始遍历；
    2.1 如果当前节点的值大于p和q的值，说明 p 和 q 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
    2.2 如果当前节点的值小于p和q的值，说明 p 和 q 应该在当前节点的右子树，因此将当前节点移动到它的右子节点；
    2.3 如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」
    => 此时，p和q要么在当前节点的不同的子树中，要么其中一个就是当前节点

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
    static List<TreeNode> getPath(TreeNode root, TreeNode target, List<TreeNode> arr){
        if (root == target){
            arr.add(root);
            return arr;
        }
        arr.add(root);
        if (root.val < target.val){
            return getPath(root.right, target, arr);
        }else{
            return getPath(root.left, target, arr);
        }
    }
    static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = getPath(root, p, new ArrayList<>());
        List<TreeNode> qPath = getPath(root, q, new ArrayList<>());

        for (int i = 0; i < pPath.size() && i < qPath.size(); i++){
            if (qPath.get(i) != pPath.get(i)){
                return qPath.get(i - 1); // 找出最后一个不相等结点，返回其上一个结点 => 这样写有错误，因为可能存在祖先结点就是其本身的情况
            }                            // eg: [6,2] [6,2,4] => 其公共祖先结点为2
        }
        return null;
    }

    static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = getPath(root, p, new ArrayList<>());
        List<TreeNode> qPath = getPath(root, q, new ArrayList<>());
        TreeNode res = null;
        for (int i = 0; i < pPath.size() && i < qPath.size(); i++){
            if (qPath.get(i) == pPath.get(i)){
                res = qPath.get(i); // 返回路径上最后一个相等的结点
            }                            // eg: [6,2] [6,2,4] => 其公共祖先结点为2
        }
        return res;
    }

    // 一次遍历
    static TreeNode getPath2(TreeNode root, TreeNode p, TreeNode q){
        if (root.val < p.val && root.val < q.val){
            return getPath2(root.right, p, q);
        }else if (root.val > p.val && root.val > q.val){
            return getPath2(root.left, p, q);
        }else return root;
    }

    static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = getPath2(root, p, q);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(9);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        System.out.println(lowestCommonAncestor3(root, node4, node2).val);
    }
}
