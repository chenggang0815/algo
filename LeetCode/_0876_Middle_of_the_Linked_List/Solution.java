package LeetCode._0876_Middle_of_the_Linked_List;
/*
876. Middle of the Linked List
Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.

Example 1:
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Example 2:
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
*/
/*
Solution:
Approach 1: Fast and Slow Pointer
time: o(N)
space: O(1)

*/
public class Solution {
    class ListNode{
        ListNode next;
        int val;
        ListNode(int val){
            this.val = val;
        }
    }
    //  1 2 3 4 5
    //  f   f   f
    //  s s s
    //  fast.next == null => return mid = 3

    //  1 2 3 4 5 6
    //  f   f   f   f
    //  s s s s
    //  fast == null => return mid = 4
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
    public static void main(String[] args) {

    }
}
