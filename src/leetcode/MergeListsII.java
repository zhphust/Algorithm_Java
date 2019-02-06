package leetcode;

/**
 * 23. 合并K个排序链表（困难，归并排序）
 * 合并 k 个排序链表，返回合并后的排序链表。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/6
 * @version 8.0
 */
public class MergeListsII {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return sort(lists, 0, lists.length - 1);        // 归并排序的思想
    }

    private ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi)
            return lists[lo];
        int mid = lo + (hi - lo) / 2;
        ListNode l = sort(lists, lo, mid);
        ListNode r = sort(lists, mid + 1, hi);
        return mergeTwoLists(l, r);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                temp = temp.next;
                l1 = l1.next;
            } else {
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            temp.next = l2;
        } else {
            temp.next = l1;
        }
        return result.next;
    }
}
