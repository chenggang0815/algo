package LeetCode._0080_Remove_Duplicates_from_Sorted_Array_II;
/*
80. Remove Duplicates from Sorted Array II

Example 1:
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
*/

/*
Solution: time:O(n) space:O(1)
if k=2, for example
[1, 1,   1,   1, 1, 1,    2,    2, 2, 2, 2, 2, 3]
     index=2           index=6
1. make i denote the index for result array, reach time, we compare the current number with nums[i-2]
2.
when i=2, current number 1(index=2) == nums[i-2], skip the current number
when i=2, current number 1(index=3) == nums[i-2], skip the current number
when i=2, current number 1(index=4) == nums[i-2], skip the current number
when i=2, current number 1(index=5) == nums[i-2], skip the current number
when i=2, current number 2(index=6) != nums[i-2] => current number can be add to position i=2 => i++

3. if current number == nums[i - k] => skip current number
   if current number != nums[i - k] => current number can be add to position i,
                                    => nums[i] = currentNum
                                    => i++

4. return i => i = the last valid index + 1 = the length of the valid array
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        return process(nums, 2);
    }

    int process(int[] nums, int k) {
        int index = 0;
        for (int num : nums) {
            if (index < k || num != nums[index - k]){
                nums[index] = num;
                index++;
            }
        }
        return index;
    }

    public static void main(String[] args) {

    }
}
