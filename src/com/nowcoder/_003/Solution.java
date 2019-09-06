package com.nowcoder._003;

public class Solution {

    static public class node{
        public node next;
        public int data;

        public node(int data){
            this.data=data;
        }
    }

    static node head = new node(-1);

    // 添加节点
    public static void addnode(int data){
        node newnode = new node(data);
        node temp = head;
        while (temp.next != null){
            temp=temp.next;
        }

        temp.next =newnode;
    }

    //打印链表
    public static void printList(){
        node temp = head;
        while (temp!=null){
            System.out.println(""+temp.data);
            temp=temp.next;
        }
    }

    //得到链表长度
    public static int getLength(){
        int length =0;
        node temp =head;
        while (temp.next!=null){
            length++;
            temp=temp.next;
        }
        return length;
    }

    //在指定位置（前）插入节点
    public static void interNodeByIndex(int data,int index){
        if (index <1 || index > getLength()){
            System.out.println("插入位置不合法");
        }

        int len =1;
        node newnode = new node(data);
        node temp = head;
        while (temp.next!=null){
            if (index==len++){
                newnode.next=temp.next;
                temp.next=newnode;
                return;
            }
            temp=temp.next;

        }
    }
	
	
    //删除指定位置的结点
    public static void deleteNodeByIndex(int index){
        if (index <1 || index > getLength()){
            System.out.println("删除位置不合法");
        }

        node temp = head;
        int len =1;
        while (temp.next !=null){
            if (index == len++){
                temp.next = temp.next.next;
                temp=temp.next;
                return;

            }
            temp =temp.next;
        }
    }

    // 单链表倒数第k个结点查找和显示(遍历两次)
    public static void findKthToTail_1(int k){
        if (k<1 || k > getLength()){
            System.out.println("查找的位置不合法");
        }
        node temp =head;
        int len=1;
        int index = getLength()-k+1;
        while (temp.next!=null){
            if (index == len++){
                System.out.println("倒数第k个结点为:"+temp.next.data);
            }
            temp=temp.next;
        }
    }

    // 单链表倒数第k个结点查找和显示(遍历一次),两个指针a，b；保持b指针不变，当a从头开始遍历到k时，b开始遍历，当a遍历到结尾时，b刚好遍历到倒数第k个结点。
    public static void findKthTotial_2(int k){
        if (k<1 || k > getLength()){
            System.out.println("查找的位置不合法");
        }

        node a = head;
        node b= head;
        for(int i=1; i<k;i++){
            a=a.next;
        }

        while (a.next!=null){
            a=a.next;
            b=b.next;
        }

        System.out.println("倒数第k个结点为:"+b.data);
    }


    public static void main(String[] args) {

        addnode(5);
        addnode(4);
        interNodeByIndex(33,1);
        interNodeByIndex(44,3);
        printList();
        System.out.println(getLength()+"");

        


}
}
