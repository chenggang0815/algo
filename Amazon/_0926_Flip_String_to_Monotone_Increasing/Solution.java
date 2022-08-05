package Amazon._0926_Flip_String_to_Monotone_Increasing;

import java.util.Arrays;
/*
Solution:
Approach1: time:O(n) space:O(n)
1. use two array to store the cumulative number of 1 from left and the cumulative number of 0 from right
two cases:
        ____1____ =>  0001111
            i            i
        ____0____ =>  0000111
            i            i
1. assume * is the mid point, we need to calculate the number of 1 on [0, i-1], and the number of 0 on [i+1, n-1]
2. then we iterate the string, calculate each i, and get the minimum value if we make i became the mid point
3. the number of operation for i => the number of 1 in [0, i-1] + the number of 0 in [i+1, n-1]

Approach2: Dynamic Programming time:O(n) space:O(1)
1. if s[i] == '1',  no more flip should be applied, since '1' is appended to the tail of the original string
  => dp[i] = dp[i - 1]
  => count the number of '1'
2. if s[i] == '0', we have two cases:
    1. flip s[i] from '0' to '1' => dp[i-1]+1
    2. flip all the '1' in s[0, i-1] to 0 => it need cnt number of operation
3. so we need get min(dp[i-1] + 1, cnt)
*/

public class Solution {
    static int minFlipsMonoIncr1(String s){
        int[] one = new int[s.length()];
        int[] zero = new int[s.length()];
        for (int i = 1; i < s.length(); i++){
            one[i] = s.charAt(i - 1) == '1' ? 1 + one[i - 1] : one[i - 1];
        }
        for (int i = s.length() - 2; i >= 0; i--){
            zero[i] = s.charAt(i + 1) == '0' ? 1 + zero[i + 1] : zero[i + 1];
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++){
            res = Math.min(res, one[i] + zero[i]);

        }

        return res;
    }

    static int minFlipsMonoIncr2(String s){
        int dp = 0, cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                ++cnt;
            } else {
                dp = Math.min(dp + 1, cnt);
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr2("0101100011"));
        //"0  1  0  1  1  0  0  0  1  1"
        //[0, 0, 1, 1, 2, 3, 3, 3, 3, 4]
        //[4, 4, 3, 3, 3, 2, 1, 0, 0, 0]

    }
}
