package LeetCode._0243_Shortest_Word_Distance;

public class Solution {

    static public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < wordsDict.length; i++){
            if(wordsDict[i].equals(word1) || wordsDict[i].equals(word2)){
                //System.out.print(wordsDict[i]);
                //System.out.print("====");
                if(wordsDict[i].equals(word1) && index2 != -1){
                    index1 = i;
                    res = Math.min(res, Math.abs(i - index2));
                }else if(wordsDict[i].equals(word2) && index1 != -1){
                    res = Math.min(res, Math.abs(i - index1));
                }else if(wordsDict[i].equals(word1)){
                    index1 = i;
                }else if(wordsDict[i].equals(word2)){
                    index2 = i;
                }
            }
        }

        return res;
    }
    public static void main(String[] args) {
       String[] a = new String[]{"practice", "makes", "perfect", "coding", "makes"};
       String word1 = "makes";
       String word2 = "coding";
       shortestDistance(a, word1, word2);

    }
}
