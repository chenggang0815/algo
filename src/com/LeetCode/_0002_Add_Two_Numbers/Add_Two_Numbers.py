"""
2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
"""


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        n1 = []
        n2 = []

        temp1 = l1
        temp2 = l2
        while temp1:
            n1.append(str(temp1.val))
            temp1 = temp1.next

        while temp2:
            n2.append(str(temp2.val))
            temp2 = temp2.next

        n1.reverse()
        n2.reverse()
        a = int(''.join(n1))
        b = int(''.join(n2))

        l = list(str(a+b))
        l.reverse()
        head = ListNode()
        cur = head
        for item in l:
            node = ListNode(item)
            cur.next = node
            cur = cur.next

        return head.next


if __name__ == '__main__':
    head1 = ListNode(2)
    node1 = ListNode(4)
    node2 = ListNode(3)
    head1.next = node1
    node1.next = node2

    head2 = ListNode(5)
    node3 = ListNode(6)
    node4 = ListNode(4)
    head2.next = node3
    node3.next = node4

    s = Solution()
    head = s.addTwoNumbers(head1, head2)

    while head:
        print(head.val)
        head = head.next
