package LeetCode._0207_Course_Schedule;
import java.util.*;
/*
207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
*/

/*
Solution: Topological Sort

本题可约化为 => 判断课程安排图是否是有向无环图(DAG)。即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立

1. 把一个有向无环图转成线性的排序就叫拓扑排序
2. 有向图有入度和出度的概念
    2.1 入度：顶点的入度是=>「指向该顶点的边」的数量
    2.2 出度：顶点的出度是=> 该顶点指向其他点的边的数量
如果存在一条有向边A->B，则这条边给A增加了1个出度，给B增加了1个入度

入度数组：课号0到n-1作为索引，通过遍历先决条件表求出对应的初始入度

邻接表：用哈希表记录依赖关系（也可以用二维矩阵，但有点大）
key：课号
value：依赖这门课的后续课（数组）

思路1： 拓扑排序 - bfs
1. 首先用一个数组代替hashmap，统计每个结点的入度
2. 因为只能从入度为0的结点开始，所以
    2.1 申请一个queue，把所有入度为0的结点放到queue中
    2.2 申请一个list，把所有入度的为0的结点都放到list中
3. 当queue不为空时，从queue中拿出一个结点
    3.1 如果图中有其他结点依赖当前节点，那么更新此节点的入度 => 入度减1 => 入度减1后如果为0，那么此节点也可以被选中，加入queue和list中

4. 当queue为空后，如果list中的结点数和图中的结点数相等，那么所有结点都可以被选中，存在拓扑排序

思路2： 拓扑排序 - dfs

int[][] prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
                                    0->1   0->2  1->3  2->3
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
            if (indegrees[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()){
            int curNode = queue.poll();
            for(int[] node :prerequisites){
                numCourses--;
                if (node[1] == curNode){
                    indegrees[node[0]]--;
                    if (indegrees[node[0]] == 0) queue.offer(node[0]);
                    // 这个if必须在if (node[1] == curNode)里面，否则会无限循环
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
