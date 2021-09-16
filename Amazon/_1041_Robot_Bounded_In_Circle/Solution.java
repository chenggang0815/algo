package Amazon._1041_Robot_Bounded_In_Circle;
/*
1041. Robot Bounded In Circle

On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degrees to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

Example 1:
Input: instructions = "GGLLGG"
Output: true
Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.

Constraints:
1 <= instructions.length <= 100
instructions[i] is 'G', 'L' or, 'R'.

    up right down left
    0   1     2    3
left => -1
right => +1

if and only if there not exists a circle => performs the instructions once and the position is not (0, 0) && direction is not north

一次指令之后，只有(x,y)不是原点，并且方向和原来的方向一致，最后才回不去
 */
public class Solution {
    static boolean isRobotBounded(String instructions){
        int x = 0;
        int y = 0;
        int d = 0;
        for (int i = 0; i < instructions.length(); i++){
            char c = instructions.charAt(i);
            if (c == 'L'){
                if (d == 0) d = 3;
                else d--;
            }else if (c == 'R'){
                if (d == 3) d = 0;
                else d++;
            }else {
                if (d % 4 == 0){
                    y++;
                }else if (d % 4 == 1){
                    x++;
                }else if (d % 4 == 2){
                    y--;
                }else {
                    x--;
                }
            }
        }
        /*
        if and only if there not exists a circle => performs the instructions once and the position is not (0, 0) && direction is not north
        一次指令之后，只有(x,y)不是原点，并且方向和原来的方向一致，最后才回不去
         */
        return (x == 0 && y == 0 || d != 0) ;
    }

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGG"));
    }
}
