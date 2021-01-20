//验证给定的字符串是否可以解释为十进制数字。 
//
// 例如: 
//
// "0" => true 
//" 0.1 " => true 
//"abc" => false 
//"1 a" => false 
//"2e10" => true 
//" -90e3 " => true 
//" 1e" => false 
//"e3" => false 
//" 6e-1" => true 
//" 99e2.5 " => false 
//"53.5e93" => true 
//" --6 " => false 
//"-+3" => false 
//"95a54e53" => false 
//
// 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表： 
//
// 
// 数字 0-9 
// 指数 - "e" 
// 正/负号 - "+"/"-" 
// 小数点 - "." 
// 
//
// 当然，在输入中，这些字符的上下文也很重要。 
//
// 更新于 2015-02-10: 
//C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。 
// Related Topics 数学 字符串 
// 👍 168 👎 0

package leetcode.editor.cn;

//Java：有效数字
public class P65ValidNumber {
    public static void main(String[] args) {
        Solution solution = new P65ValidNumber().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isNumber(String s) {
            if (s == null || s.length() == 0) {
                return false;
            }
            // 普通数字
            boolean numSeen = false;
            // 点号
            boolean dotSeen = false;
            // 指数 e
            boolean eSeen = false;

            char[] arr = s.trim().toCharArray();
            // 依次扫描
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= '0' && arr[i] <= '9') {
                    numSeen = true;
                } else if (arr[i] == '.') {
                    if (dotSeen || eSeen) {
                        return false;
                    }
                    dotSeen = true;
                } else if (arr[i] == 'E' || arr[i] == 'e') {
                    // 多个e或者前面没有数字
                    if (eSeen || !numSeen) {
                        return false;
                    }
                    eSeen = true;
                    // e后要有数字才是正常的
                    numSeen = false;
                } else if (arr[i] == '+' || arr[i] == '-') {
                    // 不是第一个且后面没有e E
                    if (i != 0 && arr[i - 1] != 'e' && arr[i - 1] != 'E') {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return numSeen;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
