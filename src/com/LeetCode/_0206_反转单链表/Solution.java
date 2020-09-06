package com.LeetCode._0206_反转单链表;
/*
206 Reverse Linked List 反转单链表
Reverse a singly linked list.
Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

思路:
1. 指针法
2. 递归
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static void addNode(ListNode head, int data){
        ListNode node = new ListNode(data);
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        temp.next = node;
    }

    static void printNode(ListNode head){
        if (head == null){
            System.out.println("null");
        }
        ListNode temp = head;
        while (temp != null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }


    // 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
    public static ListNode reverseList2(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return  pre;
    }

    //递归法
    private static ListNode reverseList1(ListNode head){
        if (head == null || head.next == null){
            return head;
        }

        /*
        1->2->3->4->5
        head = 4 => r(head.next) => return 5
        4.next.next = 4  => 5.next = 4
        4.next = null
        null<-4<-5
        3.next.next = 3  => 4.next = 3    4
        3.next = null
        null<-3<-4<-5
        ...
        null<-1<-2<-3<-4<-5
         */
        ListNode newhead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;

        return newhead;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        addNode(head,2);
        addNode(head,3);
        addNode(head,4);
        printNode(head);
        System.out.println("=========翻转=========");
        printNode(reverseList2(head));
    }
}
