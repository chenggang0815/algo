package LeetCode._0269_Alien_Dictionary;

import java.util.*;

/*
269. Alien Dictionary

There is a new alien language that uses the English alphabet.
However, the order among the letters is unknown to you.
You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
*/

/*
Solution:
words = ["wrt","wrf","er","ett","rftt"] => wertf
1. because each word in the array is sorted lexicographically
2. so we can compare words[i] and words[i+1], get the order of letters
    2.1 if words[i].length() > words[i+1].length() && words[i].startsWith(words[i+1]) => return ""
    2.2 compare words[i].charAt(j) and words[i+1].charAt(j), until j < Math.min(words[i].length(), words[i+1].length())
    2.3 then we can get t < f => t -> f which means the order of "t" is before "f"
    2.4 for above array, we can get
        // t -> f
        // w -> e
        // r -> t
        // e -> r
     2.5 we can use topological sort to get the order of all the letters

time: O(c) Let C be the total length of all the words in the input list, added together.
*/
public class Solution {
    static  public String alienOrder(String[] words) {
        //initialize the in-degree for all the letters is 0
        HashMap<Character, Integer> inDegree = new HashMap<>();
        for (String word : words){
            for (Character c : word.toCharArray()){
                inDegree.put(c, 0);
            }
        }
        List<char[]> edges = new ArrayList<>(); // get the edge
        for (int i = 0; i < words.length - 1; i++){
            String word1 = words[i];
            String word2 = words[i + 1];
            int len1 = word1.length();
            int len2 = word2.length();
            if (len1 > len2 && word1.startsWith(word2)) return "";
            int j = 0;
            while (j < Math.min(len1, len2)){
                if (word1.charAt(j) != word2.charAt(j)){
                    edges.add(new char[]{word1.charAt(j), word2.charAt(j)});
                    break;
                }
                j++;
            }
        }
        for(char[] ch : edges){ // calculate the in-degree
            inDegree.put(ch[1], inDegree.get(ch[1]) + 1);
        }

        List<Character> res = new ArrayList<>();
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()){
            if (inDegree.get(c) == 0){ // if the in-degree of the letter is 0, add this letter in result
                res.add(c);
                queue.add(c);
            }
        }
        // bfs the edge
        while (!queue.isEmpty()){
            char c = queue.poll();
            for (char[] edge : edges){
                if (edge[0] == c){
                    inDegree.put(edge[1], inDegree.get(edge[1]) - 1);
                    if (inDegree.get(edge[1]) == 0){
                        res.add(edge[1]);
                        queue.add(edge[1]);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (res.size() == inDegree.size()){ // we find a valid order
            for (char c : res) sb.append(c);
        }

        return sb.toString();
    }


        public static void main(String[] args) {

    }
}
