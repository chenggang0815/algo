# 46. Permutations
from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        res = []
        if len(nums) == 0:
            return res

        def backtrack(nums, index, arr):
            if index == len(nums):
                res.append(arr[:])
                return
            for num in nums:
                if num in arr:
                    continue
                arr.append(num)
                backtrack(nums, index + 1, arr)
                arr.pop(index)

        backtrack(nums, 0, [])

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.permute([1, 2, 3]))
