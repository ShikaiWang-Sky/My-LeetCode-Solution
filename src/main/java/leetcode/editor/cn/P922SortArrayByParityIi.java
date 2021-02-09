//给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。 
//
// 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。 
//
// 你可以返回任何满足上述条件的数组作为答案。 
//
// 
//
// 示例： 
//
// 输入：[4,2,5,7]
//输出：[4,5,2,7]
//解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 20000 
// A.length % 2 == 0 
// 0 <= A[i] <= 1000 
// 
//
// 
// Related Topics 排序 数组 
// 👍 190 👎 0

package leetcode.editor.cn;

//Java：按奇偶排序数组 II
public class P922SortArrayByParityIi {
    public static void main(String[] args) {
        Solution solution = new P922SortArrayByParityIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArrayByParityII(int[] A) {
            // 记录奇数
            int j = 1;
            for (int i = 0; i < A.length; i += 2) {
                if (A[i] % 2 == 1) {
                    // 移动j, 直到遇到偶数, 退出while
                    while (A[j] % 2 == 1) {
                        j += 2;
                    }
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
            return A;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
