package LeetCode._2063_Vowels_of_All_Substrings;
/*
2063. Vowels of All Substrings
Given a string word, return the sum of the number of vowels ('a', 'e', 'i', 'o', and 'u') in every substring of word.
A substring is a contiguous (non-empty) sequence of characters within a string.
Note: Due to the large constraints, the answer may not fit in a signed 32-bit integer. Please be careful during the calculations.

Example 1:
Input: word = "aba"
Output: 6
Explanation:
All possible substrings are: "a", "ab", "aba", "b", "ba", and "a".
- "b" has 0 vowels in it
- "a", "ab", "ba", and "a" have 1 vowel each
- "aba" has 2 vowels in it
Hence, the total sum of vowels = 0 + 1 + 1 + 1 + 1 + 2 = 6.

Example 2:
Input: word = "abc"
Output: 3
Explanation:
All possible substrings are: "a", "ab", "abc", "b", "bc", and "c".
- "a", "ab", and "abc" have 1 vowel each
- "b", "bc", and "c" have 0 vowels each
Hence, the total sum of vowels = 1 + 1 + 1 + 0 + 0 + 0 = 3.
*/

/*
Solution
Approach 1
For each vowels s[i],
it could be in the substring starting at s[x] and ending at s[y],
where 0 <= x <= i and i <= y < n,
that is (i + 1) choices for x and (n - i) choices for y.
        //      a   b   a
        //i+1  1   0   3
        //n-i  3   0   1
        //     1*3 +   3*1 = 6
        //  a ab aba   a ba aba

        //      a    b    a      a
        //      1    0    3      4
        //      4    0    2      1
        //     a ab      a ab
        //     aba       aba abaa
        //     abaa      baa aa
Approach 2: dp

*/
public class Solution {
    public long countVowels(String word) {
        long res = 0;
        long n = word.length();
        for(int i = 0; i < word.length(); i++){
            if("aeiou".indexOf(word.charAt(i)) >= 0){
                res += (i + 1)*(n - i);
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
