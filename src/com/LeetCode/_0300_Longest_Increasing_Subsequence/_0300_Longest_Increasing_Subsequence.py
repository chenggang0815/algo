from typing import List


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        res = [nums[0]]
        for num in nums[1:]:
            if num > res[-1]:
                res.append(num)
                continue

            left = 0
            right = len(res) - 1
            index = 0
            while left <= right:  # 找出第一个大于target的位置 参考34题
                mid = left + (right - left) // 2
                if res[mid] >= num:
                    right = mid - 1
                    index = mid
                else:
                    left = mid + 1

            res[index] = num

        return len(res)


if __name__ == '__main__':
    s = Solution()
    print(s.lengthOfLIS([18, 55, 66, 2, 3, 54]))
"""
[4,10,4,3,8,9]
4
4 10
4 10 4=4
3 10 4=3
3 8  10=8
3 8 9

"""
