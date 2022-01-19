package Facebook._1344_Angle_Between_Hands_of_a_Clock;
/*
1344. Angle Between Hands of a Clock
Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.
Answers within 10-5 of the actual value will be accepted as correct.
Example 1:
Input: hour = 12, minutes = 30
Output: 165
*/
/*
Solution
Approach 1:
1. calculate the degree of the minutes => minutest / 60.0 * 360.0
2. calculate the degree of the hour => hour / 12.0 * 360.0 + minutest / 60.0 * 30.0
    2.1 corner case, if hour = 12 => hour = 0
3. if the final degree > 180 => return 360 - 180
*/
public class Solution {
    public double angleClock(int hour, int minutes) {
        hour = hour == 12 ? 0 : hour;
        double minDegree = minutes / 60.0 * 360;
        double hourDegree = hour / 12.0 * 360 + minutes / 60.0 * 30.0;
        double res = Math.abs(hourDegree - minDegree);

        return  res > 180.0 ? 360.0 - res : res;
    }

    public static void main(String[] args) {

    }
}
