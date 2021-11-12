package LeetCode._0032_Longest_Valid_Parentheses;
/*
32. Longest Valid Parentheses
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.


Example 1:
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

Example 2:
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

Example 3:
Input: s = ""
Output: 0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Approach 1：动态规划
1. dp[i] 表示以下标 i 字符结尾的最长有效括号的长度
2. if nums[i]='(' => 不可能是最长有效括号的子串
3. if nums[i]=')'
    3.1 nums[i-1]='(' => 根据2，（nums[i-1], nums[i]）肯定组成一对有效括号,且不影响i-2之前的组合 => dp[i] = dp[i-2] + 2
    3.2 nums[i-1]=')' => 需要考虑i-1之前的有效括号有多长 eg: (      )           (        (()      )    )
                                                          i-dp[i-1]-2     i-dp[i-1]-1        i-1  i
                                                       dp[i-dp[i-1]-2]=2                  dp[i-1]=4
                                                       dp[i] = 2 + 4 + 2
                dp[i-1]=4 => if(nums[i - dp[i-1] - 1]) => if(nums[i-5] == '(') => dp[i]=dp[i-1]+dp[i-dp[i-1]-2]+2
    3.3 还需要判断边界    i - dp[i - 1] > 0 和 i - dp[i - 1] > 2

Approach 2：stack
1. 先利用栈找到所有可以匹配的括号的索引，然后再对索引求最长连续子序列的长度
eg:      (          )             (          )              (          (           )
      push(0)    pop()        push(2)      pop()           push(4)   push(5)      pop()
arr             arr.add(peek())          arr.add(peek())                        arr.add(peek())
                arr.add(1)               arr.add(3)                             arr.add(6)

2. Array.sort(arr) => arr = [0,1,2,3,5,6]

3. => return 4
 */
public class Solution {
    public int longestValidParentheses(String s) {
        List<Integer> arr = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') stack.push(i);
            if(!stack.isEmpty() && s.charAt(i) == ')'){
                arr.add(stack.peek());
                arr.add(i);
                stack.pop();
            }
        }
        // edge case => s=""
        if(arr.size() == 0) return 0;

        arr.sort((a, b) -> a - b);
        int res = 0;
        int cnt = 1;
        // arr= 123 5678
        for(int i = 0; i < arr.size() - 1; i++){
            if(arr.get(i) + 1 == arr.get(i + 1)){
                cnt++;
            }else{
                res = Math.max(res, cnt);
                cnt = 1;
            }
        }

        return Math.max(res, cnt);
    }

    static int longestValidParentheses1(String s) {
        int res =0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == ')'){
                if (s.charAt(i - 1) == '('){
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                }else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
                    if (i - dp[i - 1] - 2 > 0) dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                    else dp[i] = dp[i - 1] + 2;
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    static int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            }else if (s.charAt(i) == ')' && !stack.isEmpty()){
                arr.add(stack.peek());
                arr.add(i);
                stack.pop();
            }
        }
        int[] nums = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++){
            nums[i] = arr.get(i);
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        //System.out.println(maxNums(arr));
        return maxNums(nums);
    }

    static int maxNums(int[] nums){
        if (nums.length <= 1) return nums.length;
        int[] dp = new int[nums.length];
        int res = 0;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i - 1] + 1){
                dp[i] = dp[i - 1] + 1;
            }else dp[i] = 1;

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses2("()()()"));
        //System.out.println(maxNums(new int[] {1,2,4,5,6,8}));
    }
}
