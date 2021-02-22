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
        int rowNum, columnNum;

        public int numIslands(char[][] grid) {
            rowNum = grid.length;
            if (grid == null || rowNum == 0) {
                return 0;
            }
            columnNum = grid[0].length;

            int islandNum = 0;
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < columnNum; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);
                        islandNum++;
                    }
                }
            }
            return islandNum;
        }

        private void dfs(char[][] grid, int x, int y) {
            if (x < 0 || x >= rowNum || y < 0 || y >= columnNum || grid[x][y] == '0') {
                return;
            }
            // 当前为1, 变为0, 递归结束条件为碰到边界或者遇到相邻的不为1的节点
            grid[x][y] = '0';
            dfs(grid, x + 1, y);
            dfs(grid, x - 1, y);
            dfs(grid, x, y + 1);
            dfs(grid, x, y - 1);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
