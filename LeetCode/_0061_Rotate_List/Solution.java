package LeetCode._0061_Rotate_List;
/*
61. Rotate List
Given the head of a linked list, rotate the list to the right by k places.
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Constraints:
1. The number of nodes in the list is in the range [0, 500].
2. -100 <= Node.val <= 100
3. 0 <= k <= 2 * 109
*/
/*
Solution:
Approach1: intuition time:o(N) space:O(1)
1. move the last node to the head, operate k times
2. optimized method => when k is very large, we get k = k % length
  for example: 1->2->3 k=300001 <=> k=1

Approach2: time:o(N) space:O(1)
// 1 -> 2 -> 3 -> 4 ->5 n=5 k=2
// 0    1    2    3   4
index of newHead = n-k = 5-2 = 3 => newHead=4
index of new tail = n-k-1 = 5-3 = 2 => newTail=3
1. iterate the list, find the tail, => tail.next = head
2. we the above method to find the newHead and newTail
3. newTail.next = null
4. return newHead
*/
public class Solution {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    public ListNode rotateRight1(ListNode head, int k) {
        if(head == null) return head;

        int length = 0;
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            length++;
        }

        k = k % length;

        while(k > 0){
            cur = head;
            ListNode pre = null;
            while(cur.next != null){
                pre = cur;
                cur = cur.next;
                if(cur.next == null){
                    pre.next = null;
                    cur.next = head;
                    head = cur;
                    break;
                }
            }
            k--;
        }

        return head;
    }

    public ListNode rotateRight2(ListNode head, int k) {
        // 1 -> 2 -> 3 -> 4 ->5 n=5 k=2  newHead=n-k tail=n-k-1
        // 0    1    2    3   4
        if(head == null) return null;

        int n = 1;
        ListNode tail = head;
        while(tail.next != null){
            tail = tail.next;
            n++;
        }
        tail.next = head;

        ListNode cur = head;
        for(int i = 0; i < n - (k % n) - 1; i++){
            cur = cur.next;
        }

        ListNode newHead = cur.next;
        cur.next = null;

        return newHead;
    }
    public static void main(String[] args) {

    }
}
