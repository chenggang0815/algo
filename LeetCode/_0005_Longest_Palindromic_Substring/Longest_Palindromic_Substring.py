"""
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
"""


class Solution(object):
    def longestPalindrome1(self, s):
        """
        :type s: str
        :rtype: str
        """
        left = 0
        right = 0
        for i in range(len(s)):
            left1, right1 = self.helper(s, i, i)
            left2, right2 = self.helper(s, i, i + 1)

            if right1 - left1 > right - left:
                left = left1
                right = right1
            if right2 - left2 > right - left:
                left = left2
                right = right2
        print('left: {}'.format(left))
        print('right: {}'.format(right))
        return s[left: right + 1]

    def helper(self, s, left, right):
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1

        return left + 1, right - 1

    def longestPalindrome2(self, s):
        length = len(s)
        if len(s) < 2:
            return s
        dp = [[0 for _ in range(length)] for _ in range(length)]
        maxLength = 1
        start = 0

        for i in range(length):
            dp[i][i] = 0

        for j in range(1, length):
            for i in range(j):
                if s[i] != s[j]:
                    dp[i][j] = 0
                else:
                    if j - i < 3:
                        dp[i][j] = 1
                    else:
                        dp[i][j] = dp[i + 1][j - 1]

                if dp[i][j] == 1 and j - i + 1 > maxLength:
                    maxLength = j - i + 1
                    start = i

        return s[start:start+maxLength]


if __name__ == '__main__':
    solution = Solution()
    print(solution.longestPalindrome2("ac"))
