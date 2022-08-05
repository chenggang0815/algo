package LeetCode._0324_Wiggle_Sort_II;
/*
324. Wiggle Sort II

Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
You may assume the input array always has a valid answer.

Example 1:
Input: nums = [1,5,1,1,6,4]
Output: [1,6,1,5,1,4]
Explanation: [1,4,1,5,1,6] is also accepted.

Example 2:
Input: nums = [1,3,2,2,3,1]
Output: [2,3,1,3,1,2]

Constraints:
1 <= nums.length <= 5 * 104
0 <= nums[i] <= 5000
It is guaranteed that there will be an answer for the given input nums.

Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

import java.util.Arrays;

/*
思路1： 排序+穿插 time：o(n*log(n)) space:o(n)
1. 首先，可以很容易想到一种简单的解法：将数组进行排序，然后从中间位置进行等分（如果数组长度为奇数，则将中间的元素分到前面），然后将两个数组进行穿插
   例如：对于数组[1,5,2,4,3]，将其排序，得到[1,2,3,4,5]，然后将其分割为[1,2,3]和[4,5]，对两个数组进行穿插，得到[1,4,2,5,3]
2. 但是这一解法有一个问题，例如，对于数组[1,2,2,3]，按照这种做法求得的结果仍为[1,2,2,3]
3. 如果题目不要求各元素严格大于或小于相邻元素，即，只要求nums[0] <= nums[1] >= nums[2] <= nums[3]...，那么这一解法是符合要求的，但题目要求元素相互严格大于或小于，那么需要稍微做一点改进
4. 一种可行的办法是将A和B反序：
    例如，对于数组[1,1,2,2,2,3]，分割为[1,1,2]和[2,2,3]，分别反序后得到[2,1,1]和[3,2,2]，此时2在A头部，B尾部，穿插后就不会发生相邻了。
                              正序穿插:[1,2,1,2,2,3]           反序穿插:[2,3,1,2,1,2]

思路2：快速选择 + 3-way-partition
思路3：快速选择 + 3-way-partition + 虚地址

作者：hexcat
链接：https://leetcode-cn.com/problems/wiggle-sort-ii/solution/yi-bu-yi-bu-jiang-shi-jian-fu-za-du-cong-onlognjia/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/
public class Solution {
    /*
    123456 => 123 456 => 321 654
    362514
    */
    static void wiggleSort(int[] nums) {
        int[] copyNums = nums.clone();
        Arrays.sort(copyNums);

        int length = nums.length;
        for (int i = 1; i < nums.length; i += 2){
            nums[i] = copyNums[--length];
        }

        for (int i = 0; i < nums.length; i += 2){
            nums[i] = copyNums[--length];
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,4,2,5,3,6};
        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
