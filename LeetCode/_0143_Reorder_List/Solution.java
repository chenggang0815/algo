package LeetCode._0143_Reorder_List;
/*
143. Reorder List
You are given the head of a singly linked-list. The list can be represented as:
L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4]
Output: [1,4,2,3]
*/

/*
Solution
Approach 1
1. find the middle node
2. reverse the second part list node
3. merge two list node

for example:
1. input=[1,2,3,4] mid=3
2. reverse [3,4] => [4,3]
3. merge [1,2] [4,3] => [1,4,2,3]
*/
public class Solution {
    class ListNode{
        ListNode next;
        int val;
        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public void reorderList(ListNode head) {
        int length = 0;
        ListNode cur = head;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        int mid = (length + 1) / 2;
        cur = head;
        for(int i = 0; i < mid - 1; i++){
            cur = cur.next;
        }
        ListNode head2 = reverse(cur.next);
        cur.next = null;
        ListNode head1 = head;
        int i = 0;
        ListNode newHead = new ListNode(-2);
        cur = newHead;
        while(head1 != null || head2 != null){
            if(i % 2 == 0){
                cur.next = head1;
                head1 = head1.next;
            }else{
                cur.next = head2;
                head2 = head2.next;
            }
            i++;
            cur = cur.next;
        }

        head = newHead.next;
    }

    public static void main(String[] args) {

    }
}
