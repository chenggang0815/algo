package LeetCode._0430_Flatten_a_Multilevel_Doubly_Linked_List;
/*
430. Flatten a Multilevel Doubly Linked List
You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer.
This child pointer may or may not point to a separate doubly linked list, also containing these special nodes.
These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list.
Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.
Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.

for example:
// 1 -> 2 -> 3 -> 4 -> 5 -> 6
//           7 -> 8 -> 9 -> 10
//                11-> 12

Output: [1,2,3,7,8,11,12,9,10,4,5,6]
*/
/*
Approach 1: traverse the list

Approach 2: DFS by recursion

Approach 3: DFS by iteration
* */
public class Solution {
    class Node{
        int val;
        Node prev;
        Node child;
        Node next;
        Node(int val){
            this.val = val;
        }
    }

    Node flatten(Node head){
        if (head == null) return head;

        Node cur = head;
        while (cur != null){
            if (cur.child == null){
                cur = cur.next;
                continue;
            }
            Node temp = cur.child;
            // Find the tail of the child list
            while (temp.next != null){
                temp = temp.next;
            }
            // connect tail node of child list and next node of current node
            temp.next = cur.next;
            if (cur.next != null) cur.next.prev = temp;

            // connect current node and current child node, set current child node as null
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null;
        }

        return head;
    }

    public static void main(String[] args) {

    }
}
