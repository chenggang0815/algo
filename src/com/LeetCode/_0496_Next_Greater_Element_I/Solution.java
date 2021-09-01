package com.LeetCode._0496_Next_Greater_Element_I;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
496. Next Greater Element I

The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.

1. 暴力解法 time: o(n^2)
2. 单调栈 time: o(n)
如果需要找到左边或者右边第一个比当前位置的数大或者小，则可以考虑使用单调栈
 => 找出nums2中，每个元素右边第一个比它大的数；把结果放在map中
 => 因为nums1是nums2的子集，所以遍历nums1，寻找map中是否有结果
参考739题
*/
public class Solution {
    static public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            int index = 0;
            while(index < nums2.length && nums1[i] != nums2[index]){
                index++;
            }
            for(int j = index + 1; j < nums2.length; j++){
                if(nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
            if(res[i] == 0) res[i] = -1;
        }

        return res;
    }

    static public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums2){
            while (!stack.isEmpty() && num > stack.peek()){
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++){
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{4,1,2};
        int[] nums2 = new int[]{1,3,4,2};
        int[] res = nextGreaterElement2(nums1, nums2);
        for (int num: res){
            System.out.println(num);
        }
    }
}
