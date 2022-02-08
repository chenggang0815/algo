package Facebook._1060_Missing_Element_in_Sorted_Array;
/*
1060. Missing Element in Sorted Array

Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k, return the kth missing number starting from the leftmost number of the array.

Example 1:
Input: nums = [4,7,9,10], k = 1
Output: 5
Explanation: The first missing number is 5.

Example 3:
Input: nums = [1,2,4], k = 3
Output: 6
Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.

Constraints:
1. 1 <= nums.length <= 5 * 104
2. 1 <= nums[i] <= 107
3. nums is sorted in ascending order, and all the elements are unique.
4. 1 <= k <= 108

Follow up: Can you find a logarithmic time complexity (i.e., O(log(n))) solution?
*/
/*
Solution
Approach 1: brute force time:O(n)

Approach 2: binary search time:O(log(n))
*/
public class Solution {
    public int missingElement(int[] nums, int k) {
        int length = nums.length;
        int missNum = nums[length - 1] - nums[0] + 1 - length;
        if(missNum < k) return nums[length - 1] + k - missNum;

        int left = 0;
        int right = nums.length - 1;
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            int missCnt = nums[mid] - nums[left] - (mid - left);
            if(missCnt >= k){
                right = mid;
            }else{
                k = k - missCnt;
                left = mid;
            }
        }

        return nums[left] + k;
    }
    public static void main(String[] args) {

    }
}
