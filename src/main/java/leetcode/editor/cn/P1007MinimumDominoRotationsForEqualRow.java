//在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 
//该平铺的每一半上都有一个数字。） 
//
// 我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。 
//
// 返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。 
//
// 如果无法做到，返回 -1. 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
//输出：2
//解释：
//图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
//如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
// 
//
// 示例 2： 
//
// 输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
//输出：-1
//解释：
//在这种情况下，不可能旋转多米诺牌使一行的值相等。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A[i], B[i] <= 6 
// 2 <= A.length == B.length <= 20000 
// 
// Related Topics 贪心算法 数组 
// 👍 65 👎 0

package leetcode.editor.cn;

//Java：行相等的最少多米诺旋转
public class P1007MinimumDominoRotationsForEqualRow {
    public static void main(String[] args) {
        Solution solution = new P1007MinimumDominoRotationsForEqualRow().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDominoRotations(int[] A, int[] B) {
            int n = A.length;
            // 求解A或B全部变成A[0]需要多少次旋转
            int rotation = check(A[0], A, B, n);
            // 如果不存在满足要求的结果或者两个相同的A[0]和B[0]
            if (rotation != -1 || A[0] == B[0]) {
                return rotation;
            } else {
                rotation = check(B[0], A, B, n);
            }
            return rotation;
        }

        /**
         * 检查A或B全部变成X需要多少次旋转
         *
         * @param x 要变为的数字
         * @param A A数组
         * @param B B数组
         * @param n 数组长度
         * @return 翻转次数
         */
        public int check(int x, int[] A, int[] B, int n) {
            // 将A元素全部变为X需要多少次旋转, 将B元素全部变为X需要多少次旋转
            int rotationA = 0, rotationB = 0;
            // 遍历骨牌判断是否能完成任务
            for (int i = 0; i < n; i++) {
                // 如果当前多米诺骨牌上没有x, 则不可能完成任务
                if (A[i] != x && B[i] != x) {
                    return -1;
                } else if (A[i] != x) {
                    rotationA++;
                } else if (B[i] != x) {
                    rotationB++;
                }
            }
            // 返回最小的旋转次数
            return Math.min(rotationA, rotationB);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
