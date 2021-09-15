package 剑指Offer._20_包含min函数的栈;

import java.util.Stack;

/*
定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 */


/*
利用一个辅助栈min。

1. 每次主栈push一个value时，比较value和min栈顶元素的大小，如果小于min的栈顶元素，则将value压入min栈。
2. 每次主栈pop一个value时，比较value和min栈顶元素的大小，如果相等则同时pop主栈和min。如果不等只pop主栈。

以上两步可以保证，min栈顶元素一直是主栈中的最小值。

eg：2314

主：2    23  231  2314 231  23
min：2   2   21   21   21   2
 */
public class Solution {

    static Stack<Integer> stack = new Stack<>();
    static Stack<Integer> min = new Stack<>();

    static void push(int val){
        stack.push(val);
        if (min.isEmpty()){
            min.push(val);
        }else if (val <= min.peek()){
            min.push(val);
        }
    }

    static void pop(){
        if (stack.peek() == min.peek()){
            stack.pop();
            min.pop();
        }else{
            stack.pop();
        }
    }

    static int top(){
        return stack.peek();
    }

    static int min(){
        return min.peek();
    }

    public static void main(String[] args) {
        push(5);
        push(6);
        push(4);
        push(2);
        push(1);
        push(8);
        System.out.println(min());


    }
}
