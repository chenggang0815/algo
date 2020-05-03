package com.leetcode._0037;
/*
69. Sqrt(x) Easy

Implement int sqrt(int x).
Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:
Input: 4
Output: 2

Example 2:
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.

 */
public class Solution {

    //I think this problem's key point is that we want to find the largest number, which num*num <= x,
    // therefore we should use the binary search to find the upper bound within the range(1,x).
    // time: o(log(n))
    //space: o(1)
    static public int mySqrt(int x) {
        int left = 1;
        int right = x;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(mid == x/mid) return mid;
            else if(mid < x/mid){
                left = mid + 1;
            }
            else if(mid > x/mid){
                right = mid -1;
            }
        }
        return left-1;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(0));
    }

}
