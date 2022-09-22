package LeetCode._0611_Valid_Triangle_Number;

import java.util.Arrays;

/*
611. Valid Triangle Number

Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

Example 2:
Input: nums = [4,2,3,4]
Output: 4

Constraints:
1 <= nums.length <= 1000
0 <= nums[i] <= 1000
*/
/*
Approach: Sort + two pointer
similar question: 3 sum
time: O(n^2)
space: O(log(n)). Sorting takes O(log(n)) space
 */
public class Solution {
    static public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        // 2 3 4 4 => 2 3 4 / 2 3 4 / 3 4 4 / 2 4 4
        // l   r i => 2 4 4 / 3 4 4 two pointers left == right之后， i--, 直接跳到最后一行，会miss掉第三行
        // l r   i => 2 3 4 two pointers 会miss掉这个解
        // l r i   => 2 3 4
        int res = 0;
        for(int i = nums.length - 1; i >= 2; i--){
            int left = 0;
            int right = i - 1;
            while(left < right){
                if(nums[left] + nums[right] > nums[i]){
                    res += right - left;
                    right--;
                }else{
                    left++;
                }
            }
        }

        return res;

    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,3,4,4};
        System.out.println(triangleNumber(nums));
    }
}
