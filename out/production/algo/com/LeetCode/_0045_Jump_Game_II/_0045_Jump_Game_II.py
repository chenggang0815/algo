from typing import List


class Solution:
    def jump(self, nums: List[int]) -> int:
        res = 0
        index = 0
        i = 0
        max_length = 0
        while i < len(nums) - 1:  # 为什么是 len(nums) - 1，因为当遍历到最后一个数字4的时候，i=index=4时，会多加一次
            # 所以不能访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了。
            # 如果访问最后一个元素，在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素
            max_length = max(max_length, nums[i] + i)
            if i == index:
                res += 1
                index = max_length

            i = i + 1
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.jump([2,1]))
    # print(s.jump([1,2,3]))
    print(s.jump([2, 3, 1, 1, 4]))
