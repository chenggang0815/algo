class ListNode:
    def __init__(self, val):
        self.val = val
        self.next = None


class Solution:
    def deleteNode(self, node: ListNode):
        node.val = node.next.val
        node.next = node.next.next


if __name__ == '__main__':
    head = ListNode(4)
    node1 = ListNode(5)
    node2 = ListNode(1)
    node3 = ListNode(9)
    head.next = node1
    node1.next = node2
    node2.next = node3

    s = Solution()
    s.deleteNode(node1)
    cur = head
    while cur:
        print(cur.val)
        cur = cur.next;