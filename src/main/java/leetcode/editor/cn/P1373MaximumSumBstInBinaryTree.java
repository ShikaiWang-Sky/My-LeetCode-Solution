//给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。 
//
// 二叉搜索树的定义如下： 
//
// 
// 任意节点的左子树中的键值都 小于 此节点的键值。 
// 任意节点的右子树中的键值都 大于 此节点的键值。 
// 任意节点的左子树和右子树都是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//输出：20
//解释：键值为 3 的子树是和最大的二叉搜索树。
// 
//
// 示例 2： 
//
// 
//
// 输入：root = [4,3,null,1,2]
//输出：2
//解释：键值为 2 的单节点子树是和最大的二叉搜索树。
// 
//
// 示例 3： 
//
// 输入：root = [-4,-2,-5]
//输出：0
//解释：所有节点键值都为负数，和最大的二叉搜索树为空。
// 
//
// 示例 4： 
//
// 输入：root = [2,1,3]
//输出：6
// 
//
// 示例 5： 
//
// 输入：root = [5,4,8,3,null,6,3]
//输出：7
// 
//
// 
//
// 提示： 
//
// 
// 每棵树最多有 40000 个节点。 
// 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。 
// 
// Related Topics 二叉搜索树 动态规划 
// 👍 45 👎 0

package leetcode.editor.cn;

import javax.swing.tree.TreeNode;
import java.sql.ResultSet;

//Java：二叉搜索子树的最大键值和
public class P1373MaximumSumBstInBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P1373MaximumSumBstInBinaryTree().new Solution();
        // TO TEST
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public int maxSumBST(TreeNode root) {
            int[] max = new int[1];
            traverse(root, max);
            return max[0];
        }

        /**
         * 后序遍历查找最大值
         * @param root 根节点
         * @param max 最大值数组
         * @return Result
         */
        private Result traverse(TreeNode root, int[] max) {
            if (root == null) {
                return null;
            }
            Result leftResult = traverse(root.left, max);
            Result rightResult = traverse(root.right, max);

            // 左子树不是二叉搜索子树
            if (leftResult != null && ((!leftResult.isBST) || leftResult.max >= root.val)) {
                return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
            }
            // 右子树不是二叉搜索子树
            if (rightResult != null && ((!rightResult.isBST) || rightResult.min <= root.val)) {
                return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
            }

            // 是二叉搜索树
            int nsum = leftResult == null ? 0 : leftResult.sum;
            nsum += rightResult == null ? 0 : rightResult.sum;
            nsum += root.val;

            int minVal = leftResult == null ? root.val : leftResult.min;
            int maxVal = rightResult == null ? root.val : rightResult.max;
            max[0] = Math.max(max[0], nsum);

            return new Result(minVal, maxVal, nsum, true);
        }


        /**
         * 使用一个类来返回多个结果
         */
        class Result {
            int min;
            int max;
            int sum;
            boolean isBST;

            public Result(int min, int max, int sum, boolean isBST) {
                this.min = min;
                this.max = max;
                this.sum = sum;
                this.isBST = isBST;
            }
        }

        /**
         * 处理二叉搜索树遍历的函数
         * @param root 根节点
         * @return 数组, 元素分别表示当前子树是否为二叉搜索树, 前一个节点的值, 当前的键值和
         */
        public int[] isValidBST(TreeNode root) {
            int[] state = new int[3];
            state[0] = 0;
            state[1] = Integer.MIN_VALUE;
            state[2] = 0;
            // Java数组传递的是引用, 函数中可以直接改变数组的内容而不用返回值
            // 如果传递的是基本类型的变量, 则传递的是值, 不会改变引用的内容
            helper(root, state);
            return state;
        }

        /**
         * 遍历二叉搜索树
         * @param root 根节点
         * @param state 状态数组
         */
        private void helper(TreeNode root, int[] state) {
            if (root == null) {
                state[0] = 1;
                return;
            }
            helper(root.left, state);

            // 不满足递增, 或者前一个子树不是二叉搜索子树, 记录当前状态并将和归零
            if (root.val <= state[1] || state[0] == 0) {
                state[0] = 0;
                state[2] = 0;
                return;
            }
            // 当前满足二叉搜索子树, 处理当前状态
            state[1] = root.val;
            state[2] = state[2] + root.val;

            helper(root.right, state);
        }

        /**
         * 建立一颗二叉搜索树
         *
         * @param root  根节点
         * @param value 待插入的值
         * @return 二叉搜索树
         */
        public TreeNode insert(TreeNode root, int value) {
            if (root == null) {
                return new TreeNode(value);
            } else if (value < root.val) {
                root.left = insert(root.left, value);
            } else {
                root.right = insert(root.right, value);
            }
            return root;
        }

        /**
         * 二叉搜索树查找给定值的递归实现
         * @param root 根节点
         * @param value 待查找值
         * @return 是否存在
         */
        public boolean searchBSTRecursion(TreeNode root, int value) {
            boolean state = false;
            if (root == null) {
                return false;
            } else if (value == root.val) {
                state = true;
            } else if (value < root.val) {
                state = searchBSTRecursion(root.left, value);
            } else {
                state = searchBSTRecursion(root.right, value);
            }
            return state;
        }

        /**
         * 二叉搜索树查找给定值的迭代实现
         * @param root 根节点
         * @param value 待查找值
         * @return 是否存在
         */
        public boolean searchBSTIteration(TreeNode root, int value) {
            while (root != null) {
                if (value == root.val) {
                    return true;
                }
                if (value < root.val) {
                    root = root.left;
                }
                if (value >= root.val) {
                    root = root.right;
                }
            }
            return false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
