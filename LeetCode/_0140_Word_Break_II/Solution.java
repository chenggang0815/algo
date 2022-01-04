package LeetCode._0140_Word_Break_II;

import java.util.ArrayList;
import java.util.List;

/*
140. Word Break II

Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []

Constraints:
1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/
/*
Solution
Approach 1: similar to question 139
*/
public class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res2 = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        backTracking(s, wordDict, res, new ArrayList<>(), 0, s.length());

        for(List<String> list : res){
            StringBuilder sb = new StringBuilder();
            for(String word : list){
                sb.append(word).append(" ");
            }
            res2.add(sb.deleteCharAt(sb.length() - 1).toString());
        }

        return res2;
    }

    void backTracking(String s, List<String> wordDict, List<List<String>> res, List<String> temp, int index, int length){
        if(index == length){
            //System.out.print(temp);
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i = 0; i < s.length(); i++){
            String word = s.substring(0, i + 1);
            if(wordDict.contains(word)){
                //System.out.print(word + '\n');
                temp.add(word);
                backTracking(s.substring(i + 1), wordDict, res, temp, index + word.length(), length);
                temp.remove(temp.size() - 1);
            }
        }
    }


    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();

        backTracking2(s, wordDict, res, "", 0, s.length());

        return res;
    }

    void backTracking2(String s, List<String> wordDict, List<String> res, String sb, int index, int length){
        if(index == length){
            res.add(sb.substring(0, sb.length() - 1));
            return;
        }

        for(int i = 0; i < s.length(); i++){
            String word = s.substring(0, i + 1);
            if(wordDict.contains(word)){
                backTracking2(s.substring(i + 1), wordDict, res, sb + word + " ", index + word.length(), length);
            }
        }
    }
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("hello").append(" ").append("world").append(" ");

    }
}
