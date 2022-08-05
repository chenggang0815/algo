package LeetCode._0206_Reverse_Linked_List;
/*
206. Reverse Linked List
Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Solution
Approach 1 Iterative
null   1 -> 2 -> 3 -> 4 -> 5
pre   cur  next
1. pre = null cur = head
2. reverse cur and pre
    cur.next = pre
    pre = cur
    cur = cur.next => false, because cur.next already point to pre, it exists cycle
3. so we need to record next node first.
    ListNode next = cur.next;
    cur.next = pre;
    pre = cur;
    cur = next;
4. return pre

Approach 2 Recursion
1 -> 2 -> 3 -> 4 -> 5
newHead = reverse(node) => if(node == null || node.next == null) return the tail node(5) of original list
node = 5, we can't return to 4

newHead = reverse(node.next) => if(node == null || node.next == null) return the tail node(5) of original list
node = 4 => node.next.next = next => 5.next=4
1 -> 2 -> 3 -> 4 <- 5

node.next.next = node
node.next = null
 => for the original head node
    node=1 node.node.next = node => 2->1 1->2
    so we need node.next = null => null <- 1 <- 2

92. Reverse Linked List II
*/
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static void addNode(ListNode head, int data){
        ListNode node = new ListNode(data);
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        temp.next = node;
    }

    static void printNode(ListNode head){
        if (head == null){
            System.out.println("null");
        }
        ListNode temp = head;
        while (temp != null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }


    public static ListNode reverseList1(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    //recursion
    private static ListNode reverseList2(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        addNode(head,2);
        addNode(head,3);
        addNode(head,4);
        printNode(head);
        System.out.println("=========翻转=========");
        printNode(reverseList2(head));
    }
}
