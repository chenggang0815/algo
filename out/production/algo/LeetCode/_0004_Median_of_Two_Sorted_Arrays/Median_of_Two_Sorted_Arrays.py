class Solution(object):
    """
    time: o(m+n); space: o(m+n)
    """
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        index1 = 0
        index2 = 0
        nums = [0] * (len(nums1) + len(nums2))
        index = 0
        while index1 < len(nums1) and index2 < len(nums2):
            if nums1[index1] <= nums2[index2]:
                nums[index] = nums1[index1]
                index += 1
                index1 += 1
            else:
                nums[index] = nums2[index2]
                index += 1
                index2 += 1

        while index1 < len(nums1):
            nums[index] = nums1[index1]
            index += 1
            index1 += 1
        while index2 < len(nums2):
            nums[index] = nums2[index2]
            index += 1
            index2 += 1

        length = len(nums1) + len(nums2)
        if length % 2 == 0:
            return (nums[int(length/2)] + nums[int(length/2 - 1)]) / 2.0
        else:
            return nums[int(length/2)] / 1.0

if __name__ == '__main__':
    solution = Solution()
    print(solution.findMedianSortedArrays([1,3], [2]))