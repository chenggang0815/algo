"""
Given a string s, find the length of the longest substring without repeating characters.
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
"""


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> str:
        left = 0
        maxLength = 0
        map = {}
        for index in range(len(s)):
            if s[index] in map:
                left = max(left, map[s[index]] + 1)
            map[s[index]] = index
            maxLength = max(maxLength, index - left + 1)

        return maxLength


if __name__ == '__main__':
    solution = Solution()
    print(solution.lengthOfLongestSubstring("aab"))
