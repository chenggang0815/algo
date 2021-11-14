package LeetCode._0109_Convert_Sorted_List_to_Binary_Search_Tree;
/*
109. Convert Sorted List to Binary Search Tree
Given the head of a singly linked list where elements are sorted in ascending order,
convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:
            0
           / \
         -3   9
        /  \  /
      -10    5
Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
*/
/*
Solution:
Approach 1 => recursion time:O(NlogN) space:O(logN)

Approach 2 => recursion + convert to array => time:O(N) space:O(N)
1. each time choose the middle node be the root node, and recursive convert the left side and right side
1 2 3 4  5   6 7 8 9
       5
      / \
     2   7
    / \ / \
   1  3 6 8
   /     /
   4     9

Approach 3 => Inorder Simulation time:O(N) space:O(logN)


*/

import java.util.ArrayList;
import java.util.List;


public class Solution {
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> nodes = new ArrayList<>();
        while(head != null){
            nodes.add(head.val);
            head = head.next;
        }

        return helper(nodes, 0, nodes.size() - 1);
    }

    TreeNode helper(List<Integer> nodes, int left, int right){
        if(left > right) return null;

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nodes.get(mid));
        if(left == right) return node;

        node.left = helper(nodes, left, mid - 1);
        node.right = helper(nodes, mid + 1, right);

        return node;
    }
    public static void main(String[] args) {

    }
}
