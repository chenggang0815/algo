package Facebook._1209_Remove_All_Adjacent_Duplicates_in_String_II;

import java.util.Stack;
/*
1209. Remove All Adjacent Duplicates in String II
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
*/
/*
Solution
Approach 1: brute force
Approach 2: use array to record
1. for each character, if count[i] = count[i - 1] + 1 = k
2. we delete the character from i-k+1 to i
Approach 3: use stack to record
*/
public class Solution {
    // a b b b a a k=3
    // 0 1 2 3 4 5
    public String removeDuplicates1(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[s.length()];
        for(int i = 0; i < sb.length(); i++){
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)){
                count[i] = 1;
            }else{
                count[i] = count[i - 1] + 1;
                if(count[i] == k){
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }

        return sb.toString();
    }

    public String removeDuplicates2(String s, int k) {
        Stack<Integer> count = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < sb.length(); i++){
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)){
                count.push(1);
            }else{
                int cnt = count.pop() + 1;
                if(cnt == k){
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }else{
                    count.push(cnt);
                }
            }
        }

        return sb.toString();
    }
    public static void main(String[] args) {

    }
}
