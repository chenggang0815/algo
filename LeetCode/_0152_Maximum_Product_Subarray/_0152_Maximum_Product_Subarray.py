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


思路：
这个问题很像「力扣」第 53 题：最大子序和，只不过当前这个问题求的是乘积的最大值；
「连续」这个概念很重要，可以参考第 53 题的状态设计，将状态设计为：以 nums[i]结尾的连续子数组的最大值；
类似状态设计的问题还有「力扣」第 300 题：最长上升子序列，「子数组」、「子序列」问题的状态设计的特点是：以 nums[i] 结尾，这是一个经验，可以简化讨论。
提示：以 nums[i] 结尾这件事情很重要，贯穿整个解题过程始终，请大家留意。

分析与第 53 题的差异
求乘积的最大值，示例中负数的出现，告诉我们这题和 53 题不一样了，一个正数乘以负数就变成负数，即：最大值乘以负数就变成了最小值；
因此：最大值和最小值是相互转换的，这一点提示我们可以把这种转换关系设计到「状态转移方程」里去；
如何解决这个问题呢？这里常见的技巧是在「状态设计」的时候，在原始的状态设计后面多加一个维度，减少分类讨论，降低解决问题的难度

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/dong-tai-gui-hua-li-jie-wu-hou-xiao-xing-by-liweiw/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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

    def maxProduct3(self, nums: List[int]) -> int:
        reverse_nums = nums[::-1]
        for i in range(1, len(nums)):
            nums[i] *= nums[i - 1] or 1  # 若相乘后为0则取1
            reverse_nums[i] *= reverse_nums[i - 1] or 1

        print(nums)
        print(reverse_nums)
        return max(nums + reverse_nums)


if __name__ == '__main__':
    s = Solution()
    # print(s.maxProduct2([2, 3, -2, 4]))
    print(s.maxProduct3([-2, -3, -4, -4, -2]))
    """
    * 以0为分界成一段一段分析
    * 在一段中负数的个数要么为偶数要么为奇数
    * 倘若负数的个数为偶数，那么直接相乘必然是最大值，因为偶数个负数相乘为正数
    * 倘若负数有奇数个，设为n个的话，那么n-1个负数则为偶数个
    * 那么这个减一因为是连续的子序列，所以要么是不包含最左边的负数，要么是不包含最右边的负数，只有这两种情况
    * 那么只需要从左往右遍历一次，从右往左遍历一次，求最大值就可以了
    * 注意为0的时候，虽然product要设置回1，但是有可能最大值就是0(每段都只有1个负数)，那么需要判断下answer是否更新为0
    """
