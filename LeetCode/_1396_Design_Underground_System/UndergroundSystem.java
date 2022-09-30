package LeetCode._1396_Design_Underground_System;

import javafx.util.Pair;
import java.util.HashMap;

/*
1396. Design Underground System
An underground railway system is keeping track of customer travel times between different stations.
They are using this data to calculate the average time it takes to travel from one station to another.

Implement the UndergroundSystem class:
* void checkIn(int id, string stationName, int t)
    A customer with a card ID equal to id, checks in at the station stationName at time t.
    A customer can only be checked into one place at a time.
* void checkOut(int id, string stationName, int t)
    A customer with a card ID equal to id, checks out from the station stationName at time t.
* double getAverageTime(string startStation, string endStation)
    Returns the average time it takes to travel from startStation to endStation.
    The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
    The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
    There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.

You may assume all calls to the checkIn and checkOut methods are consistent.
If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.
 */
public class UndergroundSystem {
    // checkout map => <key, pair(total_time, cnt)>
    // checkIn map => <id, pair(start, time)>
    HashMap<Integer, Pair<String, Integer>> checkin;
    HashMap<String, Pair<Double, Double>> info;
    public UndergroundSystem() {
        checkin = new HashMap<>();
        info = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkin.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> pair = checkin.get(id);
        String key = pair.getKey() + "_" + stationName;
        double time = t - pair.getValue();
        Pair<Double, Double> tempPair = info.getOrDefault(key, new Pair<>(0.0, 0.0));
        info.put(key, new Pair<>(tempPair.getKey() + 1, tempPair.getValue() + time));
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "_" + endStation;
        return info.get(key).getValue() / info.get(key).getKey();
    }
}
