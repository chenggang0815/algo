package com.nowcoder._002;
import java.util.Stack;

public class Solution {
        private  Stack<Integer> stack1 = new Stack<Integer>();
        private  Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int data){
            stack1.push(data);
        }

        public int pop(){
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
        s.push(2);
        s.push(4);
        s.push(8);

        System.out.println(""+s.pop());
        System.out.println(""+s.pop());
        System.out.println(""+s.pop());
        System.out.println(""+s.pop());

    }
}
