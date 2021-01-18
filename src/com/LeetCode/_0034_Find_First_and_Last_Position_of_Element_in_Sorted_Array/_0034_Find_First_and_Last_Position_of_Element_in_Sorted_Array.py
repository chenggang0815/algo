from typing import List

class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        res = [-1] * 2
        for index in range(len(nums)):
            if nums[index] == target:
                res[0] = index
                break

        for index in range(len(nums) - 1, -1, -1):
            if nums[index] == target:
                res[1] = index
                break

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.searchRange([5,7,7,8,8,10], 8))
    for index in range(5, 0, -1):
        print(index)
