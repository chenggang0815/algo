package com.leetcode._0010;

import java.util.HashMap;
import java.util.List;

/*
141. Linked List Cycle
Given a linked list, determine if it has a cycle in it.
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    //Time Complexity:O(n^2)
    //Space Complexity:O(1);
    static boolean hasCycle(ListNode head) {
        if(head==null ||head.next==null) return false;
        if(head.next.next==head) return true;
        ListNode temp = head;

        while(temp.next!=null){
            ListNode t = head;
            temp = temp.next;
            while(t!=temp){
                if(t.next==temp.next) return true;
                t=t.next;
            }
        }
        return false;
    }

    //Time Complexity:O(n)
    //Space Complexity:O(n)
    static boolean hasCycle2(ListNode head){
        if (head==null || head.next==null) return false;
        HashMap<ListNode,ListNode> map = new HashMap();
        ListNode temp = head;
        while (temp.next!=null){
            map.put(temp,temp);
            temp = temp.next;
            if (map.containsKey(temp)) return true;
        }
        return false;
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node2;

        System.out.println(hasCycle2(head));
    }
}
