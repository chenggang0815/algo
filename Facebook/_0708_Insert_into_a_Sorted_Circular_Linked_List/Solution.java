package Facebook._0708_Insert_into_a_Sorted_Circular_Linked_List;
/*
708. Insert into a Sorted Circular Linked List
Given a Circular Linked List node, which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.
If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.
If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.

Example 1:
Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
*/
/*
Solution
*/
public class Solution {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };

    public Node insert(Node head, int insertVal) {
        if(head == null){
            Node newNode = new Node(insertVal, null);
            newNode.next = newNode;
            return newNode;
        }

        Node prev = head;
        Node cur = head.next;

        while(true){
            // case 1
            if(prev.val <= insertVal && insertVal <= cur.val){
                prev.next = new Node(insertVal, cur);
                return head;
            }else if(prev.val > cur.val){ // case 2
                if(insertVal >= prev.val || insertVal <= cur.val){
                    prev.next = new Node(insertVal, cur);
                    return head;
                }
            }

            prev = cur;
            cur = cur.next;
            if(prev == head) break;
        }

        // case 3
        prev.next = new Node(insertVal, cur);

        return head;

    }
    public static void main(String[] args) {

    }
}
