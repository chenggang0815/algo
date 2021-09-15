package LeetCode._0328_Odd_Even_Linked_List;
/*
328. Odd Even Linked List

Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

Example 1:
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]

Example 2:
Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]

Constraints:
The number of nodes in the linked list is in the range [0, 104].
-106 <= Node.val <= 106
Follow up: Could you solve it in O(1) space complexity and O(nodes) time complexity?
*/

/*
思路1： time:o(n) space:o(1)
1. 分别定义奇偶链表；
2. 遍历原链表，将当前结点交替插入到奇链表和偶链表（尾插法）；
3. 将偶链表拼接在奇链表后面

[1,2,3,4] => [1,3,2,4]
evenHead = head.next
odd=1
even=2
1.next=2.next => 1.next=3 odd=odd.next => odd=3
2.next=3.next => 2.next=4 even=even.next => even=4
因为 (even == null || even.next == null) => odd.next = evenHead

 */
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static ListNode oddEvenList(ListNode head) {
        if (head == null) return head;

        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        oddEvenList(head);

        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }

    }
}
