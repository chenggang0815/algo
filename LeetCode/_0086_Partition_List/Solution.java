package LeetCode._0086_Partition_List;
/*
86. Partition List
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
*/
/*
Solution:
1. create two new head, head1 denote the first part, head2 denote the second part
2. cur1 = head1, cur2 =head2
3. compare the current node value with x
   if(cur.val < x){
   cur1.next = cur;
   cur1 = cur1.next;
   }else{
   cur2.next = cur;
   cur2 = cur2.next;
   }
4. move the cur node to next => cur = cur.next
5. for example: 1 -> 4 -> 3 -> 2 -> 5 -> 2
               cur
when cur = 1 => cur1.next = cur
                cur1 = cur1.next
first part: head1 -> 1
then cur=cur.next = 4

1, dose node 1 need to operate cur.next = null?
2, or when cur2.next = cur => cur2.next =4
3, second part: head2 -> 4
4, at same time we have 1.next=4 => 1 -> 4 =>  head1->1->4
                                               head2->4
but we don't need to worry about it
5, since the next round, when cur node is 2, we have head1 -> 1 -> 2
6, then we cut the 1(the first node1) and 4(the second node2)
*/
public class Solution {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    // time:O(N) space:O(1)
    public ListNode partition(ListNode head, int x) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode cur = head;
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while(cur != null){
            if(cur.val < x){
                cur1.next = cur;
                cur1 = cur1.next;
            }else{
                cur2.next = cur;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        cur2.next = null;
        cur1.next = head2.next;

        return head1.next;
    }

    // time:O(N) space:O(N)
    public ListNode partition2(ListNode head, int x) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode cur = head;
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while(cur != null){
            if(cur.val < x){
                ListNode temp = new ListNode(cur.val);
                cur1.next = temp;
                cur1 = cur1.next;
            }else{
                ListNode temp = new ListNode(cur.val);
                cur2.next = temp;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        cur1.next = head2.next;

        return head1.next;
    }

    public static void main(String[] args) {

    }
}
