from typing import List

class Solution:
    def canJump(self, nums: List[int]) -> bool:
        max_jump = 0
        for i in range(len(nums)):
            if i > max_jump:
                return False
            max_jump = max(max_jump, nums[i] + i)
            if max_jump >= len(nums):
                return True

        return True