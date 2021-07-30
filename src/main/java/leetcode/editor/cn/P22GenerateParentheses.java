//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 1914 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：括号生成
public class P22GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            backtrack(new char[n * 2], 0, 0, 0, result);
            return result;
        }

        public void backtrack(char[] current, int pos, int leftCount, int rightCount,
                              List<String> result) {
            if (pos == current.length) {
                result.add(new String(current));
                return;
            }

            if (leftCount < current.length / 2) {
                current[pos] = '(';
                backtrack(current, pos + 1, leftCount + 1, rightCount, result);
            }

            if (rightCount < leftCount) {
                current[pos] = ')';
                backtrack(current, pos + 1, leftCount, rightCount + 1, result);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
