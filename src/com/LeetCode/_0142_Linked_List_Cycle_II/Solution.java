package com.LeetCode._0142_Linked_List_Cycle_II;

import java.util.HashSet;

/*
142. Linked List Cycle II

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

solution 1：hash set
time: o(n)
space: o(1)

solution 2： 快慢指针
1. 使用两个指针，fast 与slow。它们起始都位于链表的头部。slow指针每次向后移动一个位置，fast指针向后移动两个位置。
2. 如果链表中存在环，则fast指针最终将再次与slow指针在环中相遇。

3. 设链表中 环外部分的长度为a，slow指针进入环后，又走了b的距离与fast相遇。
4. 此时，fast 指针已经走完了环的n圈，因此它走过的总距离为 a+n(b+c)+b=a+(n+1)b+nc

5. 任意时刻，fast指针走过的距离都为slow指针的2倍。因此，有：
    a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)

6. 有了a=c+(n−1)(b+c)的等量关系 =>
    6.1 从相遇点到入环点的距离加上n−1圈的环长 => c+(n−1)(b+c)
    6.2 恰好等于从链表头部到入环点的距离 => a

7. 因此，当发现slow与fast相遇时，我们再额外使用一个指针ptr。
8. 起始，它指向链表头部；随后，它和slow每次向后移动一个位置。
9. 最终，它们会在入环点相遇。

time: o(n)
space: o(1)
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    // time:o(n) space:o(n)
    static ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null){
            if (set.contains(cur)){
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }

        return null;
    }
    // time:o(n) space:o(1)
    static ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null){
            slow = slow.next;
            if (fast.next != null){
                fast = fast.next.next;
            }else {
                return null;
            }
            if (slow == fast){
                ListNode cur = head;
                while (cur != slow){
                    cur =cur.next;
                    slow = slow.next;
                }
                return cur;
            }
        }

        return null;
    }
    public static void main(String[] args) {
//        ListNode head = new ListNode(3);
//        ListNode node1 = new ListNode(2);
//        ListNode node2 = new ListNode(0);
//        ListNode node3 = new ListNode(-4);
//        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node1;

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        node2.next = node1;

        System.out.println(detectCycle1(node1).val);
    }
}
