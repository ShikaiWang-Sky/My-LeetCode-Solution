//给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// [
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
// Related Topics 双指针 二分查找 分治算法 
// 👍 18 👎 0

package leetcode.editor.cn;

//Java：排序矩阵查找
public class P10_09SortedMatrixSearchLcci {
    public static void main(String[] args) {
        Solution solution = new P10_09SortedMatrixSearchLcci().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            // 行数
            int m = matrix.length;
            if (m == 0) {
                return false;
            }
            // 列数
            int n = matrix[0].length;
            if (n == 0) {
                return false;
            }

            int currentRow = 0;
            int currentColumn = n - 1;
            while (currentColumn >= 0 && currentRow < m) {
                if (matrix[currentRow][currentColumn] == target) {
                    return true;
                }
                if (matrix[currentRow][currentColumn] < target) {
                    currentRow++;
                } else {
                    currentColumn--;
                }
            }
            return false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
