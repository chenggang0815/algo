package Amazon._1152_Analyze_User_Website_Visit_Pattern;

/*
1152. Analyze User Website Visit Pattern

You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].
A pattern is a list of three websites (not necessarily distinct).

For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.
For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.

Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.
 */
import java.util.*;

/*

*/
public class Solution {
    static class pair{
        int time;
        String website;
        pair(int time, String website){
            this.time = time;
            this.website = website;
        }
    }

    static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<pair>> map = new HashMap<>();
        for(int i = 0; i < username.length; i++){
            if(!map.containsKey(username[i])) map.put(username[i], new ArrayList<>());
            map.get(username[i]).add(new pair(timestamp[i], website[i]));
        }

        Map<String, Integer> count = new HashMap<>();
        String res = "";
        for(String key: map.keySet()){
            Set<String> set = new HashSet<>();
            List<pair> list = map.get(key);
            list.sort((a, b) -> a.time - b.time);
            for(int i = 0; i < list.size(); i++){
                for(int j = i + 1; j < list.size(); j++){
                    for(int k = j + 1; k < list.size(); k++){
                        String str = list.get(i).website + " " + list.get(j).website + " " + list.get(k).website;
                        if(!set.contains(str)){
                            count.put(str, count.getOrDefault(str, 0) + 1);
                            set.add(str);
                        }
                        if(res.equals("") || count.get(res) < count.get(str) || (count.get(res) == count.get(str) && res.compareTo(str) > 0)){
                            res = str;
                        }
                    }
                }
            }

        }

        return Arrays.asList( res.split(" "));

    }

    public static void main(String[] args) {
        String[] username = new String[]{"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = new int[]{1,2,3,4,5,6,7,8,9,10};
        String[] website = new String[]{"home","about","career","home","cart","maps","home","home","about","career"};
        System.out.println(mostVisitedPattern(username, timestamp, website));
        System.out.println("a".compareTo("b"));

    }
}
