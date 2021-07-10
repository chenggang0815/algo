from typing import List

"""
64. Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

solution 1:
dfs暴力遍历所有路径

solution 2: 动态规划 time:o(m*n) space:o(m*n)
    2.1 设定dp[i][j]的值表示走到（i，j）的最小路径和
    2.2 上一步只能从dp[i-1][j] or dp[i][j-1]得来
"""


class Solution:
    def minPathSum1(self, grid: List[List[int]]) -> int:
        res = []

        def dfs(grid, sum, row, col):
            if row == len(grid) - 1 and col == len(grid[0]) - 1:
                res.append(sum + grid[row][col])
                return

            if row + 1 < len(grid):
                dfs(grid, sum + grid[row][col], row + 1, col)
            if col + 1 < len(grid[0]):
                dfs(grid, sum + grid[row][col], row, col + 1)

        dfs(grid, 0, 0, 0)
        return min(res)

    def minPathSum2(self, grid: List[List[int]]) -> int:
        rows = len(grid)
        cols = len(grid[0])
        dp = [[0 for _ in range(cols)] for _ in range(rows)]
        dp[0][0] = grid[0][0]

        for i in range(1, rows):
            dp[i][0] = dp[i - 1][0] + grid[i][0]
        for j in range(1, cols):
            dp[0][j] = dp[0][j - 1] + grid[0][j]

        for i in range(1, rows):
            for j in range(1, cols):
                if dp[i - 1][j] > dp[i][j - 1]:
                    dp[i][j] = dp[i][j - 1] + grid[i][j]
                else:
                    dp[i][j] = dp[i - 1][j] + grid[i][j]

        return dp[rows - 1][cols - 1]

    def minPathSum2(self, grid: List[List[int]]) -> int:
        rows = len(grid)
        cols = len(grid[0])
        dp = [[0 for _ in range(cols)] for _ in range(rows)]
        dp[0][0] = grid[0][0]

        for i in range(1, rows):
            dp[i][0] = dp[i - 1][0] + grid[i][0]
        for j in range(1, cols):
            dp[0][j] = dp[0][j - 1] + grid[0][j]

        for i in range(1, rows):
            for j in range(1, cols):
                dp[i][j] = min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j]

        return dp[rows - 1][cols - 1]


if __name__ == '__main__':
    s = Solution()
    print(s.minPathSum2([[1, 3, 1], [1, 5, 1], [4, 2, 1]]))
    print(s.minPathSum2([[1, 2, 3], [4, 5, 6]]))
