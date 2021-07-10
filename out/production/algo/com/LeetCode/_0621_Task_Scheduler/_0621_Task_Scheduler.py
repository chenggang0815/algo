from typing import List
from collections import Counter


class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        ct = Counter(tasks)
        print(ct)
        print(ct.values())
        print(list(ct.values()))

        nbucket = ct.most_common(1)[0][1]
        last_bucket_size = list(ct.values()).count(nbucket)  # 最后一个桶中的元素个数
        print(last_bucket_size)
        res = (nbucket - 1) * (n + 1) + last_bucket_size
        print(res)

        return max(res, len(tasks))


if __name__ == '__main__':
    s = Solution()
    print(s.leastInterval(["A", "A", "B", "C", "D", "E", "F", "G", "H"], 2))
