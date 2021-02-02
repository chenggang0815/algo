package com.LeetCode._0350_Intersection_of_Two_Arrays_II;

import java.util.ArrayList;
import java.util.Arrays;

/*
350. Intersection of Two Arrays II

Given two arrays, write a function to compute their intersection.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
 */
/*
solution 1: 排序+双指针
1. 先对数组排序
2. 从任意数组开始遍历，需要两个指针，外层数组i=0，内层数组j => 每次从startindex开始遍历，startindex初始化为0
    2.1 对外层数组从i=0开始依次遍历，如果当前nums[i] == nums[j] => 更新startindex => startindex = j + 1
    2.2 优化点（6ms => 2ms）如果当前 if (nums1[i] < nums2[j]) => break => 直接从nums1的下一个数字开始遍历，因为nums[j]右边的数字都大于nums[i]
time: o(mlog(m)+nlog(n)) 遍历的时间复杂度o(m+n)=>没有重复遍历
space: o(nlog(n))

solution2: hash

 */
public class Solution {
    //Runtime: 2 ms, faster than 95.60% of Java online submissions for Intersection of Two Arrays II.
    static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int startIndex = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (arr.size() == 0) startIndex = 0;
            for (int j = startIndex; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    arr.add(nums1[i]);
                    startIndex = j + 1;
                    break;
                }
                if (nums1[i] < nums2[j]) break;
            }
        }
        int[] res = new int[arr.size()];
        for(int i  = 0; i < arr.size(); i++){
            res[i] = arr.get(i);
        }
        return res;
    }

    public static void main(String[] args) {

        intersect(new int[]{1,2,2,1}, new int[]{2,2});

    }
}
