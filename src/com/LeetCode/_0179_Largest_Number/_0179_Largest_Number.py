class LargerNumKey(str):
    def __lt__(x, y):
        return x+y > y+x

class Solution:
    def largestNumber1(self, nums):
        print(sorted(map(str, nums), key=LargerNumKey))

        largest_num = ''.join(sorted(map(str, nums), key=LargerNumKey))
        return '0' if largest_num[0] == '0' else largest_num

    def largestNumber2(self, nums):
        for i in range(len(nums)):
            for j in range(len(nums) - i - 1):
                if str(nums[j]) + str(nums[j + 1]) < str(nums[j + 1]) + str(nums[j]):
                    nums[j], nums[j+1] = nums[j+1], nums[j]

        res = "".join(map(str, nums))
        if res[0] == "0":
            return "0"
        return "".join(map(str, nums))


if __name__ == '__main__':
    s = Solution()
    print(s.largestNumber2([9, 3, 6]))