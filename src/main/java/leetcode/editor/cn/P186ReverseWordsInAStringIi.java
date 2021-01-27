//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 示例： 
//
// 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
//输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"] 
//
// 注意： 
//
// 
// 单词的定义是不包含空格的一系列字符 
// 输入字符串中不会包含前置或尾随的空格 
// 单词与单词之间永远是以单个空格隔开的 
// 
//
// 进阶：使用 O(1) 额外空间复杂度的原地解法。 
// Related Topics 字符串 
// 👍 48 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

//Java：翻转字符串里的单词 II
public class P186ReverseWordsInAStringIi {
    public static void main(String[] args) {
        Solution solution = new P186ReverseWordsInAStringIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void reverseWords(char[] s) {
            int length = s.length;
            reverse(s, 0, length - 1);
            int start = 0;
            for (int i = 0; i < length; i++) {
                if (s[i] == ' ') {
                    reverse(s, start, i - 1);
                    start = i + 1;
                }
            }
            reverse(s,start,length - 1);
        }

        /**
         * 反转char[]
         * @param s source char array
         * @param start begin position
         * @param end end position
         */
        private void reverse(char[] s, int start, int end) {
            while (start < end) {
                char t = s[start];
                s[start++] = s[end];
                s[end--] = t;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
