package LeetCode._2034_Stock_Price_Fluctuation;

import java.util.HashMap;
import java.util.TreeMap;

/*
2034. Stock Price Fluctuation
You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.

Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.

Design an algorithm that:
1. Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
2. Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
3. Finds the maximum price the stock has been based on the current records.
4. Finds the minimum price the stock has been based on the current records.

Implement the StockPrice class:
1. StockPrice() Initializes the object with no price records.
2. void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
3. int current() Returns the latest price of the stock.
4. int maximum() Returns the maximum price of the stock.
5. int minimum() Returns the minimum price of the stock.


Example 1:
Input
["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
Output
[null, null, null, 5, 10, null, 5, null, 2]
Explanation
StockPrice stockPrice = new StockPrice();
stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                          // Timestamps are [1,2] with corresponding prices [3,5].
stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.

Constraints:
1 <= timestamp, price <= 109
At most 105 calls will be made in total to update, current, maximum, and minimum.
current, maximum, and minimum will be called only after update has been called at least once.
*/

/*
Solution:
price: 1  2  6  4  5
ts:    a  b  c  d  c

use a HashMap => priceMap to store <ts, price>
use a TreeMap => priceCnt to store <price, cnt> => the key in the TreeMap is sorted form small to large
when update a exists ts, we need to update the cnt of this price => cnt = cnt - 1, if cnt == 0 => priceCnt.remove(price)

for example
1. priceMap<a=1,b=2,c=6,d=4>
2. priceCnt<1=1,2=1,4=1,6=1>
    => min price = return priceCnt.firstKey() = 1
    => max price = return priceCnt.lastKey() = 6
3. update(ts=c, price=5)
    => c exists in priceMap
    => update priceCnt => priceCnt<1=1,2=1,4=1,6=0>
                       => priceCnt<1=1,2=1,4=1>
                       => priceCnt<1=1,2=1,4=1, 5=1>
    => max price = return priceCnt.lastKey() = 5
 */
public class StockPrice {
    HashMap<Integer, Integer> timePrice;
    TreeMap<Integer, Integer> order;
    int currentTime;

    public StockPrice() {
        timePrice = new HashMap<>();
        order = new TreeMap<>();
        currentTime = 0;
    }

    public void update(int timestamp, int price) {
        if(timePrice.containsKey(timestamp)){
            int prePrice = timePrice.get(timestamp);
            order.put(prePrice, order.get(prePrice) - 1);
            if(order.get(prePrice) == 0){
                order.remove(prePrice);
            }
        }
        timePrice.put(timestamp, price);
        order.put(price, order.getOrDefault(price, 0) + 1);

        if(timestamp > currentTime){
            currentTime = timestamp;
        }
    }

    public int current() {
        return timePrice.get(currentTime);
    }

    public int maximum() {
        return order.lastKey();
    }

    public int minimum() {
        return order.firstKey();
    }

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(5,1);
        map.put(2,1);
        map.put(10,1);
        map.put(1,1);
        System.out.println(map.lastKey()); // 10
        System.out.println(map.firstKey()); // 1
    }
}
