from typing import List


class Solution:
    def maxArea(self, height: List[int]) -> int:
        maxArea = 0
        for i in range(len(height)):
            for j in range(i, len(height)):
                area = min(height[i], height[j]) * (j - i)
                if area > maxArea:
                    maxArea = area

        return maxArea

    def maxArea2(self, height: List[int]) -> int:
        maxArea = 0
        left = 0
        right = len(height) - 1
        while left < right:
            area = min(height[left], height[right]) * (right - left)
            maxArea = max(maxArea, area)
            if height[left] <= height[right]:
                left += 1
            else:
                right -= 1

        return maxArea


if __name__ == '__main__':
    solution = Solution()
    print(solution.maxArea2([1, 2, 1]))