package com.LeetCode._0003_Longest_Substring_Without_Repeating_Characters;
import java.util.HashMap;

/*
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

intput : "abcdbgh"
output: 5 ("cdbgh")
 */
/*
思路：
1、首先，判断当前字符是否包含在map中，如果不包含，将该字符添加到map（字符，字符在数组下标）,
   此时没有出现重复的字符，左指针不需要变化。此时不重复子串的长度为：i-left+1，与原来的maxLen比较，取最大值；
2、如果当前字符 ch 包含在 map中，此时有2类情况：
   1）当前字符包含在当前有效的子段中，如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
      那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
   2）当前字符不包含在当前最长有效子段中，如：abba，我们先添加a,b进map，此时left=0，我们再添加b，发现map中包含b，
      而且b包含在最长有效子段中，就是1）的情况，我们更新 left=map.get(b)+1=2，此时子段更新为 b，而且map中仍然包含a，map.get(a)=0；
      随后，我们遍历到a，发现a包含在map中，且map.get(a)=0，如果我们像1）一样处理，就会发现 left=map.get(a)+1=1，实际上，left此时
      应该不变，left始终为2，子段变成 ba才对。

    为了处理以上2类情况，我们每次更新left，left=Math.max(left , map.get(ch)+1).
    另外，更新left后，不管原来的 s.charAt(i) 是否在最长子段中，我们都要将 s.charAt(i) 的位置更新为当前的i，
    因此此时新的 s.charAt(i) 已经进入到 当前最长的子段中！
 */

/*
总体思路： 用哈希表保存当前滑动窗口窗口的元素（保存每个元素索引）， 如果窗口的下一个元素已经在哈希表中，则将窗口的起点重置为哈希表中重复元素的下一个索引（跳过重复元素）。
每一次迭代都通过max函数比较选出滑动窗口的最大长度。

start = max(table.get(v) + 1, start)

这段代码是用来规避哈希表查询到在滑动窗口左边的重复字符的。例如，在"tmmzuxt"这个字符串中，遍历到最后一步时，最后一个字符't'和第一个字符't'是相等的。
 如果没有max函数锁定住滑动窗口的左边界，start就会弹回去第一个't'的索引0处，最后输出的最长无重复子串会变成"mmzuxt"。
 */
public class Solution {
    /*
复杂度分析
时间复杂度：O(N)O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。

空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。
在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0, 128)内的字符，
即∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，而字符最多有∣Σ∣ 个，因此空间复杂度为O(∣Σ∣)。
     */
    static int longestSubString(String s){
        if (s.length() == 0) return 0;

        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0; //滑动窗口左指针

        for (int i = 0; i < s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - left + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestSubString("abcabefgh"));
    }
}

