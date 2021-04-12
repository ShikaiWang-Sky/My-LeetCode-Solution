//给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 
//的一条无向边，且该边遍历成功的概率为 succProb[i] 。 
//
// 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。 
//
// 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, e
//nd = 2
//输出：0.25000
//解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
// 
//
// 示例 2： 
//
// 
//
// 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, e
//nd = 2
//输出：0.30000
// 
//
// 示例 3： 
//
// 
//
// 输入：n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
//输出：0.00000
//解释：节点 0 和 节点 2 之间不存在路径
// 
//
// 
//
// 提示： 
//
// 
// 2 <= n <= 10^4 
// 0 <= start, end < n 
// start != end 
// 0 <= a, b < n 
// a != b 
// 0 <= succProb.length == edges.length <= 2*10^4 
// 0 <= succProb[i] <= 1 
// 每两个节点之间最多有一条边 
// 
// Related Topics 图 
// 👍 49 👎 0

package leetcode.editor.cn;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//Java：概率最大的路径
public class P1514PathWithMaximumProbability {
    public static void main(String[] args) {
        Solution solution = new P1514PathWithMaximumProbability().new Solution();
        // TO TEST
        int[][] graph = {{0, 1}, {1, 2}, {0, 2}};
        int n = 3;
        double[] succProb = {0.5, 0.5, 0.2};
        int start = 0;
        int end = 2;
        System.out.println(solution.maxProbability(n, graph, succProb, start, end));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            List<List<Pair>> adjGraph = new ArrayList<List<Pair>>();
            // 初始化
            for (int i = 0; i < n; i++) {
                adjGraph.add(new ArrayList<Pair>());
            }
            // 填充邻接表
            for (int i = 0; i < edges.length; i++) {
                // get(edges[i][0]) => 获得当前节点
                adjGraph.get(edges[i][0]).add(new Pair(edges[i][1], succProb[i]));
                // 由于是无向图, 需要记录双向的邻接表
                adjGraph.get(edges[i][1]).add(new Pair(edges[i][0], succProb[i]));
            }

            // 创建优先队列, 寻找邻接点最大概率
            PriorityQueue<Pair> pq = new PriorityQueue<>();

            double[] probability = new double[n];
            probability[start] = 1;

            pq.offer(new Pair(start, 1));
            while (!pq.isEmpty()) {
                Pair current = pq.poll();
                int currentNode = current.node;
                double currentProbability = current.probability;
                // 如果到当前节点的可能性已经比我们记录中的可能性还要小, 没有继续遍历的必要, 直接跳过该次循环
                if (currentProbability < probability[currentNode]) {
                    continue;
                }
                // 遍历邻接点
                for (Pair nextPair : adjGraph.get(currentNode)) {
                    int nextNode = nextPair.node;
                    double nextProbability = nextPair.probability;
                    // 找出最小的nextNode, 这样做可以防止回头遍历(无向图)
                    if (probability[nextNode] < probability[currentNode] * nextProbability) {
                        probability[nextNode] = probability[currentNode] * nextProbability;
                        pq.offer(new Pair(nextNode, probability[nextNode]));
                    }
                }
            }
            return probability[end];
        }
    }

    /**
     * 定义一个Pair类, 存放邻居节点以及其到相邻节点的概率
     */
    class Pair implements Comparable<Pair> {
        public int node;
        public double probability;

        public Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }

        @Override
        public int compareTo(Pair pair2) {
            if (this.probability == pair2.probability) {
                return this.node - pair2.node;
            } else {
                return this.probability > pair2.probability ? -1 : 1;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
