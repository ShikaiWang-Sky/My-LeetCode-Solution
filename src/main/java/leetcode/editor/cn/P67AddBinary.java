//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串 
// 👍 544 👎 0

package leetcode.editor.cn;

//Java：二进制求和
public class P67AddBinary {
    public static void main(String[] args) {
        Solution solution = new P67AddBinary().new Solution();
        // TO TEST
        System.out.println(solution.addBinary("0", "0"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public String addBinary(String a, String b) {
            char[] cha = a.toCharArray();
            char[] chb = b.toCharArray();
            int maxLength = Math.max(cha.length, chb.length);
            int carry = 0;
            char[] res = new char[maxLength + 1];
            for (int i = 0; i < maxLength; i++) {
                int numa = (cha.length - 1 - i < 0 ? 0 : cha[cha.length - 1 - i] - '0');
                int numb = (chb.length - 1 - i < 0 ? 0 : chb[chb.length - 1 - i] - '0');
                int sum = numa + numb + carry;
                carry = sum / 2;
                res[maxLength - i] = (char) ('0' + sum % 2);
            }
            res[0] = (char) ((carry == 1 ? 1 : 0) + '0');
            String resStr = String.valueOf(res);
            if (resStr.charAt(0) == '0') {
                resStr = resStr.substring(1);
            }
            return resStr;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
