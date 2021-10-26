package LeetCode._0033_Search_in_Rotated_Sorted_Array;
/*
33. Search in Rotated Sorted Array

You are given an integer array nums sorted in ascending order (with distinct values), and an integer target.

Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

If target is found in the array return its index, otherwise, return -1.

 */
/*
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
Solution:
Approach 1: binary search time:O(log(n)) space:O(1)
1. intuition: the array can be split two sorted array
2. so, the first step, use binary search, we can find the smallest number in the array,
3. [0, ..,minIndex, ... length-1] => then we get two sorted array nums[0:minIndex-1] and nums[minIndex: length-1]
4. if   nums[minIndex] <= target <= nums[length - 1] => return binarySearch(nums, minIndex, length - 1, target)
5. else  return binarySearch(nums, 0, minIndex - 1, target)

Approach 2: binary search one-pass time:O(log(n)) space:O(1)
思路一：
1. 将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。
拿[4,5,6,7,0,1,2]来看，从6这个位置分开以后数组变成了[4,5,6]和[7,0,1,2]两个部分，其中左边[4,5,6]这个部分的数组是有序的，其他也是如此。

2. 这启示我们可以在常规二分搜索的时候查看当前mid为分割位置分割出来的两个部分[l, mid]和[mid + 1, r]哪个部分是有序的，

3. 并根据有序的那个部分确定我们该如何改变二分搜索的上下界 => 因为我们能够根据有序的那部分判断出target在不在这个部分

    3.1 如果[l,mid-1]是有序数组，且target满足nums[left] <= target && target < nums[mid]，则应该将搜索范围缩小至[left, mid-1]，否则在[mid+1,right]中寻找
    3.2 如果[mid, r]是有序数组，且target满足nums[mid] < target && target <= nums[right]，则应该将搜索范围缩小至[mid+1,right]，否则在[left, mid-1]中寻找

time: o(log(n))


 */
public class Solution {
    // 思路1
    static int search(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // |_________|___________|
            // left     pivot      right
            // mid
            // if nums[left] == nums[mid], mid - right is not sorted array, so to make sure our search part is always sorted,
            // we must consider the condition left == mid
            if (nums[left] <= nums[mid]){ //注意细节 =>  nums[0] <= nums[mid] => [3,1] 1
                if (nums[left] <= target && target < nums[mid]){ //注意细节 =>  nums[left] <= target => [1,3,5] 1
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(nums[mid] < target && target <= nums[right]){ //注意细节 =>  nums[left] <= target => [6,3,5] 5
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    //
    static int search2(int[] nums, int target){
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++){
            if (minValue > nums[i]){
                minIndex = i;
                minValue = nums[i];
            }
        }
        // 456 0 1234
        if (target > nums[0]){
            return binarySearch(nums, 0, minIndex - 1, target);
        }else{
            return binarySearch(nums, minIndex, nums.length - 1, target);
        }
    }

    static int searchMinIndex(int[] nums){
        // 3 4 5 6 0 1 2
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid - 1]){
                return mid;
            }else{
                if (nums[mid] < nums[left]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
        }

        return 0;
    }

    static int binarySearch(int[] nums, int left, int right, int target){
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target){ // l mid target r
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(search2(new int[]{3,4,5,1,2}, 4));
        System.out.println(searchMinIndex(new int[]{3,4,5,1,2}));
    }
}
