package Facebook._0896_Monotonic_Array;
/*
896. Monotonic Array
An array is monotonic if it is either monotone increasing or monotone decreasing.
An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
Given an integer array nums, return true if the given array is monotonic, or false otherwise.

Example 1:
Input: nums = [1,2,2,3]
Output: true

Example 2:
Input: nums = [6,5,4,4]
Output: true

Example 3:
Input: nums = [1,3,2]
Output: false

Constraints:
1. 1 <= nums.length <= 105
2. -105 <= nums[i] <= 105
*/
/*
Solution
Approach 1: two pass

Approach 2: one pass

*/
public class Solution {
    public boolean isMonotonic(int[] nums) {
        return isIncrease(nums) || isDecrease(nums);
    }

    boolean isIncrease(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] > nums[i + 1]) return false;
        }

        return true;
    }

    boolean isDecrease(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] < nums[i + 1]) return false;
        }

        return true;
    }
    public static void main(String[] args) {

    }
}
