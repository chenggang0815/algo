package LeetCode._0503_Next_Greater_Element_II;

import java.util.Arrays;
import java.util.Stack;

/*
503. Next Greater Element II

Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

Example 1:
Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.

Example 2:
Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]


Constraints:
1 <= nums.length <= 104
-109 <= nums[i] <= 109


solution:
        Loop once, we can get the Next Greater Number of a normal array.
        Loop twice, we can get the Next Greater Number of a circular array
time: O(n)
space: O(n)

 */
public class Solution {
    static int[] nextGreaterElements(int[] nums) {
        if(nums.length == 0) return new int[]{};

        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < 2 * nums.length; i++){
            int index = i % nums.length;
            while(!stack.isEmpty() && nums[index] > nums[stack.peek()]){
                if(stack.peek() < nums.length){
                    res[stack.peek()] = nums[index];
                }
                stack.pop();
            }
            stack.push(index);
        }

        return res;
    }

    // [1,2,3,4,3,2]
    // [1] 1 < 2 => [2] => <1,2>
    // [2] 2 < 3 => [3] => <2,3>
    // [3] 3 < 4 => [4] => <3,4>
    // [4] 4 > 3 => [4,3]
    // [4,3] 3 > 2 => [4,3,2]
    // stack=[4,3,2] [1,2,3,4,3,2]
    // i=8%6=2 nums[i]=3 => the next greater element for 2 is 3,
    // because this is the second pass, we don't  need to add 3 in the stack to avoid duplicate computation.
    // 1 2 3 4 3  2 len=6
    // 0          5
    // 6 7 8 9 10 11
    // 6%6        11%6
    // 0 <= i < 2*len
    //
    public int[] nextGreaterElements2(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < 2 * nums.length; i++){
            if(i < nums.length){
                while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                    res[stack.pop()] = nums[i];
                }
                stack.push(i);
            }else{
                int j = i % nums.length;
                while(!stack.isEmpty() && nums[stack.peek()] < nums[j]){
                    res[stack.pop()] = nums[j];
                }
                //stack.push(j);
                // because this is the second pass, we don't  need to add 3 in the stack to avoid duplicate computation.
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] res = nextGreaterElements(new int[]{1,2,1});
        System.out.println(Arrays.toString(res));
    }
}
