package LeetCode._0025_K个一组翻转链表;

/*
25. K个一组翻转链表
给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
k是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。



示例：
给你这个链表：1->2->3->4->5
当k = 2时，应当返回: 2->1->4->3->5
当k = 3时，应当返回: 3->2->1->4->5
 */

/*
思路
1. https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    // time: o(n) space: o(1)
    static ListNode reverseKGroup(ListNode head, int k){
        if (head == null || head.next == null) return head;

        ListNode dummmy = new ListNode(0);
        dummmy.next = head;
        //dummy + 待翻转 + 未翻转
        //初始化pre和end都指向dummy。pre是每次要翻转的链表的头结点的上一个节点。end是每次要翻转的链表的尾节点
        ListNode pre = dummmy;
        ListNode end = dummmy;

        while (end.next != null){
            for (int i = 0; i < k && end != null; i++){
                end = end.next;
            }
            //如果end==null，即需要翻转的链表的节点数小于k，结束
            if (end == null){
                break;
            }
            //先记录下end.next,方便后面链接链表
            ListNode next = end.next;
            //然后断开链表
            end.next = null;
            //记录下要翻转链表的头节点
            ListNode start = pre.next;
            //翻转链表,pre.next指向翻转后的链表的头节点。1->2 变成2->1。 dummy->2->1
            pre.next = reverse(start);
            //翻转后头节点变到最后。通过.next把断开的链表重新链接。
            start.next = next;
            //将pre换成下次要翻转的链表的头结点的上一个节点。即start
            pre = start;
            //翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
            end = start;
        }

        return dummmy.next;
    }

    static ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    static void addNode(ListNode head, int data){
        ListNode node = new ListNode(data);
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        temp.next = node;
    }


    static void printNode(ListNode head){
        if (head == null){
            System.out.println("null");
        }
        ListNode temp = head;
        while (temp != null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        addNode(head, 2);
        addNode(head, 3);
        addNode(head, 4);
        addNode(head, 5);

        printNode(head);

        System.out.println("============");
        printNode(reverseKGroup(head, 3));
    }
}
