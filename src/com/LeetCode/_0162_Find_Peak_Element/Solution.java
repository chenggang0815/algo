package com.LeetCode._0162_Find_Peak_Element;
/*
162. Find Peak Element

A peak element is an element that is strictly greater than its neighbors.

Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
*/

/*
思路1 ：线性查找 time:o(n) space:o(1)
遍历数组
    1. 如果能找到满足 nums[i] < nums[i + 1] && nums[i + 1] > nums[i + 2] 的元素，return i+1
    2. 如果不能找到，则数组中的最大值作为peek element

思路2 ：线性查找 time:o(n) space:o(1)
思路1的思路可以等价于 <=> 返回第一个大于下一个元素的元素下标

思路3：二分法-递归 time:o(log(n)) space:o(log(n))
因为：
1. 可以将nums数组中的任何给定序列视为交替的升序和降序序列
2. 以及“可以返回任何一个峰作为结果”的要求，
所以：
=> 可以利用二分查找来找到所需的峰值元素

3. 在简单的二分查找中，处理的是一个有序数列，并通过在每一步减少搜索空间来找到所需要的数字。在本例中，需要对二分查找进行一点修改。
    3.1 首先从数组nums中找到中间的元素mid
    => 若该元素恰好位于降序序列或者一个局部下降坡度中（通过将nums[i]与右侧比较判断)，则说明峰值会在本元素的左边。
       于是，我们将搜索空间缩小为mid的左边(包括其本身)，并在左侧子数组上重复上述过程
       if(nums[mid] < nums[mid + 1)) => left = mid + 1 // mid比右侧小,峰顶在右侧[mid+1,r]
    => 若该元素恰好位于升序序列或者一个局部上升坡度中（通过将nums[i]与右侧比较判断)，则说明峰值会在本元素的右边。
       于是，我们将搜索空间缩小为mid的右边，并在右侧子数组上重复上述过程
       if(nums[mid] > nums[mid + 1)) =>  right = mid  // mid比右侧大, 峰顶在左侧或就在mid处

4. 不断地缩小搜索空间，直到搜索空间中只有一个元素，该元素即为峰值元素。

思路4：二分法-迭代 time:o(log(n)) space:o(1)
  */
public class Solution {
    static int findPeakElement1(int[] nums) {
        if (nums.length == 1) return 0;

        for (int i = 0; i < nums.length - 2; i++){
                if (nums[i] < nums[i + 1] && nums[i + 1] > nums[i + 2]){
                    return i + 1;
                }
        }

        int ans = 0;
        int temp = nums[0];
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > temp){
                ans = i;
                temp = nums[i];
            }
        }

        return ans;
    }

    static int findPeakElement2(int[] nums){
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i] > nums[i + 1]){
                return i;
            }
        }

        return nums.length - 1;
    }

    static int findPeakElement3(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    static int search(int[] nums, int left, int right){
        if (left == right) return left;

        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[mid + 1]){
            return search(nums, 0, mid);
        }else{
            return search(nums, mid + 1, right);
        }

    }

/*
    static int findPeakElement4(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right){
            if (left == right) return left;
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }
*/

    static int findPeakElement4(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }


    public static void main(String[] args) {
        System.out.println(findPeakElement4(new int[]{1,2,3,4,5,2,1}));
    }
}
