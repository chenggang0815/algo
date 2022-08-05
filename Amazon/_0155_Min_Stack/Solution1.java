package Amazon._0155_Min_Stack;

import java.util.Stack;
// stack<int[num, minNum]>
// stack push => 3, 2, 5, 6, 1
// element in stack [3,3], [3,2], [5,2], [6,2], [1,2]
// getMin() => stack.peek()[1]
public class Solution1 {
    Stack<int[]> stack;
    public Solution1() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if(!stack.isEmpty()){
            int[] item = new int[]{val, Math.min(val, stack.peek()[1])};
            stack.push(item);
        }else{
            stack.add(new int[]{val, val});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}
