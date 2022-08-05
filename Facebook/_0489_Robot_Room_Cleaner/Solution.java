package Facebook._0489_Robot_Room_Cleaner;
/*
489. Robot Room Cleaner
You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.
The robot starts at an unknown location in the root that is guaranteed to be empty, and you do not have access to the grid, but you can move the robot using the given API Robot.
You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.
Design an algorithm to clean the entire room using the following APIs:
interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.
*/
/*
Solution:
https://leetcode.com/problems/robot-room-cleaner/discuss/153530/DFS-Logical-Thinking
*/
public class Solution {
    public static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

//    public void cleanRoom(Robot robot) {
//        clean(robot, 0, 0, 0, new HashSet<>());
//    }
//
//    private void clean(Robot robot, int x, int y, int curDirection, Set<String> cleaned) {
//        robot.clean();
//        cleaned.add(x + " " + y);
//
//        for (int i = 0; i < 4; i++) {
//            int nx = x + DIRS[curDirection][0];
//            int ny = y + DIRS[curDirection][1];
//            if (!cleaned.contains(nx + " " + ny) && robot.move()) {
//                clean(robot, nx, ny, curDirection, cleaned);
//                goBack(robot);
//            }
//            robot.turnRight();
//            curDirection = (curDirection + 1) % 4;
//        }
//
//    }
//
//    private void goBack(Robot robot) {
//        robot.turnRight();
//        robot.turnRight();
//        robot.move();
//        robot.turnRight();
//        robot.turnRight();
//    }

    public static void main(String[] args) {

    }
}
