package Amazon._0973_K_Closest_Points_to_Origin;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
973. K Closest Points to Origin

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

solution 1:  O(nlog(n))

solution 2: use head O(nlog(k))

PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b - a) 表示最大堆，堆顶为最大元素
*/
public class Solution {
    static int[][] kClosest1(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> a[0]*a[0] + a[1]*a[1] - b[0]*b[0] - b[1]*b[1]);

        return Arrays.copyOfRange(points, 0, k);
    }

    static int[][] kClosest2(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0]*b[0] + b[1]*b[1] - a[0]*a[0] - a[1]*a[1]);
        int i = 0;
        while (i < k){
            pq.add(points[i]);
            i++;
        }

        System.out.println(Arrays.toString(pq.peek()));

        while (i < points.length){
            int distance = points[i][0]*points[i][0] + points[i][1]*points[i][1];
            int[] peek = pq.peek();
            int peekDistance = peek[0]*peek[0] + peek[1]*peek[1];
            if (distance < peekDistance){
                pq.poll();
                pq.add(points[i]);
            }
            i++;
        }
        System.out.println(Arrays.toString(pq.peek()));


        int[][] res = new int[k][2];
        i = 0;
        while (!pq.isEmpty()){
            res[i] = pq.poll();
            i++;
        }

        return res;
    }


    public static void main(String[] args) {
        //int[][] points = new int[][]{{3,3},{5,-1},{-2,4}};
        int[][] points = new int[][]{{1,3},{-2,2},{2,-2}};
        int[][] res = kClosest2(points, 2);
//        for (int[] item: res){
//            System.out.println(Arrays.toString(item));
//        }
    }
}
