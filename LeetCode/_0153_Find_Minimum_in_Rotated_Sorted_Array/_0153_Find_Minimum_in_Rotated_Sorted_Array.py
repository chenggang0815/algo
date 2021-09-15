from typing import List

class Solution:
    def findMin(self, nums: List[int]) -> int:
        left = 0
        right = len(nums) - 1
        while left <= right:
            mid = left + (right - left) // 2
            if nums[left] <= nums[mid] <= nums[right]:
                return nums[left]
            if nums[mid] >= nums[left] and nums[mid] >= nums[right]:
                left = mid + 1
            elif nums[mid] <= nums[left] and nums[mid] <= nums[right]:
                right = mid

        return -1


if __name__ == '__main__':
    s = Solution()
    print(s.findMin([4,5,6,7,0,1,2]))