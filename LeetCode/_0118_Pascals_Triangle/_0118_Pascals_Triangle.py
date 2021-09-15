from typing import List

"""
思路：
1. 每一行首尾都是1
2. 除开首尾，dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
"""


class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        res = []
        for i in range(numRows):
            temp = []
            for j in range(0, i + 1):
                if j == 0 or j == i:
                    temp.append(1)
                else:
                    temp.append(res[i - 1][j - 1] + res[i - 1][j])
            res.append(temp)

        return res

    def generate2(self, numRows: int) -> List[List[int]]:
        res = []
        for i in range(numRows):
            temp = [1] * (i + 1)
            for j in range(1, i):
                temp[j] = res[i - 1][j - 1] + res[i - 1][j]
            res.append(temp)

        return res

    # 动态规划思想
    def generate3(self, numRows: int) -> List[List[int]]:
        res = [[1] * (i + 1) for i in range(numRows)]
        for i in range(numRows):
            for j in range(1, i):
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j]

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.generate3(5))
