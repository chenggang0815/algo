package LeetCode._0031_Next_Permutation;
/*
31. Next Permutation
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
The replacement must be in place and use only constant extra memory.

Example 1:
Input: nums = [1,2,3]
Output: [1,3,2]

Example 2:
Input: nums = [3,2,1]
Output: [1,2,3]
*/

/*
Solution:
Approach 1
1. iterate the array from right to left
2. [1,2,3,4,5] for example, we iterate from right, we can know if we swap 5 and 4, we can get 1 2 3 5 4 which is the answer
3. but the situation is more complex, because we will have these situations
    3.1 [5,4,3,2,1] => the array is sorted in descending order, we just need to reverse the array, the answer is [1,2,3,4,5]
    3.2 [1,2,3,4,5] => we find the first pair to meet nums[i] > nums[j], and then switch nums[i] and nums[j], but there will have a issue
    3.3 [1,2,3,6,5,4] => the first pair 3 < 6, if we swap 3 and 6, we have
        => [1,2,  3,  6, 5,  4] => it's not meet the next permutation, the next permutation is [1,2,3,4,5,6]
                i-1  i     length-1
        => yes, we should sorted from i to nums.length-1 in ascending order
        => because from i to length-1 is descending order, so we just reverse from i to length-1
    3.4 and j is not always equal i, for example => [1,  2,  3,  5,  4,  3,  2]
                                                            i-1  i   j
        => first step, we find 3 < 5, the first pair
        => second step, we find nums[j]=4 > nums[i-1]=3
        => we swap nums[i-1] and nums[j]
 */
public class Solution {
    // 6 4 3 2 1
    // 1 2 3 4 6 => 1 2 3 6 4
    //        4<6
    // 1 2 3 6 4 => 1 2 4 6 3 => 1 2 4 3 6
    // 1 2 3 6 5 3 1 => 1 2 5 6 3 3 1 => 1 2 5 1 3 3 6
    //      <  >
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while(i > 0 && nums[i - 1] >= nums[i]){
            i--;
        }

        if(i == 0){
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int j = nums.length - 1;
        while(j > i - 1 && nums[j] <= nums[i - 1]){
            j--;
        }
        swap(nums, i - 1, j);
        reverse(nums, i, nums.length - 1);
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int left, int right){
        while(left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {

    }
}
