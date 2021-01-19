//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 进阶： 
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。 
//
// 
//
// 示例： 
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
// 
// Related Topics 链表 
// 👍 321 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：两数相加 II
public class P445AddTwoNumbersIi {
    public static void main(String[] args) {
        Solution solution = new P445AddTwoNumbersIi().new Solution();
        // TO TEST
        int[] arry1 = {1, 2, 3, 4};
        int[] arry2 = {1, 2, 3, 4};
        ListNode l1 = solution.initListNode(arry1);
        ListNode l2 = solution.initListNode(arry2);
        System.out.println(solution.listNodeToArray(solution.addTwoNumbers(l1, l2)).toString());
    }

    /*Definition for singly-linked list.*/
    class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int x) {
            this.val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // 将链表压栈
            Stack<Integer> stack1 = addAll(l1);
            Stack<Integer> stack2 = addAll(l2);

            // 计算进位和个位
            int carry = 0;
            ListNode ans = null;
            while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
                int numa = stack1.isEmpty() ? 0 : stack1.pop();
                int numb = stack2.isEmpty() ? 0 : stack2.pop();
                int add = numa + numb + carry;
                int digit = add % 10;
                carry = add / 10;
                // next指向上一次的结果
                ListNode currentNode = new ListNode(digit);
                currentNode.next = ans;
                ans = currentNode;
            }
            return ans;
        }

        public Stack<Integer> addAll(ListNode listNode) {
            Stack<Integer> stack = new Stack<>();
            while (listNode != null) {
                stack.push(listNode.val);
                listNode = listNode.next;
            }
            return stack;
        }

        public ListNode initListNode(int[] arry) {
            ListNode listNodeHeader = new ListNode(-1);
            ListNode listNode = listNodeHeader;
            for (int num : arry) {
                listNode.next = new ListNode(num);
                listNode = listNode.next;
            }
            return listNodeHeader.next;
        }

        public ArrayList<Integer> listNodeToArray(ListNode listNode) {
            ArrayList<Integer> list = new ArrayList<>();
            while (listNode != null) {
                list.add(listNode.val);
                listNode = listNode.next;
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
