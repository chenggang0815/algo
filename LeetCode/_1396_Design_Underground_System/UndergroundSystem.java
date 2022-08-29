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
    // checkout map => key= start_end value=pair(total_time, cnt)
    // checkIn map => key=id value=pair(start station, time)
    HashMap<Integer, Pair<String, Integer>> checkInData;
    HashMap<String, Pair<Double, Double>> checkOutData;

    public UndergroundSystem() {
        checkInData = new HashMap<>();
        checkOutData = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInData.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> checkInInfo = checkInData.get(id);
        String checkInStation = checkInInfo.getKey();
        Integer checkInTime = checkInInfo.getValue();

        String stationKey = checkInStation + "_" + stationName;
        double time = checkInTime - t;
        Pair<Double, Double> checkOutInfo = checkOutData.getOrDefault(stationKey, new Pair<>(0.0, 0.0));
        checkOutData.put(stationKey, new Pair<>(checkOutInfo.getKey() + time, checkOutInfo.getValue() + 1));
    }

    public double getAverageTime(String startStation, String endStation) {
        String stationKey = startStation + "_" + endStation;
        return checkOutData.get(stationKey).getKey() / checkOutData.get(stationKey).getValue();
    }
}
