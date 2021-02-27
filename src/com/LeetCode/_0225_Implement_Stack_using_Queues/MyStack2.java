package com.LeetCode._0225_Implement_Stack_using_Queues;

import java.util.LinkedList;
import java.util.Queue;
/*
一个队列实现栈：当某一元素入栈队后，将之前(size-1个元素)的出队，再重新入队

进栈顺序：1 => 2 => 3 => 4
出栈顺序：4 => 3 => 2 => 1

进队列顺序: 1 => 2 => 3 => 4
1进队列 => [1]
2进队列 => [1,2] => 队列的前size-1个元素出队列再进队列(queue.offer(queue.poll());) => [2,1]
3进队列 => [2,1,3] => 队列的前size-1个元素出队列再进队列(queue.offer(queue.poll());) => [3,2,1]
4进队列 => [3,2,1,4] => 队列的前size-1个元素出队列再进队列(queue.offer(queue.poll());) => [4,3,2,1]

出队列顺序: 4 => 3 => 2 => 1
 */
public class MyStack2 {
    /** Initialize your data structure here. */
    Queue<Integer> queue;
    public MyStack2() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        for (int i = 1; i < queue.size(); i++){ // size的大小没有改变 => 有删除也有添加
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        if (queue.isEmpty()) return true;
        else return false;
    }

    public static void main(String[] args) {
        MyStack2 myStack = new MyStack2();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // return 2
        System.out.println(myStack.pop()); // return 2
        System.out.println(myStack.empty()); // return False
    }
}
