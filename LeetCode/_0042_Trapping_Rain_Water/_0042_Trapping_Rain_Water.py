from typing import List


class Solution:
    def trap1(self, height: List[int]) -> int:
        sum = 0
        for i in range(len(height)):
            max_left = 0
            max_right = 0
            for j in range(0, i):
                max_left = max(max_left, height[j])
            for j in range(i + 1, len(height)):
                max_right = max(max_right, height[j])

            if min(max_right, max_left) > height[i]:
                sum += (min(max_right, max_left) - height[i])

        return sum

    def trap2(self, height: List[int]) -> int:
        sum = 0
        max_left = [0] * len(height)
        max_right = [0] * len(height)
        for i in range(1, len(height) - 1):
            max_left[i] = max(max_left[i - 1], height[i - 1])
        for i in range(len(height) - 2, -1, -1):
            max_right[i] = max(max_right[i + 1], height[i + 1])

        for i in range(1, len(height) - 1):
            if min(max_right[i], max_left[i]) > height[i]:
                sum += (min(max_right[i], max_left[i]) - height[i])

        return sum


if __name__ == '__main__':
    s = Solution()
    print(s.trap2([4, 2, 0, 3, 2, 5]))
