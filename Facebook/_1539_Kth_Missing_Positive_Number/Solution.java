package Facebook._1539_Kth_Missing_Positive_Number;
/*
1539. Kth Missing Positive Number
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
Find the kth positive integer that is missing from this array.

Example 1:
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

Example 2:
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

Constraints:
1. 1 <= arr.length <= 1000
2. 1 <= arr[i] <= 1000
3. 1 <= k <= 1000
4. arr[i] < arr[j] for 1 <= i < j <= arr.length
*/
/*
Solution
Approach 1:
1. use 3 variable to record,
  i: the index of the array
  num: the positive integer
  cnt: the number of the missing positive integer
2. initialize
  i = 0
  num = 1
  cnt = 0
3. iterate the array,
    3.1 if the current number arr[i] != num, which means we find a missing number => num++
                                                                                    cnt++
    3.2 if arr[i] == num, => i++
                             num++
    3.3 if cnt == k => return num

4. cnt < k => return arr[i-1] + (k-cnt) // [2,4] k=5
*/
public class Solution {
    public int findKthPositive(int[] arr, int k) {
        int num = 1;
        int cnt = 0;
        int i = 0;
        while(i < arr.length){
            if(arr[i] != num){
                cnt++;
                if(cnt == k) return num;
                num++;
            }else{
                num++;
                i++;
            }
        }

        return arr[i - 1] + (k - cnt);
    }
    public static void main(String[] args) {

    }
}
