package com.LeetCode._0321_拼接最大数;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class Solution {

    static int[] maxNumber(int[] nums1, int[] nums2, int k){
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, n);
        for (int i = start; i <= end; i++){
            int[] subsequence1 = maxSubsequence2(nums1, i);
            int[] subsequence2 = maxSubsequence2(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0){
                System.arraycopy(curMaxSubsequence,0, maxSubsequence,0, k);
            }
        }
        return maxSubsequence;
    }

    static int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }
//3,4,6,5, 0
    static int[] maxSubsequence2(int[] nums, int k) {
        int length = nums.length;
        Stack<Integer> stack = new Stack<>();

        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (remain > 0 && !stack.isEmpty() && stack.peek() < num) {
                stack.pop();
                remain--;
            }
            stack.push(num);
        }


        int[] res = new int[k];
        int index = k;
        int i = 0;
        while (!stack.isEmpty() && i < k){
            res[--index] = stack.pop();
            i++;
        }

        return res;
    }

    static int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    static int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }



    public static void main(String[] args) {
        int[] s1 = new int[]{6,7,5};
        int[] s2 = new int[]{4,8,1};
        //System.out.println(Arrays.toString(merge(s1, s2)));
        //System.out.println(Arrays.toString(maxSubsequence(new int[]{4,5,2,4,1,8}, 3)));
       //System.out.println(Arrays.toString(maxSubsequence2(new int[]{3,4,6,5}, 1)));
        System.out.println(Arrays.toString(maxNumber(s1,s2,3)));

    }
}
