//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct
//-characters 相同
//
//
//
// 示例 1：
//
//
//输入：s = "bcabc"
//输出："abc"
//
//
// 示例 2：
//
//
//输入：s = "cbacdcbc"
//输出："acdb"
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 由小写英文字母组成
//
// Related Topics 栈 贪心算法 字符串
// 👍 454 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

//Java：去除重复字母
public class P316RemoveDuplicateLetters {
    public static void main(String[] args) {
        Solution solution = new P316RemoveDuplicateLetters().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicateLetters(String s) {
            int length = s.length();
            // 记录每个字母最后出现的位置
            int[] lastOccurrence = new int[26];
            for (int i = 0; i < length; i++) {
                lastOccurrence[s.charAt(i) - 'a'] = i;
            }
            Deque<Character> stack = new ArrayDeque<>();
            // 每一个字符是否在栈中出现过
            boolean[] seen = new boolean[26];
            // 再浏览一遍字符串, 对当前字符
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                // 若栈为空, 压栈
                if (stack.isEmpty()) {
                    stack.push(c);
                    seen[c - 'a'] = true;
                } else if (!seen[c - 'a']) {
                    // 栈不为空且当前字符在栈中没有出现过
                    //若当前字符大于栈顶字符, 当前字符压栈
                    //若当前字符小于栈顶字符, 且栈顶字符在当前字符后不再出现，当前字符压栈
                    if (c > stack.peek() || lastOccurrence[stack.peek() - 'a'] < i) {
                        stack.push(c);
                        seen[c - 'a'] = true;
                    } else {
                        // 若当前宇符小于栈顶字符, 且栈顶字符在当前字符后还再出现
                        // 栈顶字符出栈, 继续比较当前字符与新栈顶字符
                        //直至栈为空、或当前字符大于栈顶字符、或栈顶字符在当前字符后不再出现，将当前字符压栈
                        while (!stack.isEmpty() && c < stack.peek() && lastOccurrence[stack.peek() - 'a'] > i) {
                            seen[stack.pop() - 'a'] = false;
                        }
                        stack.push(c);
                        seen[c - 'a'] = true;
                    }
                    // 若栈不为空, 且当前字符在栈中出现过, 跳过当前字符
                }
            }
            // 将栈中的字符逆序拼接成字符串
            StringBuilder res = new StringBuilder();
            while (!stack.isEmpty()) {
                res.insert(0, stack.pop());
            }
            return res.toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
