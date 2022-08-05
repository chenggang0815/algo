package LeetCode._0207_Course_Schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution2 {
    static boolean canFinish1(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pre : prerequisites){
            if (graph.containsKey(pre[1])){
                graph.get(pre[1]).add(pre[0]);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(pre[0]);
                graph.put(pre[1], list);
            }
        }

        System.out.println(graph);

        return true;
    }

    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1,0}, {2,0}, {3,1}, {4,3}};
        System.out.println(canFinish1(3, prerequisites));
    }
}
