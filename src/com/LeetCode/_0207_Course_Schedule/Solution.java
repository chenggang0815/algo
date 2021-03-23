package com.LeetCode._0207_Course_Schedule;

import java.lang.reflect.Array;
import java.util.*;

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

*/
/*
拓扑排序

本题可约化为 => 判断课程安排图是否是有向无环图(DAG)。即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立

1. 把一个有向无环图转成线性的排序就叫拓扑排序
2. 有向图有入度和出度的概念
    2.1 入度：顶点的入度是=>「指向该顶点的边」的数量
    2.2 出度：顶点的出度是=> 该顶点指向其他点的边的数量
如果存在一条有向边A->B，则这条边给A增加了1个出度，给B增加了1个入度

入度数组：课号 0 到 n - 1 作为索引，通过遍历先决条件表求出对应的初始入度


邻接表：用哈希表记录依赖关系（也可以用二维矩阵，但有点大）
key：课号
value：依赖这门课的后续课（数组）

思路1： 拓扑排序 - bfs

思路2： 拓扑排序 - dfs


输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

int[][] prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
    0
   / \
  1  2
  \  /
   3
*/
public class Solution {
    static boolean canFinish1(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        ArrayList<Integer> res = new ArrayList<>();

        for (int[] nums: prerequisites){
            indegrees[nums[0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (indegrees[i] == 0){
                queue.offer(i);
                res.add(i);
            }
        }

        while (!queue.isEmpty()){
           int curNode = queue.poll();
           for(int i = 0; i < prerequisites.length; i++){
               int[] node = prerequisites[i];
               //{1,0}
               if (node[1] == curNode){
                   indegrees[node[0]]--;
                   if (indegrees[node[0]] == 0){
                       res.add(node[0]);
                       queue.offer(node[0]);
                   }

               }
           }
        }

        return res.size() == numCourses;
    }


    static boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];

        for (int[] nums: prerequisites){
            indegrees[nums[0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (indegrees[i] == 0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            int curNode = queue.poll();
            for(int[] node :prerequisites){
                numCourses--;
                //{1,0}
                if (node[1] == curNode){
                    indegrees[node[0]]--;
                    if (indegrees[node[0]] == 0){
                        queue.offer(node[0]);
                    }

                }
            }
        }

        return numCourses == 0;
    }

    static boolean canFinish3(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // Get the indegree and adjacency of every course.
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }

        System.out.println(adjacency);
        return true;
    }


    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        System.out.println(canFinish3(4, prerequisites));
    }
}
