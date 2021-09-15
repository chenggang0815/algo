"""
387. First Unique Character in a String

Given a string, find the first non-repeating character in it and return its index.
If it doesn't exist, return -1.

Examples:
s = "leetcode"
return 0

s = "loveleetcode"
return 2
Note: You may assume the string contains only lowercase English letters.
"""

from collections import Counter
class Solution:
    # 152 ms
    def firstUniqChar1(self, s: str) -> int:
        m = {}
        for char in s:
            count = m.get(char, 0)
            m[char] = count + 1

        for i in range(len(s)):
            if m[s[i]] == 1:
                return i

        return -1

    # 96 ms
    def firstUniqChar2(self, s: str) -> int:
        hashmap = Counter(s)
        for i in range(len(s)):
            if hashmap[s[i]] == 1:
                return i

        return -1


if __name__ == '__main__':
    s = Solution()
    print(s.firstUniqChar2("loveleetcode"))