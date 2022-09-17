package LeetCode._0225_Implement_Stack_using_Queues;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack3 {
    Queue<Integer> queue;
    public MyStack3() {
        queue = new LinkedList<>();
    }

    //push(4) 4
    //push(5) 45 => 54 (stack order)
    //push(3) 543 => 435 => 354 (stack order)
    //push(6) 3546 => 5463 => 4635 => 6354 (stack order)
    //each time, we maintain the current queue is a stack order
    //after push a number, we just move the current queue with same order after the new number
    //so the queue is always stack order, we can just call pop() and peek()
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        while(size > 1){
            queue.add(queue.poll());
            size--;
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
