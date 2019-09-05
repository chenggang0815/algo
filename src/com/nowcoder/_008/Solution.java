// 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
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
    public static void addnode(int data){
        node newnode = new node(data);
        node temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }

        temp.next = newnode;
    }

    // 求链表的中间节点，快慢指针
    public static node getMid2(node head){
        node temp1 = head;
        node temp2 = head;
        while (temp1.next!=null && temp1.next.next!=null){
            temp1= temp1.next.next;
            temp2 = temp2.next;
        }
        return temp2;
    }

    // 求链表中间结点，index = (1+n)/2
    public static node getMid1(node head){
        if (head==null || head.next==null){
            return head;
        }
        int n =0;
        node temp = head;
        while (temp!=null){
            temp =temp.next;
            n++;
        }
        node mid = head;
        System.out.println(""+n);
        int index = (1+n)/2;
        System.out.println(""+index);
        for(int i=1;i < index;i++){
            mid = mid.next;
        }
        return mid;
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
        for(int i=10;i< 16;i++){
            addnode(i);
        }
        printL(head.next);
        System.out.println("==================");
        System.out.println(""+getMid1(head.next).data);
    }

}




