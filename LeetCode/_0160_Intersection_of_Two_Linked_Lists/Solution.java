package LeetCode._0160_Intersection_of_Two_Linked_Lists;

import java.util.HashSet;

/*
160. Intersection of Two Linked Lists
Write a program to find the node at which the intersection of two singly linked lists begins.

Approach 1: hashset
    //Time: O(M + N)
    //Space: O(M)
Approach 2:
    // time: O(M + N)
    // space: O(1)
1. Calculate N; the length of list A.
2. Calculate M; the length of list B.
3. Set the start pointer for the longer list.
4. Step the pointers through the list together.
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
    //Time: O(M + N)
    //Space: O(M)
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        HashSet<ListNode> set = new HashSet<>();
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        while (temp1 != null){
            set.add(temp1);
            temp1 = temp1.next;
        }
        while (temp2 != null){
            if (set.contains(temp2)) return temp2;

            temp2 = temp2.next;
        }

        return null;
    }

    // Approach 2
    // time: O(M + N)
    // space: O(1)



    public static void main(String[] args) {

    }
}
