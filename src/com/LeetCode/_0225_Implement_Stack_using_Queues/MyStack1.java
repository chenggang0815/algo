package com.LeetCode._0225_Implement_Stack_using_Queues;

import java.util.LinkedList;
import java.util.Queue;

/*
225. Implement Stack using Queues

Implement a last in first out (LIFO) stack using only two queues.
The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).

Implement the MyStack class:
void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.

Notes:
You must use only standard operations of a queue, which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue), as long as you use only a queue's standard operations.


Example 1:
Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
 */
/*
思路1：两个队列实现一个栈
思路1.1
1. 队列是先进先出的规则，把一个队列中的数据导入另一个队列中，数据的顺序并没有变，并有变成先进后出的顺序
2. 所以用栈实现队列， 和用队列实现栈的思路还是不一样的，这取决于这两个数据结构的性质
3. 但是依然还是要用两个队列来模拟栈，只不过没有输入和输出的关系，而是另一个队列完全是又来备份的
4. => 用两个队列que1和que2实现队列的功能，que2其实完全就是一个备份的作用
5. => 把que1最后面的元素以外的元素都备份到que2，然后弹出最后面的元素，再把其他元素从que2导回que1

思路1.2 =>
入栈操作时，首先将元素入队到queue2，然后将queue1的全部元素依次出队并入队到queue2，此时queue2的前端的元素即为新入栈的元素
再将queue1和queue2互换，则queue1的元素即为栈内的元素，queue1的前端和后端分别对应栈顶和栈底


思路2：一个队列实现一个栈
一个队列实现栈：当某一元素入栈队后，将之前(size-1个元素)的出队，再重新入队

相似题目：剑指offer 05 两个栈实现一个队列
 */
public class MyStack1 {
    /** Initialize your data structure here. */
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    public MyStack1() {
         queue1 = new LinkedList<>();
         queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()){
            queue2.offer(queue1.poll());
        }

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        if (queue1.isEmpty()) return true;
        else return false;
    }

    public static void main(String[] args) {
        MyStack1 myStack = new MyStack1();
        myStack.push(1);
        myStack.push(2);
       // System.out.println(myStack.top()); // return 2
        System.out.println(myStack.pop()); // return 2
        System.out.println(myStack.empty()); // return False
    }
}
