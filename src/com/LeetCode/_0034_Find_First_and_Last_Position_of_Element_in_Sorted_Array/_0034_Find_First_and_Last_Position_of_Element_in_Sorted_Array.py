from typing import List

class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        res = [-1] * 2
        for index in range(len(nums)):
            if nums[index] == target:
                res[0] = index
                break

        for index in range(len(nums) - 1, -1, -1):
            if nums[index] == target:
                res[1] = index
                break

        return res

    def searchRange2(self, nums: List[int], target: int) -> List[int]:
        res = [-1] * 2
        for i in range(len(nums)):
            if nums[i] == target:
                res[0] = i
                break

        if res[0] == -1:
            return res
        left = res[0]
        while left < len(nums) and nums[left] == target:
            left += 1
        res[1] = left - 1

        return res

    def searchRange3(self, nums: List[int], target: int) -> List[int]:
        left = 0
        right = len(nums) - 1
        index = -1
        res = [-1] * 2
        while left <= right:
            mid = left + (right - left) // 2
            if nums[mid] == target:
                index = mid
                break
            elif nums[mid] > target:
                right = mid - 1
            elif nums[mid] < target:
                left = mid + 1
        if index == -1:
            return res

        left = index
        while left >= 0 and nums[left] == target:
            left -= 1
        right = index
        while right < len(nums) and nums[right] == target:
            right += 1

        res[0] = left + 1
        res[1] = right - 1

        return res

    def searchRange4(self, nums: List[int], target: int) -> List[int]:
        res = [-1] * 2
        # 找出第一个大于target的位置
        def searchFirst(nums, target):
            left = 0
            right = len(nums) - 1
            res = len(nums)
            while left <= right:
                mid = left + (right - left) // 2
                if nums[mid] >= target:
                    right = mid - 1
                    res = mid
                else:
                    left = mid + 1

            return res
        # 找出第一个大于target的位置
        def searchLast(nums, target):
            left = 0
            right = len(nums) - 1
            res = len(nums) # 这一块有问题，res应该为0更好理解，特殊情况[1]，target = 1
            while left <= right:
                mid = left + (right - left) // 2
                if nums[mid] > target:
                    right = mid - 1
                    res = mid
                else:
                    left = mid + 1

            return res

        left = searchFirst(nums, target)
        right = searchLast(nums, target) - 1
        print(left, right)
        if left <= right < len(nums) and nums[left] == target and nums[right] == target:
            return [left, right]

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.searchRange4([1], 1))

