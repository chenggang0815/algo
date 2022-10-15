package LeetCode._1008_Construct_Binary_Search_Tree_from_Preorder_Traversal;

import java.util.Arrays;
import java.util.HashMap;

/*
1008. Construct Binary Search Tree from Preorder Traversal
Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.

Example 1:
Input: preorder = [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
*/
/*
Approach 1: time: O(nlog(n)) space: O(n)
1. similar with question 105, we sort the preorder array, generate a new array which is inorder array

Approach 2: time: O(n) space:O(n)
1. similar with question 98
*/
public class Solution {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }
    // approach 1
    // inorder => [1,5,7,8,10,12]
    // preorder => [8,5,1,7,10,12]
    int preStart = 0;
    public TreeNode bstFromPreorder1(int[] preorder) {
        //int[] inorder = new int[preorder.length];
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);
        // inorder <int, index>
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) map.put(inorder[i], i);

        return helper1(map, preorder, 0, 0, inorder.length - 1);
    }

    TreeNode helper1(HashMap<Integer, Integer> map, int[] preorder, int preStart, int start, int end){
        if(start > end) return null;

        int rootIndex = map.get(preorder[preStart]);
        TreeNode root = new TreeNode(preorder[preStart]);

        root.left = helper1(map, preorder, preStart + 1, start, rootIndex - 1);
        root.right = helper1(map, preorder, preStart + 1 + rootIndex - start, rootIndex + 1, end);

        return root;
    }


    // approach 2
    int index;
    public TreeNode bstFromPreorder2(int[] preorder) {
        index = 0;

        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    TreeNode helper(int[] preorder, int min, int max){
        if(index == preorder.length || preorder[index] < min || preorder[index] > max) return null;

        TreeNode root = new TreeNode(preorder[index++]);
        root.left = helper(preorder, min, root.val);
        root.right = helper(preorder, root.val, max);

        return root;
    }
    public static void main(String[] args) {

    }
}
