package com.LeetCode._0268_Missing_Number;

/*
268. Missing Number

Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?

Example 1:
Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
2 is the missing number in the range since it does not appear in nums.
 */

/*
思路1：排序 time:o(nlog(n)) space:o(1)
先排序，如果不缺少数组，[0, n]中有n+1个数，现在数组中只有n个数，分两种情况：
    1.1 数组中的n个数都连续，依次比较nums[0],nums[1]...nums[n-1]和 [0,n-1]中的每个数，如果nums[i] != i, 那么缺少的数为n => return n
    1.2 数组中的n个数不连续，依次比较nums[0],nums[1]...nums[n-1]和 [0,n-1]中的每个数，如果nums[i] != i => return i

思路2：哈希set time:o(n) space:O(n)
将数组中所有元素都放到hash set中，从0到n遍历，如果i不在hash set中 => return i


思路3：数学方法 time:o(n) space:o(1)
因为数组的数据范围是[0,n]，[0,n]中n+1个数，但是数组的长度为n，缺少一个数，
所以只要把[0，n]之间的n+1个数的和减去数组里的n个数的和 =》 差为缺少的那个数

思路4：位运算
    异或运算的性质：
    1. a^b = b^a
    2. a^b^c = a^(b^c) = (a^b)^c
    3. a^a = 0
    4. a^0 = a
    5. 0^0 = 1

nums: [0,1,2,4]
index: 0 1 2 3
length:4

4^(0^0)^(1^1)^(2^2)^(4^3) => (4^4)^(0^0)^(1^1)^(2^2)^3 => 3
 */
public class Solution {
    static int missingNumber3(int[] nums){
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            res = res + (i - nums[i]);
        }

        return res + nums.length;
    }

    static int missingNumber4(int[] nums){
        int res = nums.length;
        for (int i = 0; i < nums.length; i++){
            res = res ^ nums[i] ^ i;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(missingNumber4(new int[]{0,1,2,4}));
    }
}
