package Amazon._0370_Range_Addition;
/*
370. Range Addition
You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].
You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
Return arr after applying all the updates.
*/
/*
Solution:
Approach1: brute force time:O(n*k)

Approach2 time; O(n+k)

1. We update the value at start index, because it will be used in the future when we are adding up the values for the sum at each index between start index and end index (both inclusive).
2. We update the negative value at the end index + 1, because the positive value of it should be only added at its previous indices (from start index to end index).
3. Thus, when we accumulate the sum at the end for each index, we will get the correct values for each index.
4. If the end index is the last index in the resulting array, we don't have to do the end index + 1 part, because there is no more index after the last index and there will be no error when we accumulate the sum.

Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]

                [1,3,2]
nums=[0,0,0,0,0]  =>  [0,2,0,0,-2]
      0 1 2 3 4
sum=>[0,2,2,2,0]
sum[0]=num[0]
sum[1]=num[0]+nums[1]=0+2=2
sum[2]=num[0]+nums[1]+nums[2]=0+2+0=2
sum[3]=num[0]+nums[1]+nums[2]+nums[3]=0+2+0+0=2
sum[4]=num[0]+nums[1]+nums[2]+nums[3]+nums[4]=0+2+0+0-2=0

           [1,3,2]           [2,4,3]
[0,0,0,0,0]  =>  [0,2,0,0,-2] => [0,2,3,0,-2]
 0 1 2 3 4
sum=>[0,2,5,5,3]
sum[0]=num[0]
sum[1]=num[0]+nums[1]=0+2=2
sum[2]=num[0]+nums[1]+nums[2]=0+2+3=5
sum[3]=num[0]+nums[1]+nums[2]+nums[3]=0+2+3+0=5
sum[4]=num[0]+nums[1]+nums[2]+nums[3]+nums[4]=0+2+3+0-2=3
*/
public class Solution {
    public int[] getModifiedArray1(int length, int[][] updates) {
        int[] res = new int[length];
        for(int[] update : updates){
            int left = update[0];
            int right = update[1];
            for(int i = left; i <= right; i++){
                res[i] += update[2];
            }
        }

        return res;
    }

    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for(int[] update : updates){
            int left = update[0];
            int right = update[1];
            res[left] += update[2];
            if(right != length - 1) res[right + 1] += -update[2];
        }
        // [0,2,0,0,-2] => [0,2,2,2,0]
        for(int i = 1; i < length; i++){
            res[i] += res[i - 1];
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
