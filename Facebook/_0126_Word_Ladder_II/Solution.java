package Facebook._0126_Word_Ladder_II;
import java.util.*;
/*
126. Word Ladder II

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
Every adjacent pair of words differs by a single letter.
    1. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
    2. sk == endWord
    3. Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 */
/*
Solution
Approach 1: BFS
https://leetcode.com/problems/word-ladder-ii/discuss/40434/C%2B%2B-solution-using-standard-BFS-method-no-DFS-or-backtracking
*/
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>(wordList);
        Queue<List<String>> queue = new LinkedList<>();

        queue.add(Arrays.asList(beginWord));
        while(!queue.isEmpty()){
            int size = queue.size();
            HashSet<String> visited = new HashSet<>();
            while(size > 0){
                List<String> path = queue.poll();
                String lastWord = path.get(path.size() - 1);
                List<String> neighborWords = getNeighbor(lastWord, set);
                //System.out.print(neighborWords + "\n");
                for(String word: neighborWords){
                    if(set.contains(word)){
                        //System.out.print(path.size());
                        //System.out.print(word + "\n");
                        List<String> currentPath = new ArrayList<>(path);
                        currentPath.add(word);
                        if(word.equals(endWord)) res.add(currentPath);
                        queue.add(currentPath);

                        visited.add(word);
                    }
                }
                size--;
            }
            for(String word: visited) set.remove(word);
        }

        //System.out.print(getNeighbor("hot", set));

        return res;

    }

    List<String> getNeighbor(String word, HashSet<String> set){
        List<String> res = new ArrayList<>();
        char[] ch = word.toCharArray();
        for(int i = 0; i < ch.length; i++){
            char temp = ch[i];
            for(char c = 'a'; c <= 'z'; c++){
                ch[i] = c;
                String neighbor = String.valueOf(ch);
                //System.out.print(neighbor + "\n");
                if(set.contains(neighbor)) res.add(neighbor);
            }
            ch[i] = temp;
        }

        return res;
    }

    public static void main(String[] args) {
        List<List<String>> array = new ArrayList<>();
        //Queue<List<String>> queue = new LinkedList<>();
        List<String> list = Arrays.asList("hello");
        array.add(list);
        List<String> l = array.get(0);
        l.add("word");
        System.out.println(l);

    }
}
