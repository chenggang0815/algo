package LeetCode._0031_Next_Permutation;

import java.util.Arrays;

public class Solution2 {
    static void nextPermutation(int[] nums) {
        int length = nums.length;
        for (int i = length - 1; i > 0; i--){
            if (nums[i - 1] < nums[i]){
                for (int j = length - 1; j >= i; j--){
                    if (nums[j] > nums[i - 1]){
                        swap(nums, i - 1, j);
                        reverse(nums, i, length - 1);
                        return;
                    }
                }
            }
        }

        reverse(nums, 0, length - 1);
    }

    static void nextPermutation2(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i]){
            i--;
        }

        if (i > 0){
            int j = nums.length - 1;
            while (j >= i && nums[j] <= nums[i - 1]){
                j--;
            }
            swap(nums, i - 1, j);
            reverse(nums, i, nums.length - 1);
            return;
        }

        reverse(nums, 0, nums.length - 1);
    }

    static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static void  reverse(int[] nums, int left, int right){
        while (left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,1,1};
        nextPermutation2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
