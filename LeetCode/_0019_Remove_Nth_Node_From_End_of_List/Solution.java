package LeetCode._0019_Remove_Nth_Node_From_End_of_List;

import java.util.HashMap;

/*
19. Remove Nth Node From End of List
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Follow up: Could you do this in one pass?
*/
/*
Solution:
Approach 1: two pass

Approach 2: one pass + hashMap <index, ListNode>
1. use a hashmap record the index and listNode
2. map.get(length - n).next = map.getOrDefault(length - n + 2, null)

Approach 3: one pass + two pointers
1. fast pointer first move n step
2. move fast and slow pointer at the same time
3. when fast reach the tail node, the next node of slow pointer need to be deleted.
4. corner case n=length of list node, fast will be null
for example:
    1 -> 2 -> 3 -> 4 -> 5 n=3
    slow          fast
       slow              fast
    we need to delete 3

*/
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    // 1 2 3 4 5
    // length = 5, n = 2, => 4
    // map.get(3).next = map.get(5)
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        HashMap<Integer, ListNode> map = new HashMap<>();
        int length = 0;
        ListNode cur = head;
        while(cur != null){
            length++;
            map.put(length, cur);
            cur = cur.next;
        }
        // 5-2+1
        if(length == n) return head.next;

        map.get(length - n).next = map.getOrDefault(length - n + 2, null);

        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }

        // 1 2 3 n=3 => remove head(1) => return 2->3
        // corner case, n == length of linked list
        if(fast == null) return head.next;

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }

}
