"""
首先利用isalnum函数排除非字母数字，lower/tolower将所有字母转为小写
然后镜像对比处理后的字符串
"""

class Solution:
    def isPalindrome(self, s: str) -> bool:
        s = [*filter(str.isalnum, s.lower())]
        return s == s[::-1]


if __name__ == '__main__':
    s = Solution()
    print(s.isPalindrome("A man, a plan, a canal: Panama"))