package LeetCode._0238_Product_of_Array_Except_Self;

import java.util.Arrays;

/*
238. Product of Array Except Self

Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
Note: Please solve it without division and in O(n).
Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
/*
Solution
Approach 1：time:o(n) space:o(n)

1. left[i] denote the product from i-1 to 0
2. right[i] denote the product from i+1 to nums.length-1
3. answer[i] = left[i] * right[i]

Approach 2：time:o(n) space:o(1)

1. the first pass, iterate the nums from left to right
   answer[i] denote the product from i-1 to 0
2. the second pass, iterate the num from right to left
   2.1 when index = i, maintain a variable right denote the product from i+1 to nums.length-1
   2.2 answer[i]=right*answer[i]
       right=right*nums[i]
 */
public class Solution {
    // time:o(n) space:o(n)
    static int[] productExceptSelf1(int[] nums) {
        int[] left = new int[nums.length];
        left[0] = 1;
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        for(int i = 1; i < nums.length; i++){
            left[i] = left[i - 1] * nums[i - 1];
        }
        for(int i = nums.length - 2; i >= 0; i--){
            right[i] = right[i + 1] * nums[i + 1];
        }
        int[] answer = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            answer[i] = left[i] * right[i];
        }

        return answer;
    }

    // time:o(n) space:o(1)
    static int[] productExceptSelf2(int[] nums) {
        int[] answer = new int[nums.length];
        answer[0] = 1;
        for(int i = 1; i < nums.length; i++){
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        int right = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            answer[i] = answer[i] * right;
            right = right * nums[i];
        }

        return answer;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf2(nums)));
    }
}
