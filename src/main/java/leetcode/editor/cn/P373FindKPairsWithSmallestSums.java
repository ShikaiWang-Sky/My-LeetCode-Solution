//给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。 
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。 
//
// 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。 
//
// 示例 1: 
//
// 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// 
//
// 示例 2: 
//
// 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// 
//
// 示例 3: 
//
// 输入: nums1 = [1,2], nums2 = [3], k = 3 
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
// 
// Related Topics 堆 
// 👍 159 👎 0

package leetcode.editor.cn;

import java.util.*;

//Java：查找和最小的K对数字
public class P373FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        Solution solution = new P373FindKPairsWithSmallestSums().new Solution();
        // TO TEST
        int[] num1 = {1, 7, 11};
        int[] num2 = {1, 7, 11};
        int k = 3;
        System.out.println(solution.kSmallestPairs(num1, num2, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            // 定义最小堆, 重写比较器, 比较两个数组的坐标(o1[0], o1[1]和o2[0], o2[1]
            PriorityQueue<int[]> queue = new PriorityQueue<>(
                    (o1, o2) -> ((nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]))
            );
            List<List<Integer>> res = new LinkedList<>();
            // 边界条件
            if (nums1.length == 0 || nums2.length == 0) {
                return res;
            }
            // 初始化一个小顶堆, 与大顶堆同样的, 优化循环终止条件
            for (int i = 0; i < Math.min(k, nums1.length); i++) {
                // 加入坐标, nums2的第0个和nums1的前k个组成的坐标, 初始化
                queue.add(new int[]{i, 0});
            }
            while (k > 0 && !queue.isEmpty()) {
                // 堆顶元素出堆
                int[] pair = queue.poll();
                // 将坐标转化为对应的值
                List<Integer> item = new ArrayList<>();
                item.add(nums1[pair[0]]);
                item.add(nums2[pair[1]]);

                // 在剩下的nums1的第0个和nums2的第k个中继续寻找
                // pair[1]是nums2的坐标
                if (pair[1] < nums2.length - 1) {
                    queue.add(new int[] {pair[0], pair[1] + 1});
                }
                res.add(item);
                k--;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
