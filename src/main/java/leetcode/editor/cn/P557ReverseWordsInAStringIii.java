//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 字符串 
// 👍 266 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

//Java：反转字符串中的单词 III
public class P557ReverseWordsInAStringIii {
    public static void main(String[] args) {
        Solution solution = new P557ReverseWordsInAStringIii().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseWords(String s) {
            int index = 0, length = s.length();
            Deque<Character> stack = new ArrayDeque<>();
            StringBuilder stringBuilder = new StringBuilder();
            while (index < length) {
                char current = s.charAt(index);
                if (current != ' ') {
                    stack.push(current);
                } else {
                    while (!stack.isEmpty()) {
                        stringBuilder.append(stack.pop());
                    }
                    stringBuilder.append(' ');
                }
                index++;
            }
            while (!stack.isEmpty()) {
                stringBuilder.append(stack.pop());
            }
            return stringBuilder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
