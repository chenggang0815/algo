package Amazon._002_Stock_Ticker;
/*
Stock Ticker
Give a stream of stock prices, design a data structure to support the following operations:
1. StockSticker(int k) Initialize the size of the ticker.
2. void addOrUpdate(String stock, double price) add or update the price of a stock to the data structure.
3. List<Map.Entry<String, double>> top() return the top k price stocks and their current prices.
 */

/*
O(1) for addOrUpdate
for top() => O(K)+O(NlogK) It takes O(K) to construct the initial heap
              and then for the rest of the N−K elements,
              we perform the extractMin and add operations on the heap each of which take logK time
for top() we also can use quickSelect to find top k
*/
import javafx.util.Pair;

import java.util.*;

public class StockSticker {
    int k;
    HashMap<String, Double> stockMap;
    PriorityQueue<String> pq;
    public StockSticker(int k){
        this.k = k;
        stockMap = new HashMap<>();
        pq = new PriorityQueue<>((p1, p2) -> stockMap.get(p1).compareTo(stockMap.get(p2)));
    }

    void addOrUpdate(String stock, double price){
        stockMap.put(stock, price);
    }

    List<Pair<String, Double>> top(){
        List<Pair<String, Double>> res = new ArrayList<>();
        for (String stock : stockMap.keySet()){
            if (pq.size() < k){
                pq.add(stock);
            }else{
                if (stockMap.get(stock) > stockMap.get(pq.peek())){
                    pq.poll();
                    pq.add(stock);
                }
            }
        }

        while (!pq.isEmpty()){
            Pair<String, Double> pair = new Pair<>(pq.peek(), stockMap.get(pq.poll()));
            res.add(pair);
        }

        return res;
    }


    public static void main(String[] args) {
        StockSticker s = new StockSticker(3);
        s.addOrUpdate("a", 6);
        s.addOrUpdate("b",5);
        s.addOrUpdate("c",7);
        s.addOrUpdate("d",1);
        s.addOrUpdate("d",9); // acd
        s.addOrUpdate("b",10); //cdb
        s.addOrUpdate("b",1); // acd
        System.out.println(s.top());

    }

}
