package com.leetcode._0015;

import java.util.Stack;

/*
206 Reverse Linked List
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

//    public ListNode reverseList(ListNode head) {
//        if (head==null)return null;
//        if (head.next==null) return head;
//        Stack<ListNode> stack = new Stack<>();
//        ListNode temp = head;
//        while(temp.next!=null){
//            stack.push(temp);
//            temp = temp.next;
//        }
//        ListNode newHead = stack.pop();
//        while (!stack.isEmpty()){
//            ListNode node = stack.pop();
//            node.next = node;
//        }
//
//    }
    public static void main(String[] args) {


    }
}
