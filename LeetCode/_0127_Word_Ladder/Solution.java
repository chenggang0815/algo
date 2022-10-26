package LeetCode._0127_Word_Ladder;
import java.util.*;
/*
127. Word Ladder

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
1. Every adjacent pair of words differs by a single letter.
2. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
3. sk == endWord

Given two words, beginWord and endWord, and a dictionary wordList,
return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
*/

/*
Solution:
Approach 1: BFS
1. Since we want the shortest path, we should use BFS rather than DFS, similar to leetcode 1730

level=1 queue=[hit]

level=2  hit => [ait, bit, cit, ..., zit]   //generate neighbor by the first index
         [hat, hbt, hct, ..., hot, ... hzt]   //generate neighbor by the second index
                               * (hot in the wordList)
         [hia, hib, hic, ..., hiz]   //generate neighbor by the third index

         queue = [hot]

level=3  hot => [aot, bot, cot, dot, ...,lot, ...,zot]
                                *         *
              => [hat, hbt, ..., hzt]
              => [hoa, hob, ... , hot, ..., hoz]
                                  that's why need to remove the matched word from set
        queue = [dot, lot]

level=4  dot => [doa, dob, ..., dog, ...doz]
                                *
        lot => [loa, lob, loc, ..., log, ...,loz]
                                    *
        queue = [dog, log]

level=5 dog => [aog, bog, cog,]
                          * return 5
        log => []

Algorithm:

*/
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        int res = 0;
        queue.add(beginWord);
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            for(int i = 0; i < size; i++){
                String curWord = queue.poll();
                if(curWord.equals(endWord)) return res;
                List<String> nextWords = getNextWords(curWord);
                for(String nextWord: nextWords){
                    if(wordSet.contains(nextWord)){
                        queue.add(nextWord);
                        wordSet.remove(nextWord);//"hit" ["hot", "dot"]
                    }
                }
            }
        }

        return 0;
    }

    List<String> getNextWords(String word){
        char[] ch = word.toCharArray();
        List<String> res = new ArrayList<>();
        for(int i = 0; i < ch.length; i++){
            char temp = ch[i]; // char[] temp = ch is not correct
            for(char j = 'a'; j <= 'z'; j++){
                ch[i] = j;
                String nextWord = String.valueOf(ch);
                res.add(nextWord);
            }
            ch[i] = temp;
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
