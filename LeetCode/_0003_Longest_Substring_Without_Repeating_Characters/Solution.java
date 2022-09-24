package LeetCode._0003_Longest_Substring_Without_Repeating_Characters;
import java.util.HashMap;
/*
3. Longest Substring Without Repeating Characters
Given a string s, find the length of the longest substring without repeating characters.
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
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
Approach 1: HashMap
总体思路： 用哈希表保存当前滑动窗口窗口的元素（保存每个元素索引）， 如果窗口的下一个元素已经在哈希表中，则将窗口的起点重置为哈希表中重复元素的下一个索引（跳过重复元素）。
每一次迭代都通过max函数比较选出滑动窗口的最大长度。

start = max(table.get(v) + 1, start)

这段代码是用来规避哈希表查询到在滑动窗口左边的重复字符的。例如，在"tmmzuxt"这个字符串中，遍历到最后一步时，最后一个字符't'和第一个字符't'是相等的。
 如果没有max函数锁定住滑动窗口的左边界，start就会弹回去第一个't'的索引0处，最后输出的最长无重复子串会变成"mmzuxt"。

Approach 2: HashSet
1. 维护一个hashset，保证hashset里面是连续的没有重复的substring
2. 如果当前字符不在set里，把当前字符加入set，并且记录set的大小，可能为最大的长度
3. 如果当前字符在set里，开始缩减窗口，直到把当前字符从set中删除，确保set中没有重复字符
for example: "abcdaef"
set =>
for example: "bcadeafg"
set => 移动right，扩张窗口，知道当前字符为a，碰到重复字符，[][b][b, c][a, b, c][a, b, c, d][a, b, c, d, e]
=> 开始缩减窗口,直到把a从set中删除 [a, c, d, e][a, d, e]
=> 移动right，扩张窗口 [d, e][a, d, e][a, d, e, f],[a, d, e, f, g]
*/
public class Solution {
    static int longestSubString(String s){
        int res = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                left = Math.max(left, map.get(s.charAt(i)) + 1); // why we need Math.max(left, xxx), consider this example: "pwaca"
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - left + 1);
        }

        return res;
    }

    // 09/20/2022
    /*
    拿 abba 举例来说
          a    b    b    a
          0    1    2    3
      i
     left
     第一次碰到重复字符，需要尝试缩减窗口 i=2 left=0 当前重复的字母是b，b上一次出现的位置是1，1大于等于left，也就是说b上一次出现的位置在当前窗口中，
     为了保持当前窗口有效，我们需要把left移动到b上一次出现的位置的后一位，也就是令left=2
          a    b    b    a
          0    1    2    3
                         i
                  left
     第二次碰到重复字符，需要尝试缩减窗口， i=3 left=2, 当前重复的字母是a，a上一次出现的位置是0，0小于left，也就是说a上一次出现的位置不在当前窗口中，
     因为a上一次出现的位置不在当前窗口中，所以不需要移动left的位置，并且不会影响当前窗口的有效性

     综上，我们需要一个判断条件来确定是否需要缩减窗口（也就是向右移动left），=》 if(map.get(s.charAt(i)) >= left)
     */
    static public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int res = 1;

        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))){ //if the current character has appeared previously and
                //res = Math.max(res, i - left)
                // if the it's index lies in the current window
                // we can contract the window by moving left boundary to index + 1
                if(map.get(s.charAt(i)) >= left) left = map.get(s.charAt(i)) + 1;
                //left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            res = Math.max(res, i - left + 1);
            map.put(s.charAt(i), i);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwaca"));
    }
}

