package com.剑指offer;

public class Solution_demo {
    class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){
            this.val = val;
        }
    }

    ListNode merge(ListNode node1, ListNode node2){
        ListNode head1 = node1;
        ListNode head2 = node2;
        ListNode head = new ListNode();
        ListNode cur = head;

        while (head1 != null && head2 != null){
            if (head1.val <= head2.val){
                cur.next = head1;
                head1 = head1.next;
            }else {
                cur.next = head2;
                head2 = head2.next;
            }

            cur = cur.next;
        }

        if (head1.next != null){
            cur.next = head;
        }

        if (head2.next != null){
            cur.next = head2;
        }

        return head;
    }

    public static void main(String[] args) {

    }
}
