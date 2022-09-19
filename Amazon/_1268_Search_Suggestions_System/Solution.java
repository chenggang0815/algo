package Amazon._1268_Search_Suggestions_System;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1268. Search Suggestions System
Given an array of strings products and a string searchWord.
We want to design a system that suggests at most three product names from products after each character of searchWord is typed.
Suggested products should have common prefix with the searchWord.
If there are more than three products with a common prefix return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord is typed.

*/
public class Solution {
    static int binarySearch(String[] products, int left, int right, String prefix){
        while (left < right){
            int mid = (right + left) / 2;
            if (products[mid].compareTo(prefix) >= 0){
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    static List<List<String>> suggestedProducts(String[] products, String searchWord){
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        String prefix = "";
        int left = 0;
        int right = products.length - 1;
        for (char c: searchWord.toCharArray()){
            prefix += c;
            int index = binarySearch(products, left, right, prefix);
            ArrayList<String> temp = new ArrayList<>();
            for (int i = index; i < Math.min(index + 3, products.length); i++){
                if (products[i].length() < prefix.length() || !products[i].substring(0, prefix.length()).equals(prefix)){
                    break;
                }
                temp.add(products[i]);
            }
            res.add(temp);

        }

        return res;
    }

    public static void main(String[] args) {
       // List<String> list = new ArrayList<>(Arrays.asList("bags","baggage","banner","box","cloths"));
       // System.out.println(list);
        // [bags, baggage, banner, box, cloths] prefix = bag
       // System.out.println("bags".compareTo("bag"));
        String[] products = new String[]{"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        System.out.println(suggestedProducts(products, searchWord));
//        Arrays.sort(products);
//        System.out.println(Arrays.toString(products));
//        System.out.println(binarySearch(products, 0, products.length, "m"));
        System.out.println("hello".substring(0, 3));


    }
}
