package 剑指offer._56_删除链表中重复的结点;
import java.util.HashMap;
//在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
//例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

class Solution {
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


//在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
    public static node deleteDuplication1(node firstNode){

        if (firstNode == null || firstNode.next == null) { // 只有0个或1个结点，则返回
            return firstNode;
        }
        if (firstNode.next.data == firstNode.data){
            node current=firstNode.next;
            while (current !=null && current.data==firstNode.data){
                current = current.next;
            }
            return deleteDuplication1(current);
        }
        else {
            firstNode.next = deleteDuplication1(firstNode.next);

            return firstNode;
        }
    }



    //删除链表中的重复结点，并且保留一个结点，hashmap
    public static node deleteDuplication2(node firstNode){
        HashMap hp =new HashMap();
        node temp =firstNode;
        while (temp.next!=null){

            hp.put(temp.data,temp.data);
            node next_node = temp.next;
            if (next_node.next!=null && hp.containsKey(next_node.data)){
                temp.next=temp.next.next;
            }
            else if (next_node.next==null && hp.containsKey(next_node.data)){
                temp.next = null;
                return firstNode ;
            }
            if (temp.next == null){
                return firstNode;
            }
            else {
                temp = temp.next;
            }
        }
        return firstNode;
    }





    public static void main(String[] args) {

        addnode(head,1);
        addnode(head,2);
        addnode(head,2);
        addnode(head,4);
        addnode(head,3);
        printL(deleteDuplication1(head.next));

    }

}




