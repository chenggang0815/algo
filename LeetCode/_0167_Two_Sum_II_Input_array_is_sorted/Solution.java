package LeetCode._0167_Two_Sum_II_Input_array_is_sorted;
/*
167. Two Sum II - Input array is sorted

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Note:
Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.


Example 1:
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.

Example 2:
Input: numbers = [2,3,4], target = 6
Output: [1,3]

Example 3:
Input: numbers = [-1,0], target = -1
Output: [1,2]
 */
import java.util.Arrays;
/*
思路1：二分查找 time: O(log(n)) space:O(1)
在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，第二个数等于目标值减去第一个数的差。
    1.1 因为数组有序 => 通过二分查找的方法寻找第二个数
    1.2 为了避免重复寻找，在寻找第二个数时，只在第一个数的右侧寻找

solution 2: two pointers time: O(n) space:O(1)
because the array is sorted,
left index = 0
right index = len(array) - 1
if(nums[left] + nums[right] > target) right--
else left++
 */
public class Solution {
    static int[] twoSum(int[] numbers, int target){
        for (int i = 0; i < numbers.length; i++){
            int num = target - numbers[i];
            int left = i + 1; int right = numbers.length - 1;
            while (left <= right){
                int mid = left + (right - left) / 2;
                if (numbers[mid] == num) return new int[]{i + 1, mid + 1};
                if (numbers[mid] > num){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
        }

        return new int[]{-1, -1};
    }

    static int[] twoSum2(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            if (nums[left] + nums[right] == target){
                return new int[]{left + 1, right + 1};
            }else if (nums[left] + nums[right] > target){
                right--;
            }else{
                left++;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 26)));
    }
}
