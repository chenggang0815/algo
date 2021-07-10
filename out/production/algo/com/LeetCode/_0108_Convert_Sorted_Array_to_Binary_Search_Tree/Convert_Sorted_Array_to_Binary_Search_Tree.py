from typing import List


class TreeNode:
    def __init__(self, val):
        self.val = val
        self.right = None
        self.left = None


class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> TreeNode:
        def helper(nums, left, right):
            if left > right:
                return
            mid = (left + right) // 2
            root = TreeNode(nums[mid])
            root.left = helper(nums, left, mid - 1)
            root.right = helper(nums, mid + 1, right)
            return root

        return helper(nums, 0, len(nums) - 1)

    def midoerder(self, root: TreeNode):
        if root is None:
            return
        self.midoerder(root.left)
        print(root.val)
        self.midoerder(root.right)


if __name__ == '__main__':
    s = Solution()
    root = s.sortedArrayToBST([-10, -3, 0, 5, 9])
    s.midoerder(root)
