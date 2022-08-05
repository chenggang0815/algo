class Solution:
    def maxProfit(self, prices):
        profit = 0
        for i in range(1, len(prices)):
            temp = prices[i] - prices[i - 1]
            if temp > 0:
                profit += temp

        return profit


if __name__ == '__main__':
    s = Solution()
    print(s.maxProfit([7, 1, 5, 3, 6, 4]))