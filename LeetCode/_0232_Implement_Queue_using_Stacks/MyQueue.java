package LeetCode._0232_Implement_Queue_using_Stacks;

import java.util.Stack;

/*
232. Implement Queue using Stacks

Implement a first in first out (FIFO) queue using only two stacks.
The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:
void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.

Notes:
You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.



Example 1:
Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]
 */

/*
两个栈实现一个队列
思路：
难点：需要考虑队列push和pop交替的情况 => push push push pop push pop
解决办法：
    1. 每次push x前
        1.1 如果stack1为空，执行stack1.push(x)
        1.2 如果stack1不为空，先将stack1中的元素移动到stack2中，再执行stack2.push(x)
        => 保证最新进入的x在stack2最顶端
        => 在出队列的时候就在stack1的最底端
        => 保持最后出队列
    2. 每次pop/peek前，将stack2中的元素移动到stack1中，返回stack1栈顶元素

总结： 每次push保证stack2中是倒序 每次pop/peek保证stack1是正序
eg: push 1 => [1]
    push 2 => [1 2]
    push 3 => [1 2 3]
    push 4 => [1 2 3 4]
    pop   => [2 3 4] 1出队列
    push 5 => [2 3 4 5] 出队列顺序 2,3,4,5

    => 总的出队列顺序：1,2,3,4,5
相似题目：leetcode 225 两个队列实现一个栈

 */
public class MyQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack1.isEmpty()){ // 如果stack1不为空，先将stack1中的元素移动到stack2中，再执行stack2.push(x)
            stack2.push(stack1.pop());
        }
        stack2.push(x); // 每次push保证stack2中是倒序 每次pop/peek保证stack1是正序
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }

        return stack1.pop(); // 每次push保证stack2中是倒序 每次pop/peek保证stack1是正序
    }

    /** Get the front element. */
    public int peek() {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }

        return stack1.peek(); // 每次push保证stack2中是倒序 每次pop/peek保证stack1是正序
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }

        if (stack1.isEmpty()) return true;
        else return false;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false
    }
}
