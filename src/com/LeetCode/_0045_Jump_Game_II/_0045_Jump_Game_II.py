from typing import List

class Solution:
    def jump(self, nums: List[int]) -> int:
        res = 0
        max_length = 0
        for i in range(len(nums)):
            max_length = i + max(nums[i:i+nums[i]])
            res += 1
            print(res)
            if max_length >= len(nums):
                return res


if __name__ == '__main__':
    s = Solution()
    print(s.jump([2,3,1,1,4]))