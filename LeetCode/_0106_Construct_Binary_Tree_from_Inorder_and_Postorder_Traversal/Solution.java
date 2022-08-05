package LeetCode._0106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal;

import java.util.Arrays;
import java.util.HashMap;

/*
106. Construct Binary Tree from Inorder and Postorder Traversal
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
construct and return the binary tree.

Example 1:
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
*/
/*
Solution
Approach 1 time:O(n^2) space:O(n^2)
1. we can use inorder and preorder/postorder traversal to construct a binary tree
2. for inorder =>    [left, root, right]
   for postorder =>  [left, right, root]
3. we always know the last element in postorder is the root node
   3.1 if we know the value of the root node, we can get the index of root node in the inorder
   3.2 if we know the index of root node in inorder, we can get the left and right part in the inorder
                                                     we also can get the left and right part in the postorder
4. for inorder, the left part => [0, rootIndex-1]
                    right part => [rootIndex+1, inorder.length]
5. for postorder, the left part => [0, rootIndex-1]
                    right part => [rootIndex, inorder.length - 1]

Approach 2 time:O(n) space:O(n)
    for example:
          postorder = [9,  15, 7,  20,  3]
                     right
                          left
            inorder = [9,  3,  15, 20,  7]
                          root
                  inorderStart
1. use a hashMap to get rootIndex
2. construct binary tree on the original inorder and postorder array
3. for inorder => left part => [inorderStart, rootIndex - 1]
                  right part => [rootIndex + 1, inorderEnd]
4. for postorder => left part => [postStart, postStart + root - inorderStart + 1 - 2]
                            left end = left start + (rootIndex - inorderStart + 1 - 2)
                    right part => right start = left end + 1 => postStart + root - inorderStart
                              => [postStart + root - inorderStart, postEnd - 1]
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

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (postorder.length == 0) return null;

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++){
            if (inorder[i] == root.val){
                rootIndex = i;
                break;
            }
        }
        int[] inorderLeft = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] inorderRight = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        int[] postorderLeft = Arrays.copyOfRange(postorder, 0, rootIndex);
        int[] postorderRight = Arrays.copyOfRange(postorder, rootIndex, postorder.length - 1);

        root.left = buildTree1(inorderLeft, postorderLeft);
        root.right = buildTree1(inorderRight, postorderRight);

        return root;
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }

        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    public TreeNode build(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer, Integer> map){
        if (inorderStart > inorderEnd) return null;

        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIndex = map.get(root.val);
        root.left = build(inorder, inorderStart, rootIndex - 1, postorder, postStart, postStart + rootIndex - inorderStart - 1, map);
        root.right = build(inorder, rootIndex + 1, inorderEnd, postorder, postStart + rootIndex - inorderStart, postEnd - 1, map);

        return root;
    }



    public static void main(String[] args) {

    }
}
