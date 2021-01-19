//不使用运算符 + 和 - ，计算两整数 a 、b 之和。 
//
// 示例 1: 
//
// 输入: a = 1, b = 2
//输出: 3
// 
//
// 示例 2: 
//
// 输入: a = -2, b = 3
//输出: 1 
// Related Topics 位运算 
// 👍 354 👎 0

package leetcode.editor.cn;

//Java：两整数之和
public class P371SumOfTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new P371SumOfTwoIntegers().new Solution();
        // TO TEST
        System.out.println(solution.getSum(1, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int getSum(int a, int b) {
            if (b == 0) {
                return a;
            } else {
                int num = a ^ b;
                int carry = (a & b) << 1;
                return getSum(num, carry);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
