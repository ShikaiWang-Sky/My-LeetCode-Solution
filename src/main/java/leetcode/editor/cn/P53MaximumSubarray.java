//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 2862 👎 0

package leetcode.editor.cn;

import java.sql.Statement;

//Java：最大子序和
public class P53MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new P53MaximumSubarray().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            return getInfo(nums, 0, nums.length - 1).mSum;
        }

        public Status getInfo(int[] a, int l, int r) {
            // 长度为1, 四个值都相等
            if (l == r) {
                return new Status(a[l], a[l], a[l], a[l]);
            }
            // 中间索引
            int m = (l + r) >> 1;
            // 左子区间
            Status lSub = getInfo(a, l, m);
            // 右子区间
            Status rSub = getInfo(a, m + 1, r);
            // 计算子区间回溯
            return pushUp(lSub, rSub);
        }

        /**
         * 根据左右子区间计算新的Status信息
         * @param lSub 左子区间
         * @param rSub 右子区间
         * @return 合成的大的区间的Status信息
         */
        public Status pushUp (Status lSub, Status rSub) {
            int iSum = lSub.iSum + rSub.iSum;
            // 新的以l端点的最大连续子串的和, 为左子区间的lSum和右子区间的lSum+左子区间的iSum(为了保证从l开始连续)的最大值
            int lSum = Math.max(lSub.lSum, lSub.iSum + rSub.lSum);
            // 与上面类似
            int rSum = Math.max(rSub.rSum, lSub.rSum + rSub.iSum);
            // mSum的三种情况, 左子区间mSum,右子区间的mSum, 跨越左右子区间, 即左子区间以r为端点和右子区间以l为端点之和(保证连续)
            int mSum = Math.max(Math.max(lSub.mSum, rSub.mSum), lSub.rSum + rSub.lSum);
            return new Status(lSum, rSum, iSum, mSum);
        }

        class Status {
            public int lSum, rSum, iSum, mSum;

            /**
             * Status的构造器
             * @param lSum 以l(左边界)为端点的最大连续子串和
             * @param rSum 以r(右边界)为端点的最大连续子串和
             * @param iSum 所有元素的和
             * @param mSum 最大连续子串的和
             */
            public Status(int lSum, int rSum, int iSum, int mSum) {
                this.lSum = lSum;
                this.rSum = rSum;
                this.iSum = iSum;
                this.mSum = mSum;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
