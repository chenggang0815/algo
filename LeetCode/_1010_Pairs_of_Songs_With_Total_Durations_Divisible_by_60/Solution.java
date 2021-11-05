package LeetCode._1010_Pairs_of_Songs_With_Total_Durations_Divisible_by_60;
/*
1010. Pairs of Songs With Total Durations Divisible by 60

You are given a list of songs where the ith song has a duration of time[i] seconds.
Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

Example 1:
Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
*/
/*
Solution
for a, b two number, exists two case meet have a total duration divisible by 60
1. a%60=0 b%60=0
2. (a+b)%60=0 => a%60 + b%60 = 60
*/
public class Solution {

    public int numPairsDivisibleBy60(int[] time) {
        int[] nums = new int[60];
        int res = 0;
        for(int num : time){
            if(num % 60 == 0){
                res += nums[0];
            }else{
                // 60 - num%60 => a%60 + b%60 = 60
                res += nums[60 - num % 60];
            }
            nums[num % 60]++;
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
