package LeetCode._0210_Course_Schedule_II;

import java.util.LinkedList;
import java.util.Queue;

/*
210. Course Schedule II

There are a total of n courses you have to take labelled from 0 to n - 1.

Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.

If there are many valid answers, return any of them.
If it is impossible to finish all courses, return an empty array.

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

    0
   / \
  1   2
   \ /
    3
*/

/*
思路1： 拓扑排序 - bfs
1. 首先用一个数组代替hashmap，统计每个结点的入度
2. 因为只能从入度为0的结点开始，所以
    2.1 申请一个queue，把所有入度为0的结点放到queue中
    2.2 申请一个list，把所有入度的为0的结点都放到list中
3. 当queue不为空时，从队列中拿出一个结点
    3.1 如果图中有其他结点依赖当前节点，那么更新此节点的入度 => 入度减一 => 入度减一后如果为0，那么此节点也可以被选中，加入queue和list中

4. 当queue为空后，如果list中的结点数和图中的结点数相等

5. 那么所有结点都可以被选中，存在拓扑排序 => 且list中元素的顺序是图的一个拓扑排序的顺序 => return list

思路1： 拓扑排序 - dfs
*/
public class Solution {
    static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        int[] res = new int[numCourses];
        int index = 0;

        for (int[] nums: prerequisites){
            indegrees[nums[0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (indegrees[i] == 0){
                queue.offer(i);
                res[index++] = i;
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
                        res[index++] = node[0];
                        queue.offer(node[0]);
                    }

                }
            }
        }

        if (index == numCourses){
            return res;
        }else {
            return new int[]{};
        }

    }

    public static void main(String[] args) {

        int[][] prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        findOrder(4, prerequisites);
    }
}
