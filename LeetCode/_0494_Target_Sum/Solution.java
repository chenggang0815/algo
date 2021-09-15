package LeetCode._0494_Target_Sum;
/*
494. Target Sum
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Constraints:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
/*
思路1：枚举 time: (o(2^n)) space: o(n)递归栈的深度
1. 递归，枚举出所有可能的情况
2. 依次遍历第i个数时，可以将它添加+或-，递归地搜索这两种情况
3. 遍历完所有的N个数时，计算出本次遍历所有数的和，并判断是否等于S => 若等于 => count++

思路2：动态规划 - 背包问题
1. 用dp[i][j]表示用数组中的前 i 个元素，组成和为j的方案数。考虑第i个数 nums[i]，它可以被添加+或-，因此状态转移方程如下：
    => dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
2. 由于数组中所有数的和不超过1000，那么j的最小值可以达到-1000，最大值可以达到1000 => j的取值范围[-1000, 1000] => 1000-(-1000)+1=2001个数
   所以int[] dp=new int[nums.length][2001]

3. 初始化dp[0][]
    1. 需要求的值dp[0][-1000]...dp[0][1000]，需要初始化的值dp[0][] => 即考虑第1个元素nums[0]
    2. 因为不支持index为负，所以对所有index+1000
    3. 因此需要初始化的值dp[0][0+1000]=dp[0][1000]
        3.1 若nums[0]=0 =>  即第一个数的符号可以为+也可以为- 都使得nums[0]=0 => dp[0][nums[i]+1000]=dp[0][0]=2
        3.1 若nums[0]!=0
            dp[0][1000 + nums[i]] = 1
            dp[0][1000 - nums[i]] = 1

4. 边界值 保证index在[0,2000]之间
   int l = (sum - nums[i] + 1000) >= 0 ? sum - nums[i] + 1000 : 0;
   int r = (sum + nums[i] + 1000) < 2000 ? sum + nums[i] + 1000: 2000;
 */
public class Solution {
    // Runtime: 476 ms, faster than 29.61% of Java online submissions for Target Sum.
    static int findTargetSumWays1(int[] nums, int S){
        int[] res = new int[1];
        helper(nums, 0, 0, S, res);
        return res[0];
    }

    static void helper(int[] nums, int index, int sum, int S, int[] res){
        if (index == nums.length){
            if (sum == S) res[0]++;
        }else{
            helper(nums, index + 1, sum + nums[index], S, res);
            helper(nums, index + 1, sum - nums[index], S, res);
        }
    }
/*
    // int count = 0; 必须作为全局变量
    static int findTargetSumWays1_1(int[] nums, int S){
        int count = 0;
        helper1_1(nums, 0, 0, S, count);
        return count;
    }

    static void helper1_1(int[] nums, int index, int sum, int S, int count){
        if (index == nums.length){
            if (sum == S) count++;
        }else{
            helper1_1(nums, index + 1, sum + nums[index], S, count);
            helper1_1(nums, index + 1, sum - nums[index], S, count);
        }
    }
*/

    static int count = 0;
    static int findTargetSumWays2(int[] nums, int S){
        helper2(nums, 0, 0, S);
        return count;
    }

    static void helper2(int[] nums, int index, int sum, int S){
        if (index == nums.length){
            if (sum == S) count++;
            return;
        }

        helper2(nums, index + 1, sum + nums[index], S);
        helper2(nums, index + 1, sum - nums[index], S);

    }
    // Runtime: 9 ms, faster than 75.89% of Java online submissions for Target Sum.

    static int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        //dp[0][nums[0] + 1000] = 1;
        //dp[0][-nums[0] + 1000] += 1;
        /*
        初始化
         */
        if (nums[0] == 0) dp[0][1000] = 2;
        else{
            dp[0][1000 + nums[0]] = 1;
            dp[0][1000 - nums[0]] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
//                if (dp[i - 1][sum + 1000] > 0) {
//                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
//                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
//                }
                int l = (sum - nums[i] + 1000) >= 0 ? sum - nums[i] + 1000 : 0;
                int r = (sum + nums[i] + 1000) < 2000 ? sum + nums[i] + 1000: 2000;
                dp[i][sum + 1000] = dp[i - 1][l] + dp[i - 1][r];
                //dp[i][sum] = dp[i - 1][sum - nums[i]] + dp[i - 1][sum + nums[i]]
            }
        }

        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }

    static int findTargetSumWays3(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        //dp[0][nums[0] + 1000] = 1;
        //dp[0][-nums[0] + 1000] += 1;
        /*
        初始化
         */
        if (nums[0] == 0) dp[0][1000] = 2;
        else{
            dp[0][1000 + nums[0]] = 1;
            dp[0][1000 - nums[0]] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }

        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        System.out.println(findTargetSumWays3(nums, 3));

    }
}
