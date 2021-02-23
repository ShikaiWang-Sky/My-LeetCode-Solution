//有 n 个网络节点，标记为 1 到 n。 
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
//i 是一个信号从源节点传递到目标节点的时间。 
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// 所有 (ui, vi) 对都 互不相同（即，不含重复边） 
// 
// Related Topics 堆 深度优先搜索 广度优先搜索 图 
// 👍 229 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：网络延迟时间
public class P743NetworkDelayTime {
    public static void main(String[] args) {
        Solution solution = new P743NetworkDelayTime().new Solution();
        // TO TEST
        int[][] times = {{2, 6, 2}, {2, 5, 8}, {6, 1, 6}, {6, 4, 2}, {5, 7, 3}, {1, 3, 3}, {4, 1, 3}, {4, 3, 1}, {3, 7, 2}};
        int n = 7;
        int k = 2;
        System.out.println(solution.networkDelayTime(times, n, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 存储从源节点到各个节点的最短距离
        public int[] dist;

        public int networkDelayTime(int[][] times, int n, int k) {
            // 构建有权图的邻接表
            List<ArrayList<int[]>> adj = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < times.length; i++) {
                adj.get(times[i][0]).add(new int[]{times[i][1], times[i][2]});
            }
            // 记录源节点到当前节点的最短距离
            dist = new int[n + 1];
            // 初始化距离为无穷大 (本题中不包含0节点)
            for (int i = 1; i <= n; i++) {
                dist[i] = Integer.MAX_VALUE;
            }

            // 源节点k放到dist中
            dist[k] = 0;
            // dijkstra 集合D
            boolean[] visited = new boolean[n + 1];

            while (true) {
                // 定义加入d节点的个节点以及距离
                int dNode = -1;
                int dDist = Integer.MAX_VALUE;
                // 寻找下一个加入集合D的节点
                for (int i = 1; i <= n; i++) {
                    // dDist存储当前到相邻节点的最短距离, 没有被遍历的点是MAX
                    if (!visited[i] && dist[i] < dDist) {
                        // 当前距离放入, 遍历完相邻的全部节点后就可以保证下一个访问的节点就是最短路径的节点
                        dDist = dist[i];
                        dNode = i;
                    }
                }
                // 所有可达节点的最短路径都搜寻完毕, 退出循环 (while一开始dNode = -1, 如果前面的if无效(全访问过了), 就退出循环)
                if (dNode < 0) {
                    break;
                }
                visited[dNode] = true;
                // 更新dNode的邻接点的dist值
                for (int i = 0; i < adj.get(dNode).size(); i++) {
                    // info是dNode的相邻节点
                    int[] info = adj.get(dNode).get(i);
                    dist[info[0]] = Math.min(dist[info[0]], dist[dNode] + info[1]);
                }
            }
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    return -1;
                }
                ans = Math.max(dist[i], ans);
            }
            return ans;
        }

        /**
         * 深度优先搜索
         *
         * @param adj     邻接表
         * @param node    起始节点
         * @param elapsed 当前节点到源节点的距离
         */
        private void dfs(List<ArrayList<int[]>> adj, int node, int elapsed) {
            // 若之前存在更快到达node节点的路径, 本次无需扩展该节点
            // 等于的情况说明遍历过这个路径了
            if (elapsed >= dist[node]) {
                return;
            }
            // 否则就把距离放在距离数组中
            dist[node] = elapsed;
            for (int i = 0; i < adj.get(node).size(); i++) {
                int[] vert = adj.get(node).get(i);
                dfs(adj, vert[0], elapsed + vert[1]);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
