from typing import List

"""
240. Search a 2D Matrix II

Write an efficient algorithm that searches for a target value in an m x n integer matrix.
The matrix has the following properties:
    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

Example 1:
Input: matrix = [[1,4,7,11,15],
                [2,5,8,12,19],
                [3,6,9,16,22],
                [10,13,14,17,24],
                [18,21,23,26,30]], target = 5
Output: true
"""

"""
solution 1 ： 暴力搜索

solution 2 ：time: o(m+n) space:o(1)
确实巧妙，想了下这个算法的关键是找到合适的遍历起点，这个点肯定具有某种特殊性，这个二维矩阵，四个角就是四个特殊点，
但他们的特点不同，左上和右下分别是矩阵的最小和最大值，左下和右上具有两面性，

选左上角，往右走和往下走都增大，不能选
选右下角，往上走和往左走都减小，不能选
选左下角，往右走增大，往上走减小，可选
选右上角，往下走增大，往左走减小，可选

solution 3： 二分搜索
每一行二分查找
跳过不需要查找的行

solution 4： 递归
解法三的话思想很简单，就是变形的二分法，每次抛弃一部分元素，但代码的话其实写出来不是很容易，相对于解法一和解法二来说是有些复杂度的。
二分法的思想就是，目标值和中点值进行比较，然后可以丢弃一半的元素。
这道题的话是矩阵，如果我们找到矩阵的中心，然后和目标值比较看能不能丢弃一些元素。

"""
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        n = len(matrix)
        m = len(matrix[0])
        for i in range(n):
            for j in range(m):
                if matrix[i][j] == target:
                    return True
        return False


# solution 2 ：time: o(m+n) space:o(1)
    def searchMatrix2_1(self, matrix: List[List[int]], target: int) -> bool:
        nums_row = len(matrix)
        nums_col = len(matrix[0])
        row = nums_row - 1
        col = 0
        while row >= 0 and col < nums_col:
            if matrix[row][col] == target:
                return True
            elif matrix[row][col] > target:
                row -= 1
            elif matrix[row][col] < target:
                col += 1

        return False

    def searchMatrix2_2(self, matrix: List[List[int]], target: int) -> bool:
        nums_row = len(matrix)
        nums_col = len(matrix[0])
        row = 0
        col = nums_col - 1
        while row < nums_row and col >= 0:
            if matrix[row][col] == target:
                return True
            elif matrix[row][col] > target:
                col -= 1
            elif matrix[row][col] < target:
                row += 1

        return False

    def searchMatrix3(self, matrix: List[List[int]], target: int) -> bool:
        def binarySearch(nums, target):
            left = 0
            right = len(nums) - 1
            while left <= right:
                mid = left + (right - left) // 2
                if nums[mid] == target:
                    return True
                elif nums[mid] > target:
                    right = mid - 1
                else:
                    left = mid + 1
            return False
        num_col = len(matrix[0])
        for i in range(len(matrix)):
            if matrix[i][num_col - 1] < target:
                continue
            if binarySearch(matrix[i], target):
                return True

        return False



if __name__ == '__main__':
    matrix = [[1,   4,   7, 11, 15],
              [2,   5,   8, 12, 19],
              [3,   6,   9, 16, 22],
              [10,  13,  14, 17, 24],
              [18,  21,  23, 26, 30]]

    matrix1 = [[-1, 3]]
    print(len(matrix1))
    print(len(matrix1[0]))
    s = Solution()
    print(s.searchMatrix3(matrix, 20))
