package Amazon._0138_Copy_List_with_Random_Pointer;

import java.util.HashMap;

/*
138. Copy List with Random Pointer

solution:
            HashMap<Node, Node> map = new HashMap<>();
            map.put(OriginalNode, CopyNode)
            map.get(cur).next = map.getOrDefault(cur.next, null);
            map.get(cur).random = map.getOrDefault(cur.random, null);
            return map.get(head)
time complexity: O(n)
space complexity: O(n)

to do:
time complexity: O(n)
space complexity: O(1)
*/
public class Solution {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {

        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null){
            Node node = new Node(cur.val);
            map.put(cur, node);
            cur = cur.next;
        }

        cur = head;
        while(cur != null){
            map.get(cur).next = map.getOrDefault(cur.next, null);
            map.get(cur).random = map.getOrDefault(cur.random, null);
            cur = cur.next;
        }

        return map.get(head);
    }
    public static void main(String[] args) {

    }
}
