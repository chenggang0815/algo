package LeetCode._0237_Delete_Node_in_a_Linked_List;
/*
Write a function to delete a node in a singly-linked list.
You will not be given access to the head of the list,
instead you will be given access to the node to be deleted directly.

It is guaranteed that the node to be deleted is not a tail node in the list.

Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5,
the linked list should become 4 -> 1 -> 9 after calling your function.
*/

/*
思路：
这道题没有给出链表的头节点，而是直接给出要删除的节点，让我们进行原地删除。
我们对于该节点的前一个节点一无所知，所以无法直接执行删除操作。
因此，我们将要删除节点的 next 节点的值赋值给要删除的节点，转而去删除 next 节点，从而达成目的。
题目中指明了「给定的节点为非末尾节点」且「链表至少包含两个节点」，所以上述方案是切实可行的

4519 => 4119 => 419
 */
// time/space : o(1)
public class Solution {

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static void deleteNode(ListNode node) {
        ListNode nextnode = node.next;
        node.val = nextnode.val;
        node.next = nextnode.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(9);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        deleteNode(node1);
        ListNode cur = head;
        while (cur != null){
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
