package LeetCode._0081_Search_in_Rotated_Sorted_Array_II;
/*
81. Search in Rotated Sorted Array II

There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Constraints:
1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
*/
/*
Solution binary search
Approach 1
case 1 => num[mid] == target, then we find it.
case 2 => nums[left] == nums[mid], we can not make sure which part is sorted, the only thing we can do is scale in the problem size.
                                   Since target != nums[mid] == nums[left], we just offset right by left++.
        // |_______|____|______|      => left - mid is sorted
        // left  mid   pivot  right
        // |_____|______|_________|   => mid - right is sorted
        // left pivot  mid     right
        assume left == mid == right, so we can not make sure which pa

case 3 => nums[left] < nums[mid], then the left - mid part must be sorted
        // |____|______|____|________|
        // left target mid  pivot   right
case 4 => nums[left] > nums[mid], then the mid - right part must be sorted;
        // |_____|_______|____|________|
        // left  pivot  mid  target   right

Approach 2
To avoid duplicates, we can refer to the solution to Problem 15: 3 Sum, which is

      while (lo < hi && nums[lo] == nums[lo + 1])
        ++lo;
      while (lo < hi && nums[hi] == nums[hi - 1])
        --hi;

After this step, this problem becomes Problem 33: Search in Rotated Sorted Array.
*/
public class Solution {
    public boolean search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return true;
            if(nums[mid] > nums[left]){
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else if(nums[mid] == nums[left]){
                left++;
            }else{
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    public boolean search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            while (left <= right && nums[left] == nums[left + 1]){
                left++;
            }
            while (left <= right && nums[right] == nums[right - 1]){
                right--;
            }


            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;

            // |_________|___________|
            // left     pivot      right
            // mid
            // if nums[left] == nums[mid], mid - right is not sorted array, so to make sure our search part is always sorted,
            // we must consider the condition left == mid
            if (nums[left] <= nums[mid]){
                // |_____mid____|___________|
                // left     pivot      right
                if (target >= nums[left] && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
                // |_________|_____mid______|
                // left     pivot      right
            }else{
                if (target <= nums[right] && target > nums[mid]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
