package com.剑指offer._15_反转链表;
// 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。

public class Solution {
    static class node{
        public    int data;
        node next;

        node (){
        }
        node(int data){
            this.data=data;
        }
    }

    static node head = new node();
    //往链表里添加结点
    public static void addnode(int data){
        node newnode = new node(data);
        node temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }

        temp.next = newnode;
    }

    // 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
    public static node reverseList2(node head){
        node reHeadnode = null;
        node curnode = head;
        node prenode = null;
        while (curnode !=null){
            node nextnode = curnode.next;
            if (curnode.next == null){
                reHeadnode = curnode;
            }

            curnode.next = prenode;
            prenode = curnode;
            curnode = nextnode;

        }

        return  reHeadnode;
    }

    static public node reverse(node head){
        if (head==null || head.next==null) return head;
        node cur = head;
        node pre = null;
        while (cur!=null){
            node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    //递归法
    private static node reverseList1(node head){
        if (head ==null || head.next ==null){
            return head;
        }
        node temp = head.next;
        node newhead =reverseList1(head.next);
        temp.next =head;
        head.next =null;
        return newhead;
    }

    //打印链表
    public static void printL(node head){
        if (head==null){
            System.out.println("链表为空");
        }
        node temp = head;
        while (temp!=null){
            System.out.println(""+temp.data);
            temp=temp.next;
        }

    }

    public static void main(String[] args) {
        for(int i=10;i< 15;i++){
            addnode(i);
        }
		
        printL(head.next);
        System.out.println("========reverse 1==========");
        //printL(reverseList1(head.next));
        System.out.println("========reverse 2==========");
        printL(reverse(head.next));


    }

}




