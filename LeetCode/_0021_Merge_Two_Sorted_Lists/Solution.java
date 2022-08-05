package LeetCode._0021_Merge_Two_Sorted_Lists;
/*
21. Merge Two Sorted Lists
You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Example 1:
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/
/*
Solution
Approach 1: recursion
    // Time Complexity:O(n)
    // Space Complexity:O(n)
Approach 2: iterative
    // Time Complexity:O(n)
    // Space Complexity:O(1)
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
    static ListNode mergeTwoList(ListNode head1, ListNode head2){
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        while (temp1 != null && temp2 != null){
            if (temp1.val < temp2.val){
                cur.next = temp1;
                temp1 = temp1.next;
            }else {
                cur.next = temp2;
                temp2 = temp2.next;
            }
            cur = cur.next;
        }
        if (temp1 == null) cur.next = temp2;
        if (temp2 == null) cur.next = temp1;

        return newHead.next;
    }

    // Time Complexity:O(n)
    // Space Complexity:O(n)
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;

        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }
    
    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
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
