"""
参考剑指offer

思路1 ： time：o(n) space:o(n)
利用hashmap，遍历链表并且对原链表每个结点，新建结点，一一对应放进hashmap
            copyNode = Node(cur.val)
            mapNode[cur] = copyNode
再次遍历hashmap，有关系：
            mapNode[cur].next = mapNode[cur.next]
            mapNode[cur].random = mapNode[cur.random]

思路2： time:o(n) space:o(1)
原地复制链表
1. 考虑next结点
2. 考虑random结点
3. 将复制后的链表一分为2

"""


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
            mapNode[cur].next = mapNode.get(cur.next,
                                            None)  # cur.next 可能为null，但是java中map的key不存在返回为null, python中 map中key不存在会报错
            mapNode[cur].random = mapNode.get(cur.random, None)
            cur = cur.next

        return mapNode[head]

    def copyRandomList2(self, head: Node) -> Node:
        mapNode = {}
        mapNode[None] = None  # cur.next 可能为null，但是java中map的key不存在返回为null, python中 map中key不存在会报错

        cur = head
        while cur:
            copyNode = Node(cur.val)
            mapNode[cur] = copyNode
            cur = cur.next

        cur = head
        while cur:
            mapNode[cur].next = mapNode[cur.next]
            mapNode[cur].random = mapNode[cur.random]
            cur = cur.next

        return mapNode[head]

    def copyRandomList3(self, head: Node) -> Node:
        if not head:
            return head
        cur = head
        while cur:
            copyNode = Node(cur.val)
            copyNode.next = cur.next
            cur.next = copyNode
            cur = cur.next.next

        cur = head
        while cur:
            if cur.random:
                cur.next.random = cur.random.next
            cur = cur.next.next

        newHead = head.next
        cur = head
        copyCur = newHead
        while cur:
            cur.next = cur.next.next
            cur = cur.next
            if copyCur.next:  # 因为copyCur在cur后面，在最后两个结点中，倒数第二个结点是cur，倒数第一个结点是copyCur
                copyCur.next = copyCur.next.next  # 所以需要对copyCur.next进行判断，若为null，则直接结束遍历
                copyCur = copyCur.next

        return newHead


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

    node = s.copyRandomList3(node1)

    cur = node

    while cur:
        print(cur.val)
        random = cur.random
        if random:
            print("random.val:", random.val)
        cur = cur.next
