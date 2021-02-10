class Node:
    def __init__(self, val):
        self.val = val
        self.next = None
        self.random = None


class Solution:
    def copyRandomList1(self, head: Node) -> Node:
        if not head:
            return head
        mapNode = {}
        cur = head

        while cur:
            temp = Node(cur.val)
            mapNode[cur] = temp
            cur = cur.next

        cur = head
        while cur:
            mapNode[cur].next = mapNode.get(cur.next, None) # cur.next 可能为null，但是java中map的key不存在返回为null, python中 map中key不存在会报错
            mapNode[cur].random = mapNode.get(cur.random, None)
            cur = cur.next

        return mapNode[head]

    def copyRandomList2(self, head: Node) -> Node:
        mapNode = {}
        mapNode[None] = None  # cur.next 可能为null，但是java中map的key不存在返回为null, python中 map中key不存在会报错

        cur = head
        while cur:
            temp = Node(cur.val)
            mapNode[cur] = temp
            cur = cur.next

        cur = head
        while cur:
            mapNode[cur].next = mapNode[cur.next]
            mapNode[cur].random = mapNode[cur.random]
            cur = cur.next

        return mapNode[head]


if __name__ == '__main__':
    node1 = Node(1)
    node2 = Node(3)
    node3 = Node(5)
    node5 = Node(7)

    node1.next = node2
    node2.next = node3
    node3.next = node5

    node3.random = node1
    node2.random = node5

    s = Solution()

    node = s.copyRandomList2(node1)

    cur = node

    while cur:
        print(cur.val)
        random = cur.random
        if random:
            print("random.val:", random.val)
        cur = cur.next
