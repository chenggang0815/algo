package Facebook._0528_Random_Pick_with_Weight;
/*
528. Random Pick with Weight
You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it.
The probability of picking an index i is w[i] / sum(w).
For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).

Example 2:
Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

Since this is a randomization problem, multiple answers are allowed.
All of the following outputs can be considered correct:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
*/

/*
Solution
Approach 1. prefixSum + linear search => time for pickIndex: O(n)
1. calculate the probability for each value
2.
w =           [1,   2,  3,   4]
weight =      0.1  0.2 0.3  0.4

if the random value between 0 and 0.1, account for 0.1 => return 0
if the random value between 0.1 and 0.3, account for 0.2 => return 1
if the random value between 0.3 and 0.6, account for 0.3 => return 2
if the random value between 0.6 and 1, account for 0.4 => return 3

3. so we can calculate a cumulative probability for each index, if the first cumulative probability > random value => return index

for example :
w =           [1,   2,  3,   4]
weight =      0.1  0.2 0.3  0.4
prefix sum =   0.1  0.3 0.6   1
random value = 0.35, random generate from [0,1]
use linear search find the first prefix_sum > 0.35 => prefix_sum[2] = 0.6 > 0.35 => return 2

Approach 2: prefixSum + binary search => time for pickIndex: O(nlog(n))
use binary search find the first prefix_sum > 0.35 => prefix_sum[2] = 0.6 > 0.35 => return 2
*/
public class Solution {

    double[] weight; // the default data type of the float point value in java is double, not the float
    public Solution(int[] w) {
        weight = new double[w.length];
        int sum = 0;
        for(int num : w) sum += num;
        weight[0] =  (1.0 * w[0]) / sum;
        for(int i = 1; i < w.length; i++){
            weight[i] = weight[i - 1] + (1.0 * w[i]) / sum;
        }
    }

    public int pickIndex() {
        double point = Math.random();
        return binarySearch(weight, point);
    }

    public int binarySearch(double[] weight, double point){
        // 0.1 0.3 0.5 0.7 1.0
        // 0.34 => the first nums[mid] > point
        int left = 0;
        int right = weight.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(weight[mid] > point){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {

    }
}
