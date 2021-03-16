class LargerNumKey(str):
    def __lt__(x, y):
        return x+y > y+x

class Solution:
    def largestNumber1(self, nums):
        print(sorted(map(str, nums), key=LargerNumKey))

        largest_num = ''.join(sorted(map(str, nums), key=LargerNumKey))
        return '0' if largest_num[0] == '0' else largest_num
    """
    解题思路：
    [9,36,6]
    对于数组来说，采用任何排序算法，例如冒泡排序，只不过需要改写两个元素之间的比较规则 
    => a排在b前面，不再是：a>b  
    => 而是：str(a)+str(b) > str(b)+str(a)
    所以只要保证排序完后的数组，任意两个相邻的元素满足上式即可
    eg:[9,36,6] => [9,6,36]
    """
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