package LeetCode._0002_Add_Two_Numbers;
/*
2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.


Solution: similar question 445. Add Two Numbers II
1. because the linked list is stored in reverse order, which means 2->4->3 denote 342
2. so we can add these two linked list from left to right.

1. 将两个链表看成是相同长度的遍历，不足的补0
2. 同位直接相加，每一位计算时需要考虑上一位的进位值，在计算结束后同样要更新当前位的进位值
3. 全部遍历完后，如果进位值为1，则需要在最后添加节点1

o(max(m,n)) o(max(m,n))
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0);
        ListNode cur = head;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int carry = 0;
        while (cur1 != null || cur2 != null){
            int v1 = cur1 == null ? 0:cur1.val;
            int v2 = cur2 == null ? 0:cur2.val;
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;

            if(cur1 != null) cur1 = cur1.next;
            if (cur2 != null) cur2 = cur2.next;
        }

        if (carry == 1){
            cur.next = new ListNode(1);
        }

        return head.next;
    }

    // approach 2 11/09/2022
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int num = 0;
        while(l1 != null || l2 != null || num != 0){
            if(l1 != null){
                num += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                num += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(num % 10);
            num /= 10;
            cur = cur.next;
        }

        return dummy.next;

    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(9);
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(9);
        head1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode head2 = new ListNode(9);
        ListNode node7 = new ListNode(9);
        ListNode node8 = new ListNode(9);
        ListNode node9 = new ListNode(9);
        head2.next = node7;
        node7.next = node8;
        node8.next = node9;
        ListNode head = addTwoNumbers(head1, head2);
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
