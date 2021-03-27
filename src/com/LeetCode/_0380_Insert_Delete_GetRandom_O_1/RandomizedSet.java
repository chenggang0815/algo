package com.LeetCode._0380_Insert_Delete_GetRandom_O_1;
/*
380. Insert Delete GetRandom O(1)
380. 常数时间插入、删除和获取随机元素
设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
insert(val)：当元素 val 不存在时，向集合中插入该项。
remove(val)：元素 val 存在时，从集合中移除该项。
getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。

示例 :
// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();
// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);
// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);
// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);
// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();
// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);
// 2 已在集合中，所以返回 false 。
randomSet.insert(2);
// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();
*/

import java.util.*;

/*
思路： 哈希表+动态数组
需要在平均复杂度为O(1) 实现：insert、remove、getRandom

1. 从insert开始，有两个平均插入时间为O(1)的选择：
    1.1 哈希表：Java 中为 HashMap，Python 中为 dictionary
    1.2 动态数组：Java 中为 ArrayList，Python 中为 list
2. 实现getRandom
    2.1 虽然哈希表提供常数时间的插入和删除，但是实现 getRandom 时会出现问题，getRandom的思想是选择一个随机索引，然后使用该索引返回一个元素
    2.2 而哈希表中没有索引，因此要获得真正的随机值，则要将哈希表中的键转换为列表，这需要线性时间
    2.3 解决的方法是用一个列表存储值，并在该列表中实现常数时间的 getRandom
3. 实现remove
    3.1 列表有索引可以实现常数时间的 insert 和 getRandom，则接下来的问题是如何实现常数时间的 remove
    3.2 删除任意索引元素需要线性时间，解决方案是总是删除最后一个元素
    3.3 将要删除元素和最后一个元素交换，将最后一个元素删除 => 用最后一个元素覆盖要删除的元素，然后再删除最后一个元素 <=> 等同于交换后删除最后一个元素
为此，必须在常数时间获取到要删除元素的索引，因此需要一个哈希表来存储值到索引的映射

综上所述，使用以下数据结构：
    1. 动态数组存储元素值
    2. 哈希表存储存储值到索引的映射

list=[51,2,10,63,57] map=(51,0),(2,1),(10,2),(63,3),(57,4)
delete 10
lastElement=list.get(list.size()-1)=57
index=map.get(10)=2
把list最后一个元素放到要删除元素的位置 => list.set(index, lastElement)
在map中更新最后一个元素的索引信息 => map.put(lastElement, index)
// ps: Actually, there is no need to swap the two numbers because we don't need the previos number any more. So we just need to set last number to new location and cover the previous one, and cut the list.
// 覆盖要删除的元素，然后再删除最后一个元素 <=> 等同于交换后删除最后一个元素
list=[51,2,10,63,57]

list中删除最后一个元素，map中删除10对应的映射关系
*/
public class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;
    Random rand = new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;

        map.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int lastElement = list.get(list.size() - 1);
        int index = map.get(val);
        list.set(index, lastElement);
        map.put(lastElement, index);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(51);
        list.add(2);
        list.add(10);
        list.add(63);
        list.add(57);
        System.out.println(list);
        list.set(2,57);
        System.out.println(list);
    }
}
