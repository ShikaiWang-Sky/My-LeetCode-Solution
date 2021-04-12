//给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
//输出：4
//解释：最长递增路径为 [1, 2, 6, 9]。
//
// 示例 2：
//
//
//输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
//输出：4
//解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
//
//
// 示例 3：
//
//
//输入：matrix = [[1]]
//输出：1
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 200
// 0 <= matrix[i][j] <= 231 - 1
//
// Related Topics 深度优先搜索 拓扑排序 记忆化
// 👍 430 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//Java：矩阵中的最长递增路径
public class P329LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        Solution solution = new P329LongestIncreasingPathInAMatrix().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 以当前节点为坐标中心, 分别向上下左右四个方向查找邻接点
        public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int rows, columns;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            rows = matrix.length;
            columns = matrix[0].length;
            // 统计每个节点的入度, 给定的矩阵并非实际的图结构, 节点间没有实际的关系
            int[][] inDegrees = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    for (int[] dir : dirs) {
                        int newRow = i + dir[0], newColumn = j + dir[1];
                        // 邻居节点小于当前节点且合法, 入度加一
                        if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns
                                && matrix[newRow][newColumn] < matrix[i][j]) {
                            ++inDegrees[i][j];
                        }
                    }
                }
            }
            // 找到入度为0的所有节点, 存放到队列中
            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (inDegrees[i][j] == 0) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }
            // 入度为0的节点依次出队, 删除它们所有的出边, 并将新产生的出度为0的节点入队
            // 最长递增路径
            int ans = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] cell = queue.poll();
                    int row = cell[0], column = cell[1];
                    for (int[] dir : dirs) {
                        int newRow = row + dir[0], newColumn = column + dir[1];
                        if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns
                                && matrix[newRow][newColumn] > matrix[row][column]) {
                            // 删除出边 => 邻居节点的入度删除即为当前节点的出度删除
                            --inDegrees[newRow][newColumn];
                            // 如果是新的入度为0的节点, 则入队 (下一层)
                            if (inDegrees[newRow][newColumn] == 0) {
                                queue.offer(new int[]{newRow, newColumn});
                            }
                        }
                    }
                }
                // 每计算一个深度的所有节点的出边, 长度加一
                ++ans;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
