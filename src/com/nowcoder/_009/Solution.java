package com.nowcoder._009;
//合并两个有序的单链表

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
    // 初始化两个头结点
    static node head1 = new node();
    static node head2 = new node();
    //往链表里添加结点
    public static void addnode(node head, int data){
        node newnode = new node(data);
        node temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }

        temp.next = newnode;
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

/*
    bigo面试:
    输入链表:1->2->3->4->5->6->7
    输出链表:1->7->2->6->3->5->4
    时间复杂度o(n) 空间复杂度o(1)

 */
    static void merge(node head){
        if (head == null) return;

        node cur = head;
        int length = 1;
        while(cur.next != null){
            cur = cur.next;
            length++;
        }

        int midnode = length/2 + 1;
        int index = 0;
        node head1 = new node(0);
        while (cur.next != null && index != midnode){
            cur = cur.next;
            head1.next = cur;
            index++;
        }

        while(head != null && head1 != null){
             head.next = head1;
             head1 = head1.next;
             head = head.next;
        }

    }

    // 合并链表 非递归
    public static node mergeList(node head1,node head2){
        node temp1 = head1;
        node temp2 = head2; //不改变原来的两个链表

        node newhead = new node();
        node temp =newhead;

        while (temp1!=null && temp2!=null) {
            if (temp1.data < temp2.data) {
                temp.next = temp1;
                temp1= temp1.next;
            } else {
                temp.next = temp2;
                temp2= temp2.next;
            }
            temp =temp.next;
        }

        if (temp1!=null){
            temp.next = temp1;
        }
        if (temp2!=null){
            temp.next = temp2;
        }

        return newhead;
    }
	
	    // 合并链表 递归
    public static node mergeList2(node head1,node head2){
        node temp1 = head1;
        node temp2 = head2;
        node newhead = new node();

        if (temp1 == null) return temp2;
        if (temp2 == null) return temp1;

        if (temp1.data < temp2.data){
            newhead=temp1;
            newhead.next=mergeList2(temp1.next,temp2);
        }
        else {
            newhead=temp2;
            newhead.next=mergeList2(temp1,temp2.next);
        }
        return newhead;
    }

    public static void main(String[] args) {

        addnode(head1,1);
        addnode(head1,3);
        //addnode(head1,5);

        addnode(head2,5);
        //addnode(head2,4);
        //addnode(head2,5);


        printL(head1.next);
        System.out.println("==================");
        printL(head2.next);
        System.out.println("==========合并链表===========");
        printL(mergeList(head1.next,head2.next).next);
    }

}




