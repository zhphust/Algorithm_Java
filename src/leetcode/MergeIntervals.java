package leetcode;

import java.util.*;

/**
 * 56. 合并区间（中等）
 * 给出一个区间的集合，请合并所有重叠的区间。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/25
 * @version 8.0
 */
public class MergeIntervals {
    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    static class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start - o2.start;
        }
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<>();
        if (intervals.size() == 0)
            return list;
        intervals.sort(new IntervalComparator());
        Interval first = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++){
            Interval curr = intervals.get(i);
            if (curr.start <= first.end) {
                first.end = first.end >= curr.end ? first.end : curr.end;
            } else {
                list.add(first);
                first = curr;
            }
        }
        list.add(first);
        return list;
    }

    public static void main(String[] args) {
        Interval i1 = new Interval(2, 3),
                i2 = new Interval(2, 2),
                i3 = new Interval(3, 4),
                i4 = new Interval(3, 4),
                i5 = new Interval(5, 5);

        List<Interval> list = new ArrayList<>();
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        list.add(i5);

        List<Interval> l = merge(list);
        System.out.println();
    }
}
