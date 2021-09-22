package Amazon._1710_Maximum_Units_on_a_Truck;

import java.util.Arrays;
/*
1710. Maximum Units on a Truck

You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
Return the maximum total number of units that can be put on the truck.
 */
public class Solution {
    static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        int res = 0;
        int i = 0;
        while(truckSize > 0 && i < boxTypes.length){ // because truck size maybe bigger than the number of all the boxes
            if(truckSize > boxTypes[i][0]){
                res += boxTypes[i][1] * boxTypes[i][0];
                truckSize -= boxTypes[i][0];
                System.out.println(truckSize);
                i++;
                System.out.println(i);
            }else{
                res += boxTypes[i][1] * truckSize;
                truckSize = 0;
            }
        }

        return res;
    }
    public static void main(String[] args) {
//        int[][] nums = new int[][]{{5,10}, {4, 9}, {6,11}};
//        Arrays.sort(nums, (a,b) -> b[1] - a[1]);
//        for (int[] num: nums){
//            System.out.println(Arrays.toString(num));
//        }
        int[][] nums = new int[][]{{1,3},{5,5},{2,5},{4,2},{4,1},{3,1},{2,2},{1,3},{2,5},{3,2}};
        int truckSize = 35;
        maximumUnits(nums, truckSize);
    }
}
