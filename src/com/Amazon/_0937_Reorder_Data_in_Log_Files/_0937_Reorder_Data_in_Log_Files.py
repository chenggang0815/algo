"""
937. Reorder Data in Log Files

You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.

There are two types of logs:
Letter-logs: All words (except the identifier) consist of lowercase English letters.
Digit-logs: All words (except the identifier) consist of digits.

Reorder these logs so that:
1. The letter-logs come before all digit-logs.
2. The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.
3. The digit-logs maintain their relative ordering.
Return the final order of the logs.

Example 1:
Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
Explanation:
The letter-log contents are all different, so their ordering is "art can", "art zero", "own kit dig".
The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
"""
"""
solution:
we need to use two list to reverse the digit log and the letter log respectively
for the letter log, we need to first sort the list by the identifier, and then we need to sort the list by the content

for example: 
["a abc", "a bca", "c abc"] the expected result is ["a abc", "c abc", "a bca"]
if we sort by content first, we get:
["a abc", "c abc", "a bca"]
then we sort by the identifier, we get:
["a abc", "a bca", "c abc"]

if we sort by identifier first, we get:
["a abc", "a bca", "c abc"]
then we sort by the content, we get:
["a abc", "c abc", "a bca"]

so,
If you sort it by identifiers after sorting by suffixes, the whole result will be sorted by identifiers.
But if you do it reversely, the result will sort by suffix first, then by identifier if there is a tie.
"""
from typing import List

class Solution:
    def reorderLogs(self, logs: List[str]) -> List[str]:
        digits = []
        letters = []
        for log in logs:
            if log.split()[1].isdigit():
                digits.append(log)
            else:
                letters.append(log)

        letters.sort(key = lambda x: x.split()[0])
        letters.sort(key = lambda x: x.split()[1:])

        return letters + digits


if __name__ == '__main__':
    s = Solution()
    res = s.reorderLogs(["a abc", "a bca", "c abc"])

    print(res)
