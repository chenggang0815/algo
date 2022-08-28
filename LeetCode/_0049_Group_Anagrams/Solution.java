package LeetCode._0049_Group_Anagrams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
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


Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */
/*
由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。

由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 26的数组记录每个字母出现的次数。需要注意的是，在使用数组作为哈希表的键时，不同语言的支持程度不同，因此不同语言的实现方式也不同。

 */
public class Solution {
    static List<List<String>> groupAnagrams(String[] strs){
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String word : strs) {
            int[] charCnt = new int[26];
            for (char ch: word.toCharArray()) {
                charCnt[ch - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (charCnt[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(charCnt[i]);
                }
            }
            String key = sb.toString();

            List<String> list = map.getOrDefault(key, new ArrayList<String>());

            list.add(word);
            map.put(key, list);
        }

        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println((char) (1 + 'a'));
    }

}
