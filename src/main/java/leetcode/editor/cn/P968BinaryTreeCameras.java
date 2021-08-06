//给定一个二叉树，我们在树的节点上安装摄像头。 
//
// 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。 
//
// 计算监控树的所有节点所需的最小摄像头数量。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[0,0,null,0,0]
//输出：1
//解释：如图所示，一台摄像头足以监控所有节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：[0,0,null,0,null,0,null,null,0]
//输出：2
//解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
// 
//
// 
//提示： 
//
// 
// 给定树的节点数的范围是 [1, 1000]。 
// 每个节点的值都是 0。 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 310 👎 0

package leetcode.editor.cn;

import java.util.Collections;

//Java：监控二叉树
public class P968BinaryTreeCameras {
    public static void main(String[] args) {
        Solution solution = new P968BinaryTreeCameras().new Solution();
        // TO TEST
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
    class TreeNode {
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

    class Solution {
        // 定义三种状态
        private final int NOT_COVERED = 0;
        private final int HAS_CAMERA = 1;
        private final int COVERED = 2;

        int res = 0;

        public int minCameraCover(TreeNode root) {
            if (root == null)
                return 0;

            // 根节点NOT_COVERED无法向上传递, 要加摄像头
            if (dfs(root) == NOT_COVERED) {
                res++;
            }
            return res;

        }

        /**
         * 后序遍历
         * @param root 根节点
         * @return 返回状态
         */
        public int dfs(TreeNode root) {
            // 空节点一定被覆盖到了(叶子节点)
            if (root == null) {
                return COVERED;
            }
            int left = dfs(root.left);
            int right = dfs(root.right);

            if (left == NOT_COVERED || right == NOT_COVERED) {
                res++;
                return HAS_CAMERA;
            }
            return left == HAS_CAMERA || right == HAS_CAMERA ? COVERED : NOT_COVERED;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
