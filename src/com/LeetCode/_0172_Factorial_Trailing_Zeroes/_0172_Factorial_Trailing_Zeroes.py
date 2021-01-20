class Solution:
    def trailingZeroes(self, n: int) -> int:
        def numFive(n):
            res = 0
            while n % 5 == 0:
                res += 1
                n //= 5
            return res
        ans = 1
        for i in range(n, 0, -5):
            ans = ans * i
        return numFive(ans)

    def trailingZeroes1(self, n: int) -> int:
        res = 0
        while n > 0:
            res += n // 5
            n //= 5
        return res



if __name__ == '__main__':
    s = Solution()
    print(s.trailingZeroes1(125))

