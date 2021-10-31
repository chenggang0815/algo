package Amazon._0155_Min_Stack;

import java.util.Stack;
// stack<int>
// minStack<int>
// stack push => 3, 2, 5, 6, 1
// element in stack    [3], [2], [5], [6], [1]
// element in minStack [3], [2]            [1]
// getMin() => minStack.peek()
public class Solution2 {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public Solution2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek()){
            minStack.push(val);
        }// 512 -1024 -1024
    }

    public void pop() {
        if(stack.peek().equals(minStack.peek())){ // must use equals not "=="
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
