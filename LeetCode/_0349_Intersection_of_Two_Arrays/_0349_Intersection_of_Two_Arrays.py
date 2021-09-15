"""
349. Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

Note:
Each element in the result must be unique.
The result can be in any order.
"""

from typing import List


class Solution:
    # time: o(m+n) space:o(n)
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        num_set = set()
        res = []
        for num in nums1:
            num_set.add(num)

        for num in nums2:
            if num in num_set:
                if num not in res:
                    res.append(num)

        return res

    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()
        res = []
        start_index = 0
        for i in range(len(nums1)):
            if nums1[i] in res:  # 跟350题相比 多了一个重复的判断 其他不变
                continue
            if len(res) == 0:
                start_index = 0
            for j in range(start_index, len(nums2)):
                if nums1[i] == nums2[j]:
                    res.append(nums2[j])
                    start_index = j + 1
                    break
                if nums1[i] < nums2[j]:
                    break

        return res
