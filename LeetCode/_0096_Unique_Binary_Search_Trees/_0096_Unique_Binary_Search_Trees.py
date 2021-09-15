class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

class Solution:
    def numTrees1(self, n: int) -> int:
        dp = [0] * (n + 1)
        dp[0] = 1
        dp[1] = 1
        for i in range(2, n + 1):
            for j in range(0, i + 1):
                dp[i] = dp[i] + dp[j - 1] * dp[i - j]

        return dp[n]

    # 42ms -> 32ms
    def numTrees2(self, n: int) -> int:
        dp = [0] * (n + 1)
        dp[0] = 1
        dp[1] = 1
        for i in range(2, n + 1):
            nums = 0
            for j in range(0, i + 1):
                nums += dp[j - 1] * dp[i - j]
            dp[i] = nums

        return dp[n]

if __name__ == '__main__':
    s = Solution()
    print(s.numTrees(4))