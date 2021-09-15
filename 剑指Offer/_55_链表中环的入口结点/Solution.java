package 剑指offer._55_链表中环的入口结点;
import java.util.HashMap;
//给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

class Solution {

    static class node{
        public    int data;
        node next;

        node (){}
        node(int data){
            this.data=data;
        }
    }


    static node head = new node();
    static node A = new node(1);
    static node B = new node(2);
    static node C = new node(3);
    static node D = new node(4);

//给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。 HashMap
    public static node entryNodeOfLoop1(node firstNode){

        if (firstNode == null || firstNode.next == null) return null;

        HashMap hp = new HashMap();
        while (firstNode.next!=null){
            hp.put(firstNode,firstNode.data);
            firstNode = firstNode.next;
            if (hp.containsKey(firstNode)){
                return firstNode;
            }
        }
        return null;
    }


    //给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。 快慢指针
    public static node entryNodeOfLoop2(node firstNode) {

        if (firstNode == null || firstNode.next == null) return null;
        node slow = firstNode;
        node fast = firstNode;
        while (slow.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = firstNode;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;

    }

    public static void main(String[] args) {
        head.next=A;
        A.next=B;
        B.next=C;
        C.next=D;
        D.next=C;
        System.out.println(entryNodeOfLoop2(head.next).data+"");

    }

}




