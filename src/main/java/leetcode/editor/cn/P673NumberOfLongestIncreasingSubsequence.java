//给定一个未排序的整数数组，找到最长递增子序列的个数。 
//
// 示例 1: 
//
// 
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
// 
//
// 示例 2: 
//
// 
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
// 
//
// 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。 
// Related Topics 树状数组 线段树 数组 动态规划 
// 👍 339 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//Java：最长递增子序列的个数
public class P673NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new P673NumberOfLongestIncreasingSubsequence().new Solution();
        // TO TEST
        solution.findNumberOfLIS(new int[]{1, 3, 5, 4});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findNumberOfLIS(int[] nums) {
            int length = nums.length;
            if (length == 1)
                return 1;

            int[] dp = new int[length];
            int[] count = new int[length];
            Arrays.fill(dp, 1);
            Arrays.fill(count, 1);
            int maxLength = 0;
            int res = 0;

            for (int i = 1; i < length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        if (dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                            count[i] = count[j];
                        } else if (dp[j] + 1 == dp[i]) {
                            count[i] += count[j];
                        }
                    }
                }
                maxLength = Math.max(maxLength, dp[i]);
            }

            for (int i = 0; i < length; i++) {
                if (dp[i] == maxLength) {
                    res += count[i];
                }
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
