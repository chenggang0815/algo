package LeetCode._0153_Find_Minimum_in_Rotated_Sorted_Array;
/*
153. Find Minimum in Rotated Sorted Array
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums, return the minimum element of this array.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times.

Constraints:
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times.
*/

/*
Solution:
Approach 1: Binary Search
nums = [5  6  7  8  1  2  3  4]
        5 <  <  <   >  >  >  >
1. the most important for binary search is to find the pattern can divided two search space
2. all the number on the left of the minimum number is bigger than nums[0]
3. all the number on the right of the minimum number is small than nums[0]
4. so, we need to find the number which is small than nums[0]
if nums[0] < nums[mid] => right = mid

Approach 2: Binary Search

*/

public class Solution {
    static int findMin(int[] nums) {
        if(nums[0] < nums[nums.length - 1]) return nums[0];

        int left = 0;
        int right = nums.length -1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[0] > nums[mid]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return nums[left];
    }

    public int findMin2(int[] nums) {
        if(nums[0] < nums[nums.length - 1]) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        int res = 0;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[0] > nums[mid]){
                right = mid - 1;
                res = mid;
            }else{
                left = mid + 1;
            }
        }

        return nums[res];
    }

    // similar to question 852, but the nums.length >=1, so this solution is not work
    public int findMin3(int[] nums) {
        if(nums[0] < nums[nums.length - 1]) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) return nums[mid]; // may be out of index
            if(nums[0] > nums[mid]){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{7,0,1}));
    }
}
