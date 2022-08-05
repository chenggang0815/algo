package LeetCode._0259_3Sum_Smaller;
/*
259. 3Sum Smaller
Given an array of n integers nums and an integer target,
find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example 1:
Input: nums = [-2,0,1,3], target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
[-2,0,1]
[-2,0,3]

Example 2:
Input: nums = [], target = 0
Output: 0

Example 3:
Input: nums = [0], target = 0
Output: 0
*/

import java.util.Arrays;

/*
solution 1: binary search time:O(n^2log(n)) space:O(1)

solution 2: two pointers time:O(n^2)) space:O(1)

for example:
Input: nums = [7,3,2,1,4,5,6], target = 20

sort => [1,2,3,4,5,6,7]
iterate the array, for nums[i], we can search the two index(left, right) in the [i+1, len(array) - 1] => nums[left] + nums[right] < target - nums[i]
res = res + (right - left + 1)

i = 0 target = 7 left = i+1=1 right = len(arr) - 1 = 6
[2,  3,4,5,6, 7] target = 19
left         right
nums[left] + nums[right] < 19  => (2,3) (2,4) (2,5) (2,6) (2,7) = > (right - left) = (6-1)=5
left++
[2, 3,  4,5,6, 7] target = 19
   left       right
nums[left] + nums[right] < 19  => (3,4) (3,5) (3,6) (3,7) = > (right - left) = (6-2) = 4

 */
public class Solution {
    static int threeSumSmaller(int[] nums, int target){
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            res += twoPointers(nums, i, target - nums[i]);
        }

        return res;
    }

    static int twoPointers(int[] nums, int i, int target){
        int left = i + 1;
        int right = nums.length - 1;
        int res = 0;
        while (left < right){
            int sum = nums[left] + nums[right];
            if (sum >= target){
                right--;
            }else{
                res += right - left + 1;
                left++;
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
