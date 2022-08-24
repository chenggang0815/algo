package LeetCode._0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;
/*
105. Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/
/*
Solution:
time:O(n) space:O(n)
similar to question 105
*/
import java.util.HashMap;

public class Solution {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }
    //  preorder => root-left-right
    //  inorder =>  left-root-right
    //  postorder => left-right-root
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }

        return build(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1, map);
    }

    public TreeNode build(int[] inorder, int inorderStart, int inorderEnd, int[] preorder, int preStart, int preEnd, HashMap<Integer, Integer> map){
        if (inorderStart > inorderEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = map.get(root.val);
        // pre_left_start = preStart + 1
        // pre_left_end =  pre_left_start + (rootIndex - inorderStart + 1 - 2)
        //              = preStart + 1 + (rootIndex - inorderStart - 1) = preStart + rootIndex - inorderStart
        // for example:
        // preorder: [3, 9,1,2, 20,15,7]
        // inorder: [1,9,2, 3, 15,20,7]
        // rootIndex = 3, for preorder, preEnd of index
        // pre_right_start = pre_left_end + 1 = preStart + rootIndex - inorderStart + 1
        // pre_right_end = preEnd
                                                                                    //pre left start = preStart + 1
        root.left = build(inorder, inorderStart, rootIndex - 1, preorder, preStart + 1, preStart + rootIndex - inorderStart, map);
        root.right = build(inorder, rootIndex + 1, inorderEnd, preorder, preStart + rootIndex - inorderStart + 1, preEnd, map);

        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(12);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(15);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        int[] pre = new int[]{3,9,12,13,20,15,7};
        int[] in = new int[]{12,9,13,3,15,20,7};



    }
}
