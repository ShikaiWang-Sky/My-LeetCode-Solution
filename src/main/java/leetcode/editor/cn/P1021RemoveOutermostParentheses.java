//有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"
//，"(())()" 和 "(()(()))" 都是有效的括号字符串。 
//
// 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。 
//
// 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。 
//
// 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。 
//
// 
//
// 示例 1： 
//
// 输入："(()())(())"
//输出："()()()"
//解释：
//输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
//删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。 
//
// 示例 2： 
//
// 输入："(()())(())(()(()))"
//输出："()()()()(())"
//解释：
//输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
//删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
// 
//
// 示例 3： 
//
// 输入："()()"
//输出：""
//解释：
//输入字符串为 "()()"，原语化分解得到 "()" + "()"，
//删除每个部分中的最外层括号后得到 "" + "" = ""。
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 10000 
// S[i] 为 "(" 或 ")" 
// S 是一个有效括号字符串 
// 
// Related Topics 栈 
// 👍 151 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：删除最外层的括号
public class P1021RemoveOutermostParentheses {
    public static void main(String[] args) {
        Solution solution = new P1021RemoveOutermostParentheses().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeOuterParentheses(String S) {
            int len = S.length();
            // 定义容器存储原语子串
            List<String> list = new ArrayList<>();
            // 定义左括号, 右括号计数器
            int left = 0, right = 0, lastOpr = 0;
            // 遍历字符串, 读取到括号时对应计数器自增
            for (int i = 0; i < len; i++) {
                char c = S.charAt(i);
                if (c == '(') {
                    left++;
                } else if (c == ')') {
                    right++;
                }
                // 检查是否到达某个原语结尾，截取原语子串添加到容器
                if (left == right) {
                    list.add(S.substring(lastOpr, i + 1));
                    lastOpr = i + 1;
                }
            }
            // 遍历容器中的原语子串，删除最外层后合并成新串
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s.substring(1, s.length() - 1));
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
