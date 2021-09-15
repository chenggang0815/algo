from typing import List

class Solution:
    def reverseString1(self, s: List[str]) -> None:
        left = 0
        right = len(s) - 1
        while left < right:
            i = s[left]
            j = s[right]
            s[left] = j
            s[right] = i
            left += 1
            right -= 1

    def reverseString2(self, s: List[str]) -> None:
        i = 0
        j = len(s) - 1
        while i < j:
            s[i], s[j] = s[j], s[i]
            i += 1
            j -= 1

    def reverseString(self, s: List[str]) -> None:
        """
            Do not return anything, modify s in-place instead.
        """
        s[:] = s[::-1]




if __name__ == '__main__':
    s = Solution()
    string = ["h","e","l","l","o"]
    s.reverseString(string)

