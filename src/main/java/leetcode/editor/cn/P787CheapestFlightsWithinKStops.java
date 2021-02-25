//有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。 
//
// 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样
//的路线，则输出 -1。 
//
// 
//
// 示例 1： 
//
// 
//输入: 
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 1
//输出: 200
//解释: 
//城市航班图如下
//
//
//从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。 
//
// 示例 2： 
//
// 
//输入: 
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 0
//输出: 500
//解释: 
//城市航班图如下
//
//
//从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。 
//
// 
//
// 提示： 
//
// 
// n 范围是 [1, 100]，城市标签从 0 到 n - 1 
// 航班数量范围是 [0, n * (n - 1) / 2] 
// 每个航班的格式 (src, dst, price) 
// 每个航班的价格范围是 [1, 10000] 
// k 范围是 [0, n - 1] 
// 航班没有重复，且不存在自环 
// 
// Related Topics 堆 广度优先搜索 动态规划 
// 👍 238 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//Java：K 站中转内最便宜的航班
public class P787CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        Solution solution = new P787CheapestFlightsWithinKStops().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            // 价格矩阵
            int[][] graph = new int[n][n];
            for (int[] flight : flights) {
                graph[flight[0]][flight[1]] = flight[2];
            }

            Map<Integer, Integer> best = new HashMap<>();

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            pq.offer(new int[]{0, 0, src});

            while (!pq.isEmpty()) {
                int[] info = pq.poll();
                int cost = info[0];
                int k = info[1];
                int place = info[2];

                if (k > K + 1 || cost > best.getOrDefault(k * 1000 + place, Integer.MAX_VALUE)) {
                    continue;
                }
                if (place == dst) {
                    return cost;
                }

                for (int nei = 0; nei < n; ++nei) {
                    if (graph[place][nei] > 0) {
                        int newcost = cost + graph[place][nei];
                        if (newcost < best.getOrDefault((k + 1) * 1000 + nei, Integer.MAX_VALUE)) {
                            pq.offer(new int[]{newcost, k + 1, nei});
                            best.put((k + 1) * 1000 + nei, newcost);
                        }
                    }
                }
            }

            return -1;

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


