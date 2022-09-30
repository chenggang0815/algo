package LeetCode._1244_Design_A_Leaderboard;

import java.util.*;

/*
1244. Design A Leaderboard

Design a Leaderboard class, which has 3 functions:
    1. addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
    2. top(K): Return the score sum of the top K players.
    3. reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.

Initially, the leaderboard is empty.

Input:
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
Output:
[null,null,null,null,null,null,73,null,null,null,141]

Explanation:
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 */

/*
Approach 1: hashmap
* for addScore() time: O(1)
* for top() time: O(Nlog(N))
* for reset() time: O(1)
Approach 2: hashmap + treeMap
* for addScore() time: O(log(N))
* for top() time: O(log(K))
* for reset() time: O(log(N))
*/
public class Leaderboard {
    // map<id, score>
    // treeMap<score, cnt>
    HashMap<Integer, Integer> map;
    TreeMap<Integer, Integer> treeMap;
    public Leaderboard() {
        map = new HashMap<>();
        treeMap = new TreeMap<>();
    }

    public void addScore(int playerId, int score) {
        if(map.containsKey(playerId)){
            int prevScore = map.get(playerId);
            map.put(playerId, prevScore + score);

            treeMap.put(prevScore, treeMap.get(prevScore) - 1);
            if(treeMap.get(prevScore) == 0) treeMap.remove(prevScore);

            treeMap.put(prevScore + score, treeMap.getOrDefault(prevScore + score, 0) + 1);
            return;
        }

        map.put(playerId, score);
        treeMap.put(score, treeMap.getOrDefault(score, 0) + 1);
    }

    public int top(int K) {
        int res = 0;
        ArrayList<Integer> scores = new ArrayList<>(treeMap.keySet());
        scores.sort((a, b) -> b - a);
        while(K > 0){
            for(int score: scores){
                int cnt = treeMap.get(score);
                if(cnt > K){
                    res += K * score;
                    return res;
                }else{
                    res += cnt * score;
                    K -= cnt;
                }
            }
        }

        return res;
    }

    public void reset(int playerId) {
        int score = map.get(playerId);
        map.remove(playerId);
        int cnt = treeMap.get(score);
        if(cnt == 1){
            treeMap.remove(score);
            return;
        }
        treeMap.put(score, treeMap.get(score) - 1);
    }
}
