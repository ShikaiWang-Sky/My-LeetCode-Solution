//如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的： 
//
// 
// n >= 3 
// 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2} 
// 
//
// 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回 0 。 
//
// （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 
//是 [3, 4, 5, 6, 7, 8] 的一个子序列） 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入: arr = [1,2,3,4,5,6,7,8]
//输出: 5
//解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
// 
//
// 示例 2： 
//
// 
//输入: arr = [1,3,7,11,12,14,18]
//输出: 3
//解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= arr.length <= 1000 
// 
// 1 <= arr[i] < arr[i + 1] <= 10^9 
// 
// 
// Related Topics 数组 哈希表 动态规划 
// 👍 180 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：最长的斐波那契子序列的长度
public class P873LengthOfLongestFibonacciSubsequence {
    public static void main(String[] args) {
        Solution solution = new P873LengthOfLongestFibonacciSubsequence().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lenLongestFibSubseq(int[] arr) {
            int N = arr.length;
            Map<Integer, Integer> index = new HashMap<>();
            for (int i = 0; i < N; i++) {
                index.put(arr[i], i);
            }
            // (i, j)结尾的对的斐波那契子序列长度
            // i * N + j = longest(FibSubSeq), (i, j)对应位置编码为 i * N + j, 这样就可以用一维数组存储pair的状态
            // 设计hash key 保证全局唯一并且方便计算即可, 这里设计为 i * N + j
            // 验证唯一     存的时候: value = i * 常量 + j
            //            取的时候: i = value / 常量, j = value % 常量
            // 由于坐标唯一, 所以哈希值不会冲突
            Map<Integer, Integer> longest = new HashMap<>();
            int res = 0;

            // if A[k] == A[j] + A[i] (i, j) => (j, k)
            // 这里反向遍历, 避免多次重复遍历
            for (int k = 0; k < N; k++) {
                for (int j = 0; j < k; j++) {
                    int i = index.getOrDefault(arr[k] - arr[j], -1);
                    if (i >= 0 && i < j) {
                        Object playerId;
                        // 最短有两个, default=2
                        // 存在一个i, 长度加一
                        int cand = longest.getOrDefault(i * N + j, 2) + 1;
                        longest.put(j * N + k, cand);
                        res = Math.max(cand, res);
                    }
                }
            }
            return res >= 3 ? res : 0;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
