package Amazon._0472_Concatenated_Words;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
472. Concatenated Words

Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example 1:
Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
"dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Example 2:
Input: words = ["cat","dog","catdog"]
Output: ["catdog"]

Constraints:
1 <= words.length <= 104
0 <= words[i].length <= 1000
words[i] consists of only lowercase English letters.
0 <= sum(words[i].length) <= 105
*/

/*
Solution:  time: helper() => O(n^3) total => O(m*n^3) m=words.length()
1. first sort the array, so for a word = words[i], if it can be concat, the shorter word must in [0, .., i-1]
2. so we can use the helper function to  check in the word(nums[0]...nums[i-1]) if exists the at least two word can concat to the nums[i]
3. for the second step, refer leetcode 139
*/
public class Solution {
    static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();

        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for(String word: words){
            if(helper(word, set)){
                res.add(word);
            }
            set.add(word);
        }

        return res;
    }

    static boolean helper(String word, HashSet<String> set){
        if (set.size() == 0) return false; // corner case String[] words = [""]

        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= word.length(); i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && set.contains(word.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }

    public static void main(String[] args) {
        String[] s = new String[]{""};
        System.out.println(findAllConcatenatedWordsInADict(s));
    }
}
