package LeetCode._0236_Lowest_Common_Ancestor_of_a_Binary_Tree;
/*
236. Lowest Common Ancestor of a Binary Tree
236. 二叉树的最近公共祖先

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
         3
        / \
       5   1
      / \ / \
     6  2 0 6
       / \
      7   4
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
*/
/*
由于需要先知道左右子树的情况，然后决定向上返回什么 => 因此「后序遍历」的思想是很关键

从根节点遍历，递归向左右子树查询节点信息
1. 递归终止条件：如果当前节点为空或等于p或q，则返回当前节点
2. 递推工作：
    2.1 递归左子节点，返回值记为left
    2.2 递归右子节点，返回值记为right
根据 left 和 right ，为四种情况:
1. 当left和right同时为空 => 说明 root 的左 / 右子树中都不包含 p,q ，返回 null；
2. 当left和right同时不为空 => 说明 p,q 分列在 root 的 异侧 （分别在 左 / 右子树），因此root为最近公共祖先，返回root
3. 当left为空，right不为空 => p,q 都不在root的左子树中 => 直接返回right
    具体可分为两种情况：
    3.1 p,q其中一个在root的右子树中，另一个节点为最近公共祖先结点，此时right指向p（假设为p）；
    3.2 p,q两节点都在root的右子树中，此时的right指向最近公共祖先节点 ；
4. 当left不为空，right为空 => 与情况3.同理

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

    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null && right == null) return null; // 1.
        if(left == null) return right; // 3.
        if(right == null) return left; // 4.

        return root; // 2. if(left != null and right != null)
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
        System.out.println(lowestCommonAncestor(root, node4, node2).val);
    }
}
