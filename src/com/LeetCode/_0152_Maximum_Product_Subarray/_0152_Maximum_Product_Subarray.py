from typing import List
"""
152. Maximum Product Subarray
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
"""

"""
思路1： 动态规划 o(n) o(n)
dp[i] = max(nums[i], dp[i - 1] * nums[i]) 不是正确的状态转移方程，由于存在负数，那么会导致最大的变最小的，最小的变最大的,因此还需要维护当前最小值

dp_max[i]表示以nums[i]结尾的子数组的最大值
dp_min[i]表示以nums[i]结尾的子数组的最小值

思路2： 动态规划+空间优化 o(n) o(1)


“最大子数组和”是DP算法里的经典案例之一，经典到这个解法甚至有一个名称Kadane's Algorithm。本题是“最大子数组和”的变型，但Kadane's Algo依然适用。
唯一要注意的是“乘法”下由于两个负数的乘积也依然可能得到一个很大的正数，所以必须同时计算“最小子数组和”，除此之外无任何区别。Kadane's Algo变型可以解决问题还有：
1186. 删除一次得到子数组最大和 - Medium
题解：1186. 删除一次得到子数组最大和
1191. K 次串联后最大子数组之和 - Hard
题解：1191. K 次串联后最大子数组之和


eg [-2, 3, -4]
----
max(3,-2*3,-2*3) = 3
min(3,-2*3,-2*3) = -6
----
max(-4, -4*4, -4*-6) = 24
min(-4, -4*4, -4*-6) = -16


思路三：根据符号的个数 
3.1 当负数个数为偶数时候，全部相乘一定最大
3.2 当负数个数为奇数时候，它的左右两边的负数个数一定为偶数，只需求两边最大值
3.3 当有0情况，重置就可以了

"""
class Solution:

    def maxProduct1(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0
        dp_max = [0] * len(nums)
        dp_max[0] = nums[0]
        dp_min = [0] * len(nums)
        dp_min[0] = nums[0]

        for i in range(1, len(nums)):
            dp_max[i] = max(nums[i], dp_min[i - 1] * nums[i], dp_max[i - 1] * nums[i])
            dp_min[i] = min(nums[i], dp_min[i - 1] * nums[i], dp_max[i - 1] * nums[i])

        return max(dp_max)

    # 动态规划+空间优化 o(n) o(1)
    def maxProduct2(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0
        f_max = nums[0]
        f_min = nums[0]
        res = f_max

        for i in range(1, len(nums)):
            temp_max = max(nums[i], f_max * nums[i], f_min * nums[i])
            temp_min = min(nums[i], f_max * nums[i], f_min * nums[i])
            f_max = temp_max
            f_min = temp_min

            if f_max > res:
                res = f_max
            """
            以下写法有问题，在计算f_min时，f_max的值已经改变
            f_max = max(nums[i], f_max * nums[i], f_min * nums[i])
            f_min = min(nums[i], f_max * nums[i], f_min * nums[i])

            if f_max > res:
                res = f_max
            """

        return res


if __name__ == '__main__':
    s = Solution()
    #print(s.maxProduct2([2, 3, -2, 4]))
    print(s.maxProduct2([-2, -3, -4]))

