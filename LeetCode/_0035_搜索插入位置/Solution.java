package LeetCode._0035_搜索插入位置;
/*
搜索插入位置
35. Search Insert Position  Easy
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
public class Solution {
    //time complexity:o(n)
    //space complexity:o(1)
    static int searchInsert(int[] nums, int target) {
        if (nums[0]==target) return 0;
        if (nums[nums.length-1]<target) return nums.length;
        if (nums[nums.length-1]==target) return nums.length-1;

        for (int i=0;i<nums.length-1;i++){
            if (nums[i]==target) return i;
            if (nums[i]<target && target<nums[i+1]) return i+1;
        }

        return -1;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int target = 5;

        System.out.println(searchInsert(nums,target));


    }
}
