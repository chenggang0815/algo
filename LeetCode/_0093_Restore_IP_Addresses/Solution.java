package LeetCode._0093_Restore_IP_Addresses;
/*
93. Restore IP Addresses

A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

Example 1:
Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]

Example 2:
Input: s = "0000"
Output: ["0.0.0.0"]

Example 3:
Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

Constraints:
0 <= s.length <= 20
s consists of digits only.
*/

import java.util.ArrayList;
import java.util.List;

/*
Solution:
example: 25525511135 => "255.255.111.35" "255.255.11.135"
1. base the ip format, we will have most 4 layer for the recursion tree
2. for current layer, we can choose put the dot('.') after 1/2/3 digit, because section in ip at most have 3 digits.
3. if the current section > 255 or section have more the one digit and start with 0 => break
4. if section number == 4 and we reach the last character of the string, we get a valid ip.
*/
public class Solution {
    static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();

        backTracking(res, s, "", 0, 0);

        return res;
    }

    static void backTracking(List<String> res, String s, String ip, int sec, int index){
        if (sec == 4 && index == s.length()){
            res.add(ip);
            return;
        }

        if (sec >= 4) return;

        for(int i = 1; i <= 3; i++){
            if (index + i > s.length()) break;
            String section = s.substring(index, index + i);
            if (section.length() > 1 && section.startsWith("0") || Integer.parseInt(section) > 255) break;
            //ip = sec == 0 ? section : ip + "." + section;
            backTracking(res, s, sec == 0 ? section : ip + "." + section, sec + 1, index + i);
        }

    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("012201"));
    }

}
