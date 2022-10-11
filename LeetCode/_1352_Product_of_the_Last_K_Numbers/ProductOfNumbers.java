package LeetCode._1352_Product_of_the_Last_K_Numbers;

import java.util.ArrayList;
import java.util.List;

/*
1352. Product of the Last K Numbers
Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.

Implement the ProductOfNumbers class:
1. ProductOfNumbers() Initializes the object with an empty stream.
2. void add(int num) Appends the integer num to the stream.
3. int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that always the current list has at least k numbers.
The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.

Example:
Input
["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]

Output
[null,null,null,null,null,null,20,40,0,null,32]

Explanation
ProductOfNumbers productOfNumbers = new ProductOfNumbers();
productOfNumbers.add(3);        // [3]
productOfNumbers.add(0);        // [3,0]
productOfNumbers.add(2);        // [3,0,2]
productOfNumbers.add(5);        // [3,0,2,5]
productOfNumbers.add(4);        // [3,0,2,5,4]
productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
productOfNumbers.add(8);        // [3,0,2,5,4,8]
productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32

Constraints:
0 <= num <= 100
1 <= k <= 4 * 104
At most 4 * 104 calls will be made to add and getProduct.
The product of the stream at any point in time will fit in a 32-bit integer.
*/
/*
approach 1
1. uas a prefix product list
2. if current num == 0, current prefix product = 1, reset the list
3. if array size < k => return 0
   if array size == k => return list.get(list.size() - 1)
   if array size > k => return list.get(list.size() - 1) / list.get(list.size() - 1 - k)
*/
public class ProductOfNumbers {
    // 3 1 2 4
    // 3 1 6 24 => 24/3=8
    // [3 1 2 4] 0 [1 3 2
    // 3 1 6 24  1  1 3 6=> 24/3=8
    List<Integer> list;
    int product;
    public ProductOfNumbers() {
        list = new ArrayList<>();
        product = 1;
    }

    public void add(int num) {
        if(num == 0){
            list = new ArrayList<>();
            product = 1;
            return;
        }
        product *= num;
        list.add(product);
    }

    public int getProduct(int k) {
        if(list.size() < k) return 0;
        if(list.size() == k) return list.get(k - 1);

        return list.get(list.size() - 1) / list.get(list.size() - 1 - k);
    }

}
