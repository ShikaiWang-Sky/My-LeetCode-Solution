//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 示例 1: 
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
// 
//
// 示例 2: 
//
// 输入: 1->1->1->2->3
//输出: 2->3 
// Related Topics 链表 
// 👍 437 👎 0

package leetcode.editor.cn;

//Java：删除排序链表中的重复元素 II
public class P82RemoveDuplicatesFromSortedListIi {
    public static void main(String[] args) {
        Solution solution = new P82RemoveDuplicatesFromSortedListIi().new Solution();
        // TO TEST
    }

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            // 创建哑结点, 防止头结点被误删
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode current = dummy;
            while (current.next != null && current.next.next != null) {
                if (current.next.val == current.next.next.val) {
                    ListNode temp = current.next;
                    while (temp.next != null && temp.val == temp.next.val) {
                        temp = temp.next;
                    }
                    current.next = temp.next;
                } else {
                    current = current.next;
                }
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
