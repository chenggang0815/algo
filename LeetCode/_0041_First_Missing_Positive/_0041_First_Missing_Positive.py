from typing import List

"""
solution 1: hashset
time: o(n) space:o(n)

solution 2: sort + binary search
time: o(nlog(n)) space:o(1)
"""


class Solution:
    def firstMissingPositive1(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 1
        num_set = set()
        for num in nums:
            num_set.add(num)

        for i in range(len(nums) + 1):
            if i + 1 not in num_set:
                return i + 1

    def firstMissingPositive2(self, nums: List[int]) -> int:
        def binarySearch(nums, target):
            left = 0
            right = len(nums) - 1
            while left <= right:
                mid = left + (right - left) // 2
                if nums[mid] == target:
                    return nums[mid]
                elif nums[mid] > target:
                    right = mid - 1
                else:
                    left = mid + 1

        nums.sort()
        for i in range(1, len(nums) + 1):
            if i != binarySearch(nums, i):
                return i

        return len(nums) + 1

