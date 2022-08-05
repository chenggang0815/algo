from typing import List


class Solution:
    # time: o(log(n)) space: o(1)
    def searchInsert(self, nums: List[int], target: int) -> int:
        if nums[-1] < target:
            return len(nums)

        left = 0
        right = len(nums) - 1
        index = 0
        while left <= right:
            mid = left + (right - left) // 2
            if nums[mid] >= target:
                right = mid - 1
                index = mid
            else:
                left = mid + 1

        return index


if __name__ == '__main__':
    s = Solution()
    print(s.searchInsert([1,3,5,6], 9))