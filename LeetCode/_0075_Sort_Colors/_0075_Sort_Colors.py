from typing import List

"""
75. Sort Colors

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, 
with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

Example 3:
Input: nums = [0]
Output: [0]

Example 4:
Input: nums = [1]
Output: [1]
"""
"""

solution 1: two-pass
    1 遍历统计每个数字出现的次数 => 遍历nums每个颜色对应的次数并且赋值

solution 2: 排序
    2. nums.sort()

solution 3: 双指针 one-pass
    3.1 维护两个指针 p0 p2 => p0左边全部是0 p2右边全部是2
    3.2 从左到右遍历nums，如果当前数字nums[cur] 等于0或者2，需要和p0或者p2指向的数字进行交换 => p0++ 或 p2--
    3.3 和0的数字交换，cur++ 
    3.4 和2的数字交换cur不变，需要继续判断2交换过来的数字是否是0
    
"""


class Solution:
    def sortColors(self, nums: List[int]) -> None:
        p0, cur, p2 = 0, 0, len(nums) - 1
        while cur <= p2:
            if nums[cur] == 0:
                nums[p0], nums[cur] = nums[cur], nums[p0]
                cur += 1
                p0 += 1
            elif nums[cur] == 2:
                nums[p2], nums[cur] = nums[cur], nums[p2]
                # cur += 1 # 为这个指针的遍历顺序是从左右的，所以在与左边交换之后，是不需要进行对交换之后的数据进行判断的，而对右边交换之后的数据是需要判断的。
                # 所以和2交换之后指针不移动;需要对交换后的数字继续判断
                p2 -= 1
            else:
                cur += 1


if __name__ == '__main__':
    s = Solution()
    nums = [0, 1, 0, 0, 2, 0]
    s.sortColors(nums)
    print(nums)
