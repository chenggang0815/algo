package com.leetcode._0160_两个链表的第一个公共结点;

import java.util.HashMap;

/*
160. Intersection of Two Linked Lists

Write a program to find the node at which the intersection of two singly linked lists begins.
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
    //Time Complexity: O(n)
    //Space Complexity: O(n)
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode,ListNode> map = new HashMap<>();
        if (headA.next==null || headB.next==null) return null;
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        while (temp1!=null){
            map.put(temp1,temp1);
            temp1 = temp1.next;
        }
        while (temp2!=null){
            if (map.containsKey(temp2)){
                return temp2;
            }
            temp2 = temp2.next;
        }
        return null;
    }


    public static void main(String[] args) {

    }
}
