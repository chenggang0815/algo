package com.kernel.dynamic_programming;
/*
每日复习专用
 */
public class temporary {

    //1 91. Decode Ways
    /*
    A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


     */

    static int decode(String s){
        if (s.length()==0 || s.charAt(0)=='0') return 0;
        if (s.length()==1) return 1;
        char[] c = s.toCharArray();
        int[] dp = new int[c.length+1];
        dp[0] = 1;
        int m = (c[0]-'0')*10+c[1]-'0';
        if (c[1]!='0'&&m<=26){
            dp[1] = 2;
        }else if (c[1]!='0'&&m>26){
            dp[1] = 1;
        }else if (c[1]=='0'&&m>26){
            dp[1] = 0;
        }else if (c[1]=='0'&&m<=26){
            dp[1] = 1;
        }

        System.out.println(dp[1]);

        for (int i=2;i<c.length;i++){
            int n = (c[i-1]-'0')*10+c[i]-'0';
            if (c[i]!='0'&&c[i-1]!='0') {
                if (n>26) dp[i] = dp[i-1];
                else dp[i] = dp[i-1]+dp[i-2];
            }else if (c[i]!='0'&&c[i-1]=='0'){
                dp[i] = dp[i-1];
            }else if (c[i]=='0'&&c[i-1]!='0'){
                if (n>26) return 0;
                else dp[i] = dp[i-2];
            }else if (c[i]=='0'&&c[i-1]=='0')
                return 0;
        }

        return dp[c.length-1];
    }


    public static void main(String[] args) {
        String s = "100";
        String s2 = "101";
        String s3 = "110";

        System.out.println(decode(s2));

    }
}
