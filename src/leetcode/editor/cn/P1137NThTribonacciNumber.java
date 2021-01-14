//泰波那契序列 Tn 定义如下： 
//
// T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2 
//
// 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。 
//
// 
//
// 示例 1： 
//
// 输入：n = 4
//输出：4
//解释：
//T_3 = 0 + 1 + 1 = 2
//T_4 = 1 + 1 + 2 = 4
// 
//
// 示例 2： 
//
// 输入：n = 25
//输出：1389537
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 37 
// 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。 
// 
// Related Topics 递归 
// 👍 55 👎 0

package leetcode.editor.cn;

//Java：第 N 个泰波那契数
public class P1137NThTribonacciNumber {
    public static void main(String[] args) {
        Solution solution = new P1137NThTribonacciNumber().new Solution();
        // TO TEST
        System.out.println(solution.tribonacci(35));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int tribonacci(int n) {
            switch (n) {
                case 0:
                    return 0;
                case 1:
                case 2:
                    return 1;
                case 3:
                    return 2;
                case 4:
                    return 4;
            }
            return 2 * tribonacci(n - 1) - tribonacci(n - 4);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
