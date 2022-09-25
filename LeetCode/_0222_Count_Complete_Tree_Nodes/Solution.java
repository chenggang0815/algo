package LeetCode._0222_Count_Complete_Tree_Nodes;
/*
222. Count Complete Tree Nodes

Given the root of a complete binary tree, return the number of the nodes in the tree.
According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

     1
   /  \
  2    3
 / \   /
4   5  6

for a complete tree, it can be a full binary tree or not a full binary tree
1. for full binary tree, it's number of nodes => 2^(depth) - 1
2. if it is not a full binary tree, recursively iterate left and right subtree, it should be full binary tree at some point.

the question is how to check it meet the situation 1 or not? => we can get the max depth of left side and right side, if maxLeftDepth != maxRightDepth => not a full binary tree

time: O(log(N))
time: It's T(n)=T(n/2)+O(log(n)).
Only one of the subtree would have difference height in left and right so only one of the subtree would continue to divide. It's O(logn * logn).
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

    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        int left = leftDepth(root);
        int right = rightDepth(root);
        if(left == right){
            // 0010 => 1000 left shift
            return (1 << left) - 1;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    int leftDepth(TreeNode root){
        if(root == null) return 0;

        return leftDepth(root.left) + 1;
    }

    int rightDepth(TreeNode root){
        if(root == null) return 0;

        return rightDepth(root.right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        //System.out.println(countNodes(root));
    }
}
