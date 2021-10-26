package Amazon._0907_Sum_of_Subarray_Minimums;

import java.util.Arrays;
import java.util.Stack;

/*

*/
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{5,4,3,1,2,4,5};
        Stack<Integer> stack = new Stack<>();

//        for(int i = 0; i < nums.length; i++){
//            while(!stack.empty() && stack.peek() > nums[i]){
//                stack.pop();
//            }
//            stack.push(nums[i]);
//        }
//        System.out.println(stack);

        // previous_less[i] = j means A[j] is the previous less element of A[i].
        // previous_less[i] = -1 means there is no previous less element of A[i].
        int[] previous_less = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            while(!stack.empty() && nums[stack.peek()] > nums[i]){
                stack.pop();
            }
            previous_less[i] = stack.isEmpty() ? -1: stack.peek();
            stack.push(i);
        }

        System.out.println(Arrays.toString(previous_less));
        System.out.println(stack);

//        // next less [5,4,3,1,2,4,5]
//        int[] next_less = new int[nums.length];
//
//        for(int i = 0; i < nums.length; i++){
//            while(!stack.empty() && nums[stack.peek()] > nums[i]){
//                int x = stack.pop();
//                next_less[x] = i;
//            }
//            stack.push(i);
//        }
//
//        System.out.println(Arrays.toString(next_less));
//        System.out.println(stack);
    }
}
