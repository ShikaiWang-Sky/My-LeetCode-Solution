//返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。 
//
// 如果没有和至少为 K 的非空子数组，返回 -1 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：A = [1], K = 1
//输出：1
// 
//
// 示例 2： 
//
// 输入：A = [1,2], K = 4
//输出：-1
// 
//
// 示例 3： 
//
// 输入：A = [2,-1,2], K = 3
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 50000 
// -10 ^ 5 <= A[i] <= 10 ^ 5 
// 1 <= K <= 10 ^ 9 
// 
// Related Topics 队列 二分查找 
// 👍 245 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//Java：和至少为 K 的最短子数组
public class P862ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        Solution solution = new P862ShortestSubarrayWithSumAtLeastK().new Solution();
        // TO TEST
        int[] A = {2,-1,2};
        int K = 3;
        System.out.println(solution.shortestSubarray(A, K));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int shortestSubarray(int[] A, int K) {
            // 前缀和perSum
            int length = A.length;
            long[] preSum = new long[length + 1];
            preSum[0] = 0;
            for (int i = 0; i < length; i++) {
                preSum[i + 1] = preSum[i] + A[i];
            }
            // 最小长度, 默认长度为length + 1
            int min = length + 1;
            // 队列, 存放索引
            Deque<Integer> queue = new LinkedList<>();

            for (int y = 0; y < preSum.length; y++) {
                // preSum[y]表示A[y]之前的数字之和 (0 加到 y - 1)
                // 存在负数的情况, 在队列尾部删除
                while (!queue.isEmpty() && preSum[y] <= preSum[queue.getLast()]) {
                    queue.removeLast();
                }
                // 当前队列头到 y 的范围内元素和大于K, 需要判断是否比之前的长度更小
                while (!queue.isEmpty() && preSum[y] >= preSum[queue.getFirst()] + K) {
                    min = Math.min(min, y - queue.removeFirst());
                }
                // 加到队列尾部
                queue.addLast(y);
            }
            return min < length + 1 ? min : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
