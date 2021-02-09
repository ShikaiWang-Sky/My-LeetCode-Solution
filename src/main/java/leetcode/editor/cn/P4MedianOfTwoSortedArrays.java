//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3676 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//Java：寻找两个正序数组的中位数
public class P4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new P4MedianOfTwoSortedArrays().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            // 较短的数组在前, 确保 m <= n
            if (m > n) {
                return findMedianSortedArrays(nums2, nums1);
            }
            // 定义p q为nums1分界线范围, 共m+1个可能的划分位置
            int p = 0, q = m;
            // 两个数组的分界位置
            int i = 0, j = 0;
            // 使用二分查找循环找位置, 满足条件 p <= q 且没有找到符合条件的位置
            while (p <= q) {
                // 二分确定nums1当前分界位置
                i = (p + q) / 2;
                // 根据i确定nums2的分界位置, 使得左侧元素-右侧元素数为0或者1
                j = (m + n + 1) / 2 - i;
                // nums1右侧最小值小于nums2左侧最大值, nums1划分位置在[i + 2, q]之间
                if (j != 0 && i != m && nums1[i] < nums2[j - 1]) {
                    p = i + 1;
                }
                // nums1左侧最大值大于nums2右侧最小值, nums1划分位置在[p, i - 1]之间
                else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                    q = i - 1;
                }
                // 当前划分位置左侧的最大值小于右侧的最小值, 满足要求
                else {
                    break;
                }
            }
            // 计算中位数
            // m+n为奇数, 返回左侧的最大值, 三种情况: nums1为空, nums2为空, 都不为空
            int maxLeft = ((i == 0) ? nums2[j - 1] : ((j == 0) ? nums1[i - 1] : Math.max(nums1[i - 1], nums2[j - 1])));
            if ((m + n) % 2 == 1) {
                return maxLeft;
            }
            // m+n为偶数, 返回左侧最大值与右侧最小值的平均, 三种情况同上
            int minRight = (i == m ? nums2[j] : ((j == n) ? nums1[i] : Math.min(nums1[i], nums2[j])));
            return (maxLeft + minRight) / 2.0;
        }

        /**
         * 归并排序
         *
         * @param arr 待排序数组
         * @return 排序后的数组
         */
        public int[] mergeSort(int[] arr) {
            // 结束条件 => 长度小于等于1 无需排序直接返回
            if (arr.length <= 1) {
                return arr;
            }
            // 分解, 计算中间位置, 分解为左右两部分
            int mid = arr.length / 2;
            // 求解: 左右两部分递归求解
            int[] left = Arrays.copyOfRange(arr, 0, mid + 1);
            // 此时left递归调用, 返回已经排好序的数组
            left = mergeSort(left);
            int[] right = Arrays.copyOfRange(arr, mid + 1, arr.length);
            right = mergeSort(right);
            // 合并: 两个排序后的数组为一个新数组
            return merge(left, right);
        }

        /**
         * 左右数组合并
         *
         * @param l 左数组
         * @param r 右数组
         * @return 合并后的数组
         */
        private int[] merge(int[] l, int[] r) {
            // 创建数组用于存放结果
            int[] result = new int[l.length + r.length];
            // 使用两个变量分别记录两个数组的当前位置
            int lIndex = 0, rIndex = 0;
            // 遍历两个数组每次找出最小的放入结果数组中
            for (int i = 0; i < result.length; i++) {
                // 两个数组都还有元素, 则比较取较小的
                if (lIndex < l.length && rIndex < r.length) {
                    if (l[lIndex] <= r[rIndex]) {
                        result[i] = l[lIndex++];
                    } else {
                        result[i] = r[rIndex++];
                    }
                } else if (lIndex < l.length) {
                    // r数组中比较完了, l数组直接放在结果数组后面
                    result[i] = l[lIndex++];
                } else {
                    // l数组中比较完了, r数组直接放在结果数组后面
                    result[i] = r[rIndex++];
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
