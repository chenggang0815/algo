// 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
class Solution {
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

    // 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
    public static node reverseList(node head){
        node reHeadNode = null;
        node curNode = head;
        node preNode = null;
        while (curNode !=null){
            node nextnode = curNode.next;
            if (curNode.next == null){
                reHeadNode = curNode;
            }

            curNode.next = preNode;
            preNode = curNode;
            curNode = nextnode;

        }
        return  reHeadNode;
    }

    //tolist 递归法

    public static void main(String[] args) {
        for(int i=0;i< 5;i++){
            addNode(i);
        }

        System.out.println(reverseList(head).data+"");

    }

}