package com.LeetCode._0207_Course_Schedule;
/*
207. Course Schedule

你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

示例 2：
输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/course-schedule
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/*
把一个有向无环图转成线性的排序就叫拓扑排序
有向图有入度和出度的概念：
如果存在一条有向边 A --> B，则这条边给 A 增加了 1 个出度，给 B 增加了 1 个入度

入度数组：课号 0 到 n - 1 作为索引，通过遍历先决条件表求出对应的初始入度。
邻接表：用哈希表记录依赖关系（也可以用二维矩阵，但有点大）
key：课号
value：依赖这门课的后续课（数组）

 */
/*
思路： 拓扑排序

本题可约化为 => 判断课程安排图是否是有向无环图(DAG)。即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立


 */
public class Solution {

    public static void main(String[] args) {
        System.out.println();
    }
}
