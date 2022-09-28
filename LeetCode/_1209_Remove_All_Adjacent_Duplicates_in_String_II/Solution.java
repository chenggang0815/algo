package LeetCode._1209_Remove_All_Adjacent_Duplicates_in_String_II;

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
/*
Approach 2:
1. because string is immutable in Java, so we can use StringBuilder to operate it.
for example:
we use a count array to store the frequency for each position until we find a position meet the k times requirement,
then we can delete this substring from this string.

if we just use a variable cnt to keep track the frequency of current character, when we delete the substring, we may lose the frequency information for previously character
for example:
d d b b c c c   b d a a k=3
            i
           cnt=3
after we delete c c c from this string,we update index i and we have:
d d b b b d a a k=3
        i
       cnt=1
we can't have the previous frequency information.

instead of using a variable, we can use an int array.
after we delete the substring and update the index i,
we can use previous frequency information to update the count array again.

    //         d e e e d b b c c c b d a a k=3
    //   i     0 1 2 3
    //count    1 1 2 3
    //         d d b b c c c b d a a k=3
    //   i     0 1 2 3 4 5
    //count    1 2 1 2 1 2 3
    //         d d b b b d a a k=3
    //count    1 2 1 2 3
    //         d d d a a k=3
    //count    1 2 3
    //         a a k=3
    //count    1 2
*/

/*
Approach 3:
1. we can use a stack to keep track the frequency of the current character.
2. if current char == previously char => stack.push(stack.pop() + 1)
3. if the current character's frequency == k, we delete this substring, and
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
                    i = i - k; // we will operate i++ after this line, so we don't need to use i = i - k + 1
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

    static String removeDuplicates3(String s, int k) {
        Stack<Integer> count = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < sb.length(); i++){
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)){
                count.push(1);
            }else{
                int cnt = count.pop() + 1;
                if(cnt >= k && (i == sb.length() - 1 || sb.charAt(i) != sb.charAt(i + 1))){
                    sb.delete(i - cnt + 1, i + 1);
                    i = i - cnt;
                }else{
                    count.push(cnt);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "aaabbbbc";
        System.out.println(removeDuplicates3(s, 3));
    }
}
