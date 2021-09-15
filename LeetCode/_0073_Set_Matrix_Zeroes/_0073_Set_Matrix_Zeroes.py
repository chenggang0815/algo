from typing import List

"""
73. Set Matrix Zeroes

Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.

Follow up:
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-2^31 <= matrix[i][j] <= 2^31 - 1

Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

[[0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]]
 
[[0,0,0,0],
 [0,4,5,0],
 [0,3,1,0]]
 
思路1：time:o(m*n) space:o(m+n)
遍历两次 => 如果矩阵中任意一个格子有零我们就记录下它的行号和列号，这些行和列的所有格子在下一轮遍历中全部赋为零

思路2：原地修改原始矩阵，设不在矩阵取值范围内的虚拟值作为标记 time:o(m*n) space:o(1)
=> 这个思路有问题，因为题目中设定-2^31 <= matrix[i][j] <= 2^31 - 1 所以不存在一定不会在矩阵中出现的虚拟值

思路3：time:o(m*n) space:o(1)
用matrix第一行和第一列记录该行该列是否有0,作为标志位

对于矩阵第一行和第一列的元素来说，如果有出现0，会与矩阵matrix[i][j]（i属于[1,cols] j属于[1,rows]）中有元素为0的情况冲突

所以 => 对于第一行,和第一列要设置一个标志位,为了防止自己这一行(一列)也有0的情况
"""

class Solution:
    def setZeroes1(self, matrix: List[List[int]]) -> None:
        if len(matrix) == 0:
            return
        rows = set()
        cols = set()
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j] == 0:
                    rows.add(i)
                    cols.add(j)

        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if i in rows or j in cols:
                    matrix[i][j] = 0

        return

    """
[[0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]]
 
[[0,0,0,0],
 [0,4,5,0],
 [0,3,1,0]]
    """

    def setZeroes2(self, matrix: List[List[int]]) -> None:
        if len(matrix) == 0:
            return

        dummy = 954093
        rows = len(matrix)
        cols = len(matrix[0])
        for i in range(rows):
            for j in range(cols):
                if matrix[i][j] == 0:
                    for index in range(rows):
                        matrix[index][j] = dummy if matrix[index][j] != 0 else 0
                    for index in range(cols):
                        matrix[i][index] = dummy if matrix[i][index] != 0 else 0
        for i in range(rows):
            for j in range(cols):
                if matrix[i][j] == dummy:
                    matrix[i][j] = 0

        return

    """
[[0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]]
 
 
[[0,0,0,0],
 [0,4,5,0],
 [0,3,1,0]]
    """

    def setZeroes3(self, matrix: List[List[int]]) -> None:
        if len(matrix) == 0:
            return

        rows = len(matrix)
        cols = len(matrix[0])
        row_flag = 1
        col_flag = 1

        for i in range(cols):
            if matrix[0][i] == 0:
                row_flag = 0

        for i in range(rows):
            if matrix[i][0] == 0:
                col_flag = 0

        for i in range(1, rows):
            for j in range(1, cols):
                if matrix[i][j] == 0:
                    matrix[i][0] = 0
                    matrix[0][j] = 0

        for i in range(0, rows):
            for j in range(0, cols):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    matrix[i][j] = 0

        if col_flag == 0:
            for i in range(rows):
                matrix[i][0] = 0

        if row_flag == 0:
            for i in range(cols):
                matrix[0][i] = 0

        return


if __name__ == '__main__':
    s = Solution()
    matrix = [[0, 1, 2, 0], [3, 4, 5, 2], [1, 3, 1, 5]]
    s.setZeroes3(matrix)
    print(matrix)
