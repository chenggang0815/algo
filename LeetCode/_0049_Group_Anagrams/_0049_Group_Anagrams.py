from typing import List
import collections
"""
49. Group Anagrams

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]
"""

"""
solution 1: 排序:由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键

时间复杂度：O(nklog(k))，其中n是strs中的字符串的数量，k是strs中的字符串的的最大长度。需要遍历n个字符串，对于每个字符串，需要O(klogk)的时间进行排序以及O(1)的时间更新哈希表

空间复杂度：O(nk)，其中n是strs中的字符串的数量，k是strs中的字符串的的最大长度。需要用哈希表存储全部字符串。


"""
class Solution:
    def groupAnagrams1(self, strs: List[str]) -> List[List[str]]:
        res = []
        map_s = {}
        for i in range(len(strs)):
            key = ''.join(sorted(s))
            map_s[key] = map_s.get(key, []).append(strs[i])

        for i in map_s.items():
            res.append(i[1])
        return res

    def groupAnagrams2(self, strs: List[str]) -> List[List[str]]:
        map_s = {}
        for s in strs:
            key = ''.join(sorted(s))
            map_s[key] = map_s.get(key, [])
            map_s[key].append(s)

        res = list(map_s.values())
        return res

    def groupAnagrams3(self, strs: List[str]) -> List[List[str]]:
        map_s = {}
        for s in strs:
            key = ''.join(sorted(s))
            map_s.setdefault(key, []).append(s)

        res = list(map_s.values())
        return res

    def groupAnagrams4(self, strs: List[str]) -> List[List[str]]:
        dict = {}
        for item in strs:
            key = tuple(sorted(item))
            dict[key] = dict.get(key, []) + [item]
        return list(dict.values())


if __name__ == '__main__':
    strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    s = Solution()
    print(s.groupAnagrams3(strs))

    mp = collections.defaultdict(list)
    print(mp)