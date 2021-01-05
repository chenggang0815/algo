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
        right = 0
        for item in s:
            res = s[left:right]
            if item not in res:
                right += 1
            else:
                left += 1
        return sub


if __name__ == '__main__':
    solution = Solution()
    print(solution.lengthOfLongestSubstring("aab"))
