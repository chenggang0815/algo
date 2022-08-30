package LeetCode._1656_Design_an_Ordered_Stream;

import java.util.ArrayList;
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
    int cur;
    String[] values;
    public OrderedStream(int n) {
        cur = 0;
        values = new String[n];
    }

    public List<String> insert(int idKey, String value) {
        List<String> res = new ArrayList<>();
        values[idKey -1] = value;
        if(cur == idKey - 1){
            for(int i = cur; i < values.length; i++){
                if(values[i] != null) {
                    res.add(values[i]);
                    cur++;
                }else break;
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
