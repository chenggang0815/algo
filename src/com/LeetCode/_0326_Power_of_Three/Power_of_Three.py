import math

"""
326. Power of Three

Given an integer n, return true if it is a power of three. Otherwise, return false.
An integer n is a power of three, if there exists an integer x such that n == 3x.

Example 1:
Input: n = 27
Output: true


Example 2:
Input: n = 0
Output: false


Example 3:
Input: n = 9
Output: true

Example 4:
Input: n = 45
Output: false

思路：
方法一：循环迭代
方法二：换底公式
    2.1 若n是3的幂，那么log3(n)一定是个整数 => 只需要判断log3(n)是不是整数即可
    2.2 由于java中没有log3(n)这个库函数，由换底公式可以的得到log3(n) = log10(n) / log10(3)

方法三：3的最大幂次方
"""


class Solution:
    # time:o(log(n))
    def isPowerOfThree1(self, n: int) -> bool:
        while n != 0 and n % 3 == 0:
            n = n / 3

        if n == 1:
            return True
        else:
            return False

    def isPowerOfThree2(self, n: int) -> bool:
        while n > 1:
            n = n / 3

        if n == 1:
            return True
        else:
            return False

    def isPowerOfThree3(self, n: int) -> bool:
        if n == 1 or n == 3 or n == 9 or n == 27 or n == 81 or n == 243 or n == 729 or n == 2187 or n == 6561 or n == 19683 or n == 59049 or n == 177147 or n == 531441 or n == 1594323 or n == 4782969 or n == 14348907 or n == 43046721 or n == 129140163 or n == 387420489 or n == 1162261467:
            return True
        else:
            return False

    # 换底公式
    def isPowerOfThree4(self, n: int) -> bool:
        return n > 0 and 3 ** n == round(math.log(27, 3))  # math.log 函数得到的数据可能不够精确，可以使用 round 取整

    def isPowerOfThree5(self, n: int) -> bool:
        return n > 0 and 1162261467 % n == 0

    # 递归
    def isPowerOfThree6(self, n: int) -> bool:
        return n > 0 and ( n == 1 or (n % 3 == 0 and self.isPowerOfThree(n / 3)))


if __name__ == '__main__':
    s = Solution()
    print(s.isPowerOfThree6(28))
