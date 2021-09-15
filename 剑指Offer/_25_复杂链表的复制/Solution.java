package 剑指Offer._25_复杂链表的复制;

import java.util.HashMap;
/*
ps：链表的操作： A.next = B 代表使A的next指针指向B
                A = B 或 A = B.next 代表令A节点等于B或者B的next节点
                需要分清：指向操作和赋值操作
 */
/*
复杂链表的复制

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
请对此链表进行深拷贝，并返回拷贝后的头结点

思路1：利用hashmap o(n);o(n)
1. 首先按照<原节点，原节点的复制节点>来新建map。
2. 遍历原链表，将原链表中的next和random指针复制过去：
    a.对原链表中的每个节点来说，知道他的next指针指向的结点(cur.next)和random指针指向的结点(cur.random)，
    b.又因为原链表中的每个节点和新链表中的节点一一对应，
    则有 map.get(cur).next = map.get(cur.next) 和 map.get(cur).random = map.get(cur.random)
    map.get(cur)表示当前节点的复制节点，map.get(cur.next)表示当前节点next指向的复制节点

思路2：先原地复制，再原地分离链表 o(n)；o(1) 不使用辅助空间
1. 先原地插入next指针指向的节点
    a. 新建一个节点 Node node = new Node(cur.val)
    b. 新节点指向当前节点的下个节点 node.next = cur.next
    c. 当前节点指向新节点 cur.next = node
    d. 更新当前节点 cur = cur.next.next (后移两位)

2. 再复制random指针
    a. 遍历链表，如果当前节点存在random指针，则当前节点的下一个结点的random指针指向当前节点的random指针的下个节点
    b. 即 cur.next.random = cur.random.next
    c. 当前节点后移 cur = cur.next.next
3. 将链表按照间隔一分为二
    a. 设置两个遍历节点分别表示原链表和复制链表 cur curCopy
    b. 遍历链表，cur.next =  cur.next.next 和 curCopy.next = curCopy.next.next 当前节点指向下下个节点
    c. 更新当前节点到下个节点 cur = cur.next （实际上cur指针后移两位）
 */
public class Solution {
    static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }


    // time: o(n) space: o(n)
    public RandomListNode Clone(RandomListNode head)
    {
        if(head == null) return head;

        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while(cur != null){
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }

    static RandomListNode Clone2(RandomListNode head) {
        if (head == null) {
            return head;
        }
        // 完成链表节点的复制
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode copyNode = new RandomListNode(cur.label);
            copyNode.next = cur.next;
            cur.next = copyNode;
            cur = cur.next.next;
        }

        // 完成链表复制节点的随机指针复制
        cur = head;
        while (cur != null) {
            if (cur.random != null) { // 注意判断原来的节点有没有random指针
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 将链表一分为二
        RandomListNode copyHead = head.next; //返回的新节点
        cur = head;
        RandomListNode curCopy = copyHead;
        while (cur != null) {
            cur.next = cur.next.next;
            cur = cur.next;
            if (curCopy.next != null) {
                curCopy.next = curCopy.next.next;
                curCopy = curCopy.next;
            }
        }
        return copyHead;
    }
    public static void main(String[] args) {

        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        RandomListNode node5 = new RandomListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(Clone2(node1).label);
    }
}
