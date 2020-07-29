package com.leetcode._0015;
/*
206 Reverse Linked List 反转单链表
Reverse a singly linked list.
Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static ListNode head = new ListNode(-1);
    static void addNode(int data){
        ListNode node = new ListNode(data);
        ListNode temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }

        temp.next=node;
    }

    static void printNode(ListNode head){
        if (head==null||head.next==null){
            System.out.println("null");
        }
        ListNode temp = head;
        while (temp.next!=null){
            temp=temp.next;
            System.out.println(temp.val);
        }
    }


    // 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
    public static ListNode reverseList2(ListNode head){
        ListNode newHead = null;
        ListNode curnode = head;
        ListNode prenode = null;
        while (curnode != null){
            ListNode nextnode = curnode.next;
            if (curnode.next == null){
                newHead = curnode;
            }

            curnode.next = prenode;
            prenode = curnode;
            curnode = nextnode;

        }

        return  newHead;
    }

    //递归法
    private static ListNode reverseList1(ListNode head){
        if (head ==null || head.next ==null){
            return head;
        }

        ListNode temp = head.next;
        ListNode newhead = reverseList1(head.next);
        temp.next = head;
        head.next = null;

        return newhead;
    }


    public static void main(String[] args) {
        addNode(4);
        addNode(3);
        addNode(2);
        addNode(1);
        printNode(head);
        System.out.println("==================");
        printNode(reverseList1(head.next));


    }
}
