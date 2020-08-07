package com.nowcoder._079;


public class Solution {
    static class ListNode{
        ListNode next;
        int val;
        ListNode(int val){
            this.val = val;
        }
    }

    static ListNode listSort(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode left = null;
        ListNode right = null;
        left = head;

        while(left.next != right){
            while(left.next != right){
                if(left.val > left.next.val){
                    int temp = left.val;
                    left.val = left.next.val;
                    left.next.val = temp;
                }
                left = left.next;
            }

            right = left;
            left = head;
        }

        return head;
    }

    static void printList(ListNode head){
        ListNode cur = head;
        while(cur != null){
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        printList(listSort(head));
    }
}
