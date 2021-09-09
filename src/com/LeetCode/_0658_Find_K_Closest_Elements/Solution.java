package com.LeetCode._0658_Find_K_Closest_Elements;

import java.util.ArrayList;
import java.util.List;

/*
658. Find K Closest Elements

Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
An integer a is closer to x than an integer b if:
|a - x| < |b - x|, or
|a - x| == |b - x| and a < b

Example 1:
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]

Example 2:
Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]

Constraints:
1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
*/

/*
solution 1: two pointers

solution 2:

solution 3:

 */
public class Solution {
    //
    static List<Integer> findKClosestElement1(int[] arr, int k, int x){
        // 1   2   3   4   5
        // l               r  => r - l = 4
        // l           r => r - l = 3
        int left = 0;
        int right = arr.length - 1;
        while (right - left >= k){
            if (Math.abs(arr[right] - x) >= Math.abs(x - arr[left])){
                right--;
            }else{
                left++;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++){
            result.add(arr[i]);
        }

        return result;
    }

    // 通过二分查找，一定会找到一个区间，且x再这个区间里面。这时，通过判断x-arr[left]和arr[right]-x的大小。来判断区间应该往那边收缩。 最后找到left。
    static List<Integer> findKClosestElement2(int[] arr, int k, int x){
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x){
                left = mid + 1;
            }
            else right = mid;
        }
        List<Integer> result = new ArrayList<>();

        for(int i = left; i < left + k; i++){
            result.add(arr[i]);
        }

        return result;
    }


    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        int k = 4;
        int x = 3;
        System.out.println(findKClosestElement1(array, k, x));
    }
}
