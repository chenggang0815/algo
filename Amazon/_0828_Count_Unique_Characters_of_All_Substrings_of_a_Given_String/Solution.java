package Amazon._0828_Count_Unique_Characters_of_All_Substrings_of_a_Given_String;
import java.util.Arrays;
/*
828. Count Unique Characters of All Substrings of a Given String

Let's define a function countUniqueChars(s) that returns the number of unique characters on s.
For example if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.
Notice that some substrings can be repeated so in this case you have to count the repeated ones too.

Example 1:
Input: s = "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 */
/*
Solution:

1. Instead of counting all unique characters and struggling with all possible substrings,
   we can count for every char in S, how many ways to be found as a unique char.

   for example "L E E T C O D E" for "T"
                0 1 2 3 4 5 6 7
   left = 0
   right = 7
   it means "T" is a unique character in [0,7]
   then "T" can contribute => 4*5=20 substring in the result


how to calculate left index and right index?
    prev=[-1]*26  prev[i] means the previous index of A+i character
    left=[-1]*26  left[i] means the previous index of S[i]
    A C B C
    0 1 2 3
    when i=1 => left[i]=prev[s[i]-'A'] => left[1]=-1
                prev[s[i]-A]=i => prev[2]=1
    when i=3 => left[i]=prev[s[i]-'A'] => left[3]=1
                prev[s[i]-A]=i => prev[2]=3

    A C B C
    0 1 2 3
    when i=3 => right[i]=prev[s[i]-'A'] => right[3]=4
                prev[s[i]-A]=i => prev[2]=3
    when i=1 => right[i]=prev[s[i]-'A'] => right[1]=3
                prev[s[i]-A]=i => prev[2]=1

https://leetcode-cn.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/solution/c-you-li-zi-yi-dong-by-smilyt_/

*/
public class Solution {
    static public int uniqueLetterString(String s) {
        int[] left = new int[s.length()];
        //Arrays.fill(left, -1);
        int[] right = new int[s.length()];
        //Arrays.fill(right, -1);

        int[] prev = new int[26];
        Arrays.fill(prev,-1);
        for (int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'A';
            left[i] = prev[index];
            prev[index] = i;
        }
        Arrays.fill(prev, s.length());
        for (int i = s.length() - 1; i >= 0; i--){
            int index = s.charAt(i) - 'A';
            right[i] = prev[index];
            prev[index] = i;
        }

        int res = 0;
        for (int i = 0; i < s.length(); i++){
            res += (i - left[i]) * (right[i] - i);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(uniqueLetterString("AABC"));
    }
}
