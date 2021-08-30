from typing import List

"""
假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
每个people[i] = [hi, ki] 表示第i个人的身高为hi，前面正好有ki个身高大于或等于hi的人。

请你重新构造并返回输入数组 people 所表示的队列。
返回的队列应该格式化为数组 queue ，其中queue[j]=[hj, kj]是队列中第j个人的属性（queue[0]是排在队列前面的人）

输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
"""

"""
https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/xian-pai-xu-zai-cha-dui-dong-hua-yan-shi-suan-fa-g/

第一步：先按照身高排序 => [[7, 0], [7, 1], [6, 1], [5, 0], [5, 2], [4, 4]]
第二步：对于每个元素p， 比较len(res) 和 p[1] （其中len(res)表示队列中的人数，p[1]表示p前面的人数）
       如果len(res) <= p[1] 则将p放到最后
       如果len(res) > p[1] 则将p放到p[1]的位置，使得队列中身高大于等于p的人数有p[1]个 => res.insert(p[1], p)
       
[7, 0]
[]
[[7, 0]]
====
[7, 1]
[[7, 0]]
[[7, 0], [7, 1]]
====
[6, 1]
[[7, 0], [7, 1]]
[[7, 0], [6, 1], [7, 1]]
====
[5, 0]
[[7, 0], [6, 1], [7, 1]]
[[5, 0], [7, 0], [6, 1], [7, 1]]
====
[5, 2]
[[5, 0], [7, 0], [6, 1], [7, 1]]
[[5, 0], [7, 0], [5, 2], [6, 1], [7, 1]]
====
[4, 4]
[[5, 0], [7, 0], [5, 2], [6, 1], [7, 1]]
[[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]]
"""


class Solution:
    def reconstructQueue(self, people: List[List[int]]) -> List[List[int]]:
        res = []
        people = sorted(people, key=lambda x: (-x[0], x[1]))
        print(people)
        print("======")
        for p in people:
            print(p)
            print(res)
            if len(res) <= p[1]:
                res.append(p)
            elif len(res) > p[1]:
                res.insert(p[1], p)
            print(res)
            print("====")

        return res


if __name__ == '__main__':
    people = [[7, 0], [4, 4], [7, 1], [5, 0], [6, 1], [5, 2]]
    s = Solution()
    print(s.reconstructQueue(people))
