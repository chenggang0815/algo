package Amazon._0155_Min_Stack;

import java.util.Stack;

public class Solution3 {
    Stack<Integer> stack;
    Stack<int[]> minStack;
    public Solution3() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val < minStack.peek()[0]){
            minStack.push(new int[]{val, 1});
        }else if(val == minStack.peek()[0]){
            minStack.peek()[1]++;
        }
    }

    public void pop() {
        if(stack.peek().equals(minStack.peek()[0])){
            if(minStack.peek()[1] == 1) minStack.pop();
            else minStack.peek()[1]--;
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek()[0];
    }
}
