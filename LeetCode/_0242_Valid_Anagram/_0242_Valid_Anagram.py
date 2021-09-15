"""
242. Valid Anagram

Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
"""


class Solution:
    def isAnagram1(self, s: str, t: str) -> bool:
        s_map = {}
        t_map = {}
        for char in t:
            t_map[char] = t_map.get(char, 0) + 1
        for char in s:
            s_map[char] = s_map.get(char, 0) + 1

        # 52 ms
        # if t_map == s_map:
        #    return True
        # return False

        # 36 ms
        return t_map == s_map

    # time: o(n) space:o(k)
    def isAnagram2(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False

        map = {}
        for char in s:
            map[char] = map.get(char, 0) + 1

        for char in t:
            if char not in map or map[char] == 0:
                return False
            map[char] = map[char] - 1

        return True

    def isAnagram3(self, s: str, t: str) -> bool:
        return sorted(s) == sorted(t)


if __name__ == '__main__':
    s = "aaccc"
    t = "ccaca"
    solution = Solution()
    print(solution.isAnagram2(s, t))
