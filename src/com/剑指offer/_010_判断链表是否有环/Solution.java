package com.剑指offer._010_判断链表是否有环;
import java.util.HashMap;
//判断链表是否有环（循环遍历，hash map，快慢指针）

class Solution {
    static class node{
        int data;
        node next;

        node(){}
        node(int data){
            this.data = data;
        }
    }
    static node head = new node();
    static node node1 = new node(1);
    static node node2 = new node(2);
    static node node3 = new node(3);
    static node node4 = new node(4);

    //往链表里添加结点
    public static void addnode(node head, int data){
        node newnode = new node(data);
        node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        temp.next = newnode;
    }

    //打印链表
    public static void printL(node head){
        if (head == null){
            System.out.println("链表为空");
        }
        node temp = head;
        while (temp != null){
            System.out.println("" + temp.data);
            temp = temp.next;
        }

    }

    //判断链表是否有环，循环遍历
    public static boolean isRingByLoop(node head){
        if (head == null) return false;

        node temp = head;
        while (temp.next != null){
            node t = head;
            temp =temp.next;
            while (t != temp){
                if (t.next == temp.next) return true;
                t = t.next;
            }
        }
        return false;
    }

    //判断链表是否有环，hashmap
    public static boolean isRingByHashMap(node head){
        node temp = head;
        HashMap<node, node> map = new HashMap<>();
        while (temp != null){
            map.put(temp, temp);
            temp = temp.next;
            if (map.get(temp) != null) return true;
        }

        return false;
    }

    //判断链表是否有环，快慢指针
    public static boolean isRingByPointer(node head){
        if (head == null) return false;
        node fast = head;
        node slow = head;
        while (fast.next.next != null && slow.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast){
                return true;
            }
        }

        return false;
    }
    

    public static void main(String[] args) {

        head.next =node1;
        node1.next =node2;
        node2.next=node3;
        node3.next =node4;
        node4.next =node2;

        System.out.println(isRingByPointer(head.next));

    }

}




