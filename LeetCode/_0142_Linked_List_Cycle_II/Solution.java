package LeetCode._0142_Linked_List_Cycle_II;
import java.util.HashSet;
/*
142. Linked List Cycle II
Given the head of a linked list, return the node where the cycle begins.
If there is no cycle, return null.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
Do not modify the linked list.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
*/

/*
Solution
Approach1 HashSet time: o(n) space: o(n)
1. iterate the linked list, store the node in the HashSet, the first duplicate node is the cycle begin node
2. 3 -> 2 -> 0 -> -4
       -4->2
3. 3 -> 2 -> 0 -> -4 -> 2 -> 0 -> -4 -> 2
                => 2 is the first duplicate node

Approach 2 fast-slow pointer time: o(n) space: o(1)
1. the first meeting point is not the cycle begin point
2. for example
    3 -> 2 -> 0 -> -4
       4->2
slow = 3   2   0   4   2   0
fast = 3   0   2   4   0   2
             head= 3   0   2
                         begin point

3. the first meeting node is 4, the begin node is 2
4. but we can find a relationship between slow and fast
    x => from 3 to 2, is the distance from start point to begin point
    y => from 2 to 4, is the distance from begin point to meeting point
    z => from 4 to 2, is the distance from meeting point to begin point
5. the distance for slow = x + y, the distance for fast = x + y + z + y
    2slow = fast => 2x+2y=x+2y+z => y=z
    ps: fast = x + n(y + z) + y, which means fast may walk n times of cycle
6. y=z means the distance from begin point to meeting point equal the distance from meeting point to begin point
7. so we can iterate from meeting point and start point, when they are equal, the current point is begin point
*/
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    // time:o(n) space:o(n)
    static ListNode detectCycle1(ListNode head) {
        if(head == null) return null;

        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null){
            if (set.contains(cur)) return cur;
            set.add(cur);
            cur = cur.next;
        }

        return null;
    }

    // time:o(n) space:o(1)
    static ListNode detectCycle2(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null){
            slow = slow.next;
            if (fast.next == null) return null;
            fast = fast.next.next;
            if (slow == fast){
                ListNode cur = head;
                while (cur != slow){
                    cur =cur.next;
                    slow = slow.next;
                }
                return cur;
            }
        }

        return null;
    }

    public static void main(String[] args) {
//        ListNode head = new ListNode(3);
//        ListNode node1 = new ListNode(2);
//        ListNode node2 = new ListNode(0);
//        ListNode node3 = new ListNode(-4);
//        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node1;

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        node2.next = node1;

        System.out.println(detectCycle1(node1).val);
    }
}
