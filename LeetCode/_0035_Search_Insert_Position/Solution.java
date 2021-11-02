package LeetCode._0035_Search_Insert_Position;
/*
35. Search Insert Position
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.
Example 1:
Input: [1,3,5,6], 5 Output: 2

Example 2:
Input: [1,3,5,6], 2 Output: 1

Example 3:
Input: [1,3,5,6], 7 Output: 4

Example 4:
Input: [1,3,5,6], 0 Output: 0
*/
/*

Solution : Binary Search
 // 1, 3, 5, 6  target=5
 // <  < >= >=
 // find first number => nums[mid] >= target
 // we just need to consider about two situations, nums[mid]>=target and nums[mid]<target

Approach 1: while(left < right) => don't need a res variable, break when left==right, find answer out the loop, left is answer

Approach 2: while(left <= right) => need a res variable, find the answer in the loop

when we find a particular number meet some requirement like
=> nums[mid] == xxx
=> xxx < nums[mid] && nums[mid] < xxx
we use while(left <= right) and need to consider about three situations,
 1. nums[mid] == target
 2. nums[mid] > target
 3. nums[mid] < target
*/
public class Solution {
    //Approach 1
    public int searchInsert1(int[] nums, int target) {
        if(nums[nums.length - 1] < target) return nums.length;

        int left = 0;
        int right = nums.length - 1;
        int res = 0;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target){
                right = mid - 1;
                res = mid;
            }else{
                left = mid + 1;
            }
        }

        return res;
    }
    // Approach 2
    public int searchInsert2(int[] nums, int target) {
        if(nums[nums.length - 1] < target) return nums.length;

        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return right;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int target = 5;
    }
}
