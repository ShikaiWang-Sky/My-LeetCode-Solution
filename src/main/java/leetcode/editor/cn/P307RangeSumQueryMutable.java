//给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。 
//
// 实现 NumArray 类： 
//
// 
// 
// 
// NumArray(int[] nums) 用整数数组 nums 初始化对象 
// void update(int index, int val) 将 nums[index] 的值更新为 val 
// int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + 
//nums[left + 1], ..., nums[right]） 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["NumArray", "sumRange", "update", "sumRange"]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//输出：
//[null, 9, null, 8]
//
//解释：
//NumArray numArray = new NumArray([1, 3, 5]);
//numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
//numArray.update(1, 2);   // nums = [1,2,5]
//numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 9
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -100 <= nums[i] <= 100 
// 0 <= index < nums.length 
// -100 <= val <= 100 
// 0 <= left <= right < nums.length 
// 最多调用 3 * 104 次 update 和 sumRange 方法 
// 
// 
// 
// Related Topics 树状数组 线段树 
// 👍 227 👎 0

package leetcode.editor.cn;

//Java：区域和检索 - 数组可修改
public class P307RangeSumQueryMutable {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        NumArray solution = new P307RangeSumQueryMutable().new NumArray(nums);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {

        int[] tree;
        int n;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                this.n = nums.length;
                tree = new int[2 * n];
                build(nums);
            }
        }

        private void build(int[] nums) {
            // 构建根节点
            for (int i = 0, j = n; j < 2 * n; i++, j++) {
                tree[j] = nums[i];
            }
            // 向上构建和节点, nums[0]为空, 我们从1开始索引
            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
            }
        }

        public void update(int index, int val) {
            // 更新叶子节点
            int pos = index + n;
            tree[pos] = val;
            // 向上更新根节点
            while (pos > 0) {
                // 判断当前的节点是左子节点还是右子节点, 能整除就是左边
                int left = pos % 2 == 0 ? pos : pos - 1;
                int right = pos % 2 == 0 ? pos + 1 : pos;
                // 根节点为左右子节点的和
                tree[pos / 2] = tree[left] + tree[right];
                pos /= 2;
            }

        }

        public int sumRange(int left, int right) {
            int sum = 0;
            // 叶子节点的左右边界
            int l = n + left;
            int r = n + right;
            while (r >= l) {
                // 如果左边界在右子树上, 其父节点计算了左子树, 我们不希望左边被加上, 因此单独计算当前节点
                if (l % 2 == 1) {
                    sum += tree[l];
                    //边界右移, 到下一个叶子节点
                    l++;
                }
                // 如果左边界在左子树上, 我们同样的不想要其父节点, 因为父节点加上了右子树
                if (r % 2 == 0) {
                    sum += tree[r];
                    r--;
                }
                // 除了以上两种情况, 说明结果存在其根节点上, 我们直接向上找根节点
                r /= 2;
                l /= 2;
            }
            return sum;
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
