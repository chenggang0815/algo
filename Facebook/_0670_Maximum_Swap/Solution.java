package Facebook._0670_Maximum_Swap;
/*
670. Maximum Swap
You are given an integer num. You can swap two digits at most once to get the maximum valued number.
Return the maximum valued number you can get.

Example 1:
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: num = 9973
Output: 9973
Explanation: No swap.

Constraints:
0 <= num <= 10^8
*/
/*
Solution
Approach 1: brute force, time: O(n^2) space:O(n)
1. exchange each two character in the char array, and compare the maximum result

Approach 2: two pointer, time: O(n) space:O(n)
// 9836982
1. we can observe the input, we want to find the a bigger value and a smaller value, and exchange them
2. and these two values must meet,
    2.1 the bigger value is on the right of the smaller value, which means the bigger value is after the smaller value
    2.2 the smaller value is expected on the left most
    2.3 the bigger value is expected on the right most
3. so we can iterate from right to left, record the current maximum value, and if current value < maximum value,
   3.1 update the current value may be the candidate smaller value
*/
public class Solution {
    // brute force
    public int maximumSwap1(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        int res = Integer.parseInt(String.valueOf(nums));
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                char temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                res = Math.max(res, Integer.parseInt(String.valueOf(nums)));
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }

        return res;
    }

    public int maximumSwap2(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        int maxIndex = nums.length - 1;
        int left = 0;
        int right = 0;
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] > nums[maxIndex]){
                maxIndex = i;
            }
            if(nums[i] < nums[maxIndex]){
                left = i; // update the current value to the candidate smaller value
                right = maxIndex; // update the latest maximum value
            }
        }
        char temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

        return Integer.parseInt(String.valueOf(nums));
    }

    public static void main(String[] args) {
    }
}
