//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯 
// 👍 757 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：全排列 II
public class P47PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new P47PermutationsIi().new Solution();
        // TO TEST
        solution.permuteUnique(new int[] {1, 1, 2});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean[] used;
        public List<List<Integer>> res = new ArrayList<>();
        public List<Integer> singleRes = new ArrayList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            this.used = new boolean[nums.length];
            this.singleRes = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(nums, 0);
            return this.res;
        }

        private void backtrack(int[] nums, int pos) {
            if (pos == nums.length) {
                this.res.add(new ArrayList<>(this.singleRes));
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (used[i] || (i > 0 && (nums[i] == nums[i - 1] && !used[i - 1])))
                        continue;
                    this.singleRes.add(nums[i]);
                    used[i] = true;
                    backtrack(nums, pos + 1);
                    used[i] = false;
                    this.singleRes.remove(pos);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
