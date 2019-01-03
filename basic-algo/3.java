
public class LinkedList {

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

    public static void main(String[] args) {

        addnode(5);
        addnode(4);
        interNodeByIndex(33,1);
        interNodeByIndex(44,3);
        printList();
        System.out.println(getLength()+"");

        


}
}
