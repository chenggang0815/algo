package Amazon._0127_Word_Ladder;
import java.util.*;

/*
127. Word Ladder

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
1. Every adjacent pair of words differs by a single letter.
2. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
3. sk == endWord
4. Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

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
        Queue<String> queue = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        //words.remove(beginWord);
        queue.add(beginWord);
        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            for(int i = 0; i < size; i++){
                String currentWord = queue.poll();
                if(currentWord.equals(endWord)) return level;
                List<String> neighbors = neighbor(currentWord);
                for(String neighbor : neighbors){
                    if(words.contains(neighbor)){
                        words.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return 0;
    }

    public List<String> neighbor(String word){
        char[] chars = word.toCharArray();
        List<String> result = new ArrayList<>();
        for(int i = 0; i < chars.length; i++){
            char temp = chars[i];
            for(char c = 'a'; c <= 'z'; c++){
                chars[i] = c;
                String neighbor = new String(chars);
                result.add(neighbor);
            }
            chars[i] = temp;
        }

        return result;
    }
    public static void main(String[] args) {

    }
}
