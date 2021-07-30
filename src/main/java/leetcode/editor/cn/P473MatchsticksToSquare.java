//还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴
//都要用到。 
//
// 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。 
//
// 示例 1: 
//
// 
//输入: [1,1,2,2,2]
//输出: true
//
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
// 
//
// 示例 2: 
//
// 
//输入: [3,3,3,3,4]
//输出: false
//
//解释: 不能用所有火柴拼成一个正方形。
// 
//
// 注意: 
//
// 
// 给定的火柴长度和在 0 到 10^9之间。 
// 火柴数组的长度不超过15。 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 
// 👍 192 👎 0

package leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

//Java：火柴拼正方形
public class P473MatchsticksToSquare {
    public static void main(String[] args) {
        Solution solution = new P473MatchsticksToSquare().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean makesquare(int[] matchsticks) {
            if (matchsticks.length < 4)
                return false;

            int sum = 0;
            for (int matchstick : matchsticks) {
                sum += matchstick;
            }
            if (sum % 4 != 0) {
                return false;
            }
            int target = sum / 4;

            List<Integer> validSubset = new ArrayList<>();
            List<Integer> validHalfSubset = new ArrayList<>();

            // 所有可能的组合方式, 每个火柴都可能用或者不用, 即2^n种
            int all = 1 << matchsticks.length;
            // 每个i的二进制代表一种取法
            for (int i = 0; i < all; i++) {
                int bucketSum = 0;
                // 找到取出的火柴的长度之和
                for (int j = 0; j < matchsticks.length; j++) {
                    // 如何判断第j根火柴被第i个组合使用了?
                    if ((i & (1 << j)) != 0) {
                        bucketSum += matchsticks[j];
                    }
                }
                if (bucketSum == target) {
                    validSubset.add(i);
                }
            }
            // 此时validSubset存储了所有可能组成正方形的边的集合
            for (int i = 0; i < validSubset.size(); i++) {
                for (int j = i + 1; j < validSubset.size(); j++) {
                    if ((validSubset.get(i) & validSubset.get(j)) == 0) {
                        validHalfSubset.add(validSubset.get(i) | validSubset.get(j));
                    }
                }
            }

            // validHalfSubset 存储了所有可能组成正方形的两条边的集合
            for (int i = 0; i < validHalfSubset.size(); i++) {
                for (int j = i + 1; j < validHalfSubset.size(); j++) {
                    if ((validHalfSubset.get(i) & validHalfSubset.get(j)) == 0) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
