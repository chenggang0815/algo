package LeetCode._2062_Count_Vowel_Substrings_of_a_String;

import java.util.HashSet;

/*
2062. Count Vowel Substrings of a String
A substring is a contiguous (non-empty) sequence of characters within a string.
A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.
Given a string word, return the number of vowel substrings in word.

Example 1:
Input: word = "aeiouu"
Output: 2
Explanation: The vowel substrings of word are as follows (underlined):
- "aeiouu"
- "aeiouu"


*/
public class Solution {
    public int countVowelSubstrings(String word) {
        char[] ch = word.toCharArray();
        int res = 0;
        for(int i = 0; i < ch.length; i++){
            for(int j = i; j < ch.length; j++){
                if(helper(ch, i, j)) res++;
            }
        }

        return res;
    }

    boolean helper(char[] ch, int i, int j){
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        for(int k = i; k <= j; k++){
            set.remove(ch[k]);
            if("aeiou".indexOf(ch[k]) < 0) return false;
        }

        return set.size() == 0;
    }
    public static void main(String[] args) {

    }
}
