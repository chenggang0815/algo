package LeetCode._0082_Remove_Duplicates_from_Sorted_List_II;
/*
82. Remove Duplicates from Sorted List II
Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.
Return the linked list sorted as well.

Example 1:
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

Example 2:
Input: head = [1,1,1,2,3]
Output: [2,3]

Constraints:
1. The number of nodes in the list is in the range [0, 300].
2. -100 <= Node.val <= 100
3. The list is guaranteed to be sorted in ascending order.
*/
/*
Solution: time:O(n) space:O(1)
1. we have two case
2. case 1 => duplicate node not at the head
    1 -> 2 -> 3 -> 3 -> 3 -> 4 -> 5
        pre            cur
 2.1 we can use two pointer to solve this
     while(cur.next != null && cur.val == cur.next.val) cur = cur.next
     cur = cur.next
     pre.next =cur

3. case 2 => duplicate node at the head, we just add a dummy node before the head,
            and make this dummy node is the pre node
   dummy -> 1 -> 1 -> 1 -> 2 -> 3 -> 4 -> 5
    pre    cur
    cur = cur.next
    pre.next = cur
*/
public class Solution {
     public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
         ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        // 1->2->3->3->3->4->5
        //    pre     cur
        //    cur = cur.next => cur = 4
        //    pre.next = cur => 2.next = 4
        // node 3 has been deleted

        // dummy -> 1->1->2->3->4
        //  pre       cur
        //  cur = cur.next => cur = 2
        //  pre.next = cur => dummy.next = 2
        //  node 1 hase been deleted
        while(cur != null){
            if(cur.next != null && cur.val == cur.next.val){
                while(cur.next != null && cur.val == cur.next.val){
                    cur = cur.next;
                }
                //delete current node
                cur = cur.next;
                pre.next = cur;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }


        return dummy.next;
    }
    public static void main(String[] args) {

    }
}
