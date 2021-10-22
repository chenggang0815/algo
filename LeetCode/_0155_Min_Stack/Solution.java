package LeetCode._0155_Min_Stack;

/*
155. 最小栈
计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

    push(x) —— 将元素 x 推入栈中。
    pop() —— 删除栈顶的元素。
    top() —— 获取栈顶元素。
    getMin() —— 检索栈中的最小元素。

155. Min Stack
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

 */
public class Solution {
    public static class MinStack{
        class ListNode{
            int val;
            ListNode next;
            ListNode(int val){
                this.val=val;
            }
        }
        ListNode head = new ListNode(0);

        public void push(int num){
            ListNode temp = head;
            while (temp.next!=null){
                temp = temp.next;
            }
            ListNode node = new ListNode(num);
            temp.next = node;
        }

        public  void pop(){
            ListNode temp = head;
            if (temp.next.next==null) {
                temp.next=null;
                return;
            }
            while (temp.next.next!=null){
                temp = temp.next;
            }

            temp.next =null;
            return;
        }

        public int top(){
            ListNode temp = head;
            if (temp.next==null) return -1;
            if (temp.next.next==null) return temp.next.val;
            while (temp.next!=null){
                temp=temp.next;
            }
            return temp.val;
        }

        public int getMin(){
            ListNode temp = head;
            if (temp.next==null) return -1;
            int minValue = Integer.MAX_VALUE;
            while (temp.next!=null){
                temp = temp.next;
                if (temp.val <= minValue){
                    minValue = temp.val;
                }
            }
            return minValue;
        }
    }


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   //--> Returns -3.
        minStack.pop();
        System.out.println(minStack.top());    //  --> Returns 0.
        System.out.println(minStack.getMin());  // --> Returns -2.
    }
}
