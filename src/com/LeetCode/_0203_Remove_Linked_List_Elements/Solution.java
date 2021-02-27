package com.LeetCode._0203_Remove_Linked_List_Elements;
/*
203. Remove Linked List Elements

Remove all elements from a linked list of integers that have value val.

Example:
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
 */

/*
思路1：先单独处理head结点
思路2：由于需要考虑到需要删除head结点，所以使用哨兵结点
思路3：递归
time:o(N) space:o(1)
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    // 1 ms
    static ListNode removeElements1(ListNode head, int val) {
        while (head != null && head.val == val){ //删除值相同的头结点后，可能新的头结点也值相等，用循环解决
            head = head.next;
        }

        if (head == null) return head;

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null){
            while (cur != null && cur.val == val){
                pre.next = cur.next;
                cur = cur.next;
            }
            if (cur != null) cur = cur.next;
            pre = pre.next;
        }

        return head; //返回head没有问题，因为已经排除这种情况 => 当head.val==val时，无法删除head结点
    }

    // 0 ms
    static ListNode removeElements1_1(ListNode head, int val) {
        while (head != null && head.val == val){ //删除值相同的头结点后，可能新的头结点也值相等，用循环解决
            head = head.next;
        }

        if (head == null) return head;

        ListNode pre = head;
        ListNode cur = head.next;
/*
        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
                cur = cur.next;
            }else{
                cur = cur.next;
                pre = pre.next;
            }
        }
*/
        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
            }else{
                pre = pre.next;
            }
            cur = cur.next;
        }

        return head; //返回head没有问题，因为已经排除这种情况 => 当head.val==val时，无法删除head结点
    }

    static ListNode removeElements2(ListNode head, int val) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode cur = head;

        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
                cur = cur.next;
            }else{
                pre = pre.next;
                cur = cur.next;
            }
        }

        return head; //返回head有问题，当head.val==val时，无法删除head结点
    }

    static ListNode removeElements3(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;

        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
                cur = cur.next;
            }else{
                pre = pre.next;
                cur = cur.next;
            }
        }

        return dummy.next; //返回dummy.next没有问题，当head.val==val时，可以删除head结点
    }

    //递归
    public ListNode removeElements(ListNode head, int val) {
        if(head==null)
            return null;
        head.next=removeElements(head.next,val);
        if(head.val==val){
            return head.next;
        }else{
            return head;
        }
    }
/*
    作者：lewis-dXStAbdZEw
    链接：https://leetcode-cn.com/problems/remove-linked-list-elements/solution/203yi-chu-lian-biao-yuan-su-by-lewis-dxstabdzew/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/

    public static void main(String[] args) {
        ListNode head = new ListNode(6);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(6);
        ListNode node3 = new ListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        ListNode node = removeElements3(head, 6);
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }

    }
}
