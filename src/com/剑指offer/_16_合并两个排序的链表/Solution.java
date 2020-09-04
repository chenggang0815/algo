package com.剑指offer._16_合并两个排序的链表;
//合并两个有序的单链表

public class Solution {
    static class ListNode{
        int val;
        ListNode next;

        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
    }

    // 初始化两个头结点
    static ListNode head1 = new ListNode();
    static ListNode head2 = new ListNode();

    //往链表里添加结点
    public static void addNode(ListNode head, int data){
        ListNode newnode = new ListNode(data);
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        temp.next = newnode;
    }

    //打印链表
    public static void printL(ListNode head){
        if (head == null){
            System.out.println("链表为空");
        }
        ListNode temp = head;
        while (temp != null){
            System.out.println("" + temp.val);
            temp = temp.next;
        }
    }

/*
    bigo面试:
    输入链表:1->2->3->4->5->6->7
    输出链表:1->7->2->6->3->5->4
    时间复杂度o(n) 空间复杂度o(1)

 */
    static void merge(ListNode head){
        if (head == null) return;

        ListNode cur = head;
        int length = 1;
        while(cur.next != null){
            cur = cur.next;
            length++;
        }

        int midnode = length/2 + 1;
        int index = 0;
        ListNode head1 = new ListNode(0);
        while (cur.next != null && index != midnode){
            cur = cur.next;
            head1.next = cur;
            index++;
        }

        while(head != null && head1 != null){
             head.next = head1;
             head1 = head1.next;
             head = head.next;
        }

    }

    //合并链表:非递归
    public static ListNode mergeList(ListNode head1, ListNode head2){
        ListNode temp1 = head1;
        ListNode temp2 = head2; //不改变原来的两个链表

        ListNode newhead = new ListNode();
        ListNode temp = newhead;

        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null){
            temp.next = temp1;
        }
        if (temp2 != null){
            temp.next = temp2;
        }

        return newhead;
    }
	
	    // 合并链表 递归
    public static ListNode mergeList2(ListNode head1, ListNode head2){
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        ListNode newhead = new ListNode();

        if (temp1 == null) return temp2;
        if (temp2 == null) return temp1;

        if (temp1.val < temp2.val){
            newhead = temp1;
            newhead.next = mergeList2(temp1.next, temp2);
        }
        else {
            newhead = temp2;
            newhead.next = mergeList2(temp1, temp2.next);
        }
        return newhead;
    }

    public static void main(String[] args) {

        addNode(head1,1);
        addNode(head1,3);
        //addnode(head1,5);

        addNode(head2,5);
        //addnode(head2,4);
        //addnode(head2,5);


        printL(head1.next);
        System.out.println("==================");
        printL(head2.next);
        System.out.println("==========合并链表===========");
        printL(mergeList(head1.next,head2.next).next);
    }

}




