package com.LeetCode._0141_Linked_List_Cycle;

import java.util.HashMap;

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
    //Time Complexity:O(n)
    //Space Complexity:O(1);
    // 1ms
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null){
            slow = slow.next;
            if(fast.next != null){
                fast = fast.next.next;
            }else{
                return false;
            }

            if (slow == fast){
                return true;
            }
        }

        return false;
    }
    // 0ms
    static boolean hasCycle0(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }

        return false;
    }

    static boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast.next != null && fast.next.next != null){
            if (slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }
    //Time Complexity:O(n^2)
    //Space Complexity:O(1);
    static boolean hasCycle2(ListNode head) {
        if(head == null || head.next == null) return false;
        if(head.next.next == head) return true;
        ListNode temp = head;

        while(temp.next != null){
            ListNode t = head;
            temp = temp.next;
            while(t != temp){
                if(t.next == temp.next) return true;
                t = t.next;
            }
        }
        return false;
    }

    //Time Complexity:O(n)
    //Space Complexity:O(n)
    static boolean hasCycle3(ListNode head){
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

        System.out.println(hasCycle1(head));
    }
}
