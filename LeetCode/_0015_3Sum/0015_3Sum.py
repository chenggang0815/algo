from typing import List


class Solution:
    def threeSum(self, nums: List[int]):
        res = []
        nums.sort()
        for k in range(0, len(nums)-1):
            if nums[k] > 0:
                break
            if k > 0 and nums[k] == nums[k-1]:
                continue
            left = k + 1
            right = len(nums) - 1
            while left < right:
                sum = nums[k] + nums[left] + nums[right]
                if sum < 0:
                    while left < right and nums[left] == nums[left+1]:
                        left += 1
                    left += 1
                elif sum > 0:
                    while left < right and nums[right] == nums[right-1]:
                        right -= 1
                    right -= 1
                else:
                    res.append([nums[k], nums[left], nums[right]])
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                    while left < right and nums[left] == nums[left+1]:
                        left += 1
                    right -= 1
                    left += 1

        return res


if __name__ == '__main__':
    nums = [-1, 0, 1, 2, -1, -4, -1, -1]
    s = Solution()
    print(s.threeSum(nums))

