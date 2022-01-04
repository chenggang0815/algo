package Facebook.git;
/*
408. Valid Word Abbreviation

A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.
For example, a string such as "substitution" could be abbreviated as (but not limited to):
"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)

The following are not valid abbreviations:
"s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
"s010n" (has leading zeros)
"s0ubstitution" (replaces an empty substring)

Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
A substring is a contiguous non-empty sequence of characters within a string.

Example 1:
Input: word = "internationalization", abbr = "i12iz4n"
Output: true
Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
*/
/*
Solution
Approach 1
1. iterate the abbr and the word
2. if current char is character, compare the abbr.charAt(i) and word.charAt(index)
3. if current char is digit, index = index + digit
4. if index == word.length and i == abbr.length => return true
*/
public class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int index = 0;
        int i = 0;
        while(i < abbr.length() && index < word.length()){
            if(Character.isAlphabetic(abbr.charAt(i))){
                if(abbr.charAt(i) == word.charAt(index)){
                    index++;
                    i++;
                }else return false;
            }else{
                if(abbr.charAt(i) == '0') return false; // has leading zeros
                StringBuilder length = new StringBuilder();
                while(i < abbr.length() && Character.isDigit(abbr.charAt(i))){
                    length.append(abbr.charAt(i));
                    i++;
                }
                index += Integer.valueOf(length.toString());
                //if(i < abbr.length() && index >= word.length()) return false;
            }
        }

        return i == abbr.length() && index == word.length();
    }

    public static void main(String[] args) {
        String s = "0hello";
        if (Character.i(s.charAt(0))){
            System.out.println("===");
        }
    }
}
