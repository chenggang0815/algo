package com.nowcoder._066;
import java.util.Stack;

/*
栈的压入、弹出序列

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

思路：
1. 借用辅助栈，按照压入顺序将数据压入栈，模拟压入和弹出的操作排列。
2. 依次判断当前栈顶元素与出栈序列的首个元素，如果相等：弹出栈顶元素，出栈序列后移一位（说明模拟的压入和弹出序列匹配）。
3. 遍历完压入数据后，辅助栈为空，返回true
 */
public class Solution {
    // time:o(n) space:o(n)
    static boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for(int i = 0; i < pushA.length; i++){
            stack.add(pushA[i]);
            while(!stack.isEmpty() && stack.peek() == popA[index]){
                stack.pop();
                index++;
            }
        }

        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println(IsPopOrder(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
    }
}
