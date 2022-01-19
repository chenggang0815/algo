package Facebook._0556_Next_Greater_Element_III;
/*
556. Next Greater Element III
Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.

Example 1:
Input: n = 12
Output: 21

Example 2:
Input: n = 21
Output: -1

Constraints:
1 <= n <= 231 - 1
*/
/*
Solution
Approach 1: brute force

Approach 2:
1. from right to left, find the first point decrease and these two number are neighboring=> nums[i - 1] < nums[i]
2. from right to left, find the first num, => nums[i - 1] < nums[j], swap nums[i - 1] and nums[j]
3. reverse from nums[i] to nums[nums.length - 1], make sure from num[i] to nums[nums.length - 1] is increase

for example:
    nums = 4  3    7  6  3  2 => first pair 3 < 7
              i-1  i
                     j
    nums = 4  6    7  3  3  2 > 4  3 7  6  3  2

    nums = 4 6 2 3 3 7 <   6    7  3  3  2
*/
public class Solution {
    public int nextGreaterElement(int n) {
        char[] ch = String.valueOf(n).toCharArray();
        for(int i = ch.length - 1; i > 0; i--){
            if(ch[i] > ch[i - 1]){
                for(int j = ch.length - 1; j >= i; j--){
                    if(ch[j] > ch[i - 1]){
                        char c = ch[j];
                        ch[j] = ch[i - 1];
                        ch[i - 1] = c;
                        reverse(ch, i, ch.length - 1);
                        long res = Long.parseLong(String.valueOf(ch));
                        return res > Integer.MAX_VALUE ? -1 : (int) res;
                    }
                }
            }
        }

        return -1;
    }

    void reverse(char[] ch, int i, int j){
        while(i < j){
            char c = ch[i];
            ch[i] = ch[j];
            ch[j] = c;
            i++;
            j--;
        }
    }
    public static void main(String[] args) {

    }
}
