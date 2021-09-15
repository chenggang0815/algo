package LeetCode._0763_Partition_Labels;

import java.util.ArrayList;
import java.util.List;

/*
763. Partition Labels

A string S of lowercase English letters is given.
We want to partition this string into as many parts as possible so that each letter appears in at most one part,
and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

Note:
S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.
*/

/*
思路1：贪心 time:o(n) space:o(K) k是字符的个数=26
1. 先遍历一遍字符串，记下每个字符在字符串中出现的最远的位置 => maxIndex[ch[i] - 'a'] = Math.max(maxIndex[ch[i] - 'a'], i);
2. 维护两个指针，start和end，初始时start=end=0
3. 再遍历一遍字符串，如果当前的位置i 与[start,i]中的字符的最远位置相等，说明当前区间[start,i]中的字符都只在这个区间中出现，并且保证这样的区间是最短的

贪心的思想寻找每个片段可能的最小结束下标，因此可以保证每个片段的长度一定是符合要求的最短长度，如果取更短的片段，则一定会出现同一个字母出现在多个片段中的情况。
由于每次取的片段都是符合要求的最短的片段，因此得到的片段数也是最多的。

*/
public class Solution {

    static List<Integer> partitionLabels(String S) {
        ArrayList<Integer> res = new ArrayList<>();

        int[] maxIndex = new int[26];
        char[] ch = S.toCharArray();
        for (int i = 0; i < ch.length; i++){
            maxIndex[ch[i] - 'a'] = Math.max(maxIndex[ch[i] - 'a'], i);
        }

        int start = 0;
        int end = 0;
        for (int i = 0; i < ch.length; i++){
            end = Math.max(end, maxIndex[ch[i] - 'a']);
            if (i == end){
                res.add(end - start + 1);
                start = end + 1;
            }
        }

        return res;
    }


    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }
}
