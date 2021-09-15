package 剑指offer._05_两个栈实现一个队列;
import java.util.Stack;
// 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

public class Solution {
    private  Stack<Integer> stack1 = new Stack<>();
    private  Stack<Integer> stack2 = new Stack<>();

    private void push(int data){
        stack1.push(data);
    }

    private int pop(){
        if(stack1.empty()&&stack2.empty()){
            return -1;
        }
        else {
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        Solution s =new Solution();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(""+s.pop());
        s.push(4);
        System.out.println(""+s.pop());
        System.out.println(""+s.pop());
        System.out.println(""+s.pop());
        // 正确出队列顺序 1，2,3,4

    }
}
