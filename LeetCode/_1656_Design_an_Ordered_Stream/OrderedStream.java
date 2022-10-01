package LeetCode._1656_Design_an_Ordered_Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
1656. Design an Ordered Stream

There is a stream of n (idKey, value) pairs arriving in an arbitrary order, where idKey is an integer between 1 and n and value is a string. No two pairs have the same id.
Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values after each insertion.
The concatenation of all the chunks should result in a list of the sorted values.

Implement the OrderedStream class:
    1. OrderedStream(int n) Constructs the stream to take n values.
    2. String[] insert(int idKey, String value) Inserts the pair (idKey, value) into the stream, then returns the largest possible chunk of currently inserted values that appear next in the order.
*/
public class OrderedStream {
    HashMap<Integer, String> map;
    int index;
    int n;
    public OrderedStream(int n) {
        this.n = n;
        index = 1;
        map = new HashMap<>();
    }

    public List<String> insert(int idKey, String value) {
        map.put(idKey, value);
        List<String> res = new ArrayList<>();
        if(idKey == index){
            while(index <= n && map.containsKey(index)){
                res.add(map.get(index));
                index++;
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
