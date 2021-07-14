package com.LeetCode._0024_Swap_Nodes_in_Pairs;

/*
24. Swap Nodes in Pairs
Given a linked list, swap every two adjacent nodes and return its head.
You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

Input: head = [1,2,3,4]
Output: [2,1,4,3]

2134

 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    /*
 dummy   1    2   3   4
    pre  l    r
dummy    2    1   3  4
  pre    r    l
dummy  2   1    3     4
           pre  l     r

     */
    static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode left = head;
        ListNode right = head.next;
        ListNode newHead = right;
        while (right != null){
            left.next = right.next;
            right.next = left;
            pre.next = right;
            pre = left;
            left = left.next;
            if (left != null) right = left.next;
            else right = null;
        }

        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode node = swapPairs(head);
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }

    }
}
