//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 5520 👎 0

package leetcode.editor.cn;

//Java：两数相加
public class P2AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new P2AddTwoNumbers().new Solution();
        // TO TEST
    }

    /**
     * Definition for singly-linked list
     */
    class ListNode {
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // 原链表的两个遍历指针
            ListNode p = l1, q = l2;
            // 结果链表的头结点
            ListNode resultHeader = new ListNode(-1);
            // 结果链表的遍历指针, 代表当前操作的节点
            ListNode curr = resultHeader;

            // 进位
            int carry = 0;

            // 遍历两个链表, 以长链表为准
            while (p != null || q != null) {
                // 获取当前节点的值, 链表较短, 已经没有节点, 取0
                int x = (p != null ? p.val : 0);
                int y = (q != null ? q.val : 0);
                // 对应位置的节点数值相加
                int sum = x + y + carry;
                // 得到进位
                carry = sum / 10;
                // 得到个位
                int num = sum % 10;

                // 将计算结果插入新链表尾部
                // 创建新节点, 追加到结果链表的尾部
                curr.next = new ListNode(num);
                // 移动指针
                curr = curr.next;

                //移动链表指针
                p = (p == null ? p : p.next);
                q = (q == null ? q : q.next);
            }
            // 最后一次进位, 处理进位节点
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            // 头结点的下一个就是数据
            return resultHeader.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
