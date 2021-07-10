from typing import List


class Solution:
    def intersect1(self, nums1: List[int], nums2: List[int]) -> List[int]:
        if len(nums1) > len(nums2):
            return self.intersect(nums2, nums1)
        num_map = {}
        for num in nums1:
            count = num_map.get(num, 0)
            num_map[num] = count + 1
        res = []
        for i in range(len(nums2)):
            count = num_map.get(nums2[i], 0)
            if count > 0:
                res.append(nums2[i])
                count -= 1
                if count > 0:
                    num_map[nums2[i]] = count
                else:
                    num_map.pop(nums2[i])

        return res

    def intersect2(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()

        res = []
        start_index = 0
        for i in range(len(nums1)):
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


if __name__ == '__main__':
    s = Solution()
    print(s.intersect2([2, 2, 1,3], [3, 4, 2, 1]))