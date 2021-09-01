package com.LeetCode._0739_Daily_Temperatures;

import java.util.Arrays;
import java.util.Stack;

/*
739. Daily Temperatures
Given a list of daily temperatures T, return a list such that,
for each day in the input,
tells you how many days you would have to wait until a warmer temperature.
If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000].
Each temperature will be an integer in the range [30, 100].
 */

/*
思路1 暴力法 time:o(n^2) space:o(n)

思路2 单调栈 time:o(n) space:o(n)

如果需要找到左边或者右边第一个比当前位置的数大或者小，则可以考虑使用单调栈
0. 栈里寸每个元素的index
1. 保证栈里的元素，从栈底到栈顶是依次减小的，
    1.1 如果当前元素比栈顶元素大，则当前元素是第一个大于栈顶元素的，栈顶元素出栈，当前元素继续和栈顶元素比较大小，直到当前元素不大于栈顶元素或者栈为空
2. 当前元素进栈
3. 遍历完数组，栈里剩下的元素都是没有找到比它更大的了

参考496题
 */
public class Solution {
    static int[] dailyTemperatures1(int[] T) {
        int len = T.length;
        if (len == 0) return new int[]{};

        int[] res = new int[len];

        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                if (T[j] > T[i]){
                    res[i] = j - i;
                    break;
                }
            }
        }

        return res;
    }

    // Runtime: 17 ms, faster than 55.61% of Java online submissions for Daily Temperatures.
    static int[] dailyTemperatures2(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];

        for (int i = 0; i < T.length; i++){
            while (!stack.isEmpty() && T[i] > T[stack.peek()]){
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        /*
        感觉代码里再加点说明更好些。
        这样写，在循环结束后，栈中有可能还剩部分元素。
        但是这部分元素其实是递减的，也就是说后面没有哪天温度更高了。因此，他们的结果为0。
        由于结果列表中，初始化的值就是0，那这部分也就不用处理了。所以代码可以省略。 但是这个情况说明出来，有益于后面单调栈的题目的解答。
         */
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures2(nums)));
    }
}
