from typing import List

"""
54. Spiral Matrix
Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

[[1,2,3],
 [4,5,6],
 [7,8,9]]
 
思路：time:o(m*n) space:o(1)
1. 首先设定上下左右边界
2. 其次向右移动到最右，此时第一行因为已经使用过了，可以将其从图中删去，体现在代码中就是重新定义上边界
3. 判断若重新定义后，上下边界交错，表明螺旋矩阵遍历结束，跳出循环，返回答案
4. 若上下边界不交错，则遍历还未结束，接着向下向左向上移动，操作过程与第一，二步同理
5. 不断循环以上步骤，直到某两条边界交错，跳出循环，返回答案

参考剑指offer 19题 顺时针打印矩阵
"""

class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        if len(matrix) == 0:
            return []
        # 赋值上下左右边界
        top = 0
        bottom = len(matrix) - 1
        left = 0
        right = len(matrix[0]) - 1

        res = []
        while True:
            for i in range(left, right + 1):  # 左到右
                res.append(matrix[top][i])
            top = top + 1  # 上边加一
            if top > bottom:
                break

            for i in range(top, bottom + 1):  # 上到下
                res.append(matrix[i][right])
            right = right - 1  # 右边减一
            if right < left:
                break

            for i in range(right, left - 1, -1):  # 右到左
                res.append(matrix[bottom][i])
            bottom = bottom - 1  # 下边减一
            if bottom < top:
                break

            for i in range(bottom, top - 1, -1):  # 下到上
                res.append(matrix[i][left])
            left = left + 1  # 左边加一
            if left > right:
                break

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.spiralOrder([[1, 2, 3], [4, 5, 6], [7, 8, 9]]))
