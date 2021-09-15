package LeetCode._0300_Longest_Increasing_Subsequence;

import java.util.ArrayList;
import java.util.Arrays;

/*
300. Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 */

/*
思路1：动态规划 - time:o(n^2) space:o(n)

1. dp[i]的值代表以nums[i]结尾的最长子序列长度 （ps：注意区分dp[i]的值代表nums前i个数字的最长子序列长度，前者需要求max(dp)）

2. 状态转移：
    dp[i] = Math.max(dp[i], dp[j] + 1);

思路2：动态规划+二分查找 - time:o(nlog(n)) space:o(n)
2.1 从前往后遍历数组nums，在遍历到nums[i]时：

    2.1.1 如果nums[i]>d[len] => 则直接加入到d数组末尾

    2.1.2 否则在d数组中二分查找，找到第一个比nums[i]大的数d[k]，并更新d[k]=nums[i]

以输入序列[0,8,4,12,2]为例：
第一步插入0 d=[0]
第二步插入8 d=[0,8]
第三步插入4 d=[0,4]
第四步插入12 d=[0,4,12]
第五步插入2 d=[0,2,12]
最终得到最大递增子序列长度为3


 */
public class Solution {
    static int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int res = 1;
        for (int i = 0; i < nums.length; i++){
            if (dp[i] > res) res = dp[i];
        }

        return res;
    }

    static int lengthOfLIS2(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(nums[0]);
        for( int num: nums){
            if (num > res.get(res.size() - 1)){
                res.add(num);
                continue;
            }

            int left = 0;
            int right = res.size() - 1;
            int index = 0;
            while (left <= right){ // 找出第一个大于target的位置 参考34题
                int mid = left + (right - left) / 2;
                if (res.get(mid) >= num){
                    right = mid - 1;
                    index = mid;
                }else{
                    left = mid + 1;
                }
            }

            res.set(index, num);
        }

        return res.size();
    }


    public static void main(String[] args) {
       System.out.println(lengthOfLIS2(new int[]{10,9,2,5,3,7,101,18}));
    }
}
