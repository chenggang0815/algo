package com.LeetCode._0092_Reverse_Linked_List_II;

import java.util.List;

/*
92. Reverse Linked List II
Given the head of a singly linked list and two integers left and right where left <= right,
reverse the nodes of the list from position left to position right, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

解题思路：头插法
1-2-3-4-5-6 left=2 right=5 从left节点开始，每次把left节点之后的元素放到首位

1-3-2-4-5-6

1-4-3-2-5-6

1-5-4-3-2-6

  -1 -  3  -  5 1,2
   pre cur
*/
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static ListNode reverseBetween(ListNode head, int left, int right){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;

        for (int i = 0; i < left - 1; i++){
            pre = pre.next;
            cur = cur.next;
        }

        System.out.println();

        for (int i = 0; i < right - left; i++){
            ListNode removed = cur.next;
            cur.next = cur.next.next;

            removed.next = pre.next;
            pre.next = removed;
        }

        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = reverseBetween(head, 2, 5);
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }


    }
}
