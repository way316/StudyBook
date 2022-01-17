/*
 * @lc app=leetcode.cn id=2 lang=java
 *
 * [2] 两数相加
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode result = pre;
        int count = 0;
        while (l1 != null || l2 != null ) {
            int firstNodeValue = l1 == null ? 0:l1.val;
            int secondNodeValue = l2 == null ? 0:l2.val;
            int resultNodeValue = firstNodeValue + secondNodeValue + count;
            if (resultNodeValue > 9) {
                count = 1;
                resultNodeValue = resultNodeValue - 10;
            } else {
                count = 0;
            }
            result.next = new ListNode(resultNodeValue);
            result = result.next;
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }
        if (count == 1) {
            result.next = new ListNode(1);
        }
        return pre.next;
    }
}
// @lc code=end

