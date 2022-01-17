/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
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
	public ListNode swapPairs(ListNode head) {
		//递归的终止条件
		if(head==null || head.next==null) {
			return head;
		}
		//假设链表是 1->2->3->4
		//这句就先保存节点2
		ListNode tmp = head.next;
		//继续递归，处理节点3->4
		//当递归结束返回后，就变成了4->3
		//于是head节点就指向了4，变成1->4->3
		head.next = swapPairs(tmp.next);
		//将2节点指向1
		tmp.next = head;
		return tmp;
	}
}
// @lc code=end

