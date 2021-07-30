//给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, we
//ighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权
//值和最小。 
//
// 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在
//某些最小生成树中但不会出现在所有最小生成树中的边。 
//
// 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
//输出：[[0,1],[2,3,4,5]]
//解释：上图描述了给定图。
//下图是所有的最小生成树。
//
//注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
//边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
// 
//
// 示例 2 ： 
//
// 
//
// 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
//输出：[[],[0,1,2,3]]
//解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= n <= 100 
// 1 <= edges.length <= min(200, n * (n - 1) / 2) 
// edges[i].length == 3 
// 0 <= fromi < toi < n 
// 1 <= weighti <= 1000 
// 所有 (fromi, toi) 数对都是互不相同的。 
// 
// Related Topics 并查集 图 最小生成树 排序 强连通分量 
// 👍 106 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：找到最小生成树里的关键边和伪关键边
public class P1489FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree {
    public static void main(String[] args) {
        Solution solution = new P1489FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<int[]>> adjacency;
        int[][] matrix;

        public void initAdjacency(int[][] edges, int n) {
            // 创建n个节点的邻接表:每一条边是一个int[]， 每个顶点有多条边
            adjacency = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjacency.add(new ArrayList<>());
            }
            // 顶点值为0~n-1， 即邻接表adjacency的索引
            for (int i = 0; i < edges.length; i++) {
                // 这条边, 既是第一个顶点的边, 也是第二个顶点的边

                int[] edge = edges[i];
                adjacency.get(edge[0]).add(edge);
                adjacency.get(edge[1]).add(edge);
            }
        }

        public int prim(int[][] edges, int n, int index, boolean giveOrTake) {
            int mstWeightSum = 0, mstVertexCount = 0;
            // 临时存放顶点
            boolean[] visited = new boolean[n];

            // 伪关键边, 直接加入当前边
            if (!giveOrTake && index >= 0) {
                visited[edges[index][0]] = true;
                visited[edges[index][1]] = true;
                mstWeightSum += edges[index][2];
                mstVertexCount = 2;
            } else {
                // 把0节点加入集合中
                visited[0] = true;
                mstVertexCount++;
            }

            // 当集合中的顶点个数达到n个,操作结束
            while (mstVertexCount < n) {
                // 选择这些顶点权值最小的边加入MST
                int[] edge = null;
                // 最小权值边
                int minWeight = Integer.MAX_VALUE;
                // 遍历集合中的所有顶点,找到最小权值的边
                for (int i = 0; i < n; i++) {
                    // 只需要已被标记的元素
                    if (visited[i]) {
                        // 获取邻接表
                        List<int[]> adj = adjacency.get(i);
                        for (int j = 0; j < adj.size(); j++) {
                            int[] adjEdge = adj.get(j);
                            // 关键边
                            if (giveOrTake && adjEdge == edges[index])
                                continue;

                            // 跳过两个顶点都在集合中的边
                            if (visited[adjEdge[0]] && visited[adjEdge[1]]) {
                                continue;
                            }
                            // 找到最小权值边
                            if (adjEdge[2] < minWeight) {
                                minWeight = adjEdge[2];
                                edge = adjEdge;
                            }
                        }
                    }
                }
                // 将最小边的顶点设置为已访问
                if (edge != null) {
                    visited[edge[0]] = true;
                    visited[edge[1]] = true;
                    mstVertexCount++;
                    mstWeightSum += minWeight;
                } else {
                    return Integer.MAX_VALUE;
                }

            }
            return mstWeightSum;
        }

        public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
            initAdjacency(edges, n);

            int minSum = prim(edges, n, -1, false);
            List<List<Integer>> result = new LinkedList<>();
            List<Integer> keyEdge = new ArrayList<>(edges.length);
            List<Integer> pseudoKeyEdge = new ArrayList<>(edges.length);
            for (int i = 0; i < edges.length; i++) {
                // 关键边
                if (prim(edges, n, i, true) > minSum) {
                    keyEdge.add(i);
                } else if (prim(edges, n, i, false) == minSum) {
                    // 伪关键边
                    pseudoKeyEdge.add(i);
                }
            }
            result.add(keyEdge);
            result.add(pseudoKeyEdge);
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
