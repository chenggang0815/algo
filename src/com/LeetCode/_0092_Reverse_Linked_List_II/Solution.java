package com.LeetCode._0092_Reverse_Linked_List_II;
/*
92. Reverse Linked List II
Given the head of a singly linked list and two integers left and right where left <= right,
reverse the nodes of the list from position left to position right, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

1 -> 2 -> 3 -> 4 -> 5
1 -> 4 -> 3 -> 2 -> 5

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
        ListNode cur = head;
        ListNode l = null;
        ListNode r = null;
        int n = 0;
        while (cur != null){
            if (left == n) l = cur;
            if (right == n) r = cur;
            cur = cur.next;
        }

        cur = head;
        while (cur != null){
            if (cur.next == l){
                cur.next = r;
            }
        }

    }

    public static void main(String[] args) {

    }
}
