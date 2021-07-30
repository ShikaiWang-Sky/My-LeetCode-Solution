//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 数组 回溯 
// 👍 946 👎 0

package leetcode.editor.cn;

import java.util.*;

//Java：N 皇后
public class P51NQueens {
    public static void main(String[] args) {
        Solution solution = new P51NQueens().new Solution();
        // TO TEST
        System.out.println(solution.solveNQueens(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            // (i,queens[i]) 放置了皇后
            int[] queens = new int[n];
            Arrays.fill(queens, -1);
            Set<Integer> columns = new HashSet<>();
            Set<Integer> diagonalsRight = new HashSet<>();
            Set<Integer> diagonalsLeft = new HashSet<>();
            backtrack(res, queens, n, 0, columns, diagonalsRight, diagonalsLeft);
            return res;
        }

        private void backtrack(List<List<String>> res, int[] queens, int n, int row,
                               Set<Integer> columns, Set<Integer> diagonalsRight,
                               Set<Integer> diagonalsLeft) {
            if (row == n) {
                List<String> board = generateBoard(queens, n);
                res.add(board);
            }

            for (int i = 0; i < n; i++) {
                // 剪枝
                if (columns.contains(i)) {
                    continue;
                }
                int diagonalRight = row - i;
                if (diagonalsRight.contains(diagonalRight)) {
                    continue;
                }
                int diagonalLeft = row + i;
                if (diagonalsLeft.contains(diagonalLeft)) {
                    continue;
                }

                queens[row] = i;
                columns.add(i);
                diagonalsRight.add(diagonalRight);
                diagonalsLeft.add(diagonalLeft);
                backtrack(res, queens, n, row + 1, columns, diagonalsRight, diagonalsLeft);
                // 复原, 因为回溯完成后会向下一列继续for循环
                queens[row] = -1;
                columns.remove(i);
                diagonalsRight.remove(diagonalRight);
                diagonalsLeft.remove(diagonalLeft);
            }

        }

        public List<String> generateBoard(int[] queens, int n) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[queens[i]] = 'Q';
                board.add(new String(row));
            }
            return board;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
