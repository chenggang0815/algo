"""
19. Remove Nth Node From End of List
Given the head of a linked list, remove the nth node from the end of the list and return its head.
Follow up: Could you do this in one pass?

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]
"""


class ListNode:
    def __init__(self, val):
        self.val = val
        self.next = None


class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        mapNode = {}
        cur = head
        index = 0
        while cur:
            index += 1
            # print("index: %s, val: %s" % (index, cur.val))
            mapNode[index] = cur
            cur = cur.next
        if n == index:  # 删除头结点的特殊情况
            return head.next
        if n == 1:  # 删除尾结点的特殊情况
            mapNode[index - 1].next = None
        else:
            mapNode[index - n].next = mapNode[index - n + 2]

        return head

    '''
[pre, 1, 2, 3, 4, 5] n = 2
            slow  fast
 slow.next = slow.next.next => pre 1 2 3 5 => return pre.next
 
[pre, 1, 2, 3, 4, 5] n = 5
 slow             fast
 slow.next = slow.next.next => pre 2 3 4 5 => return pre.next 
'''

    def removeNthFromEnd2(self, head: ListNode, n: int) -> ListNode:
        pre = ListNode(0)
        pre.next = head
        slow = pre
        fast = pre
        while fast and n != 0:
            fast = fast.next
            n -= 1
        while fast.next:
            fast = fast.next
            slow = slow.next

        slow.next = slow.next.next

        return pre.next


if __name__ == '__main__':
    s = Solution()
    head = ListNode(1)
    node1 = ListNode(2)
    node2 = ListNode(3)
    node3 = ListNode(4)
    node4 = ListNode(5)
    head.next = node1
    node1.next = node2
    node2.next = node3
    node3.next = node4

    cur = s.removeNthFromEnd2(head, 2)
    while cur:
        print(cur.val)
        cur = cur.next
