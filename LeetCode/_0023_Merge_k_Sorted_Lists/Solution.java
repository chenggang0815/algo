package LeetCode._0023_Merge_k_Sorted_Lists;
/*
23. Merge k Sorted Lists

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6

Constraints:
1. k == lists.length
2. 0 <= k <= 10^4
3. 0 <= lists[i].length <= 500
4. -10^4 <= lists[i][j] <= 10^4
5. lists[i] is sorted in ascending order.
6. The sum of lists[i].length won't exceed 10^4.
*/

/*
Solution
Approach 1:
time: O(N*k) k is the number of the lists, N is total number of nodes in lists
space: O(1)
1. input:
       lists = [node1, node2, node3]
       ListNode head=new ListNode(Integer.MIN_VALUE);
2. iterate the lists, each time merge two list
3. head = merge2Lists(head, node1)

Approach 2: Merge with Divide And Conquer
time: O(N*log(k)) k is the number of the lists, N is total number of nodes in lists
space: O(log(k))

Approach 3: Merge with Divide And Conquer
time: O(N*log(k))
space: O(1)
    //lists = [L1, L2, L3, L4] i=0 interval=1
    //lists = [L1L2, L2, L3, L4] => interval=1 i=0 => merge(index=i=0, index=i+interval=1) => list[0] = merge(i=0, i=1)
    //lists = [L1L2, L2, L3L4, L4] => interval=1 i=2 => list[2] = merge(i=2, i=3)
    //lists = [L1L2L3L4, L2, L3L4, L4] => interval=2 i=0 => merge(i=0, i=0+interval) => merge(0,2)
*/
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static ListNode merge2Lists(ListNode head1, ListNode head2){
        ListNode head = new ListNode(1);
        ListNode cur = head;
        while (head1 != null && head2 != null){
            if (head1.val <= head2.val){
                cur.next = head1;
                head1 = head1.next;
            }else{
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = (head1 == null ? head2 : head1);

        return head.next;
    }
// Approach 1
    static ListNode mergeKLists1(ListNode[] lists) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        for(ListNode list: lists){
            head = merge2Lists(head, list);
        }

        return head.next;
    }

// Approach 2
    static ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    static ListNode merge(ListNode[] lists, int left, int right){
        if (left == right) return lists[left];
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        ListNode node1 = merge(lists, left, mid);
        ListNode node2 = merge(lists, mid + 1, right);

        return merge2Lists(node1, node2);
    }


    // Approach 3
    //lists = [L1, L2, L3, L4] i=0 interval=1
    //lists = [L1L2, L2, L3, L4] => interval=1 i=0 => merge(index=i=0, index=i+interval=1) => list[0] = merge(i=0, i=1)
    //lists = [L1L2, L2, L3L4, L4] => interval=1 i=2 => list[2] = merge(i=2, i=3)
    //lists = [L1L2L3L4, L2, L3L4, L4] => interval=2 i=0 => merge(i=0, i=0+interval) => merge(0,2)
    static ListNode mergeKLists3(ListNode[] lists){
        if (lists.length == 0) return null;

        int interval = 1;
        while (interval < lists.length){
            for (int i = 0; i < lists.length - interval; i = i + 2 * interval){
                lists[i] = merge2Lists(lists[i], lists[i + interval]);
            }

            interval *= 2;
        }

        return lists[0];
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode node1 = new ListNode(5);
        head1.next = node1;

        ListNode head2 = new ListNode(2);
        ListNode node2 = new ListNode(7);
        head2.next = node2;

        ListNode head3 = new ListNode(3);
        ListNode node3 = new ListNode(6);
        head3.next = node3;

        ListNode[] lists = new ListNode[]{head1, head2, head3};
        ListNode head = mergeKLists3(lists);
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
