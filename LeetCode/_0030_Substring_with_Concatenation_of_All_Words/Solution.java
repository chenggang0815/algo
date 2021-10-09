package LeetCode._0030_Substring_with_Concatenation_of_All_Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
30. Substring with Concatenation of All Words
You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
You can return the answer in any order.

Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []

Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]

Constraints:
1 <= s.length <= 104
s consists of lower-case English letters.
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] consists of lower-case English letters.
*/
/*
Solution: time: O(len(s)/len(words)) space: O(len(words))
1. for words, count the number of each word occurs => countWords<word, cnt>
2. for s, iterate every subString(length = len(words)*len(words[0])), which means the total length of string in the words
3. for a subString, count the number of each word(length = len(words[0])) => countSubWords<word, cnt>
4. if countWords == countSubWords => find a pattern
   else not a pattern

for example: s="abcbcdefgh" words=["de","bc","bc"]
1. countWords<"de"=1, "bc"=2>
2. i = 0 sub="abcbcd" => countSubWords<"ab"=1,"cb"="1","cd"=1>
3. i = 1 sub="bcbcde" => countSubWords<"bc"=2,"de"="1"> => res.add(1)
*/
public class Solution {
    static List<Integer> findSubString(String s, String[] words){
        HashMap<String, Integer> countWords = new HashMap<>();
        for (String word: words){
            countWords.put(word, countWords.getOrDefault(word, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length() - words.length * words[0].length() + 1; i++){
            String subString = s.substring(i, i + words.length * words[0].length());

            if (helper(subString, countWords, words[0].length())){
                res.add(i);
            }
        }

        return res;
    }

    static boolean helper(String subString, HashMap<String, Integer> countWords, int lenWord){
        HashMap<String, Integer> countSubWords = new HashMap<>();
        for (int i = 0; i < subString.length(); i += lenWord){
            String word = subString.substring(i, i + lenWord);
            countSubWords.put(word, countSubWords.getOrDefault(word, 0) + 1);
        }
        System.out.println(countSubWords);

        return countSubWords.equals(countWords);
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo","bar"};
        System.out.println(findSubString(s, words));
    }
}
