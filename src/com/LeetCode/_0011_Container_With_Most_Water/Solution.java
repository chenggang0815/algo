package com.LeetCode._0011_Container_With_Most_Water;
/*
11. Container With Most Water
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container.
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
In this case, the max area of water (blue section) the container can contain is 49.
 */

/*
思路1：暴力计算所有的组合 o(n^2)
思路2：双指针（每次移动短板减少搜索空间） o(n)
面积取决于短板：
1. 因此即使长板往内移动时遇到更长的板，矩形的面积也不会改变；遇到更短的板时，面积会变小。
2. 因此想要面积变大，只能让短板往内移动(因为移动方向固定了)，当然也有可能让面积变得更小，但只有这样才存在让面积变大的可能性

初始状态：
我们设置两个指针 left 和 right，分别指向数组的最左端和最右端。
此时，两条垂直线的距离是最远的，若要下一个矩阵面积比当前面积来得大，必须要把 height[left] 和 height[right] 中较短的垂直线往中间移动，看看是否可以找到更长的垂直线。

作者：jalan
链接：https://leetcode-cn.com/problems/container-with-most-water/solution/shuang-zhi-zhen-jie-fa-by-jalan/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution {
    static int maxArea(int[] height){
        int maxArea = 0;
        for (int i = 0; i < height.length; i++){
            for (int j = i; j < height.length; j++){
                int area = Math.min(height[i], height[j]) * (j - i);
                if (area > maxArea) maxArea = area;
            }
        }

        return maxArea;
    }

    //双指针
    static int maxArea2(int[] height){
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right){
            int area = Math.min(height[left], height[right]) * (right - left);
            if(area > maxArea) maxArea = area;
            if (height[left] <= height[right]){
                left++;
            }else{
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea2(new int[] {1,8,6,2,5,4,8,3,7}));
    }
}
