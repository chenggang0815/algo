import java.util.HashMap;

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


    //删除链表中的重复结点，并且保留一个结点，hashmap
    public static node deleteDuplication(node firstNode){
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
        addnode(head,1);
        addnode(head,3);
        addnode(head,4);
        printL(deleteDuplication(head.next));

    }

}




