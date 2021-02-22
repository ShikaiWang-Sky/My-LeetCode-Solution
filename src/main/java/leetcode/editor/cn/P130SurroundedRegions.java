//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 475 👎 0

package leetcode.editor.cn;

//Java：被围绕的区域
public class P130SurroundedRegions {
    public static void main(String[] args) {
        Solution solution = new P130SurroundedRegions().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solve(char[][] board) {
            int rowNum = board.length;
            if (rowNum == 0) {
                return;
            }
            int columnNum = board[0].length;
            // 左右边界
            for (int i = 0; i < rowNum; i++) {
                dfs(board, i, 0, rowNum, columnNum);
                dfs(board, i, columnNum - 1, rowNum, columnNum);
            }
            // 上下边界
            for (int i = 0; i < columnNum; i++) {
                dfs(board, 0, i, rowNum, columnNum);
                dfs(board, rowNum - 1, i, rowNum, columnNum);
            }
            // 遍历数组, 将A替换为O, 将O替换为A
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < columnNum; j++) {
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void dfs(char[][] board, int x, int y, int rowNum, int columnNum) {
            if (x < 0 || x >= rowNum || y < 0 || y >= columnNum || board[x][y] != 'O') {
                return;
            }
            // 当前的值是O
            board[x][y] = 'A';
            // 向四个方向寻找相邻的节点
            dfs(board, x + 1, y, rowNum, columnNum);
            dfs(board, x - 1, y, rowNum, columnNum);
            dfs(board, x, y + 1, rowNum, columnNum);
            dfs(board, x, y - 1, rowNum, columnNum);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
