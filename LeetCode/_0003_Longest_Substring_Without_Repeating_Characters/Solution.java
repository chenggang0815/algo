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
总体思路： 用哈希表保存当前滑动窗口窗口的元素（保存每个元素索引）， 如果窗口的下一个元素已经在哈希表中，则将窗口的起点重置为哈希表中重复元素的下一个索引（跳过重复元素）。
每一次迭代都通过max函数比较选出滑动窗口的最大长度。

start = max(table.get(v) + 1, start)

这段代码是用来规避哈希表查询到在滑动窗口左边的重复字符的。例如，在"tmmzuxt"这个字符串中，遍历到最后一步时，最后一个字符't'和第一个字符't'是相等的。
 如果没有max函数锁定住滑动窗口的左边界，start就会弹回去第一个't'的索引0处，最后输出的最长无重复子串会变成"mmzuxt"。
 */
public class Solution {
/*
"a b c a b"
i=0 map=[[a,0]] left=0 i-left+1=1
i=1 map=[[a,0],[b,1]] left=0 i-left+1=2
i=2 map =[[a,0],[b,1],[c,2] left=0 i-left+1=3
i=3 map =[[a,3],[b,1],[c,2] left=1 i-left+1=3
i=4 map =[[a,3],[b,4],[c,2] left=2 i-left+1=3

"a b c b c"
 0 1 2 3 4
i=0 map=[[a,0]] left=0 i-left+1=1
i=1 map=[[a,0],[b,1]] left=0 i-left+1=2
i=2 map =[[a,0],[b,1],[c,2] left=0 i-left+1=3

i=3 char=b map =[[a,0],[b,3],[c,2] left=2 i-left+1=3
i=4 map =[[a,3],[b,4],[c,2] left=2 i-left+1=3
 */
    static int longestSubString(String s){
        int res = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                left = Math.max(left, map.get(s.charAt(i)) + 1); // why we need Math.max(left, xxx), consider this example: "abba"
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

