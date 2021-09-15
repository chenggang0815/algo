from typing import List

"""
solution 1： 暴力遍历 time:o(n^2) space:o(1)
solution 2： 利用hashmap， time:o(n) space:o(n)
solution 3:  two pointers  time:o(n) space:o(1)
=> left = 0 ; right = len(nums) - 1
=> sum = nums[left] + nums[right] 
=> if sum > target => right--
=> if sum < target => left++
=> return [left + 1, right + 1]
"""

"""
双指针的思路，最开始考虑left和right指针都由中位数开始，然后分别向两侧移动，但是这样会漏掉某些情况
eg：
[2, 7, 11, 15] target=26
left = right = 7
7+7 < 26 => 7+11=18 < 26 => 7+15=22 < 26 跳出循环

正确做法: left=0 right=len(nums)-1 包含所有解空间
https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/yi-zhang-tu-gao-su-ni-on-de-shuang-zhi-zhen-jie-fa/
"""


class Solution:
    # 	Time Limit Exceeded
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        for i in range(len(numbers)):
            for j in range(i + 1, len(numbers)):
                if numbers[i] + numbers[j] == target:
                    return [i + 1, j + 1]

    # 56 ms
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        map = {}
        for i in range(len(numbers)):
            temp = target - numbers[i]
            if temp in map:
                return [map[temp] + 1, i + 1]

            map[numbers[i]] = i

    # 60 ms two pointers
    def twoSum3(self, numbers: List[int], target: int) -> List[int]:
        left = 0
        right = len(numbers) - 1
        while left < right:
            sum = numbers[left] + numbers[right]
            if sum == target:
                return [left + 1, right + 1]
            elif sum > target:
                right -= 1
            elif sum < target:
                left += 1



if __name__ == '__main__':
    s = Solution()
    print(s.twoSum4([2, 7, 11, 15], 26))
