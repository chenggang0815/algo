from typing import List
"""

1. 按照区间的左端点排序 => 在排完序的列表中，可以合并的区间一定是连续的

2. 将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：

    2.1 如果当前区间的左端点 > 数组res中最后一个区间的右端点，那么它们不会重合，我们可以直接将这个区间加入数组 res 的末尾；

    2.2 否则，它们重合，需要用当前区间的右端点更新数组 res 中最后一个区间的右端点，将其置为二者的较大值

time:  o(nlog(n))
space: o(nlog(n))
"""


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort(key=lambda x: x[0])
        res = []
        for interval in intervals:
            if not res or res[-1][1] < interval[0]:
                res.append(interval)
            else:
                res[-1][1] = max(res[-1][1], interval[1])

        return res


if __name__ == '__main__':
    s = Solution()
    res = s.merge([[2, 3], [4, 5], [6, 7], [8, 9], [1, 10]])
    print(res)
