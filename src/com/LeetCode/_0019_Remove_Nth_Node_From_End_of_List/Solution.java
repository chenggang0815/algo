package com.LeetCode._0019_Remove_Nth_Node_From_End_of_List;
/*
19. Remove Nth Node From End of List
Given the head of a linked list, remove the nth node from the end of the list and return its head.
Follow up: Could you do this in one pass?

1. 整体思路是让fast指针先移动n步，之后双指针指针共同移动直到fast的指针到尾部为止
2. 由于要删除的结点可能是head结点，所以需要设立一个虚拟结点pre => pre.next = head
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static ListNode removeNthFromEnd(ListNode head, int n){
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode slow = pre;
        ListNode fast = pre;
        while (fast.next != null && n != 0){
            fast = fast.next;
            n--;
        }

        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return pre.next;
    }
}
