class Solution:
    def isHappy(self, n: int) -> bool:
        def helper(n):
            res = 0
            while n > 0:
                digit = n % 10
                res = res + digit * digit
                n = n // 10
            return res
        slow = n
        fast = n
        while True:
            slow = helper(slow)
            fast = helper(helper(fast))
            if slow == fast:
                break

        return slow == 1


if __name__ == '__main__':
    s = Solution()
    print(s.isHappy(19))