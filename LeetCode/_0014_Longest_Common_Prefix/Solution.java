package LeetCode._0014_Longest_Common_Prefix;
/*
14. Longest Common Prefix Easy
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".
Example 1:  Input: ["flower","flow","flight"]
Output: "fl"
Example 2:  Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:   All given inputs are in lowercase letters a-z.
 */
public class Solution {
/*
Solution:
1. assume the first string is the longest common prefix for all strings
2. iterate the array, if the current string start with prefix, continue compare next string with prefix string
                      otherwise, delete the last char in the prefix, compare with the current string again, until prefix == ""
*/

/*
Time complexity : O(S), where S is the sum of all characters in all strings.
There are S character comparisons, where S is the sum of all characters in the input array.
Space: O(1)

{"radog","racecar","racar"}

prefix = "radog"
"racecar".indexOf(prefix) != 0 => prefix = rado
..
"racecar".indexOf(prefix) != 0 => prefix = ra
"racecar".indexOf(prefix) == 0

prefix = "ra"
"racar".indexOf(prefix) == 0
return prefix
*/
    static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++){
            while (!strs[i].startsWith(prefix)){
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.equals("")) return "";
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"radog","racecar","racar"};
        System.out.println(longestCommonPrefix(strs));
    }
}
