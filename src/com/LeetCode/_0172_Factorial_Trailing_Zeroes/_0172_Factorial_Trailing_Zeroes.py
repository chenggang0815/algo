class Solution:
    def trailingZeroes(self, n: int) -> int:
        def numZero(n):
            res = 0
            while n % 10 == 0:
                res += 1
                n //= 10
            return res
        ans = 1
        for i in range(n, 0, -1):
            ans = ans * i
        return numZero(ans)


if __name__ == '__main__':
    s = Solution()
    print(s.trailingZeroes(60))

