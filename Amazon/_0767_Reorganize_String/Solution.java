package Amazon._0767_Reorganize_String;
/*
767. Reorganize String
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""

Constraints:
1. 1 <= s.length <= 500
2. s consists of lowercase English letters.
*/

import java.util.PriorityQueue;

/*
Solution:
Approach1 : time:O(nlogn) space:O(n)
1. intuition: if we have the most number of a character is n, which means we need n-1 other character to split the n same character
 for example => 'abaca' or 'abacada'
 so the length = n + n - 1 => n = (length + 1) / 2 is the at most number of 'a', if more than (length + 1)/2, we return ""
2. each time we choose two character to concat, the most number character and second number character
for example => 'ababac' => 'ab' => 'abab' => 'ababac'
                a=3        a=2      a=1        a=0
                b=2        b=1      b=0        b=0
                c=1        c=1      c=1        c=0
3. so we can maintain a max PriorityQueue, each time we poll two character until pq.size <= 1

Approach2 : time:O(n) space:O(n)

We construct the resulting string in sequence: at position 0, 2, 4, ... and then 1, 3, 5, ...
In this way, we can make sure there is always a gap between the same characters

Consider this example: "aaabbbcdd", we will construct the string in this way:
a _ a _ a _ _ _ _ // fill in "a" at position 0, 2, 4
a b a _ a _ b _ b // fill in "b" at position 6, 8, 1
a b a c a _ b _ b // fill in "c" at position 3
a b a c a d b d b // fill in "d" at position 5, 7

always first put the most number character
for example => "aaabb" a _ a _ a => ababa is ture
                       b _ b _ _ => babaa is false

*/
public class Solution {
    public String reorganizeString1(String s) {
        int[] charCnt = new int[26];
        for(int i = 0; i < s.length(); i++){
            charCnt[s.charAt(i) - 'a']++;
            if(charCnt[s.charAt(i) - 'a'] > (s.length() + 1) / 2) return "";
        }
        int max = 0, letter = 0;
        for (int i = 0; i < charCnt.length; i++) {
            if (charCnt[i] > max) {
                max = charCnt[i];
                letter = i;
            }
        }
        Character[] res = new Character[s.length()];
        int index = 0;
        while(charCnt[letter] > 0){
            res[index] = (char) (letter + 'a');
            index += 2;
            charCnt[letter]--;
        }
        for(int i = 0; i < 26; i++){
            while(charCnt[i] > 0){
                if(index >= s.length()){
                    index = 1;
                }
                res[index] = (char) (i + 'a');
                index += 2;
                charCnt[i]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Character c : res) sb.append(c);

        return sb.toString();
    }

    public String reorganizeString(String s) {
        int[] charCnt = new int[26];
        for(int i = 0; i < s.length(); i++){
            charCnt[s.charAt(i) - 'a']++;
            if(charCnt[s.charAt(i) - 'a'] > (s.length() + 1) / 2) return "";
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((c1, c2) -> (charCnt[c2 - 'a'] - charCnt[c1 - 'a']));
//        for(int i = 0; i < 26; i++){
//            if(charCnt[i] > (s.length() + 1) / 2) return "";
//        }
        for(Character c : s.toCharArray()){
            if(!pq.contains(c)) pq.add(c);
        }

        StringBuilder sb = new StringBuilder();
        while(pq.size() > 1){
            Character m = pq.poll();
            Character n = pq.poll();
            sb.append(m).append(n);
            charCnt[m - 'a']--;
            charCnt[n - 'a']--;
            if(charCnt[m - 'a'] > 0) pq.add(m);
            if(charCnt[n - 'a'] > 0) pq.add(n);
        }

        if(pq.size() == 1) sb.append(pq.poll()); // when length is odd, the size = 1

        return sb.toString();
    }


    public static void main(String[] args) {

    }
}
