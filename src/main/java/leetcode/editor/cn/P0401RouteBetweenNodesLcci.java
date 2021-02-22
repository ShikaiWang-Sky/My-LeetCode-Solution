//节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。 
//
// 示例1: 
//
//  输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
// 输出：true
// 
//
// 示例2: 
//
//  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [
//1, 3], [2, 3], [3, 4]], start = 0, target = 4
// 输出 true
// 
//
// 提示： 
//
// 
// 节点数量n在[0, 1e5]范围内。 
// 节点编号大于等于 0 小于 n。 
// 图中可能存在自环和平行边。 
// 
// Related Topics 图 
// 👍 26 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Java：节点间通路
public class P0401RouteBetweenNodesLcci {
    public static void main(String[] args) {
        Solution solution = new P0401RouteBetweenNodesLcci().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
            return BFS(n, graph, start, target);
        }

        /**
         * 广度优先遍历
         *
         * @param n      图中节点的个数
         * @param graph  图
         * @param start  起始节点
         * @param target 目标节点
         * @return 从起始节点到目标节点是否存在路径
         */
        private boolean BFS(int n, int[][] graph, int start, int target) {
            // 建立邻接表列表, 每一个邻接表都是一个列表, 多个邻接表构成一个列表
            List<ArrayList<Integer>> adj = new ArrayList<>();
            // 初始化, 建立邻接表列表
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < graph.length; i++) {
                // 节点数据值作为邻接表列表索引
                // graph每一个元素的第一个坐标就是节点的坐标
                // adj.get -> 得到了对应的节点的list, .add再向对应的节点中添加graph的第二个坐标, 即相邻的节点
                /*
                本题的图 [[0, 1], [0, 2], [1, 2], [1, 2]],得到的adj为
                0 -> [1, 2]
                1 -> [2, 2]
                2 -> []
                 */
                adj.get(graph[i][0]).add(graph[i][1]);
            }

            // 队列存储当前已经访问过的尚未拓展的节点
            Queue<Integer> nodeQueue = new LinkedList<>();
            boolean[] visited = new boolean[n];
            nodeQueue.add(start);
            while (!nodeQueue.isEmpty()) {
                // 当前顶点
                int currentVertex = nodeQueue.poll();
                // 当前顶点的邻接点
                ArrayList<Integer> adjacent = adj.get(currentVertex);
                int size = adjacent.size();
                // 广度优先: 扩展当前节点的邻居
                for (int i = 0; i < size; i++) {
                    // 邻接点
                    int vert = adjacent.get(i);
                    if (vert == target) {
                        return true;
                    }
                    // 如果该邻居没有被访问过
                    if (!visited[vert]) {
                        visited[vert] = true;
                        // 将该邻接点加入队列, 准备访问
                        nodeQueue.add(vert);
                    }
                }
            }
            return false;
        }


        private List<ArrayList<Integer>> adj;

        /**
         * 深度优先遍历
         *
         * @param n      图中节点的个数
         * @param graph  图
         * @param start  起始节点
         * @param target 目标节点
         * @return 从起始节点到目标节点是否存在路径
         */
        private boolean DFS(int n, int[][] graph, int start, int target) {
            boolean[] visited = new boolean[n];
            visited[start] = true;
            // 建立邻接表adj
            adj = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<Integer>());
            }
            for (int[] ints : graph) {
                adj.get(ints[0]).add(ints[1]);
            }
            return recursion(n, start, target, visited);
        }

        /**
         * DFS的递归函数
         * @param n 节点个数
         * @param start 起始节点
         * @param target 目标节点
         * @param visited 节点是否被访问过的存储数组
         * @return 是否存在路径
         */
        private boolean recursion(int n , int start, int target, boolean[] visited) {
            if (start == target) {
                return true;
            }
            for (int i = 0; i < adj.get(start).size(); i++) {
                int vert = adj.get(start).get(i);
                if (!visited[vert]) {
                    visited[vert] = true;
                    boolean result = recursion(n, vert, target, visited);
                    if (result) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
