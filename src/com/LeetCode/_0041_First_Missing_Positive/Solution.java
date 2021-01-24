package com.LeetCode._0041_First_Missing_Positive;
/*
41. First Missing Positive

Given an unsorted integer array nums, find the smallest missing positive integer.
Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space.?


Example 1:
Input: nums = [1,2,0]
Output: 3

Example 2:
Input: nums = [3,4,-1,1]
Output: 2

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1
 */

/*
solution 3: 将数组视为哈希表 time:(o(n)) space:o(1)
// 由于每次的交换操作都会使得某一个数交换到正确的位置，因此交换的次数最多为N，整个方法的时间复杂度为 O(N)

最早知道这个思路是在《剑指 Offe》这本书上看到的，感兴趣的朋友不妨做一下这道问题：剑指 Offer 03. 数组中重复的数字。
下面简要叙述：
1. 由于题目要求我们「只能使用常数级别的空间」，而要找的数一定在 [1, N + 1] 左闭右闭（这里 N 是数组的长度）这个区间里。因此，我们可以就把原始的数组当做哈希表来使用。事实上，哈希表其实本身也是一个数组；
2. 我们要找的数就在 [1, N + 1] 里，最后 N + 1 这个元素我们不用找。因为在前面的 N 个元素都找不到的情况下，我们才返回 N + 1；
3. 那么，我们可以采取这样的思路：就把 11 这个数放到下标为 00 的位置， 22 这个数放到下标为 11 的位置，按照这种思路整理一遍数组。然后我们再遍历一次数组，第 11 个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数。
4. 这个思想就相当于我们自己编写哈希函数，这个哈希函数的规则特别简单，那就是数值为 i 的数映射到下标为 i - 1 的位置。

eg:
      [3, 4,  2, 1]  hash=> [1,  2, 3, 4]
index: 0  1   2  3           0   1  2  3   => return nums.length + 1

      [3, 4, -1, 1]  hash=> [1, -1, 3, 4]
index: 0  1   2  3           0   1  2  3   => return 1+1=2

hash(nums[i]) => nums[nums[i] - 1]
     nums[0]=3   nums[3-1]=nums[2]=3

     [3,4,-1,1]=>[-1,4,3,1]=>[-1,1,3,4]=>[1,-1,3,4]
 */

/*
solution 4: https://leetcode-cn.com/problems/first-missing-positive/solution/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/
 */
public class Solution {
    static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            // 为什么要用while不用if
            /*
            [3,4,-1,1]=>[-1,4,3,1]=>[-1,1,3,4]=>[1,-1,3,4]
            i = 1时，执行了两次交换：4和1 1和-1
             */
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++){
            if (i + 1 != nums[i]){
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
    }
}
