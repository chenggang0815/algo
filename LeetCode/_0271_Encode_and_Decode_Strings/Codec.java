package LeetCode._0271_Encode_and_Decode_Strings;

import java.util.ArrayList;
import java.util.List;
/*
271. Encode and Decode Strings

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}

So Machine 1 does:
string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.
You are not allowed to solve the problem using any serialize methods (such as eval).
Example 1:
Input: dummy_input = ["Hello","World"]
Output: ["Hello","World"]
Explanation:
Machine 1:
Codec encoder = new Codec();
String msg = encoder.encode(strs);
Machine 1 ---msg---> Machine 2

Machine 2:
Codec decoder = new Codec();
String[] strs = decoder.decode(msg);
*/
public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str: strs){
            sb.append(str.length()).append("/").append(str);
        }

        return sb.toString();

    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<String>();
        int i = 0;
        while(i < s.length()){
            int slash = s.indexOf('/', i);
            int size = Integer.parseInt(s.substring(i, slash));
            i = slash + size + 1;
            res.add(s.substring(slash + 1, i));
        }

        return res;
    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("");
        System.out.println(list.size());
        for (String str: list){
            System.out.println(str);
        }
    }
}
