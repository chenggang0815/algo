package LeetCode._0238_Product_of_Array_Except_Self;

import java.util.Arrays;

/*
238. Product of Array Except Self

Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
Note: Please solve it without division and in O(n).
Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
/*
思路1：利用除法，考虑不同0的数量的情况

思路2：// time:o(n) space:o(n) 分别计算两个从左到右和从右到左的乘积，res[i] = left[i - 1] * right[i + 1]

思路3：// time:o(n) space:o(1)
1. 由于输出数组不算在空间复杂度内，利用res数字计算左边的乘积，
2. 再从右到左更新res，i = length - 1 => 计算res[i] = res[i - 1] * right
3. right 表示右边的累乘值，初始为1，每次更新res[i - 1]后，也需要更新right => right = right * num[i]
4. res[0] = right
 */
public class Solution {
    // Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
    static int[] productExceptSelf1(int[] nums) {
        int totalProduct = 1;
        int numzero = 0;
        for (int num: nums){
            if (num == 0){
                numzero++;
                continue;
            }
            totalProduct *= num;
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0 && numzero > 1) res[i] = 0;
            if (nums[i] == 0 && numzero == 1) res[i] = totalProduct;
            if (nums[i] != 0 && numzero != 0) res[i] = 0;
            if (nums[i] != 0 && numzero == 0) res[i] = totalProduct / nums[i];
        }

        return res;

    }

    // time:o(n) space:o(n)
    static int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        if (length == 0) return new int[]{};

        int[] leftProduct = new int[length];
        int[] rightProduct = new int[length];

        leftProduct[0] = nums[0];
        rightProduct[length - 1] = nums[length - 1];


        for (int i = 1; i < length; i++){
            leftProduct[i] = leftProduct[i - 1] * nums[i];
            rightProduct[length - 1 - i] = rightProduct[length - i] * nums[length - 1 - i];
        }

        int[] res = new int[length];
        res[0] = rightProduct[1];
        res[length - 1] = leftProduct[length - 2];

        for (int i = 1; i < length - 1; i++){
            res[i] = leftProduct[i-1] * rightProduct[i + 1];
        }

        return res;
    }

    // time:o(n) space:o(1)
    static int[] productExceptSelf3(int[] nums) {
        int length = nums.length;
        if (length == 0) return new int[]{};

        int[] res = new int[length];
        res[0] = nums[0];
        for (int i = 1; i < length; i++){
            res[i] = res[i - 1] * nums[i];
        }

        int right = 1;
        for (int i = length - 1; i > 0; i--){
            res[i] = res[i - 1] * right;
            right = right * nums[i];
        }
        res[0] = right;
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf3(nums)));
    }
}
