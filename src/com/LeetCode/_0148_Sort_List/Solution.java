package com.LeetCode._0148_Sort_List;
/*
148. 排序链表
在O(nlogn)时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:
输入: 4->2->1->3
输出: 1->2->3->4

示例 2:
输入: -1->5->3->4->0
输出: -1->0->3->4->5
 */

/*
思路1：冒泡排序 time:o(n^2) space:o(1)

思路2：递归排序

 */
public class Solution {
    static class ListNode{
        ListNode next;
        int val;
        ListNode(int val){
            this.val = val;
        }
    }

    // Time Limit Exceeded
    static ListNode listSort1(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode left = head;
        ListNode right = null;

        while(left.next != right){
            while(left.next != right){
                if(left.val > left.next.val){
                    int temp = left.val;
                    left.val = left.next.val;
                    left.next.val = temp;
                }
                left = left.next;
            }

            right = left; // 此时，left以及右边的结点都是有序的 => 下次遍历的右边界为left
            left = head; // 下次遍历的右边界总为head
            /*  冒泡排序
                for(int i = 0; i < length-1; i++){
                    for(int j = 0; j < length - i - 1; j++){
                    }
                }
             */
        }

        return head;
    }

    static void printList(ListNode head){
        ListNode cur = head;
        while(cur != null){
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        printList(listSort1(head));
    }
}
