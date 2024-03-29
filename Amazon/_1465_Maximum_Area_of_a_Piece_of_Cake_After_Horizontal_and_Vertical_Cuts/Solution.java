package Amazon._1465_Maximum_Area_of_a_Piece_of_Cake_After_Horizontal_and_Vertical_Cuts;

import java.util.Arrays;

/*
1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts

You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.

example 1
Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4

example 2
Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6

base the two examples, we can find:
when h = 5 and horizontalCuts = [1,2,4] => [[0,1],[1,2],[2,4],[4,5]]  max h = 4 - 2 = 2
when w = 4 and verticalCuts = [1,3] => max w = 3 - 1 = 2
so the max area = 2 * 2 = 4
 */
public class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        if(horizontalCuts.length == 0 && verticalCuts.length == 0) return h * w;

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        long maxH = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
        long maxW = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
        for(int i = 1; i < horizontalCuts.length; i++){
            maxH = Math.max(horizontalCuts[i] - horizontalCuts[i - 1], maxH);
        }

        for(int i = 1; i < verticalCuts.length; i++){
            maxW = Math.max(verticalCuts[i] - verticalCuts[i - 1], maxW);

        }

        return (int) ((maxW * maxH) % (1000000007));

    }

    public static void main(String[] args) {

    }
}
