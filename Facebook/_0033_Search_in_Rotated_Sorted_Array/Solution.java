package Facebook._0033_Search_in_Rotated_Sorted_Array;
/*
33. Search in Rotated Sorted Array

There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.



Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1
*/

/*
solution 1: time: O(nlogn) // leetcode 153 Find_Minimum_in_Rotated_Sorted_Array
1. use binary search find the minimum value, then we get two sorted array
2. use binary search find the target

solution 2: time: O(nlogn)
nums = [4,5,6,7,0,1,2]
if target = 5 target > nums[0] => target in the left side sorted array
use binary search in => [4,5,6,7,inf,inf,inf]

if target = 1 target < nums[0] => target in the right side sorted array
use binary search in => [-inf,-inf,-inf,-inf,0,1,2]

solution 3: time: O(nlogn)
nums = [4,5,6, 7, 0,1,2]
              mid
1. mid = (left + right) / 2 => if nums[mid] >= nums[left] => [left, mid] is sorted
                               else => [mid, right] is sorted
2. use binary search in the sorted array

*/
public class Solution {
    static int findTarget(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[left] <= nums[mid]){ // why <= ?  consider for nums = [3, 1] target = 1 => when left be the first position of nums, we have to make left = mid + 1
                                          // we have to make left = mid + 1, and only in the first condition, we can get left = mid + 1
                if (nums[left] <= target && target < nums[mid]){ // if target == nums[mid], will return above
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if (nums[mid] < target && target <= nums[right]){ // if target == nums[mid], will return above
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) {

    }
}
