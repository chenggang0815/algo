package Facebook._1004_Max_Consecutive_Ones_III;
/*
1004. Max Consecutive Ones III
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Constraints:
1. 1 <= nums.length <= 105
2. nums[i] is either 0 or 1.
3. 0 <= k <= nums.length
 */
/*
Solution
Approach 1: two pointers

*/
public class Solution {
    //  1    1   0  0  0    1   1
    //  0    1   2  3  4    5   6
    //  left       right res = 4 zeroCnt=2
    //  left          right => while(zeroCnt > k) => left=3 k=2
    //             left        right
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int res = 0;
        int zeroCnt = 0;
        for(int right = 0; right < nums.length; right++){
            if(nums[right] == 0) zeroCnt++;
            while(zeroCnt > k){
                if(nums[left] == 0) zeroCnt--;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
