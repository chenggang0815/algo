package com.nowcoder._005;
import java.util.Stack;

public class Solution {
//从尾到头打印单链表
    static class node{
     public    int data;
        node next;

        node(int data){
            this.data=data;
        }
    }

    static node head = new node(-1);
    //往链表里添加结点
    public static void addNode(int data){
        node newnode = new node(data);
        node temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }

        temp.next = newnode;
    }

    //利用栈来实现
    static Stack<Integer> stack = new Stack<Integer>();
    public static void printListToTail1(){
        node temp =head;
        if (head.next==null){
            System.out.println("链表为空！");
            return;
        }
        while (temp.next!=null){
            stack.push(temp.next.data);
            temp=temp.next;
        }
        while (!stack.empty()){
            System.out.println(""+stack.pop());
        }
    }
    //利用递归来实现
    public static void printListToTail2(node head){
        if (head != null){
            if (head.next !=null){
                printListToTail2(head.next);
            }
            System.out.println(""+head.data);
        }

    }
    public static void main(String[] args) {
        for(int i=0;i< 5;i++){
            addNode(i);
        }
        printListToTail1();

    }

}




