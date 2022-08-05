package Amazon._1152_Analyze_User_Website_Visit_Pattern;
import javafx.util.Pair;

import java.util.*;
// 找出所有的pattern
public class Solution2 {
    static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        HashMap<String, List<Pair<String, Integer>>> map = new HashMap<>();
        for(int i = 0; i < username.length; i++){
            if (!map.containsKey(username[i])){
                List<Pair<String, Integer>> list = new ArrayList<>();
                Pair<String, Integer> pair = new Pair<>(website[i], timestamp[i]);
                list.add(pair);
                map.put(username[i], list);
            }else{
                map.get(username[i]).add(new Pair<>(website[i], timestamp[i]));
            }
        }

        List<String> res = new ArrayList<>();
        HashMap<String, Integer> count = new HashMap<>();
        for(String user : map.keySet()){
            List<Pair<String, Integer>> pattern = map.get(user);
            HashSet<String> set = new HashSet<>();
            pattern.sort((a,b) -> a.getValue() - b.getValue());
            for(int i = 0; i < pattern.size(); i++){
                for(int j = i + 1; j < pattern.size(); j++){
                    for(int k = j + 1; k < pattern.size(); k++){
                        String p = pattern.get(i).getKey() + " " + pattern.get(j).getKey() + " " + pattern.get(k).getKey();
                       // System.out.println(p);
                        if(!set.contains(p)){
                            count.put(p, count.getOrDefault(p, 0) + 1);
                            set.add(p);
                        }
                    }
                }
            }
        }

        for (String pattern : count.keySet()){
            res.add(pattern);
        }

        return res;
    }

    public static void main(String[] args) {
        String[] username = new String[]{"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = new int[]{1,2,3,4,5,6,7,8,9,10};
        String[] website = new String[]{"home","about","career","home","cart","maps","home","home","about","career"};
        System.out.println(mostVisitedPattern(username, timestamp, website));
    }
}
