package LeetCode._0416_Partition_Equal_Subset_Sum;
/*
416. Partition Equal Subset Sum

Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 */
/*
相关问题
《背包九讲》

「力扣」上的 0-1 背包问题：

「力扣」第 416 题：分割等和子集（中等）；
「力扣」第 474 题：一和零（中等）；
「力扣」第 494 题：目标和（中等）；
「力扣」第 879 题：盈利计划（困难）；
「力扣」上的 完全背包问题：

「力扣」第 322 题：零钱兑换（中等）；
「力扣」第 518 题：零钱兑换 II（中等）；
「力扣」第 1449 题：数位成本和为目标值的最大数字（困难）。
这里要注意鉴别：「力扣」第 377 题，不是「完全背包」问题

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
/*
思考1：动态规划
本题是经典的「NP 完全问题」，也就是说，如果你发现了该问题的一个多项式算法，那么恭喜你证明出了 P=NP，可以期待一下图灵奖了。
正因如此，我们不应期望该问题有多项式时间复杂度的解法。我们能想到的，例如基于贪心算法的「将数组降序排序后，依次将每个元素添加至当前元素和较小的子集中」之类的方法都是错误的，可以轻松地举出反例。因此，我们必须尝试非多项式时间复杂度的算法，例如时间复杂度与元素大小相关的动态规划

这道题可以换一种表述：给定一个只包含正整数的非空数组nums[0]，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半。
因此这个问题可以转换成「0−1背包问题」。这道题与传统的「0−1背包问题」的区别在于
传统的「0−1背包问题」要求选取的物品的重量之和不能超过背包的总容量，这道题则要求选取的数字的和恰好等于整个数组的元素和的一半。
类似于传统的「0-1背包问题」，可以使用动态规划求解

在使用动态规划求解之前，首先需要进行以下判断：
    1. 根据数组的长度n判断数组是否可以被划分。如果n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回false
    2. 计算整个数组的元素和sum以及最大元素maxNum
        2.1 如果sum是奇数，则不可能将数组分割成元素和相等的两个子集，因此直接返回false。
        2.2 如果sum是偶数，则令target=sum/2​ => 如果maxNum>target，则除了maxNum以外的所有元素之和一定小于target，
                                            => 因此不可能将数组分割成元素和相等的两个子集，直接返回false

状态定义：
创建二维数组dp，包含n行target+1列，其中dp[i][j]表示从数组的[0,i]下标范围内选取若干个正整数（可以是0个）
=> 是否存在一种选取方案使得被选取的正整数的和等于j
=> 初始时，dp中的全部元素都是false

边界定义：
以下两种情况都属于边界情况：
    1. 如果不选取任何正整数，则被选取的正整数等于0 => 因此对于所有0≤i<n，都有dp[i][0]=true
    2. 当i==0时，只有一个正整数nums[0]可以被选取 => 因此dp[0][nums[0]]=true

状态转移方程:
对于i>0且j>0的情况，如何确定dp[i][j]的值？需要分别考虑以下两种情况
    1. 如果j ≥ nums[i]，则对于当前的数字nums[i]，可以选取也可以不选取，两种情况只要有一个为true，就有dp[i][j]=true
        1.1 如果不选取nums[i] => 则dp[i][j]=dp[i−1][j]
        1.2 如果选取nums[i] => 则dp[i][j]=dp[i−1][j−nums[i]]
    2. 如果j < nums[i]，则在选取的数字的和等于j的情况下无法选取当前的数字nums[i]
       => 因此有dp[i][j]=dp[i−1][j]

dp[i][j] = dp[i−1][j]∣dp[i−1][j−nums[i]]  j≥nums[i]
dp[i][j] = dp[i−1][j]                      j<nums[i]

最终答案 => dp[n−1][target]

思考2：动态规划 - 空间优化 (需要画表格)
上述代码的空间复杂度是O(n×target)
但是可以发现在计算dp的过程中，每一行的dp值都只与上一行的dp值有关，因此只需要一个一维数组即可将空间复杂度降到O(target)
此时的转移方程为：
    dp[j] = dp[j]∣dp[j−nums[i]]

且需要注意的是第二层的循环我们需要从大到小计算，因为如果我们从小到大更新dp值，
=> 那么在计算dp[j]值的时候，dp[j−nums[i]]已经是被更新过的状态，不再是上一行的dp值

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution {
    // time:o(length * target) space: o(length * target)
    static boolean canPartition1(int[] nums) {
        int length = nums.length;
        if (length < 2) return false;

        int sum = 0, maxNum = 0;
        for (int num: nums){
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        if (maxNum > target) return false;

        boolean[][] dp = new boolean[length][target + 1];
        for (int i = 0; i < length; i++){
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < length; i++){
            for (int j = 1; j <= target; j++){
                if (j >= nums[i]) dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                else dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[length - 1][target];
    }

    // time:o(length * target) space: o(target)
    public boolean canPartition2(int[] nums) {
        int length = nums.length;
        if (length < 2) return false;

        int sum = 0, maxNum = 0;
        for (int num: nums){
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        if (maxNum > target) return false;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < length; i++){
            for (int j = target; j >= nums[i]; j--){
                dp[j] = dp[j] | dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,11,5};
        System.out.println(canPartition1(nums));
    }
}
