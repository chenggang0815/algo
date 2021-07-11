package com.LeetCode._0023_Merge_k_Sorted_Lists;

import com.sun.deploy.ui.DialogTemplate;

import java.util.ArrayList;
import java.util.List;

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

思路1：两两合并，时间复杂度：k^2*n
思路2：归并排序，时间复杂度：nk*log（k） 空间复杂度：log（k）
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
// Runtime: 145 ms, faster than 14.22% of Java online submissions for Merge k Sorted Lists.
    static ListNode mergeKLists1(ListNode[] lists) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        for(ListNode list: lists){
            head = merge2Lists(head, list);
        }

        return head.next;
    }
// 归并排序
    static ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    static ListNode merge(ListNode[] lists, int left, int right){
        if (left == right) return lists[left];
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        return merge2Lists(merge(lists, left, mid), merge(lists, mid + 1, right));
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
        ListNode head = mergeKLists2(lists);
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
