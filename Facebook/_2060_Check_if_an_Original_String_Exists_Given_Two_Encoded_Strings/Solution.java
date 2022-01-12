package Facebook._2060_Check_if_an_Original_String_Exists_Given_Two_Encoded_Strings;
/*
2060. Check if an Original String Exists Given Two Encoded Strings

An original string, consisting of lowercase English letters, can be encoded by the following steps:
    1. Arbitrarily split it into a sequence of some number of non-empty substrings.
    2. Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
    3. Concatenate the sequence as the encoded string.

For example, one way to encode an original string "abcdefghijklmnop" might be:
    1. Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
    2. Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
    3. Concatenate the elements of the sequence to get the encoded string: "ab121p".

Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive),
return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.

Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.

Example 1:
Input: s1 = "internationalization", s2 = "i18n"
Output: true
Explanation: It is possible that "internationalization" was the original string.
- "internationalization"
  -> Split:       ["internationalization"]
  -> Do not replace any element
  -> Concatenate:  "internationalization", which is s1.
- "internationalization"
  -> Split:       ["i", "nternationalizatio", "n"]
  -> Replace:     ["i", "18",                 "n"]
  -> Concatenate:  "i18n", which is s2

Example 2:
Input: s1 = "l123e", s2 = "44"
Output: true
Explanation: It is possible that "leetcode" was the original string.
- "leetcode"
  -> Split:      ["l", "e", "et", "cod", "e"]
  -> Replace:    ["l", "1", "2",  "3",   "e"]
  -> Concatenate: "l123e", which is s1.
- "leetcode"
  -> Split:      ["leet", "code"]
  -> Replace:    ["4",    "4"]
  -> Concatenate: "44", which is s2.
*/

/*
Solution
https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings/discuss/1550342/Java-Clean-(DFS-%2B-memo)
*/
public class Solution {
    public boolean possiblyEquals(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        // dp[i][j][diff] means if s1[i:] truncated by <diff> characters if diff > 0
        // and s2[j:] truncated by <-diff> characters if diff < 0 are equal
        Boolean[][][] dp = new Boolean[l1 + 1][l2 + 1][2000];
        return dfs(0, 0, 0, s1.toCharArray(), s2.toCharArray(), dp);
    }

    private boolean dfs(int i, int j, int diff, char[] s1, char[] s2, Boolean[][][] dp) {
        if (i == s1.length && j == s2.length) {
            return diff == 0;
        }

        if (dp[i][j][diff + 1000] != null) return dp[i][j][diff + 1000];

        // Literal matching on s1[i] and s2[j]
        if (i < s1.length && j < s2.length && diff == 0 && s1[i] == s2[j]) {
            if (dfs(i + 1, j + 1, 0, s1, s2, dp)) {
                return dp[i][j][1000] = true;
            }
        }

        // Literal matching on s1[i]
        if (i < s1.length && !isDigit(s1[i]) && diff > 0 && dfs(i + 1, j, diff - 1, s1, s2, dp)) {
            return dp[i][j][diff + 1000] = true;
        }

        // Literal matching on s2[j]
        if (j < s2.length && !isDigit(s2[j]) && diff < 0 && dfs(i, j + 1, diff + 1, s1, s2, dp)) {
            return dp[i][j][diff + 1000] = true;
        }

        // Wildcard matching on s1[i]
        for (int k = i, val = 0; k < s1.length && isDigit(s1[k]); ++k) {
            val = val * 10 + (s1[k] - '0');
            if (dfs(k + 1, j, diff - val, s1, s2, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        // Wildcard matching on s2[j]
        for (int k = j, val = 0; k < s2.length && isDigit(s2[k]); ++k) {
            val = val * 10 + (s2[k] - '0');
            if (dfs(i, k + 1, diff + val, s1, s2, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        return dp[i][j][diff + 1000] = false;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {

    }
}
