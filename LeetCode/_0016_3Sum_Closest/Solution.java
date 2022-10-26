package LeetCode._0016_3Sum_Closest;

import java.util.Arrays;

/*
16. 3Sum Closest

Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
Return the sum of the three integers.
You may assume that each input would have exactly one solution.

Example 1:
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Example 2:
Input: nums = [0,0,0], target = 1
Output: 0

Constraints:
3 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-104 <= target <= 104
*/
/*
solution 1:
time O(n^2)
space Complexity: O(log(n)) to O(n), depending on the implementation of the sorting algorithm.

1. what we want is => when sum = a+b+c has the smallest difference with target, return sum
2. so we can maintain two variable, (minDiff, sum)
3. first sort the array, then iterate the array, use two pointers to search the array
4. for example:
Input: nums = [-1,2,1,-4], target = 1

i=0 left=i+1 right=len(nums)-1
sum=nums[i]+nums[left]+nums[right] => if(Math.abs(sum-target) < minDiff) => update minDiff and sum
if(sum > target) right--;
else left++;

return sum
*/
public class Solution {

    // -4 -1 1 2 3 target=1
    // -4 2 3 sum=1 diff=0
    // -4 -1 3 sum=-2 diff=3
    // -4 target= x+-4=1 => x=1-(-4)=5
    // -4 -1 1 2 3 target=5
    // res = [sum, Math.abs(sum - target)]
    public int threeSumClosest(int[] nums, int target) {
        int[] res = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            helper(nums, i, res, target);
        }

        return res[0];
    }

    void helper(int[] nums, int i, int[] res, int target){
        int left = i + 1;
        int right = nums.length - 1;
        while(left < right){
            int sum = nums[i] + nums[left] + nums[right];
            if(Math.abs(sum - target) < res[1]){
                res[0] = sum;
                res[1] = Math.abs(sum - target);
            }
            if(sum > target) right--;
            else left++;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-4, -1, 1, 2};
        //System.out.println(threeSumClosest(nums, 1));
    }
}
