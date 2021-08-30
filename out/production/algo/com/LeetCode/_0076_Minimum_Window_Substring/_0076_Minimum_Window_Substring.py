"""
76. Minimum Window Substring
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.
A substring is a contiguous sequence of characters within the string.


Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
"""

from collections import Counter


class Solution:
    def minWindow1(self, s: str, t: str) -> str:
        if len(t) > len(s):
            return ""
        minLength = len(s)
        inedx = (0, 0)
        c = Counter(t)
        for i in range(len(s)):
            for j in range(i, len(s)):
                c1 = Counter(s[i: j + 1])
                count = 0
                for char in c:
                    if c[char] <= c1[char]:
                        count += 1
                    else:
                        break
                if count == len(c) and len(t) <= (j + 1 - i) <= minLength:
                    minLength = j + 1 - i
                    inedx = (i, j + 1)
                    print("===")
                    print(inedx)

        # print(inedx)
        return s[inedx[0]:inedx[1]]

    def minWindow2(self, s: str, t: str) -> str:
        left = 0
        right = 0
        c = Counter(t)
        inedx = (left, right)
        minLength = len(s)
        while right < len(s):
            c1 = Counter(s[left: right + 1])
            count = 0
            for char in c:
                if c[char] <= c1[char]:
                    count += 1
                else:
                    break
            if count == len(c):
                if len(t) <= (right + 1 - left) <= minLength:
                    minLength = right + 1 - left
                    inedx = (left, right + 1)
                left += 1
            else:
                right += 1

        return s[inedx[0]:inedx[1]]

    def minWindow3(self, s: str, t: str) -> str:
        def helper1(c1, c2, t):
            for char in t:
                if c1[char] > c2[char]:
                    return False
            return True

        def helper2(c1, c2, t):
            for char in t:
                if c1[char] != c2[char]:
                    return False
            return True

        left = 0
        right = 0
        c = Counter(t)
        inedx = (left, right)
        minLength = len(s)
        while right < len(s):
            c1 = Counter(s[left: right + 1])
            if helper1(c, c1, t):

                while True:
                    temp = Counter(s[left: right + 1])
                    if helper1(c, temp, t):
                        left += 1
                    else:
                        if len(t) <= (right + 1 - left - 1) <= minLength:
                            minLength = min(minLength, right + 1 - left - 1)
                            inedx = (left - 1, right + 1)
                        break
            else:
                right += 1

        return s[inedx[0]:inedx[1]]


if __name__ == '__main__':
    s = Solution()
    print(s.minWindow2("acbbaca", "aba"))
