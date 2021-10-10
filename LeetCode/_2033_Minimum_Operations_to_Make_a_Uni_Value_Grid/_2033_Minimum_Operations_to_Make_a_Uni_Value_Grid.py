from typing import List

"""
Example 1:
Input: grid = [[2,4],[6,8]], x = 2
Output: 4
Explanation: We can make every element equal to 4 by doing the following:
- Add x to 2 once.
- Subtract x from 6 once.
- Subtract x from 8 twice.
A total of 4 operations were used.

Example 3:
Input: grid = [[1,2],[3,4]], x = 2
Output: -1
Explanation: It is impossible to make every element equal.

Solution:
1. put all the number in a list, sort the list
2. iterate the list, if (list[i + 1] - list[i]) % x != 0 => then list[i] can't convert to list[i + 1] by sum/minus x => return -1
3. get the median number of list => convert to every number to the mid number => minimum operation
4. iterate the list, calculate the times x of (abs(list[i]-mid)
"""


class Solution:
    def minOperations(self, grid: List[List[int]], x: int) -> int:
        l = []
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                l.append(grid[i][j])

        l.sort()
        for i in range(len(l) - 1):
            if (l[i + 1] - l[i]) % x != 0:
                return -1

        mid = l[len(l) // 2]
        res = 0
        for num in l:
            res += (abs(num - mid) / x)

        return int(res)


if __name__ == '__main__':
    s = Solution()
    print(s.minOperations([[2, 4], [6, 8]], 2))
