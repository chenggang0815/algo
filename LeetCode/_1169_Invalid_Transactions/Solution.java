package LeetCode._1169_Invalid_Transactions;

import java.util.List;

/*
1169. Invalid Transactions

A transaction is possibly invalid if:
    1. the amount exceeds $1000, or;
    2. if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.

You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.
Return a list of transactions that are possibly invalid. You may return the answer in any order.

Example 1:
Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.

Example 2:
Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]

Example 3:
Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]

Constraints:
transactions.length <= 1000
Each transactions[i] takes the form "{name},{time},{amount},{city}"
Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
Each {time} consist of digits, and represent an integer between 0 and 1000.
Each {amount} consist of digits, and represent an integer between 0 and 2000.
*/
/*
Approach:
1. use 4 arrays to store name,city,time,amount for each transaction
2. for each transaction,
*/
public class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> res = new ArrayList<>();
        int len = transactions.length;
        String[] name = new String[len];
        int[] time = new int[len];
        int[] amount = new int[len];
        String[] city = new String[len];

        for(int i = 0; i < transactions.length; i++){
            String[] transaction = transactions[i].split(",");
            name[i] = transaction[0];
            time[i] = Integer.parseInt(transaction[1]);
            amount[i] = Integer.parseInt(transaction[2]);
            city[i] = transaction[3];
        }

        int[] isValid = new int[len];
        for(int i = 0; i < len; i++){
            if(amount[i] > 1000) isValid[i] = 1;
            for(int j = i + 1; j < len; j++){
                if(name[i].equals(name[j]) && !city[i].equals(city[j]) && Math.abs(time[i] - time[j]) <= 60){
                    isValid[i] = 1;
                    isValid[j] = 1;
                }
            }
        }


        for(int i = 0; i < len; i++){
            if(isValid[i] == 1) res.add(transactions[i]);
        }

        return res;
    }    public static void main(String[] args) {

    }
}
