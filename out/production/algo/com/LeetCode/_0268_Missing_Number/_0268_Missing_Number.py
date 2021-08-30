from typing import List
"""
思路1：排序 time:o(nlog(n)) space:o(1)
先排序，如果不缺少数组，[0, n]中有n+1个数，现在数组中只有n个数，分两种情况：
    1.1 数组中的n个数都连续，依次比较nums[0],nums[1]...nums[n-1]和 [0,n-1]中的每个数，如果nums[i] != i, 那么缺少的数为n => return n
    1.2 数组中的n个数不连续，依次比较nums[0],nums[1]...nums[n-1]和 [0,n-1]中的每个数，如果nums[i] != i => return i


思路2：哈希set time:o(n) space:O(n)
将数组中所有元素都放到hash set中，从0到n遍历，如果i不在hash set中 => return i

"""
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        nums.sort()
        for i in range(len(nums)):
            if nums[i] != i:
                return i
        return len(nums)

    def missingNumber2(self, nums: List[int]) -> int:
        num_set = set()
        for num in nums:
            num_set.add(num)
        for i in range(len(nums)+1):
            if i not in num_set:
                return i

        return -1


if __name__ == '__main__':
    s = Solution()
    print(s.missingNumber2([1,2]))