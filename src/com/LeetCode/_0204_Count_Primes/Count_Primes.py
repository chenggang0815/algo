class Solution:
    def countPrimes(self, n: int) -> int:
        res = 0
        isPrimes = [1] * n
        for i in range(2, n):
            if isPrimes[i] == 1:
                res += 1
                for j in range(i * i, n, i):
                    isPrimes[j] = 0  # 将质数的倍数标记为合数, 从i*i开始标记

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.countPrimes(10))
