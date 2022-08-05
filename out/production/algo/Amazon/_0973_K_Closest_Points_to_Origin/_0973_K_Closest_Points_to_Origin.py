from typing import List

"""
973. K Closest Points to Origin
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

solution 1: time: O(nlog(n))
sort all the points, and find the k closet points => actually, we don't need to sort all the points and can get the k closet points

solution 2: time: O(nlog(k))

"""


class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        points.sort(key=lambda x: x[0] ** 2 + x[1] ** 2)

        return points[:k]


if __name__ == '__main__':
    s = Solution()
    print(s.kClosest([[1, 3], [-2, 2]], 1))
