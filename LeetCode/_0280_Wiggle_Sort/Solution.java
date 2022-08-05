package LeetCode._0280_Wiggle_Sort;

import java.util.ArrayList;
import java.util.Arrays;

/*
280. Wiggle Sort

Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
You may assume the input array always has a valid answer.

Example 1:
Input: nums = [3,5,2,1,6,4]
Output: [3,5,1,6,2,4]
Explanation: [1,6,2,5,3,4] is also accepted.

Example 2:
Input: nums = [6,6,5,6,3,8]
Output: [6,6,5,6,3,8]

Constraints:
1 <= nums.length <= 5 * 104
0 <= nums[i] <= 104
It is guaranteed that there will be an answer for the given input nums.

Follow up: Could you do it without sorting the array?
*/
/*
Solution
Approach 1: time:O(nlog(n)) space:O(n)
1. sorting + arraylist
     mid = 2 each time add(i, i+mid) until i < mid, i+mid < nums.length
index=0 1 2 3 4 5
nums= 1 2 3 4 5 6
array=1 4  => add(0, 2)
array=1 4 2 5 =>add(1, 3)
array=1 4 2 5 3 6 =>add(2,5)

Approach 2: time:O(nlog(n)) space:O(1)
1. just sort the array first, then swap elements pair-wise starting from the second element.
      [1, 2, 3, 4, 5, 6]
          ↑  ↑  ↑  ↑
          swap  swap
=> [1, 3, 2, 5, 4, 6]

Approach 3: time:O(n) space:O(1)
这道题还有一种 O(n) 的解法，根据题目要求的 nums[0] <= nums[1] >= nums[2] <= nums[3]....，可以总结出如下规律：
当i为奇数时，nums[i] >= nums[i - 1]
当i为偶数时，nums[i] <= nums[i - 1]

那么只要对每个数字，根据其奇偶性，跟其对应的条件比较，如果不符合就和前面的数交换位置即可，参见代码如下：
*/
public class Solution {
    public void wiggleSort1(int[] nums) {
        Arrays.sort(nums);
        int mid = (nums.length +1) / 2;
        // left ... mid ... right
        // 1  2     3  4 5 6
        // 1  4.    5. 2 3 6
        // 1 2 3 4 5
        // 1 4 2 5 3
        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 0; i < mid; i++){
            array.add(nums[i]);
            if(mid + i < nums.length) array.add(nums[i + mid]);
        }

        for(int i = 0; i < nums.length; i++){
            nums[i] = array.get(i);
        }

    }

    public void wiggleSort2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i+=2){
            swap(nums, i, i + 1);
        }
    }

    void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // time:o(n) space:o(1)
    public void wiggleSort3(int[] nums) {
        if (nums.length <= 1) return;
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums, i, i - 1);
            }
        }
    }

    }
