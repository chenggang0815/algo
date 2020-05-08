package com.leetcode._0038;
/*
83. Remove Duplicates from Sorted List Easy
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:
Input: 1->1->2
Output: 1->2

Example 2:
Input: 1->1->2->3->3
Output: 1->2->3
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static void printnode(ListNode head){
        ListNode current = head;
        while(current!=null){
            System.out.println(current.val);
            current = current.next;
        }
    }
    // o(n)
    //o(1)
    //因为是有序的单链表，所以只用做头节点开始比较就行了
    static public ListNode deleteDuplicates(ListNode head){
        if (head==null) return head;
        ListNode current = head;
        while (current.next!=null){
            if (current.val==current.next.val){
                current.next = current.next.next;
            }
            else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(8);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        printnode(head);
        System.out.println("=========");
        printnode(deleteDuplicates(head));

    }

}
