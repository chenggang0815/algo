package Amazon._0696_Count_Binary_Substrings;
/*
696. Count Binary Substrings

Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's,
and all the 0's and all the 1's in these substrings are grouped consecutively.
Substrings that occur multiple times are counted the number of times they occur.


Example 1:
Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

Example 2:
Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.

Constraints:
1 <= s.length <= 105
s[i] is either '0' or '1'.
*/

/*
Solution:
1. for a string "100" or "001", we can get two group of 0 and 1, and the cnt is [1,2] for "100" and [2,1] for "001"
2. the number of expected substrings is min(1,2)=1
    for "100" is "10"
    for "001" is "01"
3. so we can iterate the array, count the group, and get the number of expected substring

for example:
"1001110" => "100"       "0011"         "1110"
             min(1,2)=1  min(2,2)=2   min(3,1)=1
             "10"        "01" "0011"    "10"
             => res=1+2+1=4
*/
public class Solution {
    static int countBinarySubstrings(String s) {
        int cur = 1; int pre = 0;
        int res = 0;
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i - 1) != s.charAt(i)){
                res += Math.min(cur, pre);
                pre = cur;
                cur = 1;
            }else {
                cur++;
            }
        }

        return res + Math.min(cur, pre);
    }

    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("10011110"));
    }
}
