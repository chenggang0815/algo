"""
279. Perfect Squares

Given an integer n, return the least number of perfect square numbers that sum to n.
A perfect square is an integer that is the square of an integer;
in other words, it is the product of some integer with itself.
For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

Constraints:
1 <= n <= 104
"""
"""
思路1：暴力枚举


思路2：动态规划 time:o(n*sqrt(n)) space:o(n)

定义：dp[i]表示n=i的时候，完全平方数的和为i的最小个数
初始化：
    dp = [float('inf')] * (n + 1)
    dp[0] = 0
转移状态：
    for square in squares:
    dp[i] = min(dp[i], dp[i - square] + 1)

最终结果：
    dp[n]

方法三：贪心枚举

方法四：贪心 + BFS（广度优先搜索）

方法五：数学运算
"""
from math import sqrt


class Solution:
    # Time Limit Exceeded
    def numSquares1(self, n: int) -> int:
        squares = [i * i for i in range(1, int(sqrt(n)) + 1)]
        minNum = float('inf')

        def helper(n, minNum):
            for square in squares:
                if square == n:
                    return 1

            for square in squares:
                if square > n:
                    break
                newminNum = helper(n - square, minNum) + 1
                minNum = min(minNum, newminNum)

            return minNum

        return helper(n, minNum)

    def numSquares2(self, n: int) -> int:
        squares = [i * i for i in range(1, int(sqrt(n)) + 1)]

        dp = [float('inf')] * (n + 1)
        dp[0] = 0

        for i in range(1, n + 1):
            for square in squares:
                if i >= square:  # squares = [1,4,9] i = 3 square =4时，
                    dp[i] = min(dp[i], dp[i - square] + 1)

        return dp[n]


if __name__ == '__main__':
    s = Solution()
    print(s.numSquares2(12))
