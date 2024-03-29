package LeetCode._0069_Sqrt_x;
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
    /*
    left从1开始就足够了，因为返回值最小也是1，不会是0，并且可以保证mid>=1，可以使用x/mid，且mid!=0, 如果使用mid*mid<x 还需要考虑mid*mid的溢出情况

    目标：找出最大的mid，使得mid < x/mid
     */
    //time: o(log2(x)) time: o(1)
    // x = 64
    // mid * mid < 64 => left = mid + 1
    // mid * mid >= 64 => right = mid - 1, res = mid
    // we want to find the first mid, make mid * mid >= x
    public int mySqrt(int x) {
        int left = 1;
        int right = x;
        int res = 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(mid == x / mid) return mid;
            if(mid  > x / mid){
                right = mid - 1;
                res = mid;
            }else{
                left = mid + 1;
            }
        }

        return res - 1;
    }

    public static void main(String[] args) {
        //System.out.println(mySqrt(9));
    }
}
