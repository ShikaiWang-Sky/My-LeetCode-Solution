//给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。 
//
// 示例: 
//
// 输入: 2
//输出: 91 
//解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
// 
// Related Topics 数学 动态规划 回溯 
// 👍 146 👎 0

package leetcode.editor.cn;

//Java：计算各个位数不同的数字个数
public class P357CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        Solution solution = new P357CountNumbersWithUniqueDigits().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) {
                return 1;
            }
            int res = 1;
            // 首位不能取0,有9种取法
            int product = 9;
            for (int i = 1; i <= n; i++) {
                res += product;
                // 不能与已经取的数字重复
                product *= (10 - i);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
