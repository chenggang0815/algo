from typing import List
from collections import  Counter

class Solution:
    # Runtime: 96 ms, faster than 88.94% of Python3 online submissions for Top K Frequent Elements.
    def topKFrequent1(self, nums: List[int], k: int):
        cnt_map = {}
        for num in nums:
            cnt_map[num] = cnt_map.get(num, 0) + 1

        l = []
        for key in cnt_map.keys():
            l.append((key, cnt_map[key]))

        l.sort(key=lambda x: x[1], reverse=True)
        res = []
        for i in l:
            res.append(i[0])

        return res[:k]

    def topKFrequent2(self, nums: List[int], k: int):
        count = Counter(nums)

        return [item[0] for item in count.most_common(k)]


if __name__ == '__main__':
    s = Solution()
    print(s.topKFrequent2([1, 1, 1, 2, 2, 3, 3, 3, 3], 2))
