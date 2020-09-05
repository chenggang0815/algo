package com.LeetCode._0021_合并两个有序链表;
/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/
public class Solution {

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){
            this.val=val;
        }
    }

    static ListNode head1 = new ListNode();
    static ListNode head2 = new ListNode();

    static void addNode(ListNode head, int val){
        ListNode newNode = new ListNode(val);
        ListNode temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next = newNode;
    }

    static void printList(ListNode head){
        if (head==null){
            System.out.println("linked list is null");
        }
        ListNode tempNode = head;
        while (tempNode != null){
            System.out.println(""+tempNode.val);
            tempNode=tempNode.next;
        }
    }

    // Time Complexity:O(n)
    // Space Complexity:O(1)
    static ListNode mergeTwoList(ListNode l1, ListNode l2){
        if (l1==null && l2==null){
            return null;
        }else if (l1==null && l2 !=null)return l2;
        else if (l2==null && l1!=null)return l1;

        ListNode temp1 = head1;
        ListNode temp2 = head2;
        ListNode newHead = new ListNode();
        ListNode temp = newHead;
        while (temp1!=null && temp2!=null){
            if (temp1.val < temp2.val){
                temp.next = temp1;
                temp1 = temp1.next;
            }
            else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        if (temp1==null){
            temp.next=temp2;
        }
        if (temp2==null){
            temp.next=temp1;
        }
        return newHead;
    }

    // Time Complexity:O(n)
    // Space Complexity:O(n)
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
    
    public static void main(String[] args) {
        addNode(head1,1);
        addNode(head1,2);
        addNode(head1,4);
        addNode(head1,5);
        addNode(head1,6);

        addNode(head2,1);
        addNode(head2,3);
        addNode(head2,4);

        printList(mergeTwoLists2(head1,head2));


    }



}
