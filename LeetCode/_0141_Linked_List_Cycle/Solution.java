package LeetCode._0141_Linked_List_Cycle;

/*
141. Linked List Cycle

Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
*/

/*
Solution
Approach1 HashMap time:O(n) space:O(n)

Approach2 slow fast pointers time:O(n) space:O(n)
3 -> 2 -> 0 -> -4
   4->2
while(fast != null){
    slow = slow.next
    if(fast.next == null) return false
    fast = fast.next.next
    if(slow == fast)  return true
    }
*/
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null){
            slow = slow.next;
            if(fast.next == null) return false;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }

    static boolean hasCycle1(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
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

        System.out.println(hasCycle(head));
    }
}
