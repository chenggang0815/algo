package LeetCode._0445_Add_Two_Numbers_II;
/*
445. Add Two Numbers II

You are given two non-empty linked lists representing two non-negative integers.
The most significant digit comes first and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]
*/
import java.util.Stack;

/*

Solution: similar question 2. Add Two Numbers
* Because the linked list is stored not in reverse order, which means the most significant digit comes first.
* So 7->2->4->3 denote 7243, in contrast with question 2, we need to add these two list from right to left.
* So we use two stack to store the list.

Approach 1 => three stack
1. use two stack to store the two linked list, calculate the sum, push another stack
2. use the third stack to generate the linked list

Approach 2 => two stack
1. use two stack to store the two linked list, calculate the sum, push another stack
2. head=null
   cur.next=head;
   head=cur
   => return head;

Approach 3 =>  reverse output
1. first, don't care about carry
1 -> 5 -> 4 -> 6
     +
     3 -> 6 -> 2
=>
1 -> 8 -> 10 -> 8

2. Fix carry issue by traversing through reversed list
1 <- 9 <- 0 <- 9

3. reverse it
1 -> 9 -> 0 -> 9
*/
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    // 1->2->4->5
    //    2->6->2
    static ListNode addTwoNumber1(ListNode head1, ListNode head2){
        ListNode head = new ListNode(0);
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while (cur1 != null || cur2 != null){
            if (cur1 != null){
                stack1.push(cur1);
                cur1 = cur1.next;
            }
            if (cur2 != null){
                stack2.push(cur2);
                cur2 = cur2.next;
            }
        }
        int carry = 0;
        Stack<ListNode> stack = new Stack<>();
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            int v1 = !stack1.isEmpty() ? stack1.pop().val : 0;
            int v2 = !stack2.isEmpty() ? stack2.pop().val : 0;
            int sum = (v1 + v2 + carry) % 10;
            carry = (v1 + v2 + carry) / 10;
            ListNode cur = new ListNode(sum);
            stack.push(cur);
        }
        ListNode cur = head;
        while (!stack.isEmpty()){
            cur.next = stack.pop();
            cur = cur.next;
        }

        return head.next;
    }

    static ListNode addTwoNumber2(ListNode head1, ListNode head2){
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while (cur1 != null || cur2 != null){
            if (cur1 != null){
                stack1.push(cur1);
                cur1 = cur1.next;
            }
            if (cur2 != null){
                stack2.push(cur2);
                cur2 = cur2.next;
            }
        }
        int carry = 0;
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            int v1 = !stack1.isEmpty() ? stack1.pop().val : 0;
            int v2 = !stack2.isEmpty() ? stack2.pop().val : 0;
            int sum = (v1 + v2 + carry) % 10;
            carry = (v1 + v2 + carry) / 10;
            ListNode cur = new ListNode(sum);
            cur.next = head;
            head = cur;
        }

        return head;
    }
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        ListNode head2 = new ListNode(2);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(3);
        head1.next = node1;
        node1.next = node2;
        node2.next = node3;
        head2.next = node4;
        node4.next = node5;

        ListNode cur = addTwoNumber2(head1, head2);
        while (cur != null){
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
