public class MyStack {
// 用链表实现栈
    public class node{
          int data;
          node next;

         node(int data){
            this.data = data;
        }
    }

    node head = new node(-1);

    //入栈
    public  void push(int data){
        node newnode = new node(data);
        if (head.next == null){
            head.next = newnode;
            return;
        }

        newnode.next =head.next;
        head.next=newnode;

        }
    //弹出栈顶元素
    public int pop(){
        if (head.next == null){
            System.out.println("stack is empty");
            return -1;
        }
        else {
            head =head.next;
            return head.data;

        }
    }
    // 查看堆栈顶部的对象，但不从堆栈中移除它。
    public int peek(){
        if (head.next == null){
            System.out.println("stack is empty");
            return -1;
        }
        else {
            return head.next.data;
        }

    }


    public static void main(String[] args){
        MyStack stack = new MyStack();
        stack.push(2);
        stack.push(3);
        stack.push(5);
        System.out.println("栈中的第一个元素为:"+stack.peek());
        System.out.println(""+stack.pop());
        System.out.println("栈中的第一个元素为:"+stack.peek());
        //output
        // 栈中的第一个元素为:5
       //5
       // 栈中的第一个元素为:3

    }


}



