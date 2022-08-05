package LeetCode._0025_Reverse_Nodes_in_k_Group;

/*
25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
You may not alter the values in the list's nodes, only nodes themselves may be changed.


Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]

Example 3:
Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]
*/

/*
solution 1:  Iterative  time:O(n) space:O(1)
Input: head = [1,2,3,4,5], k = 2
 dummy->1->2->3->4->5
  pre
  end

1. move end to left k step, if end is null, don't need to reverse, return directly
 dummy-> 1 -> 2 -> 3-> 4-> 5
  pre
             end

2. cut list from end
 dummy-> 1 -> 2      3-> 4-> 5
  pre
             end   next
LinkedList next = end.next
end.next = null;

3. reverse LinkedList from start to end
 dummy-> 1 -> 2      3-> 4-> 5
  pre  start
             end   next
start = pre.next
pre.next = reverse(start) =>  dummy.next -> head
 dummy-> 2 -> 1      3-> 4-> 5
  pre   end
             start   next

4. concat the list
start.next = next
pre = start
end = start
 dummy-> 2 -> 1 -> 3-> 4-> 5
             pre
             end

solution 2: Recursion time:O(n) space:O(n/k)  n/k is the number of recursion calls

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
            pre.next = reverse(start); // dummy.next -> head
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
