package Amazon._1492_The_kth_Factor_of_n;
/*
1492. The kth Factor of n

Given two positive integers n and k.
A factor of an integer n is defined as an integer i where n % i == 0.
Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.

Example 1:
Input: n = 12, k = 3
Output: 3
Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
solution 1: brute force time: O(n)
1. iterate 1,2,...,n, find all the factor
eg: n=12, k=3 => factor = 1,2,3,4,6,12

solution 2: brute force time: O(n)
1. iterate 1,2,...,n/2+1, find all the factor, except n
eg: n=12, k=6 => 1,2,3,4,6 => return n=12

solution 3: heap time: O(sqrt(n)*log(k))
1. iterate 1,2,...,sqrt(n)+1, find all the factor
eg: n=12, k=3 => (1,12) (2,6) (3,4)
maintain a max heap, if heap.size() > k => remove

solution 4: math time: O(sqrt(n))
eg: n=15,k=3
for 1,  2,  3,  4
    k--     k--
divisors=[1,3]
if(k==0) return i;
k = 3 - 1 - 1 = 1
if k <= len(divisors) => return N / divisors[len(divisors) - k]
                      => return 15 / divisors[2 - 1] => 15 / divisors[1] = 15 / 3 = 5
                      => return 5

why if k <= len(divisors), and we can directly get the return,
if k <= number of all the factor of n => k <= len(divisors)
because the length of divisors <= 1/2 * number of all the factor of n
*/
public class Solution {
    static int kthFactor1(int n, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n2 - n1);
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 1; i < sqrtN + 1; i++){
            if (n % i == 0){
                pq.add(i);
                if (pq.size() > k) pq.remove();
                if (i != n / i){
                    pq.add(n / i);
                    if (pq.size() > k) pq.remove();
                }
            }
        }

        return k > pq.size() ? -1 : pq.poll();
    }

    static int kthFactor2(int n, int k) {
        int sqrtN = (int) Math.sqrt(n);
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i < sqrtN + 1; i++){
            if (n % i == 0){
                divisors.add(i);
                k--;
                if (k == 0) return i;
            }
        }

        // If n is a perfect square
        // we have to skip the duplicate
        // in the divisor list
        if (sqrtN * sqrtN == n){
            k++;
        }

        return k <= divisors.size() ? n / divisors.get(divisors.size() - k) : -1;
    }

    public static void main(String[] args) {

    }
}
