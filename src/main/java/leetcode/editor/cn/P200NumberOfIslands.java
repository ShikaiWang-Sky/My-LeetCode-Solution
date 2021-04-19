//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 982 👎 0

package leetcode.editor.cn;

//Java：岛屿数量
public class P200NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new P200NumberOfIslands().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class UnionFindSet {
            /*
            parent[ ]存储每个节点的父节点，路径压缩之后，存储代表节点。
            哈希表存储结构: key:节点; value:父节点(或代表节点)
            将节点坐标封装后作为parent的索引，对应的值为集合代表节点
            格式如: parent[节点] =父节点;
             */
            int[] parent;
            /*
            rank[]按理说应存储每个子集的层次(秩)，针对本题，存储每个集合的元素个数
            哈希表存储结构: key: 根节点; value: 节点个数
            将根节点坐标封装后作为rank的索引，对应的值为集合节点个数
            格式如: rank[根节点] =节点个数;
             */
            int[] rank;
            // 小集合的个数
            int count;

            public int getCount() {
                return this.count;
            }

            /**
             * 初始化
             * 把每个元素所在几何初始化为其自身
             * 几何的秩为1
             *
             * @param grid
             */
            public UnionFindSet(char[][] grid) {
                count = 0;
                int m = grid.length;
                int n = grid[0].length;
                parent = new int[m * n];
                rank = new int[m * n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        // 如果当前节点是陆地
                        if (grid[i][j] == '1') {
                            int value = hash(i, j, n);
                            // 索引代表当前节点, 值代表父节点 => 此时父节点初始化为自身
                            parent[value] = value;
                            rank[value] = 1;
                            // 小集合的数量 + 1
                            ++count;
                        }
                    }
                }
            }

            /**
             * parent数组的哈希方法
             *
             * @param i        节点的横坐标
             * @param j        节点的纵坐标
             * @param constant 常量
             * @return 当前节点的哈希值
             */
            public int hash(int i, int j, int constant) {
                /*
                存的时候: value = i * 常量 + j
                取的时候: i = value / 常量, j = value % 常量
                常量怎么设计? 常量 = m 或 n => 验证不会导致parent越界, 带入n或者m计算i和j的最大值,
                    i最大m-1, j最大n-1, i * n + j最大 => m* n - 1
                 */
                return i * constant + j;
            }

            /**
             * 查询元素所在的集合，返回该集合的代表元(代表节点)
             * 路径压缩:将所有节点(包括根节点)的父节点都设置为根节点
             * 复杂度:下一次查找效率为:O(1)
             * <p>
             * 实现方式一:循环/递归地向上查找父节点，直到找到代表节点
             * 实现方式一:路径压缩
             * 判断当前节点的父节点(即代表节点)是否与自己相同
             * 索引与值相同，意味者该节点的父节点就是自己，即找到了当前集合的代表节点
             * 反之，递归地查找父节点的父节点，并将其作为当前节点的父节点
             * 最终，每个节点的父节点都将直接指向代表节点形成一个菊花图。
             *
             * @param i 当前节点
             * @return 当前节点的代表节点
             */

            public int find(int i) {
                //// 实现方式一: 循环/递归地向上查找父节点, 直到找到代表节点
                //// 当前节点的父节点与自己不同, 说明当前节点不是代表节点
                //while (parent[i] != i) {
                //    // 父节点赋值给当前节点, 继续向上查找
                //    i = parent[i];
                //}
                //// 返回代表节点
                //return i;

                //  实现方式二: 路径压缩
                if (parent[i] != i) {
                    parent[i] = find(parent[i]);
                }
                return parent[i];
            }

            /**
             * 合并两个节点
             * 1. 分别获取两个节点的代表节点
             * 2. 不属于一个集合, 才能合并
             * 3. 集合合并, 数量减 1
             *
             * @param x x节点
             * @param y y节点
             */
            public void union(int x, int y) {
                // 分别获取两个节点的代表节点
                int rootX = find(x);
                int rootY = find(y);
                // 不属于一个集合, 才能合并
                if (rootX != rootY) {
                    // 比较两个节点的秩
                    if (rank[rootX] > rank[rootY]) {
                        // 将层次少的集合的根节点的父节点指向层次多的集合的根节点
                        parent[rootY] = rootX;
                    } else if (rank[rootX] < rank[rootY]) {
                        parent[rootX] = rootY;
                    } else {
                        // 相同, 则上面两个分支随便选一个走
                        parent[rootY] = rootX;
                        // 此时x的代表节点的秩多一层
                        rank[rootX] += 1;
                    }
                    // 集合合并, 数量减 1
                    --count;
                }
            }

        }

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int gridLen = grid.length;
            int gridWid = grid[0].length;
            UnionFindSet unionFindSet = new UnionFindSet(grid);
            // 遍历二维网格, 查找每个陆地1节点, 将其标记为水0
            for (int r = 0; r < gridLen; r++) {
                for (int c = 0; c < gridWid; c++) {
                    if (grid[r][c] == '1') {
                        grid[r][c] = '0';
                        // 使用并查集合并四个方向上的邻接点
                        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                            unionFindSet.union(
                                    unionFindSet.hash(r - 1, c, gridWid),
                                    unionFindSet.hash(r, c, gridWid)
                            );
                        }
                        if (r + 1 <= gridLen - 1 && grid[r + 1][c] == '1') {
                            unionFindSet.union(
                                    unionFindSet.hash(r + 1, c, gridWid),
                                    unionFindSet.hash(r, c, gridWid)
                            );
                        }
                        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                            unionFindSet.union(
                                    unionFindSet.hash(r, c - 1, gridWid),
                                    unionFindSet.hash(r, c, gridWid)
                            );
                        }
                        if (c + 1 <= gridWid - 1  && grid[r][c + 1] == '1') {
                            unionFindSet.union(
                                    unionFindSet.hash(r, c + 1, gridWid),
                                    unionFindSet.hash(r, c, gridWid)
                            );
                        }
                    }
                }
            }
            // 返回合并之后的集合个数
            return unionFindSet.getCount();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
