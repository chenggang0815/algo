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
        ListNode node_l = null;
        ListNode node_r = null;

        int n = 1;
        while (cur != null){
            if (n == left - 1) l = cur;
            if (n == left) node_l = cur;
            if (n == right) node_r = cur;
            if (n == right + 1) r = cur;

            cur = cur.next;
            n++;
        }

        if (node_l == null){
            return head;
        }

//        System.out.println(l.val);
//        System.out.println(node_l.val);
//        System.out.println(r.val);
//        System.out.println(node_r.val);

        ListNode pre = node_l;
        cur = node_l.next;
        while (cur != r){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        if (l != null) l.next = node_r;
        node_l.next = r;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        head = reverseBetween(head, 2, 4);
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
