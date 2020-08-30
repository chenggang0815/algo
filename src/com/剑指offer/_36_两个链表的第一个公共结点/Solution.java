package com.剑指offer._36_两个链表的第一个公共结点;
import java.util.HashMap;
//输入两个链表，找出它们的第一个公共结点。

class Solution {

    static class node{
        public    int data;
        node next;

        node (){}
        node(int data){
            this.data=data;
        }
    }


    static node head1 = new node();
    static node A = new node(1);
    static node B = new node(2);
    static node C = new node(3);
    static node D = new node(4);

    static node head2 = new node();
    static node E = new node(4);
    static node F = new node(4);

//HashMap
    public static node FindFirstCommonNode(node firstNode1,node firstNode2){

        HashMap hp = new HashMap();
        while(firstNode1 != null){
            hp.put(firstNode1,firstNode1);
            firstNode1 = firstNode1.next;
        }
        while(firstNode2 != null){
            if(hp.containsKey(firstNode2)){
                return firstNode2;
            }
            firstNode2 = firstNode2.next;
        }

        return null;
    }
    
    
    public static void main(String[] args) {

        head1.next=A;
        A.next=B;
        B.next=C;
        C.next=D;

        head2.next=E;
        E.next=F;
        F.next=D;
        //第一个公共结点：D
        System.out.println(FindFirstCommonNode(head1.next,head2.next).data+"");

    }

}




