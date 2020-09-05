package com.LeetCode._0026_删除排序数组中的重复项;

/*
26. Remove Duplicates from Sorted Array Easy 删除排序数组中的重复项

给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组 并在使用O(1)额外空间的条件下完成。


Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.

Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.

Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}

思路：

1. 双指针，nums[right] != nums[left] 时， left++(left右移一位)，将nums[right]赋值给nums[right]， right++
2. 最后nums中不重复的数字有left + 1 个 [0, left + 1]

 */
public class Solution {

    // time complexity: o(n)
    // space complexity:o(1)
    static public int removeDuplicates(int[] nums) {
        int left = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] != nums[left]){
                left++;
                nums[left] = nums[i];
            }
        }

        return left + 1;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int length = removeDuplicates(nums);
        System.out.println(length);
        for (int i=0;i<length;i++){
            System.out.println(nums[i]);
        }
    }
}
